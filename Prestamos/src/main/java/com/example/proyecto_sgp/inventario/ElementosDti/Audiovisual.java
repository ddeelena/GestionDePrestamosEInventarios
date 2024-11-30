package com.example.proyecto_sgp.inventario.ElementosDti;

import com.example.proyecto_sgp.inventario.Enums.EstadosElementos;
import com.example.proyecto_sgp.inventario.Enums.TipoDeElementos;

import java.time.LocalDate;

public class Audiovisual extends ElementosDti{

    public Audiovisual(String identificacion, String nombre, String descripcion, TipoDeElementos tipo, EstadosElementos estado, LocalDate fechaCreacion) {
        super(identificacion, nombre, descripcion, tipo, estado, fechaCreacion);
    }

    @Override
    public TipoDeElementos getTipo() {
        return null;
    }

}
