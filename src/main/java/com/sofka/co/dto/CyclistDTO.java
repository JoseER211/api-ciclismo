package com.sofka.co.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CyclistDTO {

    private Long id;
    private String fullName;
    private Integer number;
    private String country;
}
