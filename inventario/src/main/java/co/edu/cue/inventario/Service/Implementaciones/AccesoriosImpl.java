package co.edu.cue.inventario.Service.Implementaciones;

import co.edu.cue.inventario.ElementosDti.ElementosDti;
import co.edu.cue.inventario.Enums.EstadosElementos;
import co.edu.cue.inventario.Enums.TipoDeElementos;
import co.edu.cue.inventario.Fabricas.ElementosDtiFabrica;
import co.edu.cue.inventario.Fabricas.FabricaDeAccesorios;
import co.edu.cue.inventario.Interfaces.*;
import co.edu.cue.inventario.Service.AccesoriosService;
import co.edu.cue.inventario.repository.InventarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class AccesoriosImpl implements CrearElemento, EditarElementos, EliminarElementos, VerDetalles, CambiarEstado, AccesoriosService {

    private final InventarioRepository repository;
    ElementosDtiFabrica Fabrica = new FabricaDeAccesorios();
    ElementosDti accesorio;

    public AccesoriosImpl(InventarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Override
    public void crearElemento(String identificacion, String nombre, String descripcion, TipoDeElementos tipo, EstadosElementos estado, LocalDate fechaCreacion){
        this.accesorio = Fabrica.crearElementoDti(identificacion, nombre, descripcion, tipo, estado, fechaCreacion);
        System.out.println(accesorio);
        repository.save(accesorio);
    }

    @GetMapping
    @Override
    public ElementosDti VerDetalles(String identificacion){
        return repository.findById(identificacion)
                .orElseThrow(() -> new NoSuchElementException("Elemento con ID " + identificacion + " no encontrado"));
    }

    @PutMapping
    @Override
    public void EditarElemento(String identificacion, String nombre, String descripcion, EstadosElementos estado, LocalDate fechaCreacion){

        ElementosDti accesorio = repository.findById(identificacion)
                .orElseThrow(() -> new NoSuchElementException("Elemento no encontrado"));

        // Actualiza los campos necesarios
        accesorio.setNombre(nombre);
        accesorio.setDescripcion(descripcion);
        accesorio.setEstado(estado);
        accesorio.setFechaCreacion(fechaCreacion);

        // Guarda los cambios
        repository.save(accesorio);

    }

    @DeleteMapping
    @Override
    public void EliminarElemento(String identificacion){
        ElementosDti accesorio = repository.findById(identificacion)
                .orElseThrow(() -> new NoSuchElementException("Elemento con ID " + identificacion + " no encontrado"));

        // Eliminar el elemento
        repository.delete(accesorio);
    }

}
