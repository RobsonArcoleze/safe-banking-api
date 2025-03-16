package br.com.tokiomarine.safebankingapi.infrastructure.jpa;

import br.com.tokiomarine.safebankingapi.infrastructure.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountEntityRepository extends JpaRepository<BankAccountEntity, Long> {
}