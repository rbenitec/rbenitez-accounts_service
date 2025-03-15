package service.accounts.mapper.factory.impl;

import org.springframework.stereotype.Component;
import service.accounts.client.dto.ResponseCustomerDto;
import service.accounts.entities.Account;
import service.accounts.mapper.factory.AccountEntityCreator;
import service.accounts.model.RequestAccountDto;
import service.accounts.util.Constants;
import service.accounts.util.Utility;

@Component(Constants.FIXED_TERM_ACCOUNT_MAPPER)
public class FixedTermAccountEntityCreator implements AccountEntityCreator {
    @Override
    public Account createAccount(RequestAccountDto accountDto, ResponseCustomerDto customerDto) {
        return Account.builder()
                .id(Utility.generatedIdAccount())
                .accountNumber(Utility.generatedAccountNumber())
                .typeAccount(Constants.FIXED_TERM_ACCOUNT)
                .customerId(accountDto.getCustomerId())
                .amount(accountDto.getAmount())
                .dateCreated(Utility.getDateTimeNow())
                .dateUpdate(Utility.getDateTimeNow())
                .commission(Constants.NO_COMMISSION_ACCOUNT)
                .monthlyMovements(Constants.MOVEMENTS_PLAZO_FIJO) // Atributo específico de cuenta de plazo fijo
                //.maturityDate(accountDto.getMaturityDate()) // Atributo específico de cuenta de plazo fijo
                .build();
    }
}
