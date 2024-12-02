package co.edu.cue.validacionUsuarios.service.Impl;

import co.edu.cue.validacionUsuarios.interfaces.AgregarUsuario;
import co.edu.cue.validacionUsuarios.interfaces.ConsultarUsuario;
import co.edu.cue.validacionUsuarios.interfaces.EditarUsuario;
import co.edu.cue.validacionUsuarios.interfaces.EliminarUsuario;
import co.edu.cue.validacionUsuarios.model.Usuario;
import co.edu.cue.validacionUsuarios.repository.UsuarioRepository;
import co.edu.cue.validacionUsuarios.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.NoSuchElementException;

@Service
public class UsuarioImpl implements UsuarioService, AgregarUsuario, EliminarUsuario, ConsultarUsuario, EditarUsuario {

    private final UsuarioRepository repository;

    private Usuario persona;

    public UsuarioImpl(UsuarioRepository repository) {this.repository = repository;}

    @PostMapping
    @Override
    public Usuario agregarUsuario(String nombre, String id, boolean condicion, String descripcion){

        persona = new Usuario(nombre, id, condicion, descripcion);

        repository.save(persona);
        return persona;
    }

    @GetMapping
    @Override
    public Boolean consultar (String id){
        persona= repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Persona con ID " + id + " no encontrado"));
        return persona.isCondicion();
    }

    @PutMapping
    @Override
    public Usuario EditarUsuario(String id, Boolean condicion, String descripcion){
        persona = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Elemento no encontrado"));

        persona.setCondicion(condicion);
        persona.setDescripcion(descripcion);

        repository.save(persona);
        return persona;
    }

    @DeleteMapping
    @Override
    public Usuario EliminarUsuario(String id){
        persona = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Elemento no encontrado"));

        repository.delete(persona);
        return persona;
    }
}
