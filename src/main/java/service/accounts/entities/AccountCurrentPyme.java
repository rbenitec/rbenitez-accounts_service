package service.accounts.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "account")
public class AccountCurrentPyme extends Account {
    public AccountCurrentPyme() {
        this.setTypeAccount("PYME");
    }
}
