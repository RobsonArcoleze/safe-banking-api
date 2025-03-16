package br.com.tokiomarine.safebankingapi.interfaces.mapper;

import br.com.tokiomarine.safebankingapi.domain.model.Transfer;
import br.com.tokiomarine.safebankingapi.infrastructure.entity.TransferEntity;
import br.com.tokiomarine.safebankingapi.interfaces.dto.TransferEntityDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransferMapper {

    TransferMapper INSTANCE = Mappers.getMapper(TransferMapper.class);

    Transfer toModel(TransferEntity transfer);

   // @Mapping(target = "id", ignore = true)
    TransferEntity toEntity(Transfer transfer);

    //TransferEntity dtoToEntity(TransferEntityDto transferEntityDto);

    TransferEntityDto entityToDto(TransferEntity transferEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TransferEntity partialUpdate(TransferEntityDto transferEntityDto, @MappingTarget TransferEntity transferEntity);

    @Mapping(source = "accountOrigin", target = "accountOrigin")
    @Mapping(source = "accountDestination", target = "accountDestination")
    Transfer dtoToModel(TransferEntityDto transferEntityDto);


    List<TransferEntityDto> toDtoList(List<Transfer> transferList);


}
