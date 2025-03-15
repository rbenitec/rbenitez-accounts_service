package service.accounts.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import service.accounts.model.Person;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "account")
public  class Account {
    /*@Id
    private String id;
    private String typeAccount; // PYME, VIP, PLAZO_FIJO, CORRIENTE y AHORRO
    private String customerId;
    private Double amount;
    private String accountNumber;
    private String cci;
    private String dateCreated;
    private String dateUpdate;*/

    @Id
    private String id;
    private String typeAccount; // PYME, VIP, PLAZO_FIJO, CORRIENTE y AHORRO
    private String customerId;
    private Double amount;
    private String accountNumber;
    private String cci;
    private String dateCreated;
    private String dateUpdate;
    private Double commission;
    private String monthlyMovements;
    private Double minimumAverageDailyAmount;
    private List<Person> holders;
    private List<Person> authorizedSigners;
}
