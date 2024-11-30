package com.example.proyecto_sgp.inventario.ElementosDti;

import com.example.proyecto_sgp.inventario.Enums.EstadosElementos;
import com.example.proyecto_sgp.inventario.Enums.TipoDeElementos;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "accesorio")
public class AccesoriosMaterialDeSoporte extends ElementosDti {

    public AccesoriosMaterialDeSoporte(String identificacion, String nombre, String descripcion, TipoDeElementos tipo, EstadosElementos estado, LocalDate fechaCreacion) {
        super(identificacion, nombre, descripcion, tipo, estado, fechaCreacion);
    }

    @Override
    public TipoDeElementos getTipo() {
        return TipoDeElementos.Accesorio;
    }



}
