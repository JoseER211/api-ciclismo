package com.sofka.co.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
