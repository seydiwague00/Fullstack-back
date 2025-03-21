package com.esgitech.fsapp.repos;

import com.esgitech.fsapp.enums.NiveauEtude;
import com.esgitech.fsapp.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    Etudiant findByCodeEtudiant(String codeEtudiant);

    @Modifying
    @Transactional
    @Query("UPDATE Etudiant e SET e.nom = :nom, e.prenom = :prenom, e.email = :email, e.niveauEtude = :niveauEtude WHERE e.codeEtudiant = :codeEtudiant")
    int updateEtudiantByCodeEtudiant(String codeEtudiant, String nom, String prenom, String email, NiveauEtude niveauEtude);


    List<Etudiant> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String filter, String filter1);
}
