package br.com.tokiomarine.safebankingapi.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferDtoPresenter {

    Long id;
    BankAccountDto accountOrigin;
    BankAccountDto accountDestination;
    BigDecimal transferValue;
    String transferValueFormatted;
    LocalDateTime dateSchedule;
    String dateScheduleFormated;
    LocalDateTime dateTransfer;
    String dateTransferFormated;
    BigDecimal rate;
    String rateFormated;
}
