package co.edu.cue.inventario.ElementosDti;

import co.edu.cue.inventario.Enums.EstadosElementos;
import co.edu.cue.inventario.Enums.TipoDeElementos;
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
