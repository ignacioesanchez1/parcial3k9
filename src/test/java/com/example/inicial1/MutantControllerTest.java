package com.example.inicial1.controllers; // Ajusta este paquete según sea necesario

import com.example.inicial1.dto.DnaRequest;
import com.example.inicial1.exception.InvalidDnaException;
import com.example.inicial1.services.MutantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class MutantControllerTest {

    @InjectMocks
    private MutantController mutantController;

    @Mock
    private MutantService mutantService; // Simular el servicio

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testIsMutantWithValidMutantDna() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        DnaRequest request = new DnaRequest(dna);

        when(mutantService.isMutant(dna)).thenReturn(true);

        ResponseEntity<String> response = mutantController.isMutant(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Es un mutante", response.getBody());
    }

    @Test
    void testIsMutantWithValidNonMutantDna() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCTCTA", "TCACTG"};
        DnaRequest request = new DnaRequest(dna);

        when(mutantService.isMutant(dna)).thenReturn(false);

        ResponseEntity<String> response = mutantController.isMutant(request);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("No es un mutante", response.getBody());
    }

    @Test
    public void testGetMutantInfo_ReturnsInfo() throws Exception {
        ResponseEntity<String> response = mutantController.getMutantInfo();

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("El endpoint /mutant está funcionando", response.getBody());
    }


    @Test
    void testIsMutantWithEmptyDnaArray() {
        String[] dna = {}; // Arreglo vacío
        DnaRequest request = new DnaRequest(dna);

        // Verifica que se lanza InvalidDnaException
        InvalidDnaException exception = assertThrows(InvalidDnaException.class, () -> {
            mutantController.isMutant(request);
        });

        // Verifica el mensaje de la excepción
        assertEquals("El arreglo de ADN no puede estar vacío", exception.getMessage());
    }

    @Test
    void testIsMutantWithNonSquareDnaArray() {
        String[] dna = {"ATG", "CAGT", "TTAT"}; // Matriz no cuadrada
        DnaRequest request = new DnaRequest(dna);

        InvalidDnaException exception = assertThrows(InvalidDnaException.class, () -> {
            mutantController.isMutant(request);
        });

        assertEquals("La matriz debe ser de tamaño NxN", exception.getMessage());
    }

    @Test
    void testIsMutantWithInvalidCharactersInDnaArray() {
        String[] dna = {"ATGXA", "CAGTC", "TTATG", "AGAAG", "CCCXT"}; // Letras no autorizadas
        DnaRequest request = new DnaRequest(dna);

        InvalidDnaException exception = assertThrows(InvalidDnaException.class, () -> {
            mutantController.isMutant(request);
        });

        assertEquals("La secuencia solo puede contener A, T, C, G", exception.getMessage());
    }

    @Test
    public void testIsMutant_ContainsNumbers_ThrowsException() {
        String[] dna = {"ATCG", "AT2G", "ATCG", "ACTA"};

        InvalidDnaException exception = assertThrows(InvalidDnaException.class, () -> {
            mutantController.isMutant(new DnaRequest(dna));
        });

        assertEquals("La secuencia no puede contener números", exception.getMessage());
    }
}

