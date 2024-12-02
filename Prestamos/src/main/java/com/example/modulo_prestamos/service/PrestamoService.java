package com.example.modulo_prestamos.service;

import com.example.modulo_prestamos.entity.Prestamo;
import com.example.modulo_prestamos.entity.enums.Estado;
import com.example.modulo_prestamos.excepciones.PrestamoInvalidoException;
import com.example.modulo_prestamos.interfaces.PrestamoBuilder;
import com.example.modulo_prestamos.interfaces.crud.*;
import com.example.modulo_prestamos.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService implements CrearObservaciones, Crear, Eliminar, Modificar, Consultar, ListarPrestamos {

    private final PrestamoRepository prestamoRepository;
    private final PrestamoBuilder builder;
    private final PrestamoValidationService validationService;

    @Autowired
    public PrestamoService(
            PrestamoRepository prestamoRepository,
            PrestamoBuilder builder,
            PrestamoValidationService validationService
    ) {
        this.prestamoRepository = prestamoRepository;
        this.builder = builder;
        this.validationService = validationService;
    }

    @Override
    public Optional<Prestamo> consultarPrestamo(String id) {
        return prestamoRepository.findById(id);
    }

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo crearPrestamo(Prestamo prestamo) {
        // Valida antes de crear
        validationService.validarPrestamo(prestamo);

        Prestamo prestamoNuevo = builder.setUsuarioId(prestamo.getUsuarioId())
                .setRecursoId(prestamo.getRecursoId())
                .setUbicacion(prestamo.getUbicacion())
                .setSede(prestamo.getSede())
                .setEstado(Estado.ACTIVO)
                .setFechaCreacion(LocalDateTime.now())
                .build();

        return prestamoRepository.save(prestamoNuevo);
    }

    @Override
    public Prestamo modificarPrestamo(String id, Prestamo prestamo) {
        // Validar que el préstamo existe
        Prestamo prestamoConsultado = consultarPrestamo(id)
                .orElseThrow(() -> new PrestamoInvalidoException("Préstamo no encontrado"));

        // Validar los datos del préstamo
        validationService.validarPrestamo(prestamo);

        prestamoConsultado.setUsuarioId(prestamo.getUsuarioId());
        prestamoConsultado.setRecursoId(prestamo.getRecursoId());
        prestamoConsultado.setUbicacion(prestamo.getUbicacion());
        prestamoConsultado.setSede(prestamo.getSede());
        prestamoConsultado.setEstado(prestamo.getEstado());
        prestamoConsultado.setFechaModificacion(LocalDateTime.now());

        return prestamoRepository.save(prestamoConsultado);
    }

    @Override
    public void eliminarPrestamo(String id) {
        Prestamo prestamo = consultarPrestamo(id)
                .orElseThrow(() -> new PrestamoInvalidoException("Préstamo no encontrado"));

        prestamo.setEstado(Estado.INACTIVO);
        prestamo.setFechaEliminacion(LocalDateTime.now());

        prestamoRepository.save(prestamo);
    }

    @Override
    public Prestamo agregarObservaciones(String observaciones, String prestamoId) {
        validationService.validarObservaciones(observaciones, prestamoId);
        Prestamo prestamo = consultarPrestamo(prestamoId).get();
        prestamo.setObservaciones(observaciones);
        return prestamoRepository.save(prestamo);
    }
}