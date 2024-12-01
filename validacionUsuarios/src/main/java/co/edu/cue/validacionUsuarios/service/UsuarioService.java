package co.edu.cue.validacionUsuarios.service;

public interface UsuarioService {
    void agregarUsuario(String nombre, String id, boolean condicion, String descripcion);
    Boolean consultar (String id);
    void EditarUsuario(String id, Boolean condicion, String descripcion);
    void EliminarUsuario(String id);
}
