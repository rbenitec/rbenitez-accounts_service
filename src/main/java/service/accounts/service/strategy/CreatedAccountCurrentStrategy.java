package service.accounts.service.strategy;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import service.accounts.client.dto.ResponseCustomerDto;
import service.accounts.entities.Account;
import service.accounts.exception.BusinessException;
import service.accounts.repository.AccountRepository;
import service.accounts.util.Constants;

@Component(Constants.CURRENT_ACCOUNT)
public class CreatedAccountCurrentStrategy implements CreatedAccountStrategy {

    @Override
    public Mono<Account> createdAccount(Account account, ResponseCustomerDto responseCustomerDto, AccountRepository accountRepository) {
        return accountRepository.findAccountsByCustomerId(responseCustomerDto.getId())
                .filter(accountFound -> Constants.CURRENT_ACCOUNT.equalsIgnoreCase(accountFound.getTypeAccount()))
                .hasElements()
                .flatMap(existsAccountCurrent -> {
                    if (existsAccountCurrent && responseCustomerDto.getCustomerType().equals(Constants.PERSONAL_CUSTOMER)) {
                        return Mono.error(new BusinessException("CreatedAccountCurrentStrategy", "El cliente ya tiene una cuenta corriente."));
                    }
                    return accountRepository.save(account);
                });
    }
}
