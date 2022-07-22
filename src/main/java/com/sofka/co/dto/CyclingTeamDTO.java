package com.sofka.co.dto;

import com.sofka.co.entities.Cyclist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CyclingTeamDTO {

    private Long id;
    private String name;
    private String code;
    private String country;
    private Set<Cyclist> cyclists;

}
