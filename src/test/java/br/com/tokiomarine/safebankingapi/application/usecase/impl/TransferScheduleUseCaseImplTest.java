package br.com.tokiomarine.safebankingapi.application.usecase.impl;

import br.com.tokiomarine.safebankingapi.application.exception.UseCaseException;
import br.com.tokiomarine.safebankingapi.domain.model.BankAccount;
import br.com.tokiomarine.safebankingapi.domain.model.Transfer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class TransferScheduleUseCaseImplTest {

    @InjectMocks
    private TransferScheduleUseCaseImpl useCase;

    private Transfer createTransfer(int daysAhead) {
        BankAccount origin = new BankAccount(1L, "123456", "001", "5", "1234");
        BankAccount destination = new BankAccount(2L, "654321", "001", "3", "5678");
        return new Transfer(
                1L, origin, destination,
                new BigDecimal("1000.00"),
                LocalDateTime.now(), LocalDateTime.now().plusDays(daysAhead)
        );
    }

    @Test
    @DisplayName("Should return fixed rate for same-day transfer")
    void shouldReturnFixedRateForSameDayTransfer() {
        Transfer transfer = createTransfer(0);
        BigDecimal rate = useCase.calculateRate(transfer);
        BigDecimal expectedRate = BigDecimal.valueOf(3.00).add(transfer.getValue().multiply(BigDecimal.valueOf(0.025)));
        assertThat(rate).isEqualByComparingTo(expectedRate);
    }

    @Test
    @DisplayName("Should return fixed rate for transfer in 1 to 10 days")
    void shouldReturnFixedRateForTransferIn1To10Days() {
        Transfer transfer = createTransfer(5);
        BigDecimal rate = useCase.calculateRate(transfer);
        assertThat(rate).isEqualByComparingTo(BigDecimal.valueOf(12.00));
    }

    @Test
    @DisplayName("Should return percentage for transfer in 11 to 20 days")
    void shouldReturnPercentageForTransferIn11To20Days() {
        Transfer transfer = createTransfer(15);
        BigDecimal rate = useCase.calculateRate(transfer);
        BigDecimal expectedRate = transfer.getValue().multiply(BigDecimal.valueOf(0.082));
        assertThat(rate).isEqualByComparingTo(expectedRate);
    }

    @Test
    @DisplayName("Should return percentage for transfer in 21 to 30 days")
    void shouldReturnPercentageForTransferIn21To30Days() {
        Transfer transfer = createTransfer(25);
        BigDecimal rate = useCase.calculateRate(transfer);
        BigDecimal expectedRate = transfer.getValue().multiply(BigDecimal.valueOf(0.069));
        assertThat(rate).isEqualByComparingTo(expectedRate);
    }

    @Test
    @DisplayName("Should return percentage for transfer in 31 to 40 days")
    void shouldReturnPercentageForTransferIn31To40Days() {
        Transfer transfer = createTransfer(35);
        BigDecimal rate = useCase.calculateRate(transfer);
        BigDecimal expectedRate = transfer.getValue().multiply(BigDecimal.valueOf(0.047));
        assertThat(rate).isEqualByComparingTo(expectedRate);
    }

    @Test
    @DisplayName("Should return percentage for transfer in 41 to 50 days")
    void shouldReturnPercentageForTransferIn41To50Days() {
        Transfer transfer = createTransfer(45);
        BigDecimal rate = useCase.calculateRate(transfer);
        BigDecimal expectedRate = transfer.getValue().multiply(BigDecimal.valueOf(0.017));
        assertThat(rate).isEqualByComparingTo(expectedRate);
    }

    @Test
    @DisplayName("Should throw exception for transfer above 50 days")
    void shouldThrowExceptionForTransferAbove50Days() {
        Transfer transfer = createTransfer(51);
        assertThatThrownBy(() -> useCase.calculateRate(transfer))
                .isInstanceOf(UseCaseException.class)
                .hasMessage("Transfers over 50 days are not allowed.");
    }
}