package com.esgitech.fsapp.services;

import com.esgitech.fsapp.exceptions.EtudiantNotFoundException;
import com.esgitech.fsapp.model.Etudiant;
import com.esgitech.fsapp.repos.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Etudiant getEtudiantById(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new EtudiantNotFoundException(id));
    }

    public Etudiant createEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public Etudiant updateEtudiant(Long id, Etudiant etudiantDetails) {
        Etudiant etudiant = getEtudiantById(id);
        etudiant.setNom(etudiantDetails.getNom());
        etudiant.setPrenom(etudiantDetails.getPrenom());
        etudiant.setEmail(etudiantDetails.getEmail());
        etudiant.setCodeEtudiant(etudiantDetails.getCodeEtudiant());
        etudiant.setNiveauEtude(etudiantDetails.getNiveauEtude());
        return etudiantRepository.save(etudiant);
    }

    public void deleteEtudiant(Long id) {
        Etudiant etudiant = getEtudiantById(id);
        etudiantRepository.delete(etudiant);
    }
}
