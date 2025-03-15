package service.accounts.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "account")
public class Current extends Account{

    private double maintenanceCommission;

    // Solo para clientes empresariales
    private List<String> holders;
    private List<String> AuthorizedSignatories;

    public Current() {
        this.setTypeAccount("CORRIENTE");
    }

}
