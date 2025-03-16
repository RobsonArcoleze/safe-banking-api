package br.com.tokiomarine.safebankingapi.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link br.com.tokiomarine.safebankingapi.infrastructure.entity.TransferEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferEntityDto implements Serializable {

    Long id;

    @NotNull(message = "Origin account cannot be null")
    BankAccountDto accountOrigin;

    @NotNull(message = "Destination account cannot be null")
    BankAccountDto accountDestination;

    @NotNull(message = "Transfer value cannot be null")
    @Positive(message = "Transfer value must be greater than zero")
    BigDecimal transferValue;

    @NotNull(message = "Scheduled date cannot be null")
    LocalDateTime dateSchedule;

    @NotNull(message = "Transfer date cannot be null")
    LocalDateTime dateTransfer;
    BigDecimal rate;
}

