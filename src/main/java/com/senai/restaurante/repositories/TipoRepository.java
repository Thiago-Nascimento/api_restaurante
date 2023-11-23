package com.senai.restaurante.repositories;

import com.senai.restaurante.models.TipoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TipoRepository extends JpaRepository<TipoModel, UUID> {
    Optional<TipoModel> findByNome(String nome);
}
