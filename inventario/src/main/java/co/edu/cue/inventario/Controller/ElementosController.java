package co.edu.cue.inventario.Controller;

import co.edu.cue.inventario.ElementosDti.ElementosDti;
import co.edu.cue.inventario.Requests.RequestElementos;
import co.edu.cue.inventario.Service.ElementosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/elementos")
public class ElementosController {
    private final ElementosService service;

    public ElementosController(ElementosService service) {
        this.service = service;
    }


    @PostMapping("/crear")
    public ResponseEntity<String> crearElemento(@Valid @RequestBody RequestElementos request) {
        // Llama al servicio con los datos mapeados desde el cuerpo de la solicitud
        try {
            service.crearElemento(
                    request.getIdentificacion(),
                    request.getNombre(),
                    request.getDescripcion(),
                    request.getTipo(),
                    request.getEstado(),
                    request.getUbicacion(),
                    request.getFechaCreacion()
            );
            //return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna una respuesta HTTP 201
            return ResponseEntity.status(HttpStatus.CREATED).body("Elemento creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el elemento");
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> ActualizarElemento(
            @PathVariable("id") String identificacion,
            @RequestBody RequestElementos request) {
        try {
            service.EditarElemento(
                    identificacion,
                    request.getNombre(),
                    request.getDescripcion(),
                    request.getEstado(),
                    request.getUbicacion()
            );
            return ResponseEntity.ok("Elemento actualizado exitosamente");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Elemento no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el elemento");
        }
    }

    @GetMapping("/detalles/{id}")
    public ResponseEntity<?> verElemento(@PathVariable("id") String identificacion) {
        try {
            ElementosDti elementosDti = service.VerDetalles(identificacion);
            return ResponseEntity.ok(elementosDti);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Elemento no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los detalles del elemento");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteElement(@PathVariable("id") String identificacion) {
        try {
            service.EliminarElemento(identificacion);
            return ResponseEntity.ok("Elemento eliminado exitosamente");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Elemento no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el elemento");
        }
    }

    @PutMapping("/cambiarEstao/{id}")
    public ResponseEntity<String> CambiarEstado(
            @PathVariable("id") String identificacion,
            @RequestBody RequestElementos request) {
        try {
            service.CambiarEstado(
                    identificacion,
                    request.getEstado(),
                    request.getUbicacion()
            );
            return ResponseEntity.ok("Se ha cambido el estado y la ubicación exitosamente");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Elemento no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el elemento");
        }
    }
}
