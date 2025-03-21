package service.accounts.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseAccountDto {
    private String accountId;
    private String accountType;
    private String customerId;
    private String accountNumber;
    private String cci;
    private Double amount;
    private String createAt;
    private String updateAt;
    private Double commission;
    private String monthlyMovements;
    private Double minimumAverageDailyAmount;
}
