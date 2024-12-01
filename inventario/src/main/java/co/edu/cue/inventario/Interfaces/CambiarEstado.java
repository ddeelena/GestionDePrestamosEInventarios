package co.edu.cue.inventario.Interfaces;


import co.edu.cue.inventario.Enums.EstadosElementos;

public interface CambiarEstado {
    void CambiarEstado(String identificacion, EstadosElementos estado, String ubicacion);

}
