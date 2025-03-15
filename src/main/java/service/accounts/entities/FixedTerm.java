package service.accounts.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "account")
public class FixedTerm extends Account {
    private String expirationDate;
    //private boolean permiteMovimientoDiaEspecifico;

    public FixedTerm() {
        this.setTypeAccount("PLAZO_FIJO");
    }
}
