package br.com.tokiomarine.safebankingapi.application.usecase;

import br.com.tokiomarine.safebankingapi.domain.model.Transfer;

public interface TransferScheduleUseCase {

    public void execute(Transfer transfer);
}
