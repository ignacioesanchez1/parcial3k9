package com.example.inicial1.services;

import com.example.inicial1.entities.DnaRecord;
import com.example.inicial1.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MutantService {

    @Autowired
    private DnaRepository dnaRepository;

    // Método principal que determina si el ADN es mutante
    public boolean isMutant(String[] dna) {
        // Unir el array de ADN en una sola cadena para evitar duplicados en la BD
        String dnaStr = String.join("", dna);

        // Buscar si ya se ha verificado este ADN previamente
        Optional<DnaRecord> existingRecord = dnaRepository.findByDnaSequence(dnaStr);

        // Si ya existe, devolver el resultado almacenado
        if (existingRecord.isPresent()) {
            return existingRecord.get().isMutant();
        }

        // Si no existe, proceder a la verificación
        boolean isMutant = detectMutant(dna);

        // Guardar el resultado en la base de datos
        DnaRecord record = new DnaRecord();
        record.setDnaSequence(dnaStr);
        record.setIsMutant(isMutant);
        dnaRepository.save(record);

        return isMutant;
    }

    // MEtodo que contiene la lógica para detectar si es mutante
    public boolean detectMutant(String[] dna) {
        int n = dna.length;
        int sequenceCount = 0;

        // Convertir la secuencia de Strings a una matriz de caracteres
        char[][] dnaMatrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            dnaMatrix[i] = dna[i].toCharArray();
        }

        // Recorrer la matriz buscando secuencias en todas las direcciones
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Solo contar si hay una secuencia de 4 en una dirección
                if (checkHorizontal(dnaMatrix, i, j, n)) {
                    sequenceCount++;
                }
                if (checkVertical(dnaMatrix, i, j, n)) {
                    sequenceCount++;
                }
                if (checkDiagonal(dnaMatrix, i, j, n)) {
                    sequenceCount++;
                }
                // Si encontramos dos o más secuencias, podemos devolver true
                if (sequenceCount >= 2) {
                    return true;
                }
            }
        }
        return false;
    }

    // Verificar secuencia horizontal
    private boolean checkHorizontal(char[][] dna, int row, int col, int n) {
        if (col + 3 < n) {
            return dna[row][col] == dna[row][col + 1] &&
                    dna[row][col] == dna[row][col + 2] &&
                    dna[row][col] == dna[row][col + 3];
        }
        return false;
    }

    // Verificar secuencia vertical
    private boolean checkVertical(char[][] dna, int row, int col, int n) {
        if (row + 3 < n) {
            return dna[row][col] == dna[row + 1][col] &&
                    dna[row][col] == dna[row + 2][col] &&
                    dna[row][col] == dna[row + 3][col];
        }
        return false;
    }

    // Verificar secuencia diagonal
    private boolean checkDiagonal(char[][] dna, int row, int col, int n) {
        if (row + 3 < n && col + 3 < n) {
            return dna[row][col] == dna[row + 1][col + 1] &&
                    dna[row][col] == dna[row + 2][col + 2] &&
                    dna[row][col] == dna[row + 3][col + 3];
        }
        return false;
    }
}
