package com.senai.restaurante.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ReservaDto(
        @NotBlank String nome_cliente,
        @NotNull Byte numero_pessoas,
        @NotNull Date data_reserva
) {  }
