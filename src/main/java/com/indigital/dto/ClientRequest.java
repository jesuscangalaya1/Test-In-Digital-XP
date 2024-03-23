package com.indigital.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class ClientRequest {


    @Schema(
            description = "Nombre del cliente",
            example = "Jesus"
    )
    @NotBlank(message = "El nombre no debe estar en blanco")
    @Size(max = 100, min = 2, message = "El nombre debe tener como máximo {max} caracteres y como mínimo {min} caracteres.")
    private String name;

    @Schema(
            description = "Apellido del cliente",
            example = "Cangalaya"
    )
    @Size(min = 2, max = 200, message = "El apellido debe tener entre {min} y {max} caracteres.")
    private String lastName;

    @Schema(
            description = "Edad del cliente",
            example = "23"
    )
    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 150, message = "La edad no puede ser mayor a {value}")
    private Integer age;

    @Schema(
            description = "Fecha de nacimiento del cliente",
            example = "DD-MM-YYYY"
    )
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate birthDate;
}
