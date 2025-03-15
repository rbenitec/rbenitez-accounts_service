package service.accounts.mapper.factory.impl;

import org.springframework.stereotype.Component;
import service.accounts.client.dto.ResponseCustomerDto;
import service.accounts.entities.Account;
import service.accounts.mapper.factory.AccountEntityCreator;
import service.accounts.model.RequestAccountDto;
import service.accounts.util.Constants;
import service.accounts.util.Utility;

@Component(Constants.PYME_ACCOUNT_MAPPER)
public class PymeAccountEntityCreator implements AccountEntityCreator {

    @Override
    public Account createAccount(RequestAccountDto accountDto, ResponseCustomerDto customerDto) {
        if (!customerDto.isHasCreditCard()) {
            throw new IllegalArgumentException("El cliente PYME debe tener una tarjeta de crédito activa.");
        }

        return Account.builder()
                .id(Utility.generatedIdAccount())
                .accountNumber(Utility.generatedAccountNumber())
                .typeAccount(Constants.PYME_ACCOUNT)
                .customerId(accountDto.getCustomerId())
                .amount(accountDto.getAmount())
                .dateCreated(Utility.getDateTimeNow())
                .dateUpdate(Utility.getDateTimeNow())
                .commission(Constants.NO_COMMISSION_ACCOUNT) // Sin comisión de mantenimiento
                .build();

    }
}
