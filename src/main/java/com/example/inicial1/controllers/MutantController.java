package com.example.inicial1.controllers;

import com.example.inicial1.dto.DnaRequest;
import com.example.inicial1.exception.InvalidDnaException;
import com.example.inicial1.services.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mutant")
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping
    public ResponseEntity<String> isMutant(@RequestBody DnaRequest dnaRequest) {
        try {
            // Verificación del ADN
            validateDna(dnaRequest.getDna());

            boolean isMutant = mutantService.isMutant(dnaRequest.getDna());
            if (isMutant) {
                return ResponseEntity.ok("Es un mutante"); //200 OK
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No es un mutante"); // 403 Forbidden
            }
        } catch (InvalidDnaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private void validateDna(String[] dna) {
        // 1. Verificar si el arreglo está vacío
        if (dna == null || dna.length == 0) {
            throw new InvalidDnaException("El arreglo de ADN no puede estar vacío");
        }

        int length = dna[0].length();  // Longitud de la primera fila (número de columnas)
        if (dna.length != length) {    // Verifica que el número de filas sea igual al número de columnas
            throw new InvalidDnaException("La matriz debe ser de tamaño NxN");
        }

        // 2. Verificar si la matriz es NxN

        for (String row : dna) {
            if (row.length() != length) {
                throw new InvalidDnaException("La matriz debe ser de tamaño NxN");
            }
        }

        // 3. Verificar si solo contiene letras A, T, C, G
        for (String row : dna) {
            for (char nucleotide : row.toCharArray()) {
                if (nucleotide != 'A' && nucleotide != 'T' && nucleotide != 'C' && nucleotide != 'G') {
                    throw new InvalidDnaException("La secuencia solo puede contener A, T, C, G");
                }
                // Verifica si hay números
                if (Character.isDigit(nucleotide)) {
                    throw new InvalidDnaException("La secuencia no puede contener números");
                }
            }
        }
    }
}