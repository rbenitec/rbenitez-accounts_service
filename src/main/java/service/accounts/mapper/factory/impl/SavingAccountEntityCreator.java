package service.accounts.mapper.factory.impl;

import org.springframework.stereotype.Component;
import service.accounts.client.dto.ResponseCustomerDto;
import service.accounts.entities.Account;
import service.accounts.mapper.factory.AccountEntityCreator;
import service.accounts.model.RequestAccountDto;
import service.accounts.util.Constants;
import service.accounts.util.Utility;

@Component(Constants.SAVING_ACCOUNT_MAPPER)
public class SavingAccountEntityCreator implements AccountEntityCreator {
    @Override
    public Account createAccount(RequestAccountDto accountDto, ResponseCustomerDto customerDto) {
        return Account.builder()
                .accountNumber(Utility.generatedAccountNumber())
                .cci(Utility.generatedCCI(Utility.generatedAccountNumber()))
                .typeAccount("AHORRO")
                .customerId(accountDto.getCustomerId())
                .amount(accountDto.getAmount())
                .dateCreated(Utility.getDateTimeNow())
                .dateUpdate(Utility.getDateTimeNow())
                .monthlyMovements(Constants.MAX_MOVEMENT_ACCOUNT_SAVING) // Atributo específico de cuenta de ahorro
                .commission(Constants.NO_COMMISSION_ACCOUNT) // Atributo específico de cuenta de ahorro
                .build();
    }
}
