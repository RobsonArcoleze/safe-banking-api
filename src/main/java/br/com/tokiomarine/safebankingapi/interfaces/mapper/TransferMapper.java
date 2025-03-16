package br.com.tokiomarine.safebankingapi.interfaces.mapper;

import br.com.tokiomarine.safebankingapi.domain.model.Transfer;
import br.com.tokiomarine.safebankingapi.infrastructure.entity.TransferEntity;
import br.com.tokiomarine.safebankingapi.application.dto.TransferEntityDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransferMapper {

    TransferMapper INSTANCE = Mappers.getMapper(TransferMapper.class);

    Transfer toModel(Transfer transfer);

   @Mapping(target = "id", ignore = true)
    TransferEntity toEntity(Transfer transfer);

    TransferEntity dtoToEntity(TransferEntityDto transferEntityDto);

    TransferEntityDto entityToDto(TransferEntity transferEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TransferEntity partialUpdate(TransferEntityDto transferEntityDto, @MappingTarget TransferEntity transferEntity);

    @Mapping(source = "accountOrigin", target = "accountOrigin")
    @Mapping(source = "accountDestination", target = "accountDestination")
    Transfer dtoToModel(TransferEntityDto transferEntityDto);
}
