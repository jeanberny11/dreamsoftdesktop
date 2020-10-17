package com.dreamsoft.controller;

import com.dreamsoft.managers.UsuarioManager;
import com.dreamsoft.models.UsuarioModel;
import com.dreamsoft.utils.DialogUtils;
import com.dscomponent.DsPasswordField;
import com.dscomponent.DsTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class LoginController extends ModalController<UsuarioModel> implements Initializable {
    @FXML
    private AnchorPane contentpane;

    @FXML
    private DsTextField txtusuario;

    @FXML
    private DsPasswordField txtpassword;

    private UsuarioManager usuarioManager;

    public LoginController(Consumer<UsuarioModel> dialogResult) {
        super("login",dialogResult);

    }


    @FXML
    void btnentrarclick(ActionEvent event) {
        if(!validar(contentpane)){
            return;
        }
        try{
            var usuario=usuarioManager.getAuthUser(txtusuario.getText(),txtpassword.getText());
            if(usuario==null){
                DialogUtils.getInstance().mensaje("Autentificacion error","Usuario o contraseñ no validos");
                return;
            }
            setResult(usuario);
        } catch (Exception e) {
            DialogUtils.getInstance().mensaje("Autentificacion error",e.getMessage(),e);
        }
    }

    @FXML
    void btnsalirclick(ActionEvent event) {
        setResult(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.usuarioManager=UsuarioManager.getInstance();
        } catch (Exception e) {
            DialogUtils.getInstance().mensaje("Usuario Namager",e.getMessage(),e);
        }
        txtpassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnentrarclick(event);
            }
        });
    }
}
