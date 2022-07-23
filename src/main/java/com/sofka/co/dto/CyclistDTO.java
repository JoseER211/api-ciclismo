package com.sofka.co.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CyclistDTO {

    private Long id;
    @NotEmpty(message = "El nombre del ciclista es requerido")
    private String fullName;
    @NotNull(message = "El número del ciclista es requerido")
    private Integer number;
    @NotEmpty(message = "El país del ciclista es requerido")
    private String country;
}
