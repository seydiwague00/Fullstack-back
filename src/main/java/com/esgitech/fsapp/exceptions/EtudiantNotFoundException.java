package com.esgitech.fsapp.exceptions;

public class EtudiantNotFoundException extends RuntimeException {
    public EtudiantNotFoundException(Long id) {
        super("Étudiant avec l'ID " + id + " non trouvé.");
    }
}
