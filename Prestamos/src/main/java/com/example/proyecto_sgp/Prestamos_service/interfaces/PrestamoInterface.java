package com.example.proyecto_sgp.Prestamos_service.interfaces;

public interface PrestamoInterface {
    void solicitarRecurso(Long usuarioId, Long recursoId, String ubicacion, String sede);
    void aprobarPrestamo(Long prestamoId, String aprobadoPor);
    void desaprobarPrestamo(Long prestamoId, String razon, String desaprobadoPor);
    void cancelarPrestamo(Long prestamoId, String canceladoPor);
    void recursoDevuelto(Long prestamoId, String recibidoPor);
}
