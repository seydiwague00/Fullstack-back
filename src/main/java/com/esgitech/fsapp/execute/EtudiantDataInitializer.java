package com.esgitech.fsapp.execute;

import com.esgitech.fsapp.enums.NiveauEtude;
import com.esgitech.fsapp.model.Etudiant;
import com.esgitech.fsapp.model.Note;
import com.esgitech.fsapp.repository.EtudiantRepository;
import com.esgitech.fsapp.repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class EtudiantDataInitializer implements CommandLineRunner {

    private final EtudiantRepository etudiantRepository;
    private final NoteRepository noteRepository;

    public EtudiantDataInitializer(EtudiantRepository etudiantRepository, NoteRepository noteRepository) {
        this.etudiantRepository = etudiantRepository;
        this.noteRepository = noteRepository;
    }

    @Override
    public void run(String... args) {
        datas();
    }

    private void datas() {
        List<Etudiant> etudiants = Arrays.asList(
                new Etudiant("Dupont", "Pierre", "pierre.dupont@example.com", "E001", NiveauEtude.LICENCE_1, null),
                new Etudiant("Lemoine", "Marie", "marie.lemoine@example.com", "E002", NiveauEtude.LICENCE_2, null),
                new Etudiant("Durand", "Jacques", "jacques.durand@example.com", "E003", NiveauEtude.LICENCE_3, null),
                new Etudiant("Martin", "Lucie", "lucie.martin@example.com", "E004", NiveauEtude.MASTER_1, null),
                new Etudiant("Leclerc", "Simon", "simon.leclerc@example.com", "E005", NiveauEtude.MASTER_2, null),
                new Etudiant("Lefevre", "Sophie", "sophie.lefevre@example.com", "E006", NiveauEtude.MASTER_1, null),
                new Etudiant("Pires", "Carlos", "carlos.pires@example.com", "E007", NiveauEtude.LICENCE_2, null),
                new Etudiant("Rossi", "Elena", "elena.rossi@example.com", "E008", NiveauEtude.LICENCE_1, null),
                new Etudiant("Dubois", "François", "francois.dubois@example.com", "E009", NiveauEtude.DOCTORAL, null),
                new Etudiant("Girard", "Chloé", "chloe.girard@example.com", "E010", NiveauEtude.MASTER_2, null)
        );

        // Sauvegarde des étudiants
        etudiants = etudiantRepository.saveAll(etudiants);

        // Ajout des notes pour chaque étudiant
        List<Note> notes = Arrays.asList(
                new Note(null, "Mathématiques", 15.5, etudiants.get(0)),
                new Note(null, "Physique", 12.0, etudiants.get(0)),
                new Note(null, "Chimie", 14.0, etudiants.get(1)),
                new Note(null, "Informatique", 18.0, etudiants.get(2)),
                new Note(null, "Anglais", 16.5, etudiants.get(3)),
                new Note(null, "Économie", 11.0, etudiants.get(4)),
                new Note(null, "Droit", 14.5, etudiants.get(5)),
                new Note(null, "Gestion", 17.0, etudiants.get(6)),
                new Note(null, "Philosophie", 13.0, etudiants.get(7)),
                new Note(null, "Science politique", 15.0, etudiants.get(8))
        );

        noteRepository.saveAll(notes);

        System.out.println("Données initialisées avec succès !");
    }
}
