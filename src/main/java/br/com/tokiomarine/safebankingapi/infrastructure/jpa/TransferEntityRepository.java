package br.com.tokiomarine.safebankingapi.infrastructure.jpa;

import br.com.tokiomarine.safebankingapi.infrastructure.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferEntityRepository extends JpaRepository<TransferEntity, Long> {
}