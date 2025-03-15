package br.com.tokiomarine.safebankingapi.domain.model;

import br.com.tokiomarine.safebankingapi.domain.enumeration.Bank;
import br.com.tokiomarine.safebankingapi.domain.exception.DomainException;

public class BankAccount {

    private Long id;
    private String number;
    private String bank;
    private String digit;
    private String agency;

    public BankAccount(Long id, String number, String bank, String digit, String agency) {
        this.id = id;
        this.number = isNumberValid(number);
        this.bank = Bank.getBankByCode(bank);
        this.digit = isDigitValid(digit);
        this.agency = isAgencyValid(agency);
    }

    private String isNumberValid(String number) {
        if (number != null && number.matches("\\d{5,10}")) return number;
        throw new DomainException("Invalid Account Number: " + number, "INVALID_ACCOUNT_NUMBER");
    }

    private String isDigitValid(String digit) {
        if (digit != null && digit.matches("\\d{1}")) return digit;
        throw new DomainException("Digit is not a valuable value: " + digit, "INVALID_DIGIT");
    }

    private String isAgencyValid(String agency) {
        if (agency != null && agency.matches("\\d{3,5}")) return agency;
        throw new DomainException("Agency is not a valuable value: " + agency, "INVALID_AGENCY");
    }

    public Long getId() {return id;}
    public String getNumber() {
        return number;
    }
    public String getBank() {
        return bank;
    }
    public String getDigit() {
        return digit;
    }
    public String getAgency() {
        return agency;
    }
}
