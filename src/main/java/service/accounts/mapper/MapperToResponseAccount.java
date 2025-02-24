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
                account.getOpeningAmount(),
                account.getDateCreated(),
                account.getDateUpdate()
        );
    }
}
