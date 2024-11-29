package com.example.proyecto_sgp.Prestamos_service.service;

import com.example.proyecto_sgp.Prestamos_service.entity.Prestamo;
import com.example.proyecto_sgp.Prestamos_service.entity.Usuario;
import com.example.proyecto_sgp.Prestamos_service.repository.PrestamoRepository;
import com.example.proyecto_sgp.Prestamos_service.repository.UsuarioRepository;
import com.example.proyecto_sgp.Prestamos_service.interfaces.PrestamoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class PrestamoService implements PrestamoInterface {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void solicitarRecurso(Long usuarioId, Long recursoId, String ubicacion, String sede) {
        Usuario usuario = usuarioRepository.findById(usuarioId.toString())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
        // Crear el préstamo
        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamo.setRecursoId(recursoId.toString());
        prestamo.setUbicacion(ubicacion);
        prestamo.setSede(sede);
        prestamo.setEstado("Pendiente");
    
        // Guardar el préstamo
        prestamoRepository.save(prestamo);
    }

    @Override
    public void aprobarPrestamo(Long prestamoId, String aprobadoPor) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId.toString())
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        prestamo.setEstado("Aprobado");
        prestamo.setModificadoPor(aprobadoPor);

        prestamoRepository.save(prestamo);
    }

    @Override
    public void desaprobarPrestamo(Long prestamoId, String razon, String desaprobadoPor) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId.toString())
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        prestamo.setEstado("Desaprobado");
        prestamo.setObservaciones(razon);
        prestamo.setModificadoPor(desaprobadoPor);

        prestamoRepository.save(prestamo);
    }

    @Override
    public void cancelarPrestamo(Long prestamoId, String canceladoPor) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId.toString())
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        if ("Pendiente".equals(prestamo.getEstado())) {
            prestamo.setEstado("Cancelado");
            prestamo.setModificadoPor(canceladoPor);

            prestamoRepository.save(prestamo);
        } else {
            throw new RuntimeException("El préstamo no puede ser cancelado");
        }
    }

    @Override
    public void recursoDevuelto(Long prestamoId, String recibidoPor) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId.toString())
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));
    
        prestamo.setEstado("Devuelto");
        prestamo.setModificadoPor(recibidoPor);
        
        LocalDateTime fechaDevolucion = new java.util.Date().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        
        prestamo.setFechaDevolucion(fechaDevolucion);
    
        prestamoRepository.save(prestamo);
    }    

    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    public Prestamo obtenerPorId(String id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));
    }

    public void eliminarPrestamo(String id) {
        prestamoRepository.deleteById(id);
    }

    public Prestamo crearPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }
}
