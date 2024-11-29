package co.edu.cue.inventario.ElementosDti;

import co.edu.cue.inventario.Enums.EstadosElementos;
import co.edu.cue.inventario.Enums.TipoDeElementos;

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
