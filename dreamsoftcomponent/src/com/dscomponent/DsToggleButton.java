package com.dscomponent;

import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

public class DsToggleButton extends JFXToggleButton implements DsBaseComponent<Boolean>{
    private final BooleanProperty activatable = new SimpleBooleanProperty(this, "activatable", true);
    private final BooleanProperty blankable = new SimpleBooleanProperty(this, "blankable",true);
    private final BooleanProperty salvable = new SimpleBooleanProperty(this, "salvable", true);
    private final BooleanProperty defaultValue = new SimpleBooleanProperty(this, "defaultValue");
    private final StringProperty fieldName=new SimpleStringProperty(this,"fieldName","");


    public DsToggleButton(){
        super();
    }

    public boolean isActivatable() {
        return activatable.get();
    }

    public BooleanProperty activatableProperty() {
        return activatable;
    }

    public void setActivatable(boolean activatable) {
        this.activatable.set(activatable);
    }

    public boolean isSalvable() {
        return salvable.get();
    }

    public BooleanProperty salvableProperty() {
        return salvable;
    }

    public void setSalvable(boolean salvable) {
        this.salvable.set(salvable);
    }

    public boolean isDefaultValue() {
        return defaultValue.get();
    }

    public BooleanProperty defaultValueProperty() {
        return defaultValue;
    }

    public void setDefaultValue(boolean defaultValue) {
        this.defaultValue.set(defaultValue);
    }

    @Override
    public String getFieldName() {
        return fieldName.get();
    }

    public StringProperty fieldNameProperty() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName.set(fieldName);
    }

    public boolean isBlankable() {
        return blankable.get();
    }

    public BooleanProperty blankableProperty() {
        return blankable;
    }

    public void setBlankable(boolean blankable) {
        this.blankable.set(blankable);
    }

    @Override
    public boolean isValidar() {
        return false;
    }

    @Override
    public boolean isLimpiar() {
        return blankable.get();
    }

    @Override
    public boolean isSalvar() {
        return salvable.get();
    }

    @Override
    public boolean isActivar() {
        return activatable.get();
    }

    @Override
    public Boolean getValor() {
        return isSelected();
    }

    @Override
    public void setValor(Boolean value) {
        this.setSelected(value);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean validar() {
        return true;
    }

    @Override
    public void limpiar() {
        this.setSelected(defaultValue.get());
    }
}
