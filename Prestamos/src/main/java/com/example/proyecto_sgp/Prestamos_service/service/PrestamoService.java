package com.example.proyecto_sgp.Prestamos_service.service;

import com.example.proyecto_sgp.Prestamos_service.entity.Prestamo;
import com.example.proyecto_sgp.Prestamos_service.entity.Recurso;
import com.example.proyecto_sgp.Prestamos_service.entity.Usuario;
import com.example.proyecto_sgp.Prestamos_service.repository.PrestamoRepository;
import com.example.proyecto_sgp.Prestamos_service.repository.UsuarioRepository;
import com.example.proyecto_sgp.inventario.ElementosDti.ElementosDti;
import com.example.proyecto_sgp.inventario.repository.InventarioRepository;
import com.example.proyecto_sgp.Prestamos_service.excepciones.ResourceNotFoundException;
import com.example.proyecto_sgp.Prestamos_service.interfaces.PrestamoInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrestamoService implements PrestamoInterface {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    // mapea un elemento Dti a un objeto Recurso
    private Recurso mapearElementoDtiARecurso(ElementosDti elementoDti) {
        Recurso recurso = new Recurso();
        recurso.setId(elementoDti.getIdentificacion());
        recurso.setNombre(elementoDti.getNombre());
        recurso.setEstado(elementoDti.getEstado().name()); // Convertir Enum a String
        return recurso;
    }

    @Override
    public void solicitarRecurso(String usuarioId, String recursoId, String ubicacion, String sede) {
        // Verificar si el usuario existe
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID " + usuarioId + " no encontrado"));

        System.out.println("Solicitud de recurso realizada por el usuario: " + usuario.getNombre());

        // Verificar si el recurso existe
        ElementosDti elementoDti = inventarioRepository.findById(recursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso con ID " + recursoId + " no encontrado"));

        // Convertir ElementosDti a Recurso
        Recurso recurso = mapearElementoDtiARecurso(elementoDti);

        // Verificar si el recurso está disponible
        if (!"Disponible".equals(recurso.getEstado())) {
            throw new RuntimeException("El recurso con ID " + recursoId + " no está disponible.");
        }

        // Crear el préstamo
        LocalDateTime now = LocalDateTime.now();
        Prestamo prestamo = new Prestamo(
                null,
                usuarioId,
                recursoId,
                ubicacion,
                sede,
                "Pendiente",
                null,
                now,
                now,
                null,
                null,
                null);

        // Guardar el préstamo
        prestamoRepository.save(prestamo);
    }

    @Override
    public void aprobarPrestamo(String prestamoId, String aprobadoPor) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new ResourceNotFoundException("Préstamo con ID " + prestamoId + " no encontrado"));

        if (!"Pendiente".equals(prestamo.getEstado())) {
            throw new RuntimeException("El préstamo no se puede aprobar, estado actual: " + prestamo.getEstado());
        }

        prestamo.setEstado("Aprobado");
        prestamo.setModificadoPor(aprobadoPor);
        prestamo.setFechaModificacion(LocalDateTime.now());

        prestamoRepository.save(prestamo);
    }

    // Desaprobar préstamo
    @Override
    public void desaprobarPrestamo(String prestamoId, String razon, String desaprobadoPor) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new ResourceNotFoundException("Préstamo con ID " + prestamoId + " no encontrado"));

        prestamo.setEstado("Desaprobado");
        prestamo.setObservaciones(razon);
        prestamo.setModificadoPor(desaprobadoPor);
        prestamo.setFechaModificacion(LocalDateTime.now());

        prestamoRepository.save(prestamo);
    }

    // Cancelar préstamo
    @Override
    public void cancelarPrestamo(String prestamoId, String canceladoPor) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new ResourceNotFoundException("Préstamo con ID " + prestamoId + " no encontrado"));

        if (!"Pendiente".equals(prestamo.getEstado())) {
            throw new RuntimeException("El préstamo no se puede cancelar, estado actual: " + prestamo.getEstado());
        }

        prestamo.setEstado("Cancelado");
        prestamo.setModificadoPor(canceladoPor);
        prestamo.setFechaModificacion(LocalDateTime.now());

        prestamoRepository.save(prestamo);
    }

    // Marcar recurso como devuelto
    @Override
    public void recursoDevuelto(String prestamoId, String recibidoPor) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new ResourceNotFoundException("Préstamo con ID " + prestamoId + " no encontrado"));

        if (!"Aprobado".equals(prestamo.getEstado())) {
            throw new RuntimeException(
                    "El recurso no se puede marcar como devuelto, estado actual: " + prestamo.getEstado());
        }

        prestamo.setEstado("Devuelto");
        prestamo.setModificadoPor(recibidoPor);
        prestamo.setFechaDevolucion(LocalDateTime.now());
        prestamo.setFechaModificacion(LocalDateTime.now());

        prestamoRepository.save(prestamo);
    }

    // Obtener todos los préstamos
    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    // Obtener un préstamo por ID
    public Prestamo obtenerPorId(String prestamoId) {
        return prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new ResourceNotFoundException("Préstamo con ID " + prestamoId + " no encontrado"));
    }

    // Eliminar un préstamo
    public void eliminarPrestamo(String prestamoId) {
        if (!prestamoRepository.existsById(prestamoId)) {
            throw new ResourceNotFoundException("Préstamo con ID " + prestamoId + " no encontrado");
        }
        prestamoRepository.deleteById(prestamoId);
    }
}
