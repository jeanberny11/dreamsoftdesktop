package com.dreamsoft.controller;

import com.dreamsoft.managers.menu.MenusManager;
import com.dreamsoft.models.ModuloModel;
import com.dreamsoft.models.UsuarioModel;
import com.dreamsoft.utils.DialogUtils;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MenuModuloController extends ModalController<ModuloModel>{
    private MenusManager _manager;
    @FXML
    private JFXButton btncerrar;

    @FXML
    private HBox modulepane;

    public MenuModuloController(UsuarioModel usuario, Consumer<ModuloModel> dialogResult) {
        super("menumodulos",dialogResult);
        btncerrar.setOnAction(event -> setResult(null));
        try {
            _manager=MenusManager.getInstance();
            var modulos=_manager.getModulos(usuario);
            modulepane.getChildren().clear();
            for (final var modulo:modulos){
                final var modulobutton=new JFXButton();
                var imagen = new Image(getClass().getResource("/com/dreamsoft/resources/images/" + modulo.getIcono()).toExternalForm());
                var icon = new ImageView(imagen);
                icon.setPreserveRatio(true);
                icon.setFitHeight(24);
                icon.setFitWidth(24);
                modulobutton.setWrapText(true);
                modulobutton.setTextAlignment(TextAlignment.CENTER);
                modulobutton.setText(modulo.getNombre());
                modulobutton.setGraphic(icon);
                modulobutton.setPrefWidth(100.0);
                modulobutton.setPrefHeight(100.0);
                modulobutton.setGraphicTextGap(5);
                modulobutton.setContentDisplay(ContentDisplay.TOP);
                modulobutton.getStyleClass().add("modulobutton");
                modulobutton.setOnAction(event -> setResult(modulo));
                HBox.setMargin(modulobutton,new Insets(2,8,2,2));
                modulepane.getChildren().add(modulobutton);
            }
        } catch (Exception e) {
            DialogUtils.getInstance().mensaje("Error","Error obteniendo los modulos de usuario!",e);
            return;
        }
    }
}
