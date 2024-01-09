package com.elitsoft.proyectoCuestionario_backend.exceptions;

public class MissingJwtException extends RuntimeException {
    public MissingJwtException(String message) {
        super(message);
    }
}