package com.esgitech.fsapp.execute;

import com.esgitech.fsapp.model.Etudiant;
import com.esgitech.fsapp.enums.NiveauEtude;
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


    private void StudentDatas(){
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setNom("Dupont");
        etudiant1.setPrenom("Pierre");
        etudiant1.setEmail("pierre.dupont@example.com");
        etudiant1.setCodeEtudiant("E001");
        etudiant1.setNiveauEtude(NiveauEtude.LICENCE_1);
        etudiantRepository.save(etudiant1);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setNom("Lemoine");
        etudiant2.setPrenom("Marie");
        etudiant2.setEmail("marie.lemoine@example.com");
        etudiant2.setCodeEtudiant("E002");
        etudiant2.setNiveauEtude(NiveauEtude.LICENCE_2);
        etudiantRepository.save(etudiant2);

        Etudiant etudiant3 = new Etudiant();
        etudiant3.setNom("Durand");
        etudiant3.setPrenom("Jacques");
        etudiant3.setEmail("jacques.durand@example.com");
        etudiant3.setCodeEtudiant("E003");
        etudiant3.setNiveauEtude(NiveauEtude.LICENCE_3);
        etudiantRepository.save(etudiant3);

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setNom("Martin");
        etudiant4.setPrenom("Lucie");
        etudiant4.setEmail("lucie.martin@example.com");
        etudiant4.setCodeEtudiant("E004");
        etudiant4.setNiveauEtude(NiveauEtude.MASTER_1);
        etudiantRepository.save(etudiant4);

        Etudiant etudiant5 = new Etudiant();
        etudiant5.setNom("Leclerc");
        etudiant5.setPrenom("Simon");
        etudiant5.setEmail("simon.leclerc@example.com");
        etudiant5.setCodeEtudiant("E005");
        etudiant5.setNiveauEtude(NiveauEtude.MASTER_2);
        etudiantRepository.save(etudiant5);

        Etudiant etudiant6 = new Etudiant();
        etudiant6.setNom("Lefevre");
        etudiant6.setPrenom("Sophie");
        etudiant6.setEmail("sophie.lefevre@example.com");
        etudiant6.setCodeEtudiant("E006");
        etudiant6.setNiveauEtude(NiveauEtude.MASTER_1);
        etudiantRepository.save(etudiant6);

        Etudiant etudiant7 = new Etudiant();
        etudiant7.setNom("Pires");
        etudiant7.setPrenom("Carlos");
        etudiant7.setEmail("carlos.pires@example.com");
        etudiant7.setCodeEtudiant("E007");
        etudiant7.setNiveauEtude(NiveauEtude.LICENCE_2);
        etudiantRepository.save(etudiant7);

        Etudiant etudiant8 = new Etudiant();
        etudiant8.setNom("Rossi");
        etudiant8.setPrenom("Elena");
        etudiant8.setEmail("elena.rossi@example.com");
        etudiant8.setCodeEtudiant("E008");
        etudiant8.setNiveauEtude(NiveauEtude.LICENCE_1);
        etudiantRepository.save(etudiant8);

        Etudiant etudiant9 = new Etudiant();
        etudiant9.setNom("Dubois");
        etudiant9.setPrenom("François");
        etudiant9.setEmail("francois.dubois@example.com");
        etudiant9.setCodeEtudiant("E009");
        etudiant9.setNiveauEtude(NiveauEtude.DOCTORAL);
        etudiantRepository.save(etudiant9);

        Etudiant etudiant10 = new Etudiant();
        etudiant10.setNom("Girard");
        etudiant10.setPrenom("Chloé");
        etudiant10.setEmail("chloe.girard@example.com");
        etudiant10.setCodeEtudiant("E010");
        etudiant10.setNiveauEtude(NiveauEtude.MASTER_2);
        etudiantRepository.save(etudiant10);
    }

}
