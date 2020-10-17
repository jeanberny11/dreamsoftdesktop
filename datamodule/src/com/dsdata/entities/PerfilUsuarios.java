package com.dsdata.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "perfilusuarios")
public class PerfilUsuarios implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int perfilid;
    @Column
    private String nombre;
    @Column
    private boolean activo;

    public PerfilUsuarios(){}

    public PerfilUsuarios(int perfilid, String nombre, boolean activo){  this.perfilid=perfilid;
        this.nombre=nombre;
        this.activo=activo;

    }
    public int getPerfilid() {
        return perfilid;
    }

    public void setPerfilid(int perfilid) {
        this.perfilid = perfilid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
