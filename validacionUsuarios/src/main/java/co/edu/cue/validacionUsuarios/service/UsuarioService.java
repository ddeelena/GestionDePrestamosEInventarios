package co.edu.cue.validacionUsuarios.service;

import co.edu.cue.validacionUsuarios.model.Usuario;

public interface UsuarioService {
    Usuario agregarUsuario(String nombre, String id, boolean condicion, String descripcion);
    Boolean consultar (String id);
    Usuario EditarUsuario(String id, Boolean condicion, String descripcion);
    Usuario EliminarUsuario(String id);
}
