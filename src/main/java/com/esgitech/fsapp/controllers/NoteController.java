package com.esgitech.fsapp.controllers;

import com.esgitech.fsapp.model.Note;
import com.esgitech.fsapp.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // 1. Récupérer toutes les notes
    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    // 2. Récupérer une note par ID
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Optional<Note> note = noteService.getNoteById(id);
        return note.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. Récupérer les notes par code étudiant
    @GetMapping("/etudiant/{codeEtudiant}")
    public List<Note> getNotesByEtudiantCode(@PathVariable String codeEtudiant) {
        return noteService.getNotesByEtudiantCode(codeEtudiant);
    }

    // 4. Ajouter une note
    @PostMapping("/etudiant/{codeEtudiant}")
    public ResponseEntity<Note> addNote(@RequestBody Note note, @PathVariable String codeEtudiant) {
        Note savedNote = noteService.addNote(note, codeEtudiant);
        return ResponseEntity.ok(savedNote);
    }

    // 5. Mettre à jour une note
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note noteDetails) {
        try {
            Note updatedNote = noteService.updateNote(id, noteDetails);
            return ResponseEntity.ok(updatedNote);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 6. Supprimer une note
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id);
        return ResponseEntity.noContent().build();
    }
}
