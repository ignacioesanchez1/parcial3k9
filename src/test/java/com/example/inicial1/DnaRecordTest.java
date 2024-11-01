package com.example.inicial1;

import static org.junit.jupiter.api.Assertions.*;

import com.example.inicial1.entities.DnaRecord;
import org.junit.jupiter.api.Test;

class DnaRecordTest {

    @Test
    void testConstructorAndGetters() {
        DnaRecord dnaRecord = new DnaRecord(1L, "AGTCAGTC", true);

        assertEquals(1L, dnaRecord.getId());
        assertEquals("AGTCAGTC", dnaRecord.getDnaSequence());
        assertTrue(dnaRecord.isMutant());
    }

    @Test
    void testSetters() {
        DnaRecord dnaRecord = new DnaRecord();
        dnaRecord.setId(2L);
        dnaRecord.setDnaSequence("TGCATGCA");
        dnaRecord.setIsMutant(false);

        assertEquals(2L, dnaRecord.getId());
        assertEquals("TGCATGCA", dnaRecord.getDnaSequence());
        assertFalse(dnaRecord.isMutant());
    }

    @Test
    void testIsMutantMethod() {
        DnaRecord dnaRecord = new DnaRecord();
        dnaRecord.setIsMutant(true);
        assertTrue(dnaRecord.isMutant());

        dnaRecord.setIsMutant(false);
        assertFalse(dnaRecord.isMutant());
    }
    @Test
    public void testDnaRecord() {
        DnaRecord dnaRecord = DnaRecord.builder()
                .id(1L)
                .dnaSequence("ATCGATCG")
                .isMutant(true)
                .build();

        // Test getters and setters
        assertEquals(1L, dnaRecord.getId());
        assertEquals("ATCGATCG", dnaRecord.getDnaSequence());
        assertTrue(dnaRecord.isMutant());

        dnaRecord.setIsMutant(false);
        assertFalse(dnaRecord.isMutant());
    }
}


