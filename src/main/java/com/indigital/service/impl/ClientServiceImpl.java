package com.indigital.service.impl;

import com.indigital.dto.ClientRequest;
import com.indigital.dto.response.ClientKpiResponse;
import com.indigital.dto.response.ClientResponse;
import com.indigital.dto.response.PageableResponse;
import com.indigital.entity.ClientEntity;
import com.indigital.exceptions.BusinessException;
import com.indigital.mapper.ClientMapper;
import com.indigital.repository.ClientRepository;
import com.indigital.service.ClientService;
import com.indigital.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.indigital.util.AppConstants.*;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    @Transactional(readOnly = true)
    public PageableResponse<ClientResponse> paginationClients(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending()
                : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina - 1, medidaDePagina, sort);

        // Obtener una página de clientes desde el repositorio
        Page<ClientEntity> products = clientRepository.findAllByDeletedFalse(pageable);

        // Mapear la página de entidades a una página de DTOs
        List<ClientResponse> productResponsePage = clientMapper.toListClientsDTO(products.getContent());

        if (productResponsePage.isEmpty()) {
            throw new BusinessException("P-204", HttpStatus.NO_CONTENT, "Lista Vaciá de Clientes");
        }

        return PageableResponse.<ClientResponse>builder()
                .content(productResponsePage)
                .pageNumber(products.getNumber() + 1)
                .pageSize(products.getSize())
                .totalPages(products.getTotalPages())
                .totalElements(products.getTotalElements())
                .last(products.isLast())
                .build();
    }

    @Override
    @Transactional
    public ClientResponse createClient(ClientRequest clientRequest) {
        ClientEntity product = clientMapper.toClientEntity(clientRequest);

        // Guardar la entidad del cliente en la base de datos
        ClientEntity savedProduct = clientRepository.save(product);
        // Mapear la entidad del cliente guardado a un DTO de respuesta
        return clientMapper.toClientDTO(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientKpiResponse calculateClientKpi() {
        // Recuperar todos los clientes que no han sido eliminados
        List<ClientEntity> clients = clientRepository.findAllByDeletedFalse();

        /**
         * Calcular el promedio de edad de los clientes.
         * Utilizamos mapToInt para convertir la lista de clientes en una lista de edades.
         * Luego, calculamos el promedio con average().
         * Si la lista está vacía, el valor por defecto es 0.0.
         */
        double averageAge = clients.stream()
                .mapToInt(ClientEntity::getAge)
                .average()
                .orElse(0.0);

        /**
         * Calcular la desviación estándar de las edades de los clientes.
         * La varianza es la media de los cuadrados de las diferencias entre cada edad y la edad promedio.
         */
        double variance = clients.stream()
                .mapToDouble(client -> Math.pow(client.getAge() - averageAge, 2))
                .sum() / clients.size();

        /**
         * Calcular la desviación estándar de las edades de los clientes.
         * La desviación estándar es la raíz cuadrada de la varianza.
         * Redondeamos el resultado a dos decimales.
         */
        double standardDeviation = Math.round(Math.sqrt(variance) * 100.0) / 100.0;

        return ClientKpiResponse.builder()
                .averageAge(averageAge)
                .standardDeviation(standardDeviation)
                .build();
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new BusinessException(BAD_REQUEST, HttpStatus.NOT_FOUND, BAD_REQUEST_CLIENT + id);
        }
        // Desactivar (eliminar lógicamente) un cliente por su ID
        clientRepository.desactivarClient(id);
    }
}
