package com.example.modulo_prestamos.service;

import com.example.modulo_prestamos.entity.Prestamo;
import com.example.modulo_prestamos.excepciones.ObservacionesInvalidasException;
import com.example.modulo_prestamos.excepciones.PrestamoInvalidoException;
import com.example.modulo_prestamos.excepciones.RecursoNoDisponibleException;
import com.example.modulo_prestamos.inventario_service.ConexionInventarioInterface;
import com.example.modulo_prestamos.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PrestamoValidationService {
    private final ConexionInventarioInterface inventario;

    public PrestamoValidationService(ConexionInventarioInterface inventario) {
        this.inventario = inventario;
    }

    public void validarPrestamo(Prestamo prestamo) {
        if (prestamo == null) {
            throw new PrestamoInvalidoException("Préstamo no puede ser nulo");
        }

        if (prestamo.getUsuarioId() == null) {
            throw new PrestamoInvalidoException("ID de usuario es obligatorio");
        }

        if (prestamo.getRecursoId() == null) {
            throw new PrestamoInvalidoException("ID de recurso es obligatorio");
        }

        if (prestamo.getUbicacion() == null) {
            throw new PrestamoInvalidoException("Ubicación es obligatoria");
        }

        ApiResponse response = inventario.validarDisponibilidad(prestamo.getRecursoId());
        if (response.getStatus() == HttpStatus.NOT_FOUND) {
            throw new RecursoNoDisponibleException("Recurso no disponible para préstamo");
        }
    }

    public void validarObservaciones(String observaciones, String prestamoId) {
        if (observaciones == null || observaciones.isEmpty()) {
            throw new ObservacionesInvalidasException("Agregue las observaciones");
        }
        if (prestamoId == null || prestamoId.isEmpty()) {
            throw new ObservacionesInvalidasException("Agregue el id del prestamo");
        }

    }
}