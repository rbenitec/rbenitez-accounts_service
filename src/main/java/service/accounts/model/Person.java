package service.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;  // Ej: "Juan Pérez"
    private String documentType;   // Ej: "DNI", "RUC", "Pasaporte"
    private String documentNumber; // Ej: "12345678", "87654321", "A1234567"
}
