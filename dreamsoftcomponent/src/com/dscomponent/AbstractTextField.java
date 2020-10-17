package com.dscomponent;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <h1>Text Base</h1>
 * Esta es la clase abstracta donde deben heredar todos los cuadros de textos del sistema
 *
 * @author  Jean Berny Gay
 * @version 1.0
 * @since   2020-10-12
 */
public abstract class AbstractTextField<T> extends JFXTextField implements DsBaseComponent<T>{

    private final BooleanProperty emptyValidate = new SimpleBooleanProperty(this, "emptyValidate",true);
    private final BooleanProperty blankable = new SimpleBooleanProperty(this, "blankable",true);
    private final BooleanProperty entertab = new SimpleBooleanProperty(this, "entertab", true);
    private final BooleanProperty activatable = new SimpleBooleanProperty(this, "activatable", true);
    private final BooleanProperty salvable = new SimpleBooleanProperty(this, "salvable", true);
    private final StringProperty fieldName=new SimpleStringProperty(this,"fieldName","");
    private final StringProperty emptyvalidatemsg=new SimpleStringProperty(this, "emptyvalidatemsg", "Este campo es obligatorio!");
    private final RequiredFieldValidator requiredFieldValidator=new RequiredFieldValidator();
    private final AtomicBoolean isvalidatoradded=new AtomicBoolean(false);

    public AbstractTextField(){
        super();
        setLabelFloat(false);
        addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER && entertab.get()) {
                boolean isThisField = false;
                for (Node child : getParent().getChildrenUnmodifiable()) {
                    if (isThisField) {
                        //This code will only execute after the current Node
                        if (child.isFocusTraversable() && !child.isDisabled()) {
                            child.requestFocus();
                            break;
                        }
                    } else {
                        //Check if this is the current Node
                        isThisField = child.equals(this);
                    }
                }
            }
        });
        emptyvalidatemsg.addListener((observable, oldValue, newValue) -> requiredFieldValidator.setMessage(newValue));

        focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(isvalidatoradded.get()){
                validate();
            }
        });
    }

    @Override
    public boolean validar() {
        if(!emptyValidate.get()){
            return true;
        }
        if(isvalidatoradded.compareAndSet(false,true)){
            requiredFieldValidator.setMessage(emptyvalidatemsg.get());
            this.setValidators(requiredFieldValidator);
        }
        return this.validate();
    }

    public boolean isEmptyValidate() {
        return emptyValidate.get();
    }

    public BooleanProperty emptyValidateProperty() {
        return emptyValidate;
    }

    public void setEmptyValidate(boolean emptyValidate) {
        this.emptyValidate.set(emptyValidate);
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

    public boolean isEntertab() {
        return entertab.get();
    }

    public BooleanProperty entertabProperty() {
        return entertab;
    }

    public void setEntertab(boolean entertab) {
        this.entertab.set(entertab);
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

    public String getEmptyvalidatemsg() {
        return emptyvalidatemsg.get();
    }

    public StringProperty emptyvalidatemsgProperty() {
        return emptyvalidatemsg;
    }

    public void setEmptyvalidatemsg(String emptyvalidatemsg) {
        this.emptyvalidatemsg.set(emptyvalidatemsg);
    }

    @Override
    public boolean isValidar() {
        return emptyValidate.get();
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
}
