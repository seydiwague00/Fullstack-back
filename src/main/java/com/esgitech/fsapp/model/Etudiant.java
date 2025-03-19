package com.esgitech.fsapp.model;

import com.esgitech.fsapp.enums.NiveauEtude;
import jakarta.persistence.*;
import lombok.*;

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
}



