package com.example.inicial1.repositories;

import com.example.inicial1.entities.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DnaRepository extends JpaRepository<DnaRecord, Long> {

    // Contar el número de mutantes
    long countByIsMutant(boolean isMutant);

    // Buscar si una secuencia de ADN ya está registrada
    Optional<DnaRecord> findByDnaSequence(String dnaSequence);
}
