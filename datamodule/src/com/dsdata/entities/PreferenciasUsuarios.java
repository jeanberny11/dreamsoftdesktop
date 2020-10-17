package com.dsdata.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "preferenciasusuarios")
public class PreferenciasUsuarios implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int pk;
    @Column
    private int usuarioid;
    @Column
    private int moduloinicial;

    public PreferenciasUsuarios(){}

    public PreferenciasUsuarios(int usuarioid, int moduloinicial) {
        this.usuarioid = usuarioid;
        this.moduloinicial = moduloinicial;

    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public int getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    public int getModuloinicial() {
        return moduloinicial;
    }

    public void setModuloinicial(int moduloinicial) {
        this.moduloinicial = moduloinicial;
    }
}
