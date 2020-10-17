package com.dreamsoft.models;

import com.dsdata.entities.Modulos;
import javafx.beans.property.*;

public class ModuloModel {
    private final IntegerProperty moduloid=new SimpleIntegerProperty(this,"moduloid",0);
    private final StringProperty nombre=new SimpleStringProperty(this,"nombre","");
    private final IntegerProperty orden=new SimpleIntegerProperty(this,"orden",0);
    private final BooleanProperty activo=new SimpleBooleanProperty(this,"activo",false);
    private final StringProperty icono=new SimpleStringProperty(this,"icono","");

    public ModuloModel(int moduliod, String nombre, int orden, boolean activo, String icono) {
        this.moduloid.set(moduliod);
        this.nombre.set(nombre);
        this.orden.set(orden);
        this.activo.set(activo);
        this.icono.set(icono);
    }

    public ModuloModel(Modulos m) {
        this(m.getModuloid(),m.getNombre(),m.getOrden(),m.isActivo(),m.getIcono());
    }

    public int getModuloid() {
        return moduloid.get();
    }

    public IntegerProperty moduloidProperty() {
        return moduloid;
    }

    public void setModuloid(int moduloid) {
        this.moduloid.set(moduloid);
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
