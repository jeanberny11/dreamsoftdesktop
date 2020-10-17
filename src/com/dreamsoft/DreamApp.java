package com.dreamsoft;

import com.dreamsoft.managers.LoginManager;
import com.dreamsoft.managers.UsuarioManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DreamApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        var user= LoginManager.getInstance().autentificarUsuario();
        if (user==null){
            System.exit(0);
        }
        DsSystem.getInstance().setCurrentUsuario(user);
        DsSystem.getInstance().setUserPreference(UsuarioManager.getInstance().getUserPreferences(user));
        var loader=new FXMLLoader(getClass().getResource("/com/dreamsoft/resources/fxml/principal.fxml"));
        Parent root=loader.load();
        var scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.setWidth(DsSystem.SCREENWIDTH);
        primaryStage.setHeight(DsSystem.SCREENHEIGTH);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
