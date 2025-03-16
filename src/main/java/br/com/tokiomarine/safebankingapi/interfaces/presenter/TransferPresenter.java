package br.com.tokiomarine.safebankingapi.interfaces.presenter;

import br.com.tokiomarine.safebankingapi.domain.enumeration.Bank;
import br.com.tokiomarine.safebankingapi.interfaces.dto.TransferDtoPresenter;
import br.com.tokiomarine.safebankingapi.interfaces.dto.TransferEntityDto;
import br.com.tokiomarine.safebankingapi.domain.model.Transfer;
import br.com.tokiomarine.safebankingapi.interfaces.mapper.TransferMapper;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransferPresenter {

    public List<TransferDtoPresenter> formatTransferList(List<Transfer> transferList) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        DecimalFormat df = new DecimalFormat("#,##0.00");

        List<TransferDtoPresenter> transferDtoList = new ArrayList<>();
        List<TransferEntityDto> dtoList = TransferMapper.INSTANCE.toDtoList(transferList);
        dtoList.forEach(dto -> {

            transferDtoList.add(new TransferDtoPresenter(
                            dto.getId(),
                            dto.getAccountOrigin(),
                            dto.getAccountDestination(),
                            dto.getTransferValue(),
                            df.format(dto.getTransferValue()) + " R$",
                            dto.getDateSchedule(),
                            dto.getDateSchedule().format(formatter),
                            dto.getDateTransfer(),
                            dto.getDateTransfer().format(formatter),
                            dto.getRate(),
                            df.format(dto.getRate()) + " R$"
                    )
            );
        });
        return transferDtoList;
    }
}
