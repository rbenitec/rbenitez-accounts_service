package service.accounts.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "account")
public class Account {
    @Id
    private String id;
    private String typeAccount;
    private String customerId;
    private Double openingAmount;
    private String accountNumber;
    private String cci;
    private String dateCreated;
    private String dateUpdate;
}
