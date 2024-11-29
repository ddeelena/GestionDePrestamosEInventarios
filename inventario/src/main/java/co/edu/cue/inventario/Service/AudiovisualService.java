package co.edu.cue.inventario.Service;

import co.edu.cue.inventario.Enums.EstadosElementos;
import co.edu.cue.inventario.Enums.TipoDeElementos;

import java.time.LocalDate;

public interface AudiovisualService {
    void crearElemento(String identificacion, String nombre, String descripcion, TipoDeElementos tipo, EstadosElementos estado, LocalDate fechaCreacion);

    String VerDetalles();

    void EditarElemento(String identificacion, String nombre, String descripcion, TipoDeElementos tipo, EstadosElementos estado, LocalDate fechaCreacion);

    void EliminarElemento();
}
