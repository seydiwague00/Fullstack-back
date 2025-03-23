package com.esgitech.fsapp.DTO;

import com.esgitech.fsapp.enums.NiveauEtude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantDTO {
    private String nom;
    private String prenom;
    private String email;
    private String codeEtudiant;
    private NiveauEtude niveauEtude;
}
