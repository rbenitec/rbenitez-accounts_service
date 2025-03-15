package service.accounts.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.accounts.util.Constants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestAccountDto {

    @NotNull(message = "No debe ser nulo")
    @NotEmpty(message = "No debe ser vacio")
    @Pattern(regexp = Constants.ACCOUNT_TYPE_PATTERN, message = "El tipo de cuenta debe ser uno de los siguientes valores: PYME, VIP, PLAZO_FIJO, CORRIENTE o AHORRO.")
    private String accountType;// PYME, VIP, PLAZO_FIJO, CORRIENTE y AHORRO
    @NotNull(message = "No debe ser nulo")
    @NotEmpty(message = "No debe ser vacio")
    private String customerId;
    @NotNull(message = "No debe ser nulo")
    private Double amount;
}
