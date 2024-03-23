package com.indigital.service;

import com.indigital.dto.ClientRequest;
import com.indigital.dto.response.ClientKpiResponse;
import com.indigital.dto.response.ClientResponse;
import com.indigital.dto.response.PageableResponse;

public interface ClientService {

    PageableResponse<ClientResponse> paginationClients(int numeroDePagina, int medidaDePagina,
                                                        String ordenarPor, String sortDir);
    ClientResponse createClient(ClientRequest clientRequest);

    ClientKpiResponse calculateClientKpi();

    void deleteClient(Long id);

}
