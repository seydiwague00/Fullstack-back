package com.esgitech.fsapp.repository;

import com.esgitech.fsapp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM Note n WHERE n.etudiant.codeEtudiant = :codeEtudiant")
    List<Note> findByEtudiantCode(@Param("codeEtudiant") String codeEtudiant);
}
