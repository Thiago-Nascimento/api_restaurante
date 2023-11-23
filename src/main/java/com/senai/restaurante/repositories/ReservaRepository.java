package com.senai.restaurante.repositories;

import com.senai.restaurante.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservaRepository extends JpaRepository<ReservaModel, UUID> {
}