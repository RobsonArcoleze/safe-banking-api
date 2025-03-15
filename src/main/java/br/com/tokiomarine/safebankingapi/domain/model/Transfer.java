package br.com.tokiomarine.safebankingapi.domain.model;

import br.com.tokiomarine.safebankingapi.domain.exception.DomainException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transfer {

    private Long id;
    private BankAccount accountOrigin;
    private BankAccount accountDestination;
    private BigDecimal value;
    private LocalDateTime dateSchedule;
    private LocalDateTime dateTransfer;
    private BigDecimal rate;


    public Transfer(Long id, BankAccount accountOrigin, BankAccount accountDestination, BigDecimal value, LocalDateTime dateSchedule, LocalDateTime dateTransfer) {
        this.id = id;
        this.accountOrigin = accountOrigin;
        this.accountDestination = accountDestination;
        this.value = valueIsValid(value);
        this.dateSchedule = dateSchedule;
        this.dateTransfer = dateTransferIsValid(dateTransfer);
    }

    private LocalDateTime dateTransferIsValid(LocalDateTime dateTransfer){
        LocalDate now = LocalDate.now();
        if (dateTransfer.toLocalDate().isBefore(now)) {
            throw new DomainException("Transfer date cannot be less than the current date: " + dateTransfer, "DATE_TRANSFER_INVALID");
        }
        return dateTransfer;
    }

    private BigDecimal valueIsValid(BigDecimal value){
        if (value != null && value.compareTo(BigDecimal.ZERO) > 0) {
            return value;
        }
        throw new DomainException("Transfer value cannot be less than zero: " + value, "VALUE_INVALID");
    }

    public BankAccount getAccountOrigin() {
        return accountOrigin;
    }
    public BankAccount getAccountDestination() {
        return accountDestination;
    }
    public BigDecimal getValue() {
        return value;
    }
    public LocalDateTime getDateSchedule() {
        return dateSchedule;
    }
    public LocalDateTime getDateTransfer() {
        return dateTransfer;
    }
    public Long getId() {return id;}

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
