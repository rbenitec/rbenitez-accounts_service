package service.accounts.mapper;

import org.springframework.stereotype.Component;
import service.accounts.entities.Account;
import service.accounts.model.ResponseAccountDto;

import java.util.function.Function;

@Component
public class MapperToResponseAccount implements Function<Account, ResponseAccountDto> {

    @Override
    public ResponseAccountDto apply(Account account) {
        return new ResponseAccountDto(
                account.getId(),
                account.getTypeAccount(),
                account.getCustomerId(),
                account.getAccountNumber(),
                account.getCci(),
                account.getAmount(),
                account.getDateCreated(),
                account.getDateUpdate(),
                account.getCommission() != null ? account.getCommission() : null,
                account.getMonthlyMovements() != null ? account.getMonthlyMovements() : null,
                account.getMinimumAverageDailyAmount() != null ? account.getMinimumAverageDailyAmount() : null
        );
    }
}
