package service.accounts.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "account")
public class Savings extends Account{

    private int monthLimitMovements;

    public Savings() {
        this.setTypeAccount("AHORRO");
    }
}
