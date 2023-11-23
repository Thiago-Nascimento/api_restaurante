package com.senai.restaurante.controllers;

import com.senai.restaurante.dtos.UsuarioDto;
import com.senai.restaurante.models.TipoModel;
import com.senai.restaurante.models.UsuarioModel;
import com.senai.restaurante.repositories.TipoRepository;
import com.senai.restaurante.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/usuarios", produces = "application/json")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private TipoRepository tipoRepository;

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Object> exibirUsuario(@PathVariable(value = "idUsuario") UUID id) {
        Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);

        if (usuarioBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioBuscado.get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
        UsuarioModel novoUsuario = new UsuarioModel();

        BeanUtils.copyProperties(usuarioDto, novoUsuario);

        Optional<TipoModel> tipoUsuario = tipoRepository.findByNome(usuarioDto.tipo_usuario());

        if (tipoUsuario.isPresent()) {
            novoUsuario.setTipoUsuario(tipoUsuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo Usuario não encontrado");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(novoUsuario));
    }
}
