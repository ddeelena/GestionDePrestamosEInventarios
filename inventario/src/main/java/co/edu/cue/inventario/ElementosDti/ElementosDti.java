package co.edu.cue.inventario.ElementosDti;

import co.edu.cue.inventario.Enums.EstadosElementos;
import co.edu.cue.inventario.Enums.TipoDeElementos;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Document(collection = "ElementosDti")
@ToString
public abstract class ElementosDti {

    //Atributos generales de todos los elementos de Dti
    @Id
    protected String identificacion;
    protected String nombre;
    protected String descripcion;
    protected TipoDeElementos tipo;

    protected EstadosElementos estado;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    protected LocalDate fechaCreacion;


    public ElementosDti(String identificacion, String nombre, String descripcion, TipoDeElementos tipo, EstadosElementos estado, LocalDate fechaCreacion) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    //Metodos que todos los elementos deben implementar
    public abstract TipoDeElementos getTipo();

    public  String getIdentificacion(){
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipo(TipoDeElementos tipo) {this.tipo = tipo;}

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() { return descripcion;}

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public EstadosElementos getEstado() {
        return estado;
    }

    public void setEstado(EstadosElementos estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
