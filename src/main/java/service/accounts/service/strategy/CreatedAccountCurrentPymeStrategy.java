package service.accounts.service.strategy;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import service.accounts.client.dto.ResponseCustomerDto;
import service.accounts.entities.Account;
import service.accounts.exception.BusinessException;
import service.accounts.repository.AccountRepository;
import service.accounts.util.Constants;

@Component(Constants.PYME_ACCOUNT)
public class CreatedAccountCurrentPymeStrategy implements CreatedAccountStrategy {

    @Override
    public Mono<Account> createdAccount(Account account, ResponseCustomerDto responseCustomerDto, AccountRepository accountRepository) {
        if (!responseCustomerDto.isHasCreditCard()) {
            return Mono.error(new BusinessException("CreatedAccountCurrentPymeStrategy", "The VIP customer needs an active credit card for this account."));
        }
        return accountRepository.save(account);
    }
}
