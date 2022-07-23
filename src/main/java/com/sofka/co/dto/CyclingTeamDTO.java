package com.sofka.co.dto;

import com.sofka.co.entities.Cyclist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CyclingTeamDTO {

    private Long id;
    @NotEmpty(message = "El nombre del equipo es requerido")
    private String name;
    @NotEmpty(message = "El código del equipo es requerido")
    @Size(max = 3, message = "El código del equipo no debe tener mas de 3 caracteres")
    private String code;
    @NotEmpty(message = "El pais del equipo es requerido")
    private String country;
    private Set<Cyclist> cyclists;

}
