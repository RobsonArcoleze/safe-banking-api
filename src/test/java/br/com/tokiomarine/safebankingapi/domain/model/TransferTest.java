package br.com.tokiomarine.safebankingapi.domain.model;

import br.com.tokiomarine.safebankingapi.domain.exception.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransferTest {

    // Teste de valor de transferência válido
    @Test
    @DisplayName("Valid Transfer Value Test")
    public void testValidTransferValue() {
        BankAccount accountOrigin = new BankAccount("12345", "001", "1", "1234");
        BankAccount accountDestination = new BankAccount("54321", "001", "2", "5678");
        BigDecimal validValue = new BigDecimal("100.00");
        LocalDateTime now = LocalDateTime.now();

        Transfer transfer = new Transfer(accountOrigin, accountDestination, validValue, now, now.plusDays(1));

        assertEquals(validValue, transfer.getValue());
    }

    // Teste de valor de transferência inválido (menor ou igual a zero)
    @Test
    @DisplayName("Invalid Transfer Value Test - Negative")
    public void testInvalidTransferValueNegative() {
        BankAccount accountOrigin = new BankAccount("12345", "001", "1", "1234");
        BankAccount accountDestination = new BankAccount("54321", "001", "2", "5678");
        BigDecimal invalidValue = new BigDecimal("-10.00");
        LocalDateTime now = LocalDateTime.now();

        DomainException exception = assertThrows(DomainException.class, () -> {
            new Transfer(accountOrigin, accountDestination, invalidValue, now, now.plusDays(1));
        });

        assertEquals("Transfer value cannot be less than zero: " + invalidValue, exception.getMessage());
        assertEquals("VALUE_INVALID", exception.getError());
    }

    // Teste de valor de transferência inválido (zero)
    @Test
    @DisplayName("Invalid Transfer Value Test - Zero")
    public void testInvalidTransferValueZero() {
        BankAccount accountOrigin = new BankAccount("12345", "001", "1", "1234");
        BankAccount accountDestination = new BankAccount("54321", "001", "2", "5678");
        BigDecimal invalidValue = BigDecimal.ZERO;
        LocalDateTime now = LocalDateTime.now();

        DomainException exception = assertThrows(DomainException.class, () -> {
            new Transfer(accountOrigin, accountDestination, invalidValue, now, now.plusDays(1));
        });

        assertEquals("Transfer value cannot be less than zero: " + invalidValue, exception.getMessage());
        assertEquals("VALUE_INVALID", exception.getError());
    }

    // Teste de valor de transferência nulo
    @Test
    @DisplayName("Invalid Transfer Value Test - Null")
    public void testInvalidTransferValueNull() {
        BankAccount accountOrigin = new BankAccount("12345", "001", "1", "1234");
        BankAccount accountDestination = new BankAccount("54321", "001", "2", "5678");
        LocalDateTime now = LocalDateTime.now();

        DomainException exception = assertThrows(DomainException.class, () -> {
            new Transfer(accountOrigin, accountDestination, null, now, now.plusDays(1));
        });

        assertEquals("Transfer value cannot be less than zero: null", exception.getMessage());
        assertEquals("VALUE_INVALID", exception.getError());
    }

    // Teste de data de transferência válida
    @Test
    @DisplayName("Valid Transfer Date Test")
    public void testValidTransferDate() {
        BankAccount accountOrigin = new BankAccount("12345", "001", "1", "1234");
        BankAccount accountDestination = new BankAccount("54321", "001", "2", "5678");
        BigDecimal validValue = new BigDecimal("100.00");
        LocalDateTime now = LocalDateTime.now();

        // Transferência no futuro
        LocalDateTime futureDate = now.plusDays(1);
        Transfer transfer = new Transfer(accountOrigin, accountDestination, validValue, now, futureDate);

        assertEquals(futureDate, transfer.getDateTransfer());
    }

    // Teste de data de transferência inválida (anterior ao horário atual)
    @Test
    @DisplayName("Invalid Transfer Date Test")
    public void testInvalidTransferDate() {
        BankAccount accountOrigin = new BankAccount("12345", "001", "1", "1234");
        BankAccount accountDestination = new BankAccount("54321", "001", "2", "5678");
        BigDecimal validValue = new BigDecimal("100.00");
        LocalDateTime now = LocalDateTime.now();

        // Data no passado
        LocalDateTime pastDate = now.minusDays(1);

        DomainException exception = assertThrows(DomainException.class, () -> {
            new Transfer(accountOrigin, accountDestination, validValue, now, pastDate);
        });

        assertEquals("Transfer date cannot be less than the current date: " + pastDate, exception.getMessage());
        assertEquals("DATE_TRANSFER_INVALID", exception.getError());
    }
}