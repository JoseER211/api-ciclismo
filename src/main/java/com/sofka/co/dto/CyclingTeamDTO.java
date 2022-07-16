package com.sofka.co.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CyclingTeamDTO {

    private Long id;
    private String name;
    private String code;
    private String country;

}
