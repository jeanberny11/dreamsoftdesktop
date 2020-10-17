package com.dreamsoft.managers;


import com.dreamsoft.controller.LoginController;
import com.dreamsoft.managers.menu.MenusManager;
import com.dreamsoft.models.PreferenciasUsuariosModel;
import com.dreamsoft.models.UsuarioModel;
import com.dreamsoft.utils.ModalResultStage;
import com.logica.repositories.PreferenciasUsuariosRepository;
import com.logica.repositories.UsuariosRepository;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class LoginManager {
    private static LoginManager instance;



    private LoginManager() throws Exception {

    }

    public static LoginManager getInstance() throws Exception {
        if(instance == null){
            synchronized (LoginManager.class) {
                if(instance == null){
                    instance = new LoginManager();
                }
            }
        }
        return instance;
    }



    public UsuarioModel autentificarUsuario() throws Exception {
        final var stage=new ModalResultStage<UsuarioModel>();
        var controller=new LoginController(usuarioModel -> {
            stage.setResult(usuarioModel);
            stage.close();
        });
        stage.setScene(new Scene(controller,controller.getPrefWidth(),controller.getPrefHeight()));
        stage.setResizable(false);
        stage.showAndWait();
        return stage.getResult();
    }
}
