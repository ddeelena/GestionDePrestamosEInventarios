package com.example.proyecto_sgp.inventario.Controller;

import com.example.proyecto_sgp.inventario.ElementosDti.AccesoriosMaterialDeSoporte;
import com.example.proyecto_sgp.inventario.ElementosDti.ElementosDti;
import com.example.proyecto_sgp.inventario.Requests.AccesorioRequest;
import com.example.proyecto_sgp.inventario.Service.AccesoriosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/accesorios")
public class AccesoriosController {
    private final AccesoriosService service;


    public AccesoriosController(AccesoriosService service) {
        this.service = service;
    }


    @PostMapping("/crear")
    public ResponseEntity<String> crearElemento(@RequestBody AccesoriosMaterialDeSoporte request) {
        System.out.println(request.getNombre());
        // Llama al servicio con los datos mapeados desde el cuerpo de la solicitud
        try {
            service.crearElemento(
                    request.getIdentificacion(),
                    request.getNombre(),
                    request.getDescripcion(),
                    request.getTipo(),
                    request.getEstado(),
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
            @RequestBody AccesorioRequest request) {
            System.out.println(identificacion);
        try {
            service.EditarElemento(
                    identificacion,
                    request.getNombre(),
                    request.getDescripcion(),
                    request.getEstado(),
                    request.getFechaCreacion()
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
            ElementosDti accesorio = service.VerDetalles(identificacion);
            return ResponseEntity.ok(accesorio);
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
}
