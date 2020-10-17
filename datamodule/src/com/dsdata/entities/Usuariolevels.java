package com.dsdata.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuariolevels")
public class Usuariolevels implements Serializable {
    @Id
    @Column
    private int levelid;
    @Column
    private String nombre;
    @Column
    private boolean activo;

    public Usuariolevels(){}

    public Usuariolevels(int levelid, String nombre, boolean activo) {
        this.levelid = levelid;
        this.nombre = nombre;
        this.activo = activo;

    }

    public int getLevelid() {
        return levelid;
    }

    public void setLevelid(int levelid) {
        this.levelid = levelid;
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