package com.indigital.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientResponse {

    private Long id;
    private String name;
    private String lastName;
    private Integer age;
    private LocalDate birthDate;
}
