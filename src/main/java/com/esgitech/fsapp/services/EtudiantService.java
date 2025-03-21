package com.esgitech.fsapp.services;

import com.esgitech.fsapp.enums.NiveauEtude;
import com.esgitech.fsapp.exceptions.EtudiantNotFoundException;
import com.esgitech.fsapp.model.Etudiant;
import com.esgitech.fsapp.repos.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Etudiant getEtudiantById(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new EtudiantNotFoundException("ID : " + id));
    }

    public Etudiant createEtudiant(Etudiant etudiant) {
        etudiant.setCodeEtudiant(etudiant.getCodeEtudiant().toUpperCase());
        return etudiantRepository.save(etudiant);
    }

    public List<Etudiant> addEtudiants(List<Etudiant> etudiants) {
        return etudiantRepository.saveAll(etudiants);
    }

    public Etudiant updateEtudiant(Long id, Etudiant etudiantDetails) {
        Etudiant etudiant = getEtudiantById(id);
        etudiant.setNom(etudiantDetails.getNom());
        etudiant.setPrenom(etudiantDetails.getPrenom());
        etudiant.setEmail(etudiantDetails.getEmail());
        etudiant.setCodeEtudiant(etudiantDetails.getCodeEtudiant().toUpperCase());
        etudiant.setNiveauEtude(etudiantDetails.getNiveauEtude());
        return etudiantRepository.save(etudiant);
    }

    public int updateEtudiantByCodeEtudiant(
            String codeEtudiant,
            String nom,
            String prenom,
            String email,
            NiveauEtude niveauEtude
    ) {
        return etudiantRepository.updateEtudiantByCodeEtudiant(codeEtudiant, nom, prenom, email, niveauEtude);
    }

    public void deleteEtudiant(Long id) {
        Etudiant etudiant = getEtudiantById(id);
        etudiantRepository.delete(etudiant);
    }
}
