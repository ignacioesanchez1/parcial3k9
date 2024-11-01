package com.example.inicial1.controllers;

import com.example.inicial1.dto.DnaRequest;
import com.example.inicial1.exception.InvalidDnaException;
import com.example.inicial1.services.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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
                return ResponseEntity.ok("Es un mutante"); // 200 OK
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No es un mutante"); // 403 Forbidden
            }
        } catch (InvalidDnaException e) {
            // Aquí se captura la excepción y se lanza de nuevo para ser manejada por las pruebas
            throw e; // Vuelve a lanzar la excepción
        }
    }

    @GetMapping
    public ResponseEntity<String> getMutantInfo() {
        return ResponseEntity.ok("El endpoint /mutant está funcionando");
    }

    //------------------------------------------------------------------------------------------------------------------------------//

    private void validateDna(String[] dna) {
        System.out.println("Validando ADN: " + Arrays.toString(dna));

        // 1. Verificar si el arreglo está vacío
        if (dna == null || dna.length == 0) {
            throw new InvalidDnaException("El arreglo de ADN no puede estar vacío");
        }

        int length = dna[0].length(); // Longitud de la primera fila (número de columnas)
        // 2. Verifica que el número de filas sea igual al número de columnas
        if (dna.length != length) {
            throw new InvalidDnaException("La matriz debe ser de tamaño NxN");
        }

        // 3. Verificar si la matriz es NxN
        for (String row : dna) {
            if (row.length() != length) {
                throw new InvalidDnaException("La matriz debe ser de tamaño NxN");
            }
        }

        // 4. Verificar si solo contiene letras A, T, C, G
        for (String row : dna) {
            for (char nucleotide : row.toCharArray()) {
                if (Character.isDigit(nucleotide)) {
                    throw new InvalidDnaException("La secuencia no puede contener números");
                }
                if (nucleotide != 'A' && nucleotide != 'T' && nucleotide != 'C' && nucleotide != 'G') {
                    throw new InvalidDnaException("La secuencia solo puede contener A, T, C, G");
                }
            }
        }
    }
}

