package br.com.tokiomarine.safebankingapi.interfaces.rest;

import br.com.tokiomarine.safebankingapi.application.usecase.TransferScheduleUseCase;
import br.com.tokiomarine.safebankingapi.application.usecase.VisualizeExtractUseCase;
import br.com.tokiomarine.safebankingapi.domain.model.Transfer;
import br.com.tokiomarine.safebankingapi.interfaces.dto.TransferDtoPresenter;
import br.com.tokiomarine.safebankingapi.interfaces.dto.TransferEntityDto;
import br.com.tokiomarine.safebankingapi.interfaces.mapper.TransferMapper;

import javax.validation.Valid;

import br.com.tokiomarine.safebankingapi.interfaces.presenter.TransferPresenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transfer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Transfêrencias", description = "Gerência Agendamento de Transfêrencias Bancárias.")
public class TransferController {

    private final TransferScheduleUseCase transferScheduleUseCase;
    private final VisualizeExtractUseCase visualizeExtractUseCase;

    @Autowired
    private TransferPresenter transferPresenter;

    public TransferController(TransferScheduleUseCase transferScheduleUseCase, VisualizeExtractUseCase visualizeExtractUseCase) {
        this.transferScheduleUseCase = transferScheduleUseCase;
        this.visualizeExtractUseCase = visualizeExtractUseCase;
    }

    @PostMapping
    @Operation(summary = "Agendar Transfêrencia", description = "Recebe uma solicitação para agendamento de transferência.")
    private ResponseEntity<Void> transferSchedule(@RequestBody @Valid TransferEntityDto dto) {

        Transfer transfer = TransferMapper.INSTANCE.dtoToModel(dto);
        transferScheduleUseCase.execute(transfer);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Visualizar extrato", description = "Retorna uma lista de agendamento de transfêrencia bancária.")
    private ResponseEntity<List<TransferDtoPresenter>> findAll() {

        List<TransferDtoPresenter> transferDtoPresenters = transferPresenter.formatTransferList(visualizeExtractUseCase.findAll());

        return ResponseEntity.ok(transferDtoPresenters);
    }
}
