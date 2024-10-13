package com.example.inicial1.controllers;

import com.example.inicial1.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private DnaRepository dnaRepository;

    @GetMapping
    public ResponseEntity<?> getStats() {
        long mutantCount = dnaRepository.countByIsMutant(true);  // Cuenta los mutantes
        long humanCount = dnaRepository.countByIsMutant(false);  // Cuenta los humanos no mutantes
        double ratio = (humanCount > 0) ? (double) mutantCount / humanCount : 0;  // Evitar división por cero

        // Crear el objeto JSON con las estadísticas
        Map<String, Object> stats = new HashMap<>();
        stats.put("count_mutant_dna", mutantCount);
        stats.put("count_human_dna", humanCount);
        stats.put("ratio", ratio);

        return ResponseEntity.ok(stats);  // Devuelve las estadísticas en formato JSON
    }
}
