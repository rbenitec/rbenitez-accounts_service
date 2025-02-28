package service.accounts.mapper;

import org.springframework.stereotype.Component;
import service.accounts.entities.Account;
import service.accounts.model.RequestUpdateAccountDto;
import service.accounts.util.Utility;

import java.util.function.BiConsumer;

@Component
public class MapperUpdateToAccount implements BiConsumer<RequestUpdateAccountDto, Account> {

    @Override
    public void accept(RequestUpdateAccountDto requestUpdateAccountDto, Account account) {
        account.setAmount(requestUpdateAccountDto.getAmount());
        account.setDateUpdate(Utility.getDateTimeNow());
    }

}
