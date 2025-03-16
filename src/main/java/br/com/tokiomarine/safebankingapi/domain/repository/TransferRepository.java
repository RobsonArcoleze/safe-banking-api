package br.com.tokiomarine.safebankingapi.domain.repository;

import br.com.tokiomarine.safebankingapi.domain.model.Transfer;

import java.util.List;

public interface TransferRepository {
    public void save(Transfer transfer);
    public List<Transfer> findAll();
}
