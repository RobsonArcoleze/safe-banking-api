package br.com.tokiomarine.safebankingapi.application.usecase.impl;

import br.com.tokiomarine.safebankingapi.application.exception.UseCaseException;
import br.com.tokiomarine.safebankingapi.application.usecase.TransferScheduleUseCase;
import br.com.tokiomarine.safebankingapi.domain.model.Transfer;
import br.com.tokiomarine.safebankingapi.domain.repository.TransferRepository;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public class TransferScheduleUseCaseImpl implements TransferScheduleUseCase {

    private final TransferRepository transferRepository;

    public TransferScheduleUseCaseImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public void execute(Transfer transfer) {

        transfer.setRate(calculateRate(transfer));

        transferRepository.save(transfer);
    }

    protected BigDecimal calculateRate(Transfer transfer) {

        long days = ChronoUnit.DAYS.between(transfer.getDateSchedule(), transfer.getDateTransfer());

        if(days == 0) return BigDecimal.valueOf(3.00).add(transfer.getTransferValue().multiply(BigDecimal.valueOf(0.025)));
        if(days >= 1 && days <= 10) return BigDecimal.valueOf(12.00);
        if(days >= 11 && days <= 20) return transfer.getTransferValue().multiply(BigDecimal.valueOf(0.082));
        if(days >= 21 && days <= 30) return transfer.getTransferValue().multiply(BigDecimal.valueOf(0.069));
        if(days >= 31 && days <= 40) return transfer.getTransferValue().multiply(BigDecimal.valueOf(0.047));
        if(days >= 41 && days <= 50) return transfer.getTransferValue().multiply(BigDecimal.valueOf(0.017));

        throw new UseCaseException("Transfers over 50 days are not allowed.", "RATE_INVALID");

    }
}
