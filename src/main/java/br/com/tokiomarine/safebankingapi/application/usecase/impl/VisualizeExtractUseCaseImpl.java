package br.com.tokiomarine.safebankingapi.application.usecase.impl;

import br.com.tokiomarine.safebankingapi.application.exception.UseCaseException;
import br.com.tokiomarine.safebankingapi.application.usecase.VisualizeExtractUseCase;
import br.com.tokiomarine.safebankingapi.domain.model.Transfer;
import br.com.tokiomarine.safebankingapi.domain.repository.TransferRepository;

import java.util.List;

public class VisualizeExtractUseCaseImpl implements VisualizeExtractUseCase {

    private final TransferRepository transferRepository;

    public VisualizeExtractUseCaseImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public List<Transfer> findAll() {

        List<Transfer> transferList = this.transferRepository.findAll();
        if (transferList.isEmpty()) {
            throw new UseCaseException("Transfer not found!", "TRANSFER_NOT_FOUND");
        }
        return transferList;
    }
}
