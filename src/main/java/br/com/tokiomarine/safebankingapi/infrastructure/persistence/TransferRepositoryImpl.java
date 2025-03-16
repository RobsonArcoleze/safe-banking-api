package br.com.tokiomarine.safebankingapi.infrastructure.persistence;

import br.com.tokiomarine.safebankingapi.domain.model.Transfer;
import br.com.tokiomarine.safebankingapi.domain.repository.TransferRepository;
import br.com.tokiomarine.safebankingapi.infrastructure.entity.TransferEntity;
import br.com.tokiomarine.safebankingapi.infrastructure.jpa.TransferEntityRepository;
import br.com.tokiomarine.safebankingapi.interfaces.mapper.TransferMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransferRepositoryImpl implements TransferRepository {

    @Autowired
    private TransferEntityRepository transferEntityRepository;

    @Override
    public void save(Transfer transfer) {

        TransferEntity entity = TransferMapper.INSTANCE.toEntity(transfer);
        transferEntityRepository.save(entity);
    }

    @Override
    public List<Transfer> findAll() {
        List<TransferEntity> all = transferEntityRepository.findAll();
        List<Transfer> transfers = new ArrayList<>();
        if(!all.isEmpty()) {
            all.forEach(entity -> {
                transfers.add(TransferMapper.INSTANCE.toModel(entity));
            });
            return transfers;
        }
        return List.of();
    }
}
