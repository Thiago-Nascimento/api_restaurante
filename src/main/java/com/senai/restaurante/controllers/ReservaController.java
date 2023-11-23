package com.senai.restaurante.controllers;

import com.senai.restaurante.dtos.ReservaDto;
import com.senai.restaurante.models.ReservaModel;
import com.senai.restaurante.repositories.ReservaRepository;
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
@CrossOrigin(origins = "*")
@RequestMapping(value = "/reservas", produces = {"application/json"})
public class ReservaController {
    @Autowired
    ReservaRepository reservaRepository;

    @GetMapping
    public ResponseEntity<List<ReservaModel>> listarReservas() {
        return ResponseEntity.status(HttpStatus.OK).body(reservaRepository.findAll());
    }

    @GetMapping("/{idReserva}")
    public ResponseEntity<Object> exibirReserva(@PathVariable(value = "idReserva") UUID id) {
        Optional<ReservaModel> reservaBuscada = reservaRepository.findById(id);

        if (reservaBuscada.isEmpty()) {
            // Retornar reserva não encontrada
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(reservaBuscada.get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarReserva(@RequestBody @Valid ReservaDto reservaDto) {
        ReservaModel reserva = new ReservaModel();

        BeanUtils.copyProperties(reservaDto, reserva);

        return ResponseEntity.status(HttpStatus.CREATED).body(reservaRepository.save(reserva));
    }

}
