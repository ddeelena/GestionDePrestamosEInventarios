package co.edu.cue.inventario.Controller;


import co.edu.cue.inventario.ElementosDti.ElementosDti;
import co.edu.cue.inventario.Enums.EstadosElementos;
import co.edu.cue.inventario.Enums.TipoDeElementos;
import co.edu.cue.inventario.Service.ConsultasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {
    private final ConsultasService service;

    public ConsultasController(ConsultasService service) {this.service = service;}


    @GetMapping("/filtrar/tipo")
    public ResponseEntity<List<ElementosDti>> filtrarPorTipo(@RequestParam("tipo") TipoDeElementos tipo) {
        try {
            List<ElementosDti> elementos = service.filtrarPorTipo(tipo);
            if (elementos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(elementos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/filtrar/estado")
    public ResponseEntity<List<ElementosDti>> filtrarPorEstado(@RequestParam("estado") EstadosElementos estado) {
        try {
            List<ElementosDti> elementos = service.filtrarPorEstado(estado);
            if (elementos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(elementos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

