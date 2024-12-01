package co.edu.cue.validacionUsuarios.controller;

import co.edu.cue.validacionUsuarios.model.Usuario;
import co.edu.cue.validacionUsuarios.request.UsuarioRequest;
import co.edu.cue.validacionUsuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarUsuario(@Valid @RequestBody UsuarioRequest request){
        try{
            service.agregarUsuario(
                    request.getNombre(),
                    request.getId(),
                    request.isCondicion(),
                    request.getDescripcion()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario");
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> ActualizarElemento(
            @PathVariable("id") String identificacion,
            @RequestBody UsuarioRequest request) {
        try {
            service.EditarUsuario(
                    identificacion,
                    request.isCondicion(),
                    request.getDescripcion()
            );
            return ResponseEntity.ok("Usuario actualizado exitosamente");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el usuario");
        }
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> consultarUsuario(@PathVariable("id") String identificacion) {
        try {
            Boolean esta = service.consultar(identificacion);
            return ResponseEntity.ok(esta);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los detalles del usuario");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteElement(@PathVariable("id") String identificacion) {
        try {
            service.EliminarUsuario(identificacion);
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario");
        }
    }
}
