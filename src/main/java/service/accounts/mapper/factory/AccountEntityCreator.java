package service.accounts.mapper.factory;

import service.accounts.client.dto.ResponseCustomerDto;
import service.accounts.entities.Account;
import service.accounts.model.RequestAccountDto;

public interface AccountEntityCreator {
    Account createAccount(RequestAccountDto requestAccountDto, ResponseCustomerDto customerDto);
}
