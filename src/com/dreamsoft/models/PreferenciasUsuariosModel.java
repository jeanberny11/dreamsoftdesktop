package com.dreamsoft.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class PreferenciasUsuariosModel {
    private final IntegerProperty usuarioid=new SimpleIntegerProperty(this,"usuarioid",0);
    private final ObjectProperty<ModuloModel> moduloinicial=new SimpleObjectProperty<ModuloModel>(this,"moduloinicial");

    public PreferenciasUsuariosModel(int usuarioid, ModuloModel moduloinicial) {
        this.usuarioid.set(usuarioid);
        this.moduloinicial.set(moduloinicial);
    }

    public int getUsuarioid() {
        return usuarioid.get();
    }

    public IntegerProperty usuarioidProperty() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid.set(usuarioid);
    }

    public ModuloModel getModuloinicial() {
        return moduloinicial.get();
    }

    public ObjectProperty<ModuloModel> moduloinicialProperty() {
        return moduloinicial;
    }

    public void setModuloinicial(ModuloModel moduloinicial) {
        this.moduloinicial.set(moduloinicial);
    }
}
