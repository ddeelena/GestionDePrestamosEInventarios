package co.edu.cue.inventario.ElementosDti;

import co.edu.cue.inventario.Enums.EstadosElementos;
import co.edu.cue.inventario.Enums.TipoDeElementos;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "controles")
public class Controles extends ElementosDti{

    public Controles(String identificacion, String nombre, String descripcion, TipoDeElementos tipo,
                     EstadosElementos estado,String ubicacion, LocalDate fechaCreacion) {
        super(identificacion, nombre, descripcion, tipo, estado, ubicacion, fechaCreacion);
    }

    @Override
    public TipoDeElementos getTipo() {
        return null;
    }


}
