package co.edu.cue.validacionUsuarios.controller;

import co.edu.cue.validacionUsuarios.model.Usuario;
import co.edu.cue.validacionUsuarios.request.RespuestaApi;
import co.edu.cue.validacionUsuarios.request.UsuarioRequest;
import co.edu.cue.validacionUsuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RespuestaApi<Usuario>> agregarUsuario(@Valid @RequestBody UsuarioRequest request){
        try{
           Usuario usuario = service.agregarUsuario(
                    request.getNombre(),
                    request.getId(),
                    request.isCondicion(),
                    request.getDescripcion()
                    );
           RespuestaApi<Usuario> response = new RespuestaApi<>(
                    "OK",
                    "Elemento creado exitosamente",
                    usuario
           );
           return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            RespuestaApi<Usuario> errorResponse = new RespuestaApi<>(
                    "ERROR",
                    "Error al crear el usuario: " + e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<RespuestaApi<Usuario>> ActualizarElemento(
            @PathVariable("id") String identificacion,
            @RequestBody UsuarioRequest request) {
        try {
            Usuario usuario = service.EditarUsuario(
                    identificacion,
                    request.isCondicion(),
                    request.getDescripcion()
            );
            RespuestaApi<Usuario> response = new RespuestaApi<>(
                    "OK",
                    "Elemento actualizado exitosamente",
                    usuario // El objeto creado se pasa directamente como `data`
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }  catch (NoSuchElementException e) {
            // Captura la excepción cuando no se encuentra el elemento
            RespuestaApi<Usuario> errorResponse = new RespuestaApi<>(
                    "ERROR",
                    "Usuario no encontrado",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            // Captura cualquier otro tipo de error general
            RespuestaApi<Usuario> errorResponse = new RespuestaApi<>(
                    "ERROR",
                    "Error al actualizar el usuario: " + e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<RespuestaApi<Boolean>> consultarUsuario(@PathVariable("id") String identificacion) {
        try {
            Boolean esta = service.consultar(identificacion);
            if (esta){
                RespuestaApi<Boolean> response = new RespuestaApi<>(
                        "OK",
                        "El usuario "+identificacion+" esta en estado activo ",
                        esta // El objeto creado se pasa directamente como `data`
                );
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                RespuestaApi<Boolean> response = new RespuestaApi<>(
                        "OK",
                        "El usuario "+identificacion+" tiene  estado inactivo",
                        esta // El objeto creado se pasa directamente como `data`
                );
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        } catch (NoSuchElementException e) {
            // Captura la excepción cuando no se encuentra el elemento
            RespuestaApi<Boolean> errorResponse = new RespuestaApi<>(
                    "ERROR",
                    "Usuario no encontrado",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            // Captura cualquier otro tipo de error general
            RespuestaApi<Boolean> errorResponse = new RespuestaApi<>(
                    "ERROR",
                    "Error al buscar al usuario: " + e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<RespuestaApi<Usuario>> deleteElement(@PathVariable("id") String identificacion) {
        try {
            Usuario usuario = service.EliminarUsuario(identificacion);
            RespuestaApi<Usuario> response = new RespuestaApi<>(
                    "OK",
                    "Usuario con id "+identificacion+" eliminado exitosamente",
                    usuario // El objeto creado se pasa directamente como `data`
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (NoSuchElementException e) {
            // Captura la excepción cuando no se encuentra el elemento
            RespuestaApi<Usuario> errorResponse = new RespuestaApi<>(
                    "ERROR",
                    "Usuario no encontrado",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            // Captura cualquier otro tipo de error general
            RespuestaApi<Usuario> errorResponse = new RespuestaApi<>(
                    "ERROR",
                    "Error al eliminar el usuario: " + e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
