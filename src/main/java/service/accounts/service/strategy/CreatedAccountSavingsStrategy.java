package service.accounts.service.strategy;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import service.accounts.client.dto.ResponseCustomerDto;
import service.accounts.entities.Account;
import service.accounts.exception.BusinessException;
import service.accounts.repository.AccountRepository;
import service.accounts.util.Constants;

@Component(Constants.SAVING_ACCOUNT)
public class CreatedAccountSavingsStrategy implements CreatedAccountStrategy {

    @Override
    public Mono<Account> createdAccount(Account account, ResponseCustomerDto responseCustomerDto, AccountRepository accountRepository) {
        return accountRepository.findAccountsByCustomerId(responseCustomerDto.getId())
                .filter(accountFound -> accountFound.getTypeAccount().equals("AHORRO") &&
                        responseCustomerDto.getCustomerType().equals("PERSONAL"))
                .hasElements()
                .flatMap(existsAccountSaving -> {
                    if (existsAccountSaving) {
                        return Mono.error(new BusinessException("CreatedAccountSavingsStrategy", "The customer already has a savings account"));
                    }
                    if (responseCustomerDto.getCustomerType().equals("EMPRESARIAL")) {
                        return Mono.error(new BusinessException("CreatedAccountSavingsStrategy", "The business customer cannot have a savings account"));
                    }
                    return accountRepository.save(account);
                });
    }
}
