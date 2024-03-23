package com.indigital.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clientes")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , unique = true)
    private Long id;

    @Column(name = "nombre", length = 100)
    private String name;

    @Column(name = "apellido", length = 200)
    private String lastName;

    @Column(name = "edad")
    private Integer age;

    @Column(name = "fecha_nacimiento")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    private boolean deleted;
}
