package com.example.proyecto_sgp.Prestamos_service.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DevolucionDTO {
    private Long id;
    private List<Long> recursosIds;
    private Long usuarioId;
    private String ubicacion;
    private String observaciones;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
}
