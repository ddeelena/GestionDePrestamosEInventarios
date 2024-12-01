package com.example.proyecto_sgp.Prestamos_service.excepciones;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}