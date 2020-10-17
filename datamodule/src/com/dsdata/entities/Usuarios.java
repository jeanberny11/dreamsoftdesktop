package com.dsdata.entities;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuarios")
public class Usuarios implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int usuarioid;
    @Column
    private String usuario;
    @Column
    private String clave;
    @Column
    private String nombre;
    @Column
    private String direccion;
    @Column
    private String telefono;
    @Column
    private String email;
    @ManyToOne
    @JoinColumn(name="levelid", nullable=false)
    private Usuariolevels usuariolevels;
    @ManyToOne
    @JoinColumn(name="perfilid", nullable=false)
    private PerfilUsuarios perfilUsuarios;
    @Column
    private boolean activo;

    public Usuarios(){

    }

    public int getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuariolevels getUsuariolevels() {
        return usuariolevels;
    }

    public void setUsuariolevels(Usuariolevels usuariolevels) {
        this.usuariolevels = usuariolevels;
    }

    public PerfilUsuarios getPerfilUsuarios() {
        return perfilUsuarios;
    }

    public void setPerfilUsuarios(PerfilUsuarios perfilUsuarios) {
        this.perfilUsuarios = perfilUsuarios;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
