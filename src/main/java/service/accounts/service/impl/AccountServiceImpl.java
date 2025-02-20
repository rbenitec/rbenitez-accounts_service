package service.accounts.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import service.accounts.api.AccountsApiDelegate;
import service.accounts.entities.Account;
import service.accounts.model.RequestBodyAperturarCuentaAhorro;
import service.accounts.model.RequestUpdateCuentaAhorro;
import service.accounts.model.StatusResponseOK;
import service.accounts.model.StatusResponseOKDelete;
import service.accounts.repository.AccountRepository;
import service.accounts.service.AccountsService;
import service.accounts.util.Utility;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountsService {

    private final AccountRepository accountRepository;

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

    @Override
    public Mono<ResponseEntity<StatusResponseOKDelete>> delete(String id, String xUserId, String xConsumerId, String xTransactionId, String xCallerId, ServerWebExchange exchange) {
        return AccountsApiDelegate.super.delete(id, xUserId, xConsumerId, xTransactionId, xCallerId, exchange);
    }

    @Override
    public Mono<ResponseEntity<StatusResponseOK>> obtener(String id, String xUserId, String xConsumerId, String xTransactionId, String xCallerId, ServerWebExchange exchange) {
        return AccountsApiDelegate.super.obtener(id, xUserId, xConsumerId, xTransactionId, xCallerId, exchange);
    }

    @Override
    public Mono<ResponseEntity<StatusResponseOK>> update(String id, String xUserId, String xConsumerId, String xTransactionId, String xCallerId, Mono<RequestUpdateCuentaAhorro> requestUpdateCuentaAhorro, ServerWebExchange exchange) {
        return AccountsApiDelegate.super.update(id, xUserId, xConsumerId, xTransactionId, xCallerId, requestUpdateCuentaAhorro, exchange);
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
