package com.dreamsoft.controller;

import com.dreamsoft.managers.UsuarioManager;
import com.dreamsoft.utils.WindowMode;
import com.dscomponent.DsToolBar;
import com.dscomponent.IntegerTextField;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class InUsuariosController extends DsController implements Initializable {
    @FXML
    private DsToolBar toolbar;

    @FXML
    private AnchorPane contentpane;

    private IntegerTextField txtusuarioid;

    private  JFXButton button=new JFXButton();

    public InUsuariosController(){
        super("inusuarios");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindToolbar(toolbar);
        try {
            UsuarioManager manager=UsuarioManager.getInstance();
            manager.usuario.usuarioidProperty().bindBidirectional(txtusuarioid.valueProperty());
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        manager.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
