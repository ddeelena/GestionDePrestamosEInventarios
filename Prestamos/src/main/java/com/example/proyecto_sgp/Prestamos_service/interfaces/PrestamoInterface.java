package com.example.proyecto_sgp.Prestamos_service.interfaces;

public interface PrestamoInterface {
    void solicitarRecurso(String usuarioId, String recursoId, String ubicacion, String sede);
    void aprobarPrestamo(String prestamoId, String aprobadoPor);
    void desaprobarPrestamo(String prestamoId, String razon, String desaprobadoPor);
    void cancelarPrestamo(String prestamoId, String canceladoPor);
    void recursoDevuelto(String prestamoId, String recibidoPor);
}