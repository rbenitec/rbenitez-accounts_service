package service.accounts.mapper.factory.impl;

import org.springframework.stereotype.Component;
import service.accounts.client.dto.ResponseCustomerDto;
import service.accounts.entities.Account;
import service.accounts.mapper.factory.AccountEntityCreator;
import service.accounts.model.RequestAccountDto;
import service.accounts.util.Constants;
import service.accounts.util.Utility;

@Component(Constants.CURRENT_ACCOUNT_MAPPER)
public class CurrentAccountEntityCreator implements AccountEntityCreator {
    @Override
    public Account createAccount(RequestAccountDto accountDto, ResponseCustomerDto customerDto) {
        return Account.builder()
                .id(Utility.generatedIdAccount())
                .accountNumber(Utility.generatedAccountNumber())
                .typeAccount(Constants.CURRENT_ACCOUNT)
                .customerId(accountDto.getCustomerId())
                .amount(accountDto.getAmount())
                .dateCreated(Utility.getDateTimeNow())
                .dateUpdate(Utility.getDateTimeNow())
                .commission(Constants.COMMISSION_CURRENT_ACCOUNT) // Atributo específico de cuenta corriente
                .monthlyMovements(Constants.NOT_LIMITED_MOVEMENTS)
                .build();
    }
}
