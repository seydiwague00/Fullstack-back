package com.esgitech.fsapp.services;

import com.esgitech.fsapp.model.Etudiant;
import com.esgitech.fsapp.model.Note;
import com.esgitech.fsapp.repository.EtudiantRepository;
import com.esgitech.fsapp.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final EtudiantRepository etudiantRepository;

    public NoteService(NoteRepository noteRepository, EtudiantRepository etudiantRepository) {
        this.noteRepository = noteRepository;
        this.etudiantRepository = etudiantRepository;
    }

    // Récupérer toutes les notes
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Récupérer une note par ID
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    // Récupérer toutes les notes d'un étudiant par code étudiant
    public List<Note> getNotesByEtudiantCode(String codeEtudiant) {
        return noteRepository.findByEtudiantCode(codeEtudiant);
    }

    // Ajouter une nouvelle note
    public Note addNote(Note note, String codeEtudiant) {

        Etudiant etudiant;
        try {
            etudiant = etudiantRepository.findByCodeEtudiant(codeEtudiant);

        } catch (RuntimeException e) {
            throw new RuntimeException("Etudiant " + codeEtudiant + " non trouvé");
        }
        note.setEtudiant(etudiant);
        return noteRepository.save(note);
    }

    // Mettre à jour une note
    public Note updateNote(Long id, Note noteDetails) {
        return noteRepository.findById(id).map(note -> {
            note.setMatiere(noteDetails.getMatiere());
            note.setValeur(noteDetails.getValeur());
            return noteRepository.save(note);
        }).orElseThrow(() -> new RuntimeException("Note non trouvée"));
    }

    // Supprimer une note
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }
}
