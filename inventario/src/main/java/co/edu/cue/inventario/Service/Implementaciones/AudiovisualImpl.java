package co.edu.cue.inventario.Service.Implementaciones;

import co.edu.cue.inventario.ElementosDti.ElementosDti;
import co.edu.cue.inventario.Enums.EstadosElementos;
import co.edu.cue.inventario.Enums.TipoDeElementos;
import co.edu.cue.inventario.Fabricas.ElementosDtiFabrica;
import co.edu.cue.inventario.Fabricas.FabricaDeAudiovisual;
import co.edu.cue.inventario.Interfaces.*;
import co.edu.cue.inventario.Service.AudiovisualService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDate;

public class AudiovisualImpl implements CrearElemento, EditarElementos, EliminarElementos, VerDetalles, CambiarEstado, AudiovisualService {

    ElementosDtiFabrica Fabrica = new FabricaDeAudiovisual();
    ElementosDti audiovisual;

    @PostMapping
    public void crearElemento(String identificacion, String nombre, String descripcion, TipoDeElementos tipo, EstadosElementos estado, LocalDate fechaCreacion){
        this.audiovisual = Fabrica.crearElementoDti(identificacion, nombre, descripcion, tipo, estado, fechaCreacion);
    }

    @GetMapping("/detalles")
    public String VerDetalles(){
        return "Nombre:"+audiovisual.getNombre()+
                "Identifiacion "+audiovisual.getIdentificacion()+
                "Descripcion "+audiovisual.getDescripcion()+
                "ubicacion "+audiovisual.getTipo()+
                "Estado "+audiovisual.getEstado()+
                "Fecha de creación "+audiovisual.getFechaCreacion();
    }

    @PutMapping
    public void EditarElemento(String identificacion, String nombre, String descripcion, TipoDeElementos tipo, EstadosElementos estado, LocalDate fechaCreacion){

        audiovisual.setDescripcion(descripcion);
        audiovisual.setFechaCreacion(fechaCreacion);
        audiovisual.setNombre(nombre);
        audiovisual.setTipo(tipo);
        audiovisual.setIdentificacion(identificacion);
        audiovisual.setEstado(estado);

    }

    @DeleteMapping
    public void EliminarElemento(){

    }

}
