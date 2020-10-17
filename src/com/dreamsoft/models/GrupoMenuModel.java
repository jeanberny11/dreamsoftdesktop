package com.dreamsoft.models;

import com.dsdata.entities.Grupomenu;
import javafx.beans.property.*;

public class GrupoMenuModel {
    private final IntegerProperty grupomenuid=new SimpleIntegerProperty(this,"grupomenuid",0);
    private final StringProperty nombre=new SimpleStringProperty(this,"nombre","");
    private final IntegerProperty orden=new SimpleIntegerProperty(this,"orden",0);
    private final BooleanProperty activo=new SimpleBooleanProperty(this,"activo",false);
    private final StringProperty icono=new SimpleStringProperty(this,"icono","");

    public GrupoMenuModel(int grupomenuid, String nombre, int orden, boolean activo, String icono) {
        this.grupomenuid.set(grupomenuid);
        this.nombre.set(nombre);
        this.orden.set(orden);
        this.activo.set(activo);
        this.icono.set(icono);
    }

    public GrupoMenuModel(Grupomenu m) {
        this(m.getGrupomenuid(),m.getNombre(),m.getOrden(),m.getActivo(),m.getIcono());
    }

    public int getGrupomenuid() {
        return grupomenuid.get();
    }

    public IntegerProperty grupomenuidProperty() {
        return grupomenuid;
    }

    public void setGrupomenuid(int grupomenuid) {
        this.grupomenuid.set(grupomenuid);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public int getOrden() {
        return orden.get();
    }

    public IntegerProperty ordenProperty() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden.set(orden);
    }

    public boolean isActivo() {
        return activo.get();
    }

    public BooleanProperty activoProperty() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo.set(activo);
    }

    public String getIcono() {
        return icono.get();
    }

    public StringProperty iconoProperty() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono.set(icono);
    }
}
