package com.example.proyecto_sgp.Prestamos_service.controller;

import com.example.proyecto_sgp.Prestamos_service.entity.Prestamo;
import com.example.proyecto_sgp.Prestamos_service.service.PrestamoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @GetMapping
    public List<Prestamo> obtenerTodos() {
        return prestamoService.obtenerTodos();
    }

    @PostMapping
    public void crearPrestamo(@RequestBody Prestamo prestamo) {
        prestamoService.solicitarRecurso(prestamo.getUsuarioId(), prestamo.getRecursoId(), prestamo.getUbicacion(), prestamo.getSede());
    }

    @GetMapping("/{id}")
    public Prestamo obtenerPorId(@PathVariable String id) {
        return prestamoService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarPrestamo(@PathVariable String id) {
        prestamoService.eliminarPrestamo(id);
    }
}