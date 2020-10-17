package com.dscomponent;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.textfield.CustomTextField;

public class DsSearchField extends CustomTextField {
    private final BooleanProperty mayusculas = new SimpleBooleanProperty(this, "mayusculas",true);
    private JFXButton btnsearch;
    private JFXButton btnclear;

    public DsSearchField(){
        super();
        inibutton();
        setLeft(btnsearch);
        setRight(btnclear);
    }

    private void inibutton(){
        //boton buscar //
        btnsearch=new JFXButton();
        var iconsearch = new ImageView(new Image(getClass().getResource("/com/dscomponent/resources/search20x20.png").toExternalForm()));
        iconsearch.setPreserveRatio(true);
        iconsearch.setFitHeight(20);
        iconsearch.setFitWidth(20);
        btnsearch.setGraphic(iconsearch);
        btnsearch.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnsearch.setPrefWidth(24.0);
        btnsearch.setPrefHeight(24.0);
        // fin //
        // boton lmpiar //
        btnclear=new JFXButton();
        var iconclear = new ImageView(new Image(getClass().getResource("/com/dscomponent/resources/close20x20.png").toExternalForm()));
        iconclear.setPreserveRatio(true);
        iconclear.setFitHeight(20);
        iconclear.setFitWidth(20);
        btnclear.setGraphic(iconclear);
        btnclear.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnclear.setPrefWidth(24.0);
        btnclear.setPrefHeight(24.0);
        btnclear.setOnAction(event -> setText(""));
        // fin //
    }

    public JFXButton getBtnsearch() {
        return btnsearch;
    }

    public JFXButton getBtnclear() {
        return btnclear;
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

    public boolean isMayusculas() {
        return mayusculas.get();
    }

    public BooleanProperty mayusculasProperty() {
        return mayusculas;
    }

    public void setMayusculas(boolean mayusculas) {
        this.mayusculas.set(mayusculas);
    }
}
