package com.example.inicial1.dto;

public class DnaRequest {
    private String[] dna;

    // Constructor por defecto necesario para la deserialización
    public DnaRequest() {
    }

    // Constructor con parámetro
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



