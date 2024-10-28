package com.example.inicial1;

import com.example.inicial1.repositories.DnaRepository;
import com.example.inicial1.services.MutantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MutantServiceTest {
    @InjectMocks
    private MutantService mutantService;

    @Mock
    private DnaRepository dnaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //_--------------------------------------------------------------//


    @Test
    void testDetectMutantDiagonal() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAAG",
                "CCCTAG",
                "TCACTG"
        };

        boolean result = mutantService.detectMutant(dna);

        assertTrue(result);
    }

    @Test
    void testDetectMutantMultipleSequences() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AAAAGG",
                "CCCTAA",
                "TCACTG"
        };

        boolean result = mutantService.detectMutant(dna);

        assertTrue(result);
    }

    @Test
    void testDetectNoMutantHorizontal() {
        String[] dna = {
                "AAGCGA",
                "CAGTGC",
                "TTCTGT",
                "AGAAAC",
                "CCCTAG",
                "TCACTG"
        };

        boolean result = mutantService.detectMutant(dna);

        assertFalse(result);
    }

    @Test
    void testDetectNoMutantVertical() {
        String[] dna = {
                "AAGCGA",
                "CAGTGC",
                "TTCTGT",
                "AGAAAC",
                "CCCTAG",
                "TCACTG"
        };

        boolean result = mutantService.detectMutant(dna);

        assertFalse(result);
    }

    @Test
    void testDetectNoMutantDiagonal() {
        String[] dna = {
                "AAGCGA",
                "CAGTGC",
                "TTCTGT",
                "AGAAAC",
                "CCCTAG",
                "TCACTG"
        };

        boolean result = mutantService.detectMutant(dna);

        assertFalse(result);
    }

    //______________________________________________________________________//

    @Test
    void testDetectMutant1() {
        String[] dna = {
                "AAAA",
                "CCCC",
                "TCAG",
                "GGTC"
        };

        boolean result = mutantService.detectMutant(dna);

        assertTrue(result);
    }

    @Test
    void testDetectNoMutant1() {
        String[] dna = {
                "AAAT",
                "AACC",
                "AAAC",
                "CGGG"
        };

        boolean result = mutantService.detectMutant(dna);

        assertFalse(result);
    }

    @Test
    void testDetectMutant2() {
        String[] dna = {
                "TGAC",
                "AGCC",
                "TGAC",
                "GGTC"
        };

        boolean result = mutantService.detectMutant(dna);

        assertTrue(result);
    }

    @Test
    void testDetectNoMutant2() {
        String[] dna = {
                "TGAC",
                "ATCC",
                "TAAG",
                "GGTC"
        };

        boolean result = mutantService.detectMutant(dna);

        assertFalse(result);
    }

    @Test
    void testDetectMutant3() {
        String[] dna = {
                "ATGCGAATC",
                "CAGTGCTAT",
                "TTATGTAAT",
                "AGAAAGTTA",
                "CCCTAGCAC",
                "TCACTGACA",
                "AGTGATCGT",
                "GATCAAAAC",
                "CTGACTGTG"

        };

        boolean result = mutantService.detectMutant(dna);

        assertTrue(result);
    }

    @Test
    void testDetectNoMutant3() {
        String[] dna = {
                "ATGCGAATC",
                "CGGTGCTAT",
                "TTATGTAAT",
                "AGATAGTTA",
                "CCCTAGCAC",
                "TCACTGACA",
                "AGTGATCGT",
                "GATCAACAC",
                "CTGACTGTG"

        };

        boolean result = mutantService.detectMutant(dna);

        assertFalse(result);
    }


}
