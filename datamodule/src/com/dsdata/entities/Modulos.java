package com.dsdata.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedNativeQuery(
        name = "get_modulosperfil",
        query = "select modulos.moduloid,modulos.nombre,modulos.orden,modulos.activo,modulos.icono from modulos " +
                "    where modulos.activo=true and modulos.moduloid in " +
                "    (select m.moduloid from menus m,menuperfil u where m.menuid=u.menuid and u.perfilid=:xperfilid group by m.moduloid)" +
                "    order by modulos.orden",
        resultClass = Modulos.class
)
@Table(name = "modulos")
public class Modulos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int moduloid;
    @Column
    private String nombre;
    @Column
    private int orden;
    @Column
    private boolean activo;
    @Column
    private String icono;

    public Modulos() {
    }

    public Modulos(int moduloid, String nombre, int orden, boolean activo, String icono) {
        this.moduloid = moduloid;
        this.nombre = nombre;
        this.orden = orden;
        this.activo = activo;
        this.icono = icono;
    }

    public int getModuloid() {
        return moduloid;
    }

    public void setModuloid(int moduloid) {
        this.moduloid = moduloid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
}
