package com.esgitech.fsapp.model;

import com.esgitech.fsapp.enums.NiveauEtude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "etudiants")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonManagedReference  // Ajout de cette annotation
    private List<Note> notes;

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
