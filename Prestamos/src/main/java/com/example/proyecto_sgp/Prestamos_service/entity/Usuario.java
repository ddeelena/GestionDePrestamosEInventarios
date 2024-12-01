package com.example.proyecto_sgp.Prestamos_service.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;

    @Field("nombre_usuario")
    private String nombreUsuario;

    @Field("nombre")
    private String nombre;

    @Field("apellido")
    private String apellido;

    @Field("contrasena")
    private String contrasena;

    @Field("correo_electronico")
    private String email;

    @Field("numero_telefono")
    private String numeroTelefono;

    @Field("roles")
    private List<String> roles;

    @Field("estado")
    private String estado;

    @Field("fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Field("fecha_modificacion")
    private LocalDateTime fechaModificacion;

    public Usuario(String id, String nombreUsuario, String nombre, String apellido, String contrasena,
                   String email, String numeroTelefono, List<String> roles, String estado,
                   LocalDateTime fechaCreacion, LocalDateTime fechaModificacion) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.email = email;
        this.numeroTelefono = numeroTelefono;
        this.roles = roles;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }
}