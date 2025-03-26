package com.esgitech.fsapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matiere;
    private Double valeur;

    @ManyToOne
    @JoinColumn(name = "etudiant_id", nullable = false)
    @JsonBackReference
    private Etudiant etudiant;
}
