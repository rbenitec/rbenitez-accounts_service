package service.accounts.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import service.accounts.exception.BusinessException;
import service.accounts.mapper.MapperToAccount;
import service.accounts.mapper.MapperToResponseAccount;
import service.accounts.model.RequestAccountDto;
import service.accounts.model.ResponseAccountDto;
import service.accounts.model.ResponseDeleteDto;
import service.accounts.repository.AccountRepository;
import service.accounts.service.AccountsService;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountsService {

    private final AccountRepository accountRepository;

    private final MapperToAccount mapperToAccount;
    private final MapperToResponseAccount mapperToResponseAccount;


    @Override
    public Mono<ResponseAccountDto> saveAccount(Mono<RequestAccountDto> customerDto) {
        return customerDto
                .map(mapperToAccount)
                .flatMap(accountRepository::save)
                .switchIfEmpty(Mono.error(new BusinessException("saveAccount", "Error saving account to database")))
                .map(mapperToResponseAccount)
                .onErrorMap(ex -> new BusinessException("[saveAccount]: Error in the process of saving a account", ex.getMessage()));
    }

    @Override
    public Mono<ResponseAccountDto> findAccountById(String accountId) {
        return accountRepository.findById(accountId)
                .map(mapperToResponseAccount)
                .onErrorMap(throwable -> new BusinessException("[findAccountById]: error in the process of obtaining a account by its id", throwable.getMessage()));
    }

    @Override
    public Mono<ResponseAccountDto> updateAccount(String accountId, Mono<RequestAccountDto> requestAccountDto) {
        return accountRepository.findById(accountId)
                .switchIfEmpty(Mono.error(new BusinessException("updateAccount", "Account with id" + accountId + "does not exist")))
                .flatMap(customer -> requestAccountDto
                        .map(mapperToAccount)
                        .flatMap(customerUpdate -> {
                            customerUpdate.setId(accountId);
                            return accountRepository.save(customerUpdate);
                        })
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
