package com.example.inicial1;

import com.example.inicial1.entities.DnaRecord;
import com.example.inicial1.repositories.DnaRepository;
import com.example.inicial1.services.MutantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.*;

class MutantServiceTest {

    @InjectMocks
    private MutantService mutantService;

    @Mock
    private DnaRepository dnaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsMutant_WithExistingRecord() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        String dnaStr = String.join("", dna);

        DnaRecord record = new DnaRecord();
        record.setIsMutant(true);

        when(dnaRepository.findByDnaSequence(dnaStr)).thenReturn(Optional.of(record));

        boolean result = mutantService.isMutant(dna);
        assertTrue(result);
    }

    @Test
    void testIsMutant_WithNewRecord() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        when(dnaRepository.findByDnaSequence(anyString())).thenReturn(Optional.empty());
        when(dnaRepository.save(any(DnaRecord.class))).thenReturn(new DnaRecord());

        boolean result = mutantService.isMutant(dna);
        assertTrue(result);
    }

    @Test
    public void testIsMutant_ValidDna_NotMutantCached() throws Exception {
        String[] dna = {"ATCG", "ATCG", "AAGT", "CTCG"};
        boolean isMutant = false;

        // Mock repository behavior
        Optional<DnaRecord> emptyOptional = Optional.empty();
        when(dnaRepository.findByDnaSequence(String.join("", dna))).thenReturn(emptyOptional);

        // Call isMutant (should detect non-mutant and save to DB)
        boolean result = mutantService.isMutant(dna);

        // Verify detection and persistence
        assertEquals(isMutant, result);
        verify(dnaRepository).save(Mockito.any(DnaRecord.class));

        // Second call with same DNA (should return from cache)
        result = mutantService.isMutant(dna);
    }

    @Test
    public void testIsMutant_ValidDna_MutantCached() throws Exception {
        String[] dna = {"AAAA", "TACG", "TCAT", "ATCA"};
        boolean isMutant = true;

        // Mock repository behavior
        Optional<DnaRecord> emptyOptional = Optional.empty();
        when(dnaRepository.findByDnaSequence(String.join("", dna))).thenReturn(emptyOptional);

        // Call isMutant (should detect non-mutant and save to DB)
        boolean result = mutantService.isMutant(dna);

        // Verify detection and persistence
        assertEquals(isMutant, result);
        verify(dnaRepository).save(Mockito.any(DnaRecord.class));

        // Second call with same DNA (should return from cache)
        result = mutantService.isMutant(dna);
    }

    @Test
    public void testIsMutant_ExistingMutantRecord() throws Exception {
        String[] dna = {"ATCG", "ATCG", "ATCG", "ATCG"};
        boolean isMutant = true;

        // Mock repository behavior (existing mutant record)
        DnaRecord record = new DnaRecord();
        record.setDnaSequence(String.join("", dna));
        record.setIsMutant(true);
        Optional<DnaRecord> existingRecord = Optional.of(record);
        when(dnaRepository.findByDnaSequence(String.join("", dna))).thenReturn(existingRecord);

        // Call isMutant (should return from existing record)
        boolean result = mutantService.isMutant(dna);

        // Verify result and no interaction with detectMutant
        assertEquals(isMutant, result);
        verify(dnaRepository).findByDnaSequence(String.join("", dna));
        verifyNoMoreInteractions(dnaRepository); // No save or update
    }
}