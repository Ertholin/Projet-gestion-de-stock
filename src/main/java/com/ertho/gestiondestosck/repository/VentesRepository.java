package com.ertho.gestiondestosck.repository;

import com.ertho.gestiondestosck.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VentesRepository extends JpaRepository<Ventes, Integer> {

    Optional<Ventes> findVenteByCode(String code);
}
