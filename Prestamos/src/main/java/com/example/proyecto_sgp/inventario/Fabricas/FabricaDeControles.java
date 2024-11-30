package com.example.proyecto_sgp.inventario.Fabricas;

import com.example.proyecto_sgp.inventario.ElementosDti.Controles;
import com.example.proyecto_sgp.inventario.ElementosDti.ElementosDti;
import com.example.proyecto_sgp.inventario.Enums.EstadosElementos;
import com.example.proyecto_sgp.inventario.Enums.TipoDeElementos;

import java.time.LocalDate;

public class FabricaDeControles implements ElementosDtiFabrica{
    @Override
    public ElementosDti crearElementoDti(String identificacion, String nombre, String descripcion, TipoDeElementos tipo, EstadosElementos estado, LocalDate fechaCreacion) {
        return new Controles(identificacion, nombre, descripcion, tipo, estado, fechaCreacion);
    }
}
