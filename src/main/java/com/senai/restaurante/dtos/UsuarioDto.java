package com.senai.restaurante.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record UsuarioDto(
        @NotBlank String nome,
        @NotBlank @Email(message = "O email deve estar em um formato válido") String email,
        @NotBlank String senha,
        String endereco,
        String cep,
        String tipo_usuario
//        MultipartFile imagem
) {  }
