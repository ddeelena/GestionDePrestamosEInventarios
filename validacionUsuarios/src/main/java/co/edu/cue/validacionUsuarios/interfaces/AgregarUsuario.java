package co.edu.cue.validacionUsuarios.interfaces;

import co.edu.cue.validacionUsuarios.model.Usuario;

public interface AgregarUsuario {
    Usuario agregarUsuario(String nombre, String id, boolean condicion, String descripcion);
}
