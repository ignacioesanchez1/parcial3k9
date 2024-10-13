package com.example.inicial1.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="dna")
@AllArgsConstructor
@NoArgsConstructor //constructor vacio
@Setter
@Getter
@Builder
public class DnaRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 500)
    private String dnaSequence;

    @Column
    private boolean isMutant;

    public boolean isMutant() {
        return isMutant;
    }

    public void setIsMutant(boolean isMutant) {
        this.isMutant = isMutant;
    }


}

