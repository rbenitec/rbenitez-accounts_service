package service.accounts.service.strategy;

import reactor.core.publisher.Mono;
import service.accounts.client.dto.ResponseCustomerDto;
import service.accounts.entities.Account;
import service.accounts.repository.AccountRepository;

public interface CreatedAccountStrategy {
    Mono<Account> createdAccount(Account account, ResponseCustomerDto responseCustomerDto, AccountRepository accountRepository);
}
