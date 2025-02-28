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
                .accountNumber(Utility.generatedAccountNumber())
                .typeAccount(requestCustomerDto.getAccountType())
                .customerId(requestCustomerDto.getCustomerId())
                .amount(requestCustomerDto.getAmount())
                .dateCreated(Utility.getDateTimeNow())
                .dateUpdate(Utility.getDateTimeNow())
                .build();
        account.setCci(Utility.generatedCCI(account.getAccountNumber()));
        return account;
    }
}
