package com.dscomponent;

import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.NumberStringConverter;

public class NumericTextField extends AbstractTextField<Double> {
    private final StringProperty inputformat = new SimpleStringProperty(this, "inputformat","###,###,##0.00");
    private TextFormatter<Number> formatter;
    public NumericTextField() {
        super();
        this.setAlignment(Pos.CENTER_RIGHT);
        formatter = new TextFormatter<>(new NumberStringConverter(this.inputformat.get()));
        formatter.setValue(0);
        setTextFormatter(formatter);
        inputformat.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            this.formatter=new TextFormatter<>(new NumberStringConverter(newValue));
            this.setTextFormatter(formatter);
        });

        addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent event) -> {
            if (!isNumeric(event.getCharacter())) {
                event.consume();
            }
        });

        this.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (newPropertyValue) {
                if (formatter.getValue().doubleValue() == 0) {
                    selectAll();
                } else {
                    positionCaret(getText().length());
                }
            }else{
                if(getText().isEmpty()){
                    formatter.setValue(0);
                }
            }
        });
    }

    private boolean isNumeric(String str) {
        return (str.matches("[+-.]?\\d*(\\.\\d+)?") && !str.equals(""));
    }

    public StringProperty inputformatProperty() {
        return this.inputformat;
    }

    public void setInputformat(String inputformat) {
        this.inputformat.set(inputformat);
    }

    public String getInputformat() {
        return this.inputformat.get();
    }

    @Override
    public void limpiar() {
        this.formatter.setValue(0);
    }

    @Override
    public Double getValor() {
        return formatter.getValue().doubleValue();
    }

    @Override
    public void setValor(Double value) {
        formatter.setValue(value);
    }

    @Override
    public boolean isEmpty() {
        return this.formatter.getValue().doubleValue()==0;
    }
}
