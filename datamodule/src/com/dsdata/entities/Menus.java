package com.dsdata.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedNativeQuery(name = "get_menus",
query = "select menus.menuid,menus.nombre,menus.moduloid,menus.grupomenuid,menus.formulario,menus.orden," +
        "    menus.activo,menus.icono,menus.resizable,menus.maximizable,menus.duplicable from menus where menus.grupomenuid=:xgrupoid and menus.moduloid=:xmoduloid AND " +
        "    menus.menuid in (select menuperfil.menuid from menuperfil where menuperfil.perfilid=:xperfilid) order by menus.orden",
resultClass = Menus.class)
@Table(name = "menus")
public class Menus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int menuid;
    @Column
    private String nombre;
    @Column
    private int moduloid;
    @Column
    private int grupomenuid;
    @Column
    private String formulario;
    @Column
    private int orden;
    @Column
    private boolean activo;
    @Column
    private String icono;
    @Column
    private boolean resizable;
    @Column
    private boolean maximizable;
    @Column
    private boolean duplicable;

    public Menus(){}

    public Menus(int menuid, String nombre, int moduloid, int grupomenuid, String formulario, int orden, boolean activo, String icono,boolean resizable,boolean maximizable,boolean duplicable){
        this.menuid=menuid;
        this.nombre=nombre;
        this.moduloid=moduloid;
        this.grupomenuid=grupomenuid;
        this.formulario=formulario;
        this.orden=orden;
        this.activo=activo;
        this.icono=icono;
        this.resizable=resizable;
        this.maximizable=maximizable;
        this.duplicable=duplicable;
    }
    public int getMenuid() {
        return menuid;
    }

    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getModuloid() {
        return moduloid;
    }

    public void setModuloid(int moduloid) {
        this.moduloid = moduloid;
    }

    public int getGrupomenuid() {
        return grupomenuid;
    }

    public void setGrupomenuid(int grupomenuid) {
        this.grupomenuid = grupomenuid;
    }

    public String getFormulario() {
        return formulario;
    }

    public void setFormulario(String formulario) {
        this.formulario = formulario;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean getActivo() {
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

    public boolean isActivo() {
        return activo;
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public boolean isMaximizable() {
        return maximizable;
    }

    public void setMaximizable(boolean maximizable) {
        this.maximizable = maximizable;
    }

    public boolean isDuplicable() {
        return duplicable;
    }

    public void setDuplicable(boolean duplicable) {
        this.duplicable = duplicable;
    }

}
