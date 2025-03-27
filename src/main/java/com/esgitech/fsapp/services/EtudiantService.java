package com.esgitech.fsapp.services;

import com.esgitech.fsapp.enums.NiveauEtude;
import com.esgitech.fsapp.exceptions.EtudiantNotFoundException;
import com.esgitech.fsapp.model.Etudiant;
import com.esgitech.fsapp.model.Note;
import com.esgitech.fsapp.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Etudiant findByCodeEtudiant(String codeEtudiant) {
        return etudiantRepository.findByCodeEtudiant(codeEtudiant);
    }

    @Transactional
    public int updateEtudiantByCodeEtudiant(
            String codeEtudiant,
            String nom,
            String prenom,
            String email,
            NiveauEtude niveauEtude,
            List<Note> notes
    ) {
        // Récupérer l'étudiant par son code
        Optional<Etudiant> optionalEtudiant = Optional.ofNullable(etudiantRepository.findByCodeEtudiant(codeEtudiant));

        if (optionalEtudiant.isEmpty()) {
            throw new RuntimeException("Étudiant avec le code " + codeEtudiant + " non trouvé !");
        }

        Etudiant etudiant = optionalEtudiant.get();

        // Mettre à jour les informations de l'étudiant
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setEmail(email);
        etudiant.setNiveauEtude(niveauEtude);

        // Mettre à jour les notes
        etudiant.getNotes().clear(); // Supprime les anciennes notes
        for (Note note : notes) {
            note.setEtudiant(etudiant); // Associe la nouvelle note à l'étudiant
        }
        etudiant.getNotes().addAll(notes);

        // Sauvegarder l'étudiant avec ses nouvelles notes
        etudiantRepository.save(etudiant);

        return 1; // Indiquer qu'une mise à jour a été effectuée
    }


    public void deleteEtudiant(Long id) {
        Etudiant etudiant = getEtudiantById(id);
        etudiantRepository.delete(etudiant);
    }

    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }

    /**
     * Recherche des étudiants par nom ou prénom.
     *
     * @param filter Le terme de recherche (nom ou prénom)
     * @return Une liste d'étudiants correspondants au filtre
     */
    public List<Etudiant> findByNomOrPrenomContainingIgnoreCase(String filter) {
        return etudiantRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(filter, filter);
    }
}
