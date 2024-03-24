package com.indigital.mapper;

import com.indigital.dto.ClientRequest;
import com.indigital.dto.response.ClientResponse;
import com.indigital.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientResponse toClientDTO(ClientEntity clientEntity);

    @Mapping(target = "id", ignore = true) // Ignorar el mapeo del campo 'id'
    @Mapping(target = "deleted", ignore = true)
    ClientEntity toClientEntity(ClientRequest clientRequest);


    List<ClientResponse> toListClientsDTO(List<ClientEntity> clientEntityList);
}
