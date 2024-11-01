package com.example.inicial1.dto;

public class DnaRequest {
    private String[] dna;

    public DnaRequest(String[] dna) {
        this.dna = dna;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}


