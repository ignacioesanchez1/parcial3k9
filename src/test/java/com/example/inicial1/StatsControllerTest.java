package com.example.inicial1;

import com.example.inicial1.controllers.StatsController;
import com.example.inicial1.repositories.DnaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class StatsControllerTest {

    @InjectMocks
    private StatsController statsController;

    @Mock
    private DnaRepository dnaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetStats() {
        when(dnaRepository.countByIsMutant(true)).thenReturn(40L);
        when(dnaRepository.countByIsMutant(false)).thenReturn(100L);

        ResponseEntity<?> response = statsController.getStats();
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Map<String, Object> stats = (Map<String, Object>) response.getBody();
        assertEquals(40L, stats.get("count_mutant_dna"));
        assertEquals(100L, stats.get("count_human_dna"));
        assertEquals(0.4, stats.get("ratio"));
    }

    @Test
    void testGetStats_NoHumans() {
        when(dnaRepository.countByIsMutant(true)).thenReturn(50L);
        when(dnaRepository.countByIsMutant(false)).thenReturn(0L);

        ResponseEntity<?> response = statsController.getStats();
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Map<String, Object> stats = (Map<String, Object>) response.getBody();
        assertEquals(50L, stats.get("count_mutant_dna"));
        assertEquals(0L, stats.get("count_human_dna"));
        assertEquals(0.0, stats.get("ratio"));
    }
}

