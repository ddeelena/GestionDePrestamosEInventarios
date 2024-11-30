package com.example.proyecto_sgp.inventario.Service.Implementaciones;

import com.example.proyecto_sgp.inventario.ElementosDti.ElementosDti;
import com.example.proyecto_sgp.inventario.Enums.EstadosElementos;
import com.example.proyecto_sgp.inventario.Enums.TipoDeElementos;
import com.example.proyecto_sgp.inventario.Fabricas.ElementosDtiFabrica;
import com.example.proyecto_sgp.inventario.Fabricas.FabricaDeControles;
import com.example.proyecto_sgp.inventario.Interfaces.*;
import com.example.proyecto_sgp.inventario.Service.ControlesService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDate;

public class ControlesImpl implements CrearElemento, EditarElementos, EliminarElementos, VerDetalles, CambiarEstado, ControlesService {
    ElementosDtiFabrica Fabrica = new FabricaDeControles();
    ElementosDti control;

    @PostMapping
    public void crearElemento(String identificacion, String nombre, String descripcion, TipoDeElementos tipo, EstadosElementos estado, LocalDate fechaCreacion){
        this.control = Fabrica.crearElementoDti(identificacion, nombre, descripcion, tipo, estado, fechaCreacion);
    }

    @GetMapping
    public String VerDetalles(){
        return "Nombre:"+control.getNombre()+
                "Identifiacion "+control.getIdentificacion()+
                "Descripcion "+control.getDescripcion()+
                "tipo "+control.getTipo()+
                "Estado "+control.getEstado()+
                "Fecha de creación "+control.getFechaCreacion();
    }

    @PutMapping
    public void EditarElemento(String identificacion, String nombre, String descripcion, TipoDeElementos tipo, EstadosElementos estado, LocalDate fechaCreacion){

        control.setDescripcion(descripcion);
        control.setFechaCreacion(fechaCreacion);
        control.setNombre(nombre);
        control.setTipo(tipo);
        control.setIdentificacion(identificacion);
        control.setEstado(estado);

    }

    @DeleteMapping
    public void EliminarElemento(){

    }

}
