package service.accounts.mapper.factory;

import org.springframework.stereotype.Component;
import service.accounts.client.dto.ResponseCustomerDto;
import service.accounts.entities.Account;
import service.accounts.exception.BusinessException;
import service.accounts.model.RequestAccountDto;

import java.util.Map;

@Component
public class AccountEntityFactory {

    private final Map<String, AccountEntityCreator> creatorsEntity;

    public AccountEntityFactory(Map<String, AccountEntityCreator> creatorsEntity) {
        this.creatorsEntity = creatorsEntity;
    }
    //Metodo de creación

    public Account createAccountEntity(RequestAccountDto accountDto, ResponseCustomerDto customerDto) {
        String type = accountDto.getAccountType().toUpperCase().concat("_MAPPER");
        if (!creatorsEntity.containsKey(type)) {
            throw new BusinessException("CreatedAccountStrategyFactory", "Tipo de cuenta no válido: " + type);
        }
        return creatorsEntity.get(type).createAccount(accountDto, customerDto);
    }
}
