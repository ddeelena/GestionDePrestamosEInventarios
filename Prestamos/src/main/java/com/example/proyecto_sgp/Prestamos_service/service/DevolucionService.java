package com.example.proyecto_sgp.Prestamos_service.service;

import com.example.proyecto_sgp.Prestamos_service.entity.Devolucion;
import com.example.proyecto_sgp.Prestamos_service.repository.DevolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevolucionService {

    @Autowired
    private DevolucionRepository devolucionRepository;

    public List<Devolucion> obtenerTodas() {
        return devolucionRepository.findAll();
    }

    public Devolucion registrarDevolucion(Devolucion devolucion) {
        return devolucionRepository.save(devolucion);
    }

    public Devolucion obtenerPorId(String id) {
        return devolucionRepository.findById(id).orElseThrow(() -> new RuntimeException("Devolución no encontrada"));
    }

    public void eliminarDevolucion(String id) {
        devolucionRepository.deleteById(id);
    }
}
