package service.accounts.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import service.accounts.client.CustomerClientConnector;
import service.accounts.entities.Account;
import service.accounts.exception.BusinessException;
import service.accounts.mapper.MapperToResponseAccount;
import service.accounts.mapper.MapperUpdateToAccount;
import service.accounts.mapper.factory.AccountEntityFactory;
import service.accounts.model.RequestAccountDto;
import service.accounts.model.RequestUpdateAccountDto;
import service.accounts.model.ResponseAccountDto;
import service.accounts.model.ResponseDeleteDto;
import service.accounts.repository.AccountRepository;
import service.accounts.service.AccountsService;
import service.accounts.service.factory.CreatedAccountStrategyFactory;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountsService {

    private final AccountRepository accountRepository;
    private final CustomerClientConnector customerClientConnector;
    //private final MapperToAccount mapperToAccount;
    private final MapperUpdateToAccount mapperUpdateToAccount;
    private final MapperToResponseAccount mapperToResponseAccount;
    private final AccountEntityFactory accountEntityFactory;
    private final CreatedAccountStrategyFactory createdAccountStrategyFactory;

    //Identificar si el cliente es personal o empresarial
    //Si el cliente es personal, solo se le asigna una cuenta de ahorros, una cuenta corriente o plazo fijo
    //Si el cliente es empresarial, se le asigna una cuenta corriente

    @Override
    public Mono<ResponseAccountDto> createdAccount(Mono<RequestAccountDto> request) {
        return request
                .flatMap(requestAccount -> customerClientConnector
                        .getCustomerById(requestAccount.getCustomerId())
                        .doOnSuccess(responseCustomerDto -> log.info("Customer found: {}", responseCustomerDto))
                        .onErrorMap(throwable -> new BusinessException("Error in the process of obtaining a customer by its id", throwable.getMessage()))
                        .switchIfEmpty(Mono.error(new BusinessException("createdAccount", "The customer does not exist")))
                        .flatMap(responseCustomerDto -> createdAccountStrategyFactory.getStrategy(requestAccount.getAccountType())
                                .switchIfEmpty(Mono.error(new BusinessException("createdAccount", "The account type is not valid")))
                                .flatMap(strategy ->
                                {
                                    Account account = accountEntityFactory.createAccountEntity(requestAccount, responseCustomerDto);
                                    System.out.println("accountEntity obtenida por el factory: " + account.toString());
                                    return strategy.createdAccount(account, responseCustomerDto, accountRepository)
                                            .map(mapperToResponseAccount);
                                })));
    }

    /**
     * Verifica si el cliente es personal basándose en sus cuentas actuales.
     */
    @Override
    public Mono<ResponseAccountDto> findAccountById(String accountId) {
        return accountRepository.findById(accountId)
                .map(mapperToResponseAccount)
                .onErrorMap(throwable -> new BusinessException("[findAccountById]: error in the process of obtaining a account by its id", throwable.getMessage()));
    }

    @Override
    public Mono<ResponseAccountDto> updateAccount(String accountId, Mono<RequestUpdateAccountDto> requestAccountDto) {
        return accountRepository.findById(accountId)
                .switchIfEmpty(Mono.error(new BusinessException("updateAccount", "Account with id" + accountId + "does not exist")))
                .flatMap(existingAccount -> requestAccountDto
                        .doOnSuccess(requestUpdateAccountDto -> mapperUpdateToAccount.accept(requestUpdateAccountDto, existingAccount))
                        .then(accountRepository.save(existingAccount))
                        .map(mapperToResponseAccount))
                .onErrorMap(ex -> new BusinessException("[updateAccount]: Error in the process of update a account", ex.getMessage()));
    }

    @Override
    public Mono<ResponseDeleteDto> deleteAccount(String accountId) {
        return accountRepository.deleteById(accountId)
                .then(Mono.just(ResponseDeleteDto.builder()
                        .message("The Account was successfully deleted, with id: ".concat(accountId))
                        .build()))
                .onErrorResume(error -> Mono.just(ResponseDeleteDto.builder()
                        .message("Error deleting account with id: "
                                .concat(accountId)
                                .concat(" - error: ".concat(error.getMessage()))
                        )
                        .build()));
    }

}
