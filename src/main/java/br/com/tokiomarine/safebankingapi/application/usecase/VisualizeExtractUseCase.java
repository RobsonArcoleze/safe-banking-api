package br.com.tokiomarine.safebankingapi.application.usecase;

import br.com.tokiomarine.safebankingapi.domain.model.Transfer;

import java.util.List;

public interface VisualizeExtractUseCase {

    public List<Transfer> findAll();
}
