package com.example.proyecto_sgp.Prestamos_service.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PrestamoDTO {
    private Long id;
    private Long usuarioId;
    private List<Long> recursosIds;
    private String ubicacion;
    private String sede;
    private String estado;
    private LocalDate fechaDevolucion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;   
}
