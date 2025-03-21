package com.esgitech.fsapp.controllers;

import com.esgitech.fsapp.enums.NiveauEtude;
import com.esgitech.fsapp.model.Etudiant;
import com.esgitech.fsapp.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        return ResponseEntity.ok(etudiantService.getEtudiantById(id));
    }

    @PostMapping
    public Etudiant createEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.createEtudiant(etudiant);
    }

    @PostMapping("/add-students")
    public List<Etudiant> addEtudiant(@RequestBody List<Etudiant> etudiants) {
        return etudiantService.addEtudiants(etudiants);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiantDetails) {
        return ResponseEntity.ok(etudiantService.updateEtudiant(id, etudiantDetails));
    }

    @PutMapping("/updateByCodeEtudiant")
    public ResponseEntity<String> updateEtudiantByCodeEtudiant(@RequestBody Etudiant etudiantDetails) {
        try {
            String code = etudiantDetails.getCodeEtudiant();
            String nom = etudiantDetails.getNom();
            String prenom = etudiantDetails.getPrenom();
            String email = etudiantDetails.getEmail();
            NiveauEtude niveauEtude = etudiantDetails.getNiveauEtude();

            // Afficher les données reçues dans les logs (optionnel)
            System.out.println(etudiantDetails);

            // Appel du service pour mettre à jour l'étudiant
            int rowsUpdated = etudiantService.updateEtudiantByCodeEtudiant(code, nom, prenom, email, niveauEtude);

            if (rowsUpdated == 0) {
                // Si aucune ligne n'a été mise à jour, l'étudiant n'a pas été trouvé
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Étudiant non trouvé avec le code : " + code);
            }

            // Retourner une réponse de succès
            return ResponseEntity.ok("Étudiant mis à jour avec succès.");
        } catch (Exception e) {
            // Gestion d'autres exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour de l'étudiant : " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
        return ResponseEntity.noContent().build();
    }
}
