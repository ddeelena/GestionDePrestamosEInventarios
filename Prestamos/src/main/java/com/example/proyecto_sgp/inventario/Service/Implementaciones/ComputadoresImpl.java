package com.example.proyecto_sgp.inventario.Service.Implementaciones;

import com.example.proyecto_sgp.inventario.ElementosDti.ElementosDti;
import com.example.proyecto_sgp.inventario.Enums.EstadosElementos;
import com.example.proyecto_sgp.inventario.Enums.TipoDeElementos;
import com.example.proyecto_sgp.inventario.Fabricas.ElementosDtiFabrica;
import com.example.proyecto_sgp.inventario.Fabricas.FabricaDeComputadores;
import com.example.proyecto_sgp.inventario.Interfaces.*;
import com.example.proyecto_sgp.inventario.Service.ComputadoresService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDate;

@Service
public class ComputadoresImpl implements CrearElemento, EditarElementos, EliminarElementos, VerDetalles, CambiarEstado, ComputadoresService {

        ElementosDtiFabrica Fabrica = new FabricaDeComputadores();
        ElementosDti computador;

        @PostMapping
        public void crearElemento(String identificacion, String nombre, String descripcion, TipoDeElementos tipo, EstadosElementos estado, LocalDate fechaCreacion){
           this.computador = Fabrica.crearElementoDti(identificacion, nombre, descripcion, tipo, estado, fechaCreacion);
        }

        @GetMapping("/detalles")
        public String VerDetalles(){
            return "Nombre:"+computador.getNombre()+
                    "Identifiacion "+computador.getIdentificacion()+
                    "Descripcion "+computador.getDescripcion()+
                    "ubicacion "+computador.getTipo()+
                    "Estado "+computador.getEstado()+
                    "Fecha de creación "+computador.getFechaCreacion();
        }

        @PutMapping
        public void EditarElemento(String identificacion, String nombre, String descripcion, EstadosElementos estado, LocalDate fechaCreacion){

            computador.setDescripcion(descripcion);
            computador.setFechaCreacion(fechaCreacion);
            computador.setNombre(nombre);
            computador.setIdentificacion(identificacion);
            computador.setEstado(estado);

        }

        @DeleteMapping
        public void EliminarElemento(){

        }


}
