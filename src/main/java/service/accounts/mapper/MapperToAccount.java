package service.accounts.mapper;

import org.springframework.stereotype.Component;
import service.accounts.entities.Account;
import service.accounts.model.RequestAccountDto;
import service.accounts.util.Utility;

import java.util.function.Function;

@Component
public class MapperToAccount implements Function<RequestAccountDto, Account> {
    @Override
    public Account apply(RequestAccountDto requestCustomerDto) {
        Account account = Account.builder()
                .id(Utility.generatedIdCredit())
                .accountNumber(Utility.generatedAccountNumber())
                .typeAccount(requestCustomerDto.getAccountType())
                .openingAmount(requestCustomerDto.getOpeningAmount())
                .dateUpdate(Utility.getDateTimeNow())
                .dateUpdate(Utility.getDateTimeNow())
                .build();
        account.setId(Utility.generatedCCI(account.getAccountNumber()));
        return account;
    }
}
