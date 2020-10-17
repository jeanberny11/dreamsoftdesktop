package com.dscomponent;

import javafx.beans.value.ObservableValue;

public class DsPasswordField extends AbstractTextField<String> {

    public DsPasswordField() {
        super();
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
