package co.edu.cue.validacionUsuarios.interfaces;

import co.edu.cue.validacionUsuarios.model.Usuario;

public interface EditarUsuario {
    Usuario EditarUsuario(String id, Boolean condicion, String descripcion);
}