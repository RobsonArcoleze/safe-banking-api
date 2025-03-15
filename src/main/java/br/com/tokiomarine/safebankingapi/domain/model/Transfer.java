package br.com.tokiomarine.safebankingapi.domain.model;

import br.com.tokiomarine.safebankingapi.domain.exception.DomainException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transfer {

    private BankAccount accountOrigin;
    private BankAccount accountDestination;
    private BigDecimal value;
    private LocalDateTime dateSchedule;
    private LocalDateTime dateTransfer;


    public Transfer(BankAccount accountOrigin, BankAccount accountDestination, BigDecimal value, LocalDateTime dateSchedule, LocalDateTime dateTransfer) {
        this.accountOrigin = accountOrigin;
        this.accountDestination = accountDestination;
        this.value = valueIsValid(value);
        this.dateSchedule = dateSchedule;
        this.dateTransfer = dateTransferIsValid(dateTransfer);
    }

    private LocalDateTime dateTransferIsValid(LocalDateTime dateTransfer){
        LocalDateTime now = LocalDateTime.now();
        if (dateTransfer.isBefore(now)) {
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

    public void setAccountOrigin(BankAccount accountOrigin) {
        this.accountOrigin = accountOrigin;
    }

    public BankAccount getAccountDestination() {
        return accountDestination;
    }

    public void setAccountDestination(BankAccount accountDestination) {
        this.accountDestination = accountDestination;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getDateSchedule() {
        return dateSchedule;
    }

    public void setDateSchedule(LocalDateTime dateSchedule) {
        this.dateSchedule = dateSchedule;
    }

    public LocalDateTime getDateTransfer() {
        return dateTransfer;
    }

    public void setDateTransfer(LocalDateTime dateTransfer) {
        this.dateTransfer = dateTransfer;
    }
}
