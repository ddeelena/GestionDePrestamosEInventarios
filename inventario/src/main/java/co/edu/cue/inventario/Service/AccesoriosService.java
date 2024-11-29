package co.edu.cue.inventario.Service;

import co.edu.cue.inventario.ElementosDti.ElementosDti;
import co.edu.cue.inventario.Enums.EstadosElementos;
import co.edu.cue.inventario.Enums.TipoDeElementos;

import javax.swing.text.Element;
import java.time.LocalDate;



public interface AccesoriosService {


    void crearElemento(String identificacion, String nombre, String descripcion, TipoDeElementos tipo, EstadosElementos estado, LocalDate fechaCreacion);

    ElementosDti VerDetalles(String identificacion);

    void EditarElemento(String identificacion, String nombre, String descripcion, EstadosElementos estado, LocalDate fechaCreacion);

    void EliminarElemento(String identificacion);
}
