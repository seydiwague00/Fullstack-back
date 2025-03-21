package com.esgitech.fsapp.controllers;

import com.esgitech.fsapp.enums.NiveauEtude;
import com.esgitech.fsapp.model.Etudiant;
import com.esgitech.fsapp.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.NestingKind;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
    public ResponseEntity<Map<String, String>> updateEtudiantByCodeEtudiant(@RequestBody Etudiant etudiantDetails) {
        Map<String, String> response = new HashMap<>();

        try {
            String code = etudiantDetails.getCodeEtudiant();
            String nom = etudiantDetails.getNom();
            String prenom = etudiantDetails.getPrenom();
            String email = etudiantDetails.getEmail();
            NiveauEtude niveauEtude = etudiantDetails.getNiveauEtude();

            System.out.println(etudiantDetails);

            int rowsUpdated = etudiantService.updateEtudiantByCodeEtudiant(code, nom, prenom, email, niveauEtude);


            if (rowsUpdated == 0) {
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }

            response.put("message", "Étudiant mis à jour avec succès.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Map<String, String>> deleteEtudiant(@PathVariable String code) {
        Map<String, String> response = new HashMap<>();
        try {
            Etudiant optionalEtudiant = etudiantService.findByCodeEtudiant(code);
            if (optionalEtudiant == null) {
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            etudiantService.deleteEtudiant(optionalEtudiant.getId());
            response.put("Message", "Étudiant supprimé avec succès.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filteredStudents")
    public List<Etudiant> getFilteredEtudiants(@RequestParam(value = "filter", required = false) String filter) {
        if (filter == null || filter.isEmpty()) {
            return etudiantService.findAll();
        } else {
            return etudiantService.findByNomOrPrenomContainingIgnoreCase(filter);
        }
    }


}
