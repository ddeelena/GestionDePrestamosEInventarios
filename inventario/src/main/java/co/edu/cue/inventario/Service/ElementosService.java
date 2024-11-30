package co.edu.cue.inventario.Service;

import co.edu.cue.inventario.ElementosDti.ElementosDti;
import co.edu.cue.inventario.Enums.EstadosElementos;
import co.edu.cue.inventario.Enums.TipoDeElementos;

import java.time.LocalDate;

public interface ElementosService {
    void crearElemento(String identificacion, String nombre, String descripcion, TipoDeElementos tipo, EstadosElementos estado, String ubicacion, LocalDate fechaCreacion);

    ElementosDti VerDetalles(String identificacion);

    void EditarElemento(String identificacion, String nombre, String descripcion, EstadosElementos estado, String ubicacion);

    void EliminarElemento(String identificacion);

    void CambiarEstado(String identificacion, EstadosElementos estado, String ubicacion);
}
