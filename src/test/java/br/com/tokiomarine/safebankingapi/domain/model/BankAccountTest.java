package br.com.tokiomarine.safebankingapi.domain.model;

import br.com.tokiomarine.safebankingapi.domain.exception.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    @DisplayName("Valid Account Number Test")
    public void testValidAccountNumber() {
        BankAccount bankAccount = new BankAccount(1L, "12345", "001", "1", "1234");
        assertEquals("12345", bankAccount.getNumber());
    }

    @Test
    @DisplayName("Invalid Account Number Test")
    public void testInvalidAccountNumber() {
        DomainException exception = assertThrows(DomainException.class, () -> {
            new BankAccount(1L, "1234", "001", "1", "1234");  // Número de conta inválido (menos de 5 dígitos)
        });
        assertEquals("Invalid Account Number: 1234", exception.getMessage());
        assertEquals("INVALID_ACCOUNT_NUMBER", exception.getError());
    }

    @Test
    @DisplayName("Valid Digit Test")
    public void testValidDigit() {
        BankAccount bankAccount = new BankAccount(1L, "12345", "001", "1", "1234");
        assertEquals("1", bankAccount.getDigit());
    }

    @Test
    @DisplayName("Invalid Digit Test")
    public void testInvalidDigit() {
        DomainException exception = assertThrows(DomainException.class, () -> {
            new BankAccount(1L, "12345", "001", "A", "1234");  // Dígito inválido (não numérico)
        });
        assertEquals("Digit is not a valuable value: A", exception.getMessage());
        assertEquals("INVALID_DIGIT", exception.getError());
    }

    @Test
    @DisplayName("Valid Agency Test")
    public void testValidAgency() {
        BankAccount bankAccount = new BankAccount(1L, "12345", "001", "1", "12345");
        assertEquals("12345", bankAccount.getAgency());
    }

    @Test
    @DisplayName("Invalid Agency Test")
    public void testInvalidAgency() {
        DomainException exception = assertThrows(DomainException.class, () -> {
            new BankAccount(1L, "12345", "001", "1", "12");  // Agência inválida (menos de 3 dígitos)
        });
        assertEquals("Agency is not a valuable value: 12", exception.getMessage());
        assertEquals("INVALID_AGENCY", exception.getError());
    }

    @Test
    @DisplayName("Valid Bank Test")
    public void testValidBank() {
        BankAccount bankAccount = new BankAccount(1L , "12345", "001", "1", "1234");
        assertEquals("001", bankAccount.getBank());
    }

    @Test
    @DisplayName("Invalid Bank Test")
    public void testInvalidBank() {
        DomainException exception = assertThrows(DomainException.class, () -> {
            new BankAccount(1L, "12345", "999", "1", "1234");  // Banco inexistente
        });
        assertEquals("Bank Code Not Found 999", exception.getMessage());
        assertEquals("BANK_NOT_FOUND", exception.getError());
    }
}