package com.dscomponent;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

public class DsTextField extends AbstractTextField<String>  {
    private final BooleanProperty mayusculas = new SimpleBooleanProperty(this, "mayusculas",true);
    public DsTextField() {
        super();
    }

    public BooleanProperty mayusculasProperty() {
        return this.mayusculas;
    }

    public boolean isMayusculas() {
        return this.mayusculas.get();
    }

    public void setMayusculas(boolean mayusculas) {
        this.mayusculas.set(mayusculas);
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (this.mayusculas.get()) {
            text = text.toUpperCase();
        }
        super.replaceText(start, end, text);
    }

    @Override
    public void replaceSelection(String text) {
        if (this.mayusculas.get()) {
            text = text.toUpperCase();
        }
        super.replaceSelection(text);

    }

    @Override
    public void limpiar() {
        this.setText("");
    }

    @Override
    public String getValor() {
        return this.getText();
    }

    @Override
    public void setValor(String value) {
       this.setText(value);
    }

    @Override
    public boolean isEmpty() {
        return this.getText().isEmpty();
    }
}
