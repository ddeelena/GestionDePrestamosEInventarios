package co.edu.cue.inventario.Interfaces;

import co.edu.cue.inventario.Enums.EstadosElementos;

public interface EditarElementos {
    void EditarElemento(String identificacion, String nombre, String descripcion, EstadosElementos estado, String ubicacion);

}
