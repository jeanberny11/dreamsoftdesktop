package com.dscomponent;

import com.jfoenix.validation.base.ValidatorBase;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.IntegerStringConverter;

public class IntegerTextField extends AbstractTextField<Integer> {
    private final TextFormatter<Integer> formatter;
    private final IntegerProperty value=new SimpleIntegerProperty(this,"value",0);

    public IntegerTextField() {
        super();
        this.setAlignment(Pos.CENTER_RIGHT);
        formatter = new TextFormatter<>(new IntegerStringConverter());
        formatter.setValue(0);
        formatter.valueProperty().addListener((observable, oldValue, newValue) -> value.set(newValue));
        setTextFormatter(formatter);

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
        getValidators().add(new ValidatorBase() {
            @Override
            protected void eval() {
                this.hasErrors.set(IntegerTextField.this.formatter.getValue().equals(0));
            }
        });
    }

    private boolean isNumeric(String str) {
        return (str.matches("[+-.]?\\d*(\\.\\d+)?") && !str.equals(""));
    }

    @Override
    public void limpiar() {
        this.formatter.setValue(0);
    }

    @Override
    public Integer getValor() {
        return formatter.getValue();
    }

    @Override
    public void setValor(Integer value) {
        formatter.setValue(value);
    }

    @Override
    public boolean isEmpty() {
        return this.formatter.getValue()==0;
    }

    public TextFormatter<Integer> getFormatter() {
        return formatter;
    }

    public int getValue() {
        return value.get();
    }

    public IntegerProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
    }
}
