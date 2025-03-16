package br.com.tokiomarine.safebankingapi.interfaces.rest;

import br.com.tokiomarine.safebankingapi.application.usecase.TransferScheduleUseCase;
import br.com.tokiomarine.safebankingapi.domain.model.Transfer;
import br.com.tokiomarine.safebankingapi.application.dto.TransferEntityDto;
import br.com.tokiomarine.safebankingapi.interfaces.mapper.TransferMapper;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transfer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class TransferController {

    private final TransferScheduleUseCase transferScheduleUseCase;

    public TransferController(TransferScheduleUseCase transferScheduleUseCase) {
        this.transferScheduleUseCase = transferScheduleUseCase;
    }

    @PostMapping
    private ResponseEntity<Void> transferSchedule(@RequestBody @Valid TransferEntityDto dto) {

        Transfer transfer = TransferMapper.INSTANCE.dtoToModel(dto);
        transferScheduleUseCase.execute(transfer);
        return ResponseEntity.ok().build();
    }
}
