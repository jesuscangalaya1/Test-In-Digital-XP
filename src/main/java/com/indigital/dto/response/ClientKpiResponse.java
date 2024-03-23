package com.indigital.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientKpiResponse {

    private double averageAge;
    private double standardDeviation;

}
