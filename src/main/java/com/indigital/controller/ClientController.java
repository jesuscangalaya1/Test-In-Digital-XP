package com.indigital.controller;

import com.indigital.dto.ClientRequest;
import com.indigital.dto.response.ClientKpiResponse;
import com.indigital.dto.response.ClientResponse;
import com.indigital.dto.response.PageableResponse;
import com.indigital.dto.response.RestResponse;
import com.indigital.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


import javax.validation.Valid;
import javax.validation.constraints.Positive;

import static com.indigital.util.AppConstants.*;

@Validated
@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Tag(name = "CLIENTE", description = "Operaciones permitidas sobre la entidad Cliente")
public class ClientController {

    private final ClientService clientService;

    @Operation(summary = "Crear un nuevo cliente")
    @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<ClientResponse> createClient(@RequestBody @Valid ClientRequest clientRequest) {

        return new RestResponse<>("SUCCESS",
                String.valueOf(HttpStatus.CREATED),
                "CLIENT SUCCESSFULLY CREATED",
                clientService.createClient(clientRequest));
    }

    @Operation(summary = "Obtener la información de todos los clientes paginados")
    @ApiResponse(responseCode = "200", description = "Clientes obtenidos exitosamente")
    @GetMapping(value = "/pagination", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<PageableResponse<ClientResponse>> pageableClients(
            @RequestParam(value = "pageNo", defaultValue = NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
            @RequestParam(value = "pageSize", defaultValue = MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
            @RequestParam(value = "sortBy", defaultValue = ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "sortDir", defaultValue = ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {

        return new RestResponse<>(SUCCESS,
                String.valueOf(HttpStatus.OK),
                "CLIENT SUCCESSFULLY READED",
                clientService.paginationClients(numeroDePagina, medidaDePagina, ordenarPor, sortDir));
    }


    @Operation(summary = "Obtener el KPI de los clientes (promedio y desviación estandar entre las edades de los clientes)")
    @ApiResponse(responseCode = "200", description = "Cliente KPI calculado exitosamente")
    @GetMapping(value = "/kpi", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<ClientKpiResponse> getClientKpi() {
        return new RestResponse<>("SUCCESS",
                String.valueOf(HttpStatus.OK),
                "CLIENT KPI SUCCESSFULLY CALCULATED",
                clientService.calculateClientKpi());
    }


    @Operation(summary = "Eliminar un cliente por su ID")
    @ApiResponse(responseCode = "200", description = "Cliente eliminado exitosamente")
    @DeleteMapping(value = "/{id}")
    public RestResponse<String> deleteClient(@Positive(message = "el ID solo acepta numeros positivos")
                                             @PathVariable Long id) {

        clientService.deleteClient(id);
        return new RestResponse<>(SUCCESS,
                String.valueOf(HttpStatus.OK),
                MESSAGE_ID_CLIENT + id + " SUCCESSFULLY DELETED",
                "null"); // Data null.
    }

}
