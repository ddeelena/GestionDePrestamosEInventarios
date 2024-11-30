package com.example.proyecto_sgp.inventario.Service;

import com.example.proyecto_sgp.inventario.ElementosDti.ElementosDti;
import com.example.proyecto_sgp.inventario.Enums.EstadosElementos;
import com.example.proyecto_sgp.inventario.Enums.TipoDeElementos;

import java.time.LocalDate;

public interface AccesoriosService {


    void crearElemento(String identificacion, String nombre, String descripcion, TipoDeElementos tipo, EstadosElementos estado, LocalDate fechaCreacion);

    ElementosDti VerDetalles(String identificacion);

    void EditarElemento(String identificacion, String nombre, String descripcion, EstadosElementos estado, LocalDate fechaCreacion);

    void EliminarElemento(String identificacion);
}
