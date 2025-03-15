package service.accounts.service.strategy;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import service.accounts.client.dto.ResponseCustomerDto;
import service.accounts.entities.Account;
import service.accounts.exception.BusinessException;
import service.accounts.repository.AccountRepository;
import service.accounts.util.Constants;

@Component(Constants.VIP_ACCOUNT)
public class CreatedAccountSavingsVipStrategy implements CreatedAccountStrategy {

    @Override
    public Mono<Account> createdAccount(Account account, ResponseCustomerDto responseCustomerDto, AccountRepository accountRepository) {
        if (!responseCustomerDto.isHasCreditCard()) {
            return Mono.error(new BusinessException("CreatedAccountSavingsVipStrategy", "El cliente VIP necesita una tarjeta de crédito activa para esta cuenta."));
        }
        return accountRepository.save(account);
    }
}
