package br.com.tokiomarine.safebankingapi.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link br.com.tokiomarine.safebankingapi.infrastructure.entity.BankAccountEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto implements Serializable {
    Long id;

    @NotNull(message = "Number cannot be null")
    @NotBlank(message = "Number cannot be empty")
    @Size(min = 5, max = 10, message = "Account number must be between 5 and 10 characters")
    String number;

    @NotNull(message = "Bank cannot be null")
    @NotBlank(message = "Bank cannot be empty")
    @Size(min = 1, max = 5, message = "Bank code must be between 1 and 5 characters")
    String bank;

    @NotNull(message = "Digit cannot be null")
    @NotBlank(message = "Digit cannot be empty")
    @Size(min = 1, max = 2, message = "Digit must be a single character")
    String digit;

    @NotNull(message = "Agency cannot be null")
    @NotBlank(message = "Agency cannot be empty")
    @Size(min = 3, max = 5, message = "Agency must be between 3 and 5 characters")
    String agency;
}