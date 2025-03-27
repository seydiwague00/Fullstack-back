package com.esgitech.fsapp.model;

import com.esgitech.fsapp.enums.NiveauEtude;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "etudiants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
// Gère les références circulaires
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;

    @Column(name = "code_etudiant", unique = true, nullable = false)
    private String codeEtudiant;

    @Enumerated(EnumType.STRING)
    private NiveauEtude niveauEtude;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes = new ArrayList<>();

    // Constructeur approprié sans Object
    public Etudiant(String nom, String prenom, String email, String codeEtudiant, NiveauEtude niveauEtude, List<Note> notes) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.codeEtudiant = codeEtudiant;
        this.niveauEtude = niveauEtude;
        this.notes = notes;
    }
}
