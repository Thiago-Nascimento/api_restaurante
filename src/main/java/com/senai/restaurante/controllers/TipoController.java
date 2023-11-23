package com.senai.restaurante.controllers;

import com.senai.restaurante.dtos.TipoDto;
import com.senai.restaurante.models.TipoModel;
import com.senai.restaurante.repositories.TipoRepository;
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
@RequestMapping(value = "/tipos", produces = {"application/json"})
public class TipoController {
    @Autowired
    TipoRepository tipoRepository;

    @GetMapping
    public ResponseEntity<List<TipoModel>> listarTipos() {
        return ResponseEntity.status(HttpStatus.OK).body(tipoRepository.findAll());
    }

    @GetMapping("{idTipo}")
    public ResponseEntity<Object> exibirTipo(@PathVariable(value = "idTipo") UUID id) {
        Optional<TipoModel> tipoBuscado = tipoRepository.findById(id);

        if (tipoBuscado.isEmpty()) {
            // Retornar tipo não encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de usuario não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tipoBuscado.get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarTipo(@RequestBody @Valid TipoDto tipoDto) {
        TipoModel novoTipo = new TipoModel();

        BeanUtils.copyProperties(tipoDto, novoTipo);

        return ResponseEntity.status(HttpStatus.CREATED).body(tipoRepository.save(novoTipo));
    }
}
