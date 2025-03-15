package service.accounts.service.strategy;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import service.accounts.client.dto.ResponseCustomerDto;
import service.accounts.entities.Account;
import service.accounts.exception.BusinessException;
import service.accounts.repository.AccountRepository;
import service.accounts.util.Constants;

@Component(Constants.FIXED_TERM_ACCOUNT)
public class CreatedAccountFixedTermStrategy implements CreatedAccountStrategy {

    @Override
    public Mono<Account> createdAccount(Account account, ResponseCustomerDto responseCustomerDto, AccountRepository accountRepository) {
        return accountRepository.findAccountsByCustomerId(responseCustomerDto.getId())
                .filter(accountFound -> accountFound.getTypeAccount().equals(Constants.FIXED_TERM_ACCOUNT) && responseCustomerDto.getCustomerType().equals(Constants.PERSONAL_CUSTOMER))
                .hasElements()
                .flatMap(existsAccountFixedTerm -> {
                    if (existsAccountFixedTerm) {
                        return Mono.error(new BusinessException("CreatedAccountFixedTermStrategy", "The customer already has a fixed-term account"));
                    }
                    if (responseCustomerDto.getCustomerType().equals(Constants.BUSINESS_CUSTOMER)) {
                        return Mono.error(new BusinessException("CreatedAccountFixedTermStrategy", "The business customer cannot have a fixed-term account"));
                    }
                    return accountRepository.save(account);
                });
    }
}
