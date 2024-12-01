package com.example.proyecto_sgp.inventario.Enums;

public enum EstadosElementos {
    DISPONIBLE("Disponible"),
    EN_PRESTAMO("En Préstamo"),
    FUERA_DE_SERVICIO("Fuera de Servicio");

    private final String estado;

    EstadosElementos(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return estado;
    }
}