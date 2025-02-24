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
    /*
    @Override
    public Mono<ResponseEntity<StatusResponseOK>> created(String xUserId, String xConsumerId, String xTransactionId, String xCallerId, Mono<RequestBodyAperturarCuentaAhorro> requestBodyAperturarCuentaAhorro, ServerWebExchange exchange) {

        return requestBodyAperturarCuentaAhorro
                .map(request -> {
                    String accountCreated = Utility.generarNumeroCuenta();
                    return Account.builder()
                            .typeAccount(request.getTypeAccount())
                            .openingBalance(request.getOpeningBalance())
                            .idClient(request.getIdClient())
                            .account(accountCreated)
                            .cci(Utility.generarCCI(accountCreated))
                            .dateCreated(Utility.obtenerFechaHoraActual())
                            .dateUpdate(Utility.obtenerFechaHoraActual())
                            .build();
                })
                .flatMap(accountRepository::save)
                .map(accountSaved -> {
                    StatusResponseOK statusResponseOK = new StatusResponseOK();
                    statusResponseOK.setId(accountSaved.getId());
                    statusResponseOK.accountNumber(accountSaved.getAccount());
                    statusResponseOK.setCci(accountSaved.getCci());
                    statusResponseOK.balance(accountSaved.getOpeningBalance());
                    statusResponseOK.typeAccount(accountSaved.getTypeAccount());
                    statusResponseOK.dateCreated(accountSaved.getDateCreated());
                    statusResponseOK.dateUpdate(accountSaved.getDateUpdate());
                    return statusResponseOK;
                })
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }



    //@Autowired
    //AccountRepository clientRepository;
/*
    @Override
    public Mono<ResponseEntity<StatusResponseOKDelete>> deleteAccounts(String xUserId, String xConsumerId, String xTransactionId, String xCallerId, ServerWebExchange exchange) {
        return AccountsApiDelegate.super.deleteAccounts(xUserId, xConsumerId, xTransactionId, xCallerId, exchange);
    }

    @Override
    public Mono<ResponseEntity<StatusResponseOK>> getAccounts(String xUserId, String xConsumerId, String xTransactionId, String xCallerId, ServerWebExchange exchange) {
        return AccountsApiDelegate.super.getAccounts(xUserId, xConsumerId, xTransactionId, xCallerId, exchange);
    }

    @Override
    public Mono<ResponseEntity<StatusResponseOK>> openingAccounts(String xUserId, String xConsumerId, String xTransactionId, String xCallerId, Mono<RequestBodyAperturarCuentaAhorro> requestBodyAperturarCuentaAhorro, ServerWebExchange exchange) {
        return AccountsApiDelegate.super.openingAccounts(xUserId, xConsumerId, xTransactionId, xCallerId, requestBodyAperturarCuentaAhorro, exchange);
    }

    @Override
    public Mono<ResponseEntity<StatusResponseOK>> updateAccounts(String xUserId, String xConsumerId, String xTransactionId, String xCallerId, Mono<RequestUpdateCuentaAhorro> requestUpdateCuentaAhorro, ServerWebExchange exchange) {
        return AccountsApiDelegate.super.updateAccounts(xUserId, xConsumerId, xTransactionId, xCallerId, requestUpdateCuentaAhorro, exchange);
    }

    /*

    @Override
    public Flux<AccountDto> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Mono<AccountDto> save(AccountDto client) {
        return clientRepository.save(client);
    }

    @Override
    public Flux<AccountDto> findClientByAge(int age) {
        return clientRepository.findAll().filter(k -> k.getAge() == age);
    }

    @Override
    public Mono<AccountDto> update(AccountDto client) {
        return clientRepository.save(client);
    }

    @Override
    public Mono<Void> delete(String id) {
        return clientRepository.deleteById(id);
    }


     */

}
