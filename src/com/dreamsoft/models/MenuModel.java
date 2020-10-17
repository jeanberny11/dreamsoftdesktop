package com.dreamsoft.models;

import com.dsdata.entities.Menus;
import javafx.beans.property.*;

public class MenuModel {
    private final IntegerProperty menuid=new SimpleIntegerProperty(this,"menuid",0);
    private final StringProperty nombre=new SimpleStringProperty(this,"nombre","");
    private final IntegerProperty moduloid=new SimpleIntegerProperty(this,"moduloid",0);
    private final IntegerProperty grupomenuid=new SimpleIntegerProperty(this,"grupomenuid",0);
    private final StringProperty formulario=new SimpleStringProperty(this,"formulario","");
    private final IntegerProperty orden=new SimpleIntegerProperty(this,"orden",0);
    private final BooleanProperty activo=new SimpleBooleanProperty(this,"activo",false);
    private final StringProperty icono=new SimpleStringProperty(this,"icono","");
    private final BooleanProperty resizable=new SimpleBooleanProperty(this,"activo",false);
    private final BooleanProperty maximizable=new SimpleBooleanProperty(this,"activo",false);
    private final BooleanProperty duplicable=new SimpleBooleanProperty(this,"activo",false);

    public MenuModel(int menuid, String nombre, int moduloid, int grupomenuid, String formulario, int orden, boolean activo, String icono,boolean resizable,boolean maximizable,boolean duplicable) {
        this.menuid.set(menuid);
        this.nombre.set(nombre);
        this.moduloid.set(moduloid);
        this.grupomenuid.set(grupomenuid);
        this.formulario.set(formulario);
        this.orden.set(orden);
        this.activo.set(activo);
        this.icono.set(icono);
        this.resizable.set(resizable);
        this.maximizable.set(maximizable);
        this.duplicable.set(duplicable);
    }

    public MenuModel(Menus m){
        this(m.getMenuid(),m.getNombre(),m.getModuloid(),m.getGrupomenuid(),m.getFormulario(),m.getOrden(),m.getActivo(),m.getIcono(),m.isResizable(),m.isMaximizable(),m.isDuplicable());
    }

    public int getMenuid() {
        return menuid.get();
    }

    public IntegerProperty menuidProperty() {
        return menuid;
    }

    public void setMenuid(int menuid) {
        this.menuid.set(menuid);
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

    public int getModuloid() {
        return moduloid.get();
    }

    public IntegerProperty moduloidProperty() {
        return moduloid;
    }

    public void setModuloid(int moduloid) {
        this.moduloid.set(moduloid);
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

    public String getFormulario() {
        return formulario.get();
    }

    public StringProperty formularioProperty() {
        return formulario;
    }

    public void setFormulario(String formulario) {
        this.formulario.set(formulario);
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

    public boolean isResizable() {
        return resizable.get();
    }

    public BooleanProperty resizableProperty() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable.set(resizable);
    }

    public boolean isMaximizable() {
        return maximizable.get();
    }

    public BooleanProperty maximizableProperty() {
        return maximizable;
    }

    public void setMaximizable(boolean maximizable) {
        this.maximizable.set(maximizable);
    }

    public boolean isDuplicable() {
        return duplicable.get();
    }

    public BooleanProperty duplicableProperty() {
        return duplicable;
    }

    public void setDuplicable(boolean duplicable) {
        this.duplicable.set(duplicable);
    }
}
