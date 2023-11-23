package com.senai.restaurante.dtos;

import jakarta.validation.constraints.NotBlank;

public record TipoDto(
        @NotBlank String nome
) {
}
