package br.com.tokiomarine.safebankingapi.domain.repository;

import br.com.tokiomarine.safebankingapi.domain.model.Transfer;

public interface TransferRepository {
    public void save(Transfer transfer);
}
