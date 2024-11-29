package com.example.proyecto_sgp.Prestamos_service.controller;

import com.example.proyecto_sgp.Prestamos_service.entity.Devolucion;
import com.example.proyecto_sgp.Prestamos_service.service.DevolucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devoluciones")
public class DevolucionController {

    @Autowired
    private DevolucionService devolucionService;

    @GetMapping
    public List<Devolucion> obtenerTodas() {
        return devolucionService.obtenerTodas();
    }

    @PostMapping
    public Devolucion registrarDevolucion(@RequestBody Devolucion devolucion) {
        return devolucionService.registrarDevolucion(devolucion);
    }

    @GetMapping("/{id}")
    public Devolucion obtenerPorId(@PathVariable String id) {
        return devolucionService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarDevolucion(@PathVariable String id) {
        devolucionService.eliminarDevolucion(id);
    }
}
