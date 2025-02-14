package service.accounts.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "accounts")
public class Account {
    @Id
    private String id;
    private String typeAccount;
    private String idClient;
    private Double openingBalance;
    private String account;
    private String cci;
    private String dateCreated;
    private String dateUpdate;
}
