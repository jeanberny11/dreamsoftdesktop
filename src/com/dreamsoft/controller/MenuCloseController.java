package com.dreamsoft.controller;

import com.dreamsoft.managers.menu.MenusManager;
import com.dreamsoft.models.GrupoMenuModel;
import com.dreamsoft.models.MenuModel;
import com.dreamsoft.models.ModuloModel;
import com.dreamsoft.models.UsuarioModel;
import com.dreamsoft.utils.DialogUtils;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MenuCloseController extends AnchorPane implements Initializable {
    private MenusManager _manager;
    @FXML
    private AnchorPane menuclosecontainer;

    @FXML
    private VBox opcionpane;

    private final UsuarioModel usuario;
    private final ObjectProperty<ModuloModel> currentModulo=new SimpleObjectProperty<>(this,"currentModulo");
    private final ObjectProperty<GrupoMenuModel> currentGrupo=new SimpleObjectProperty<>(this,"currentGrupo");
    private Consumer<MenuModel> menuhandle;

    public MenuCloseController(UsuarioModel usuario) {
        this.usuario = usuario;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/com/dreamsoft/resources/fxml/menuclose.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            _manager=MenusManager.getInstance();
        } catch (Exception e) {
            DialogUtils.getInstance().mensaje("Error","Error al instancear el manegador de menus",e);
            return;
        }

        currentGrupo.addListener(new ChangeListener<GrupoMenuModel>() {
            @Override
            public void changed(ObservableValue<? extends GrupoMenuModel> observable, GrupoMenuModel oldValue, GrupoMenuModel newValue) {
                try {
                    var menus=_manager.getMenus(usuario,currentModulo.get(),newValue);
                    opcionpane.getChildren().clear();
                    for(final var menu:menus){
                        var boton=new JFXButton();
                        var btnimg = new Image(getClass().getResource("/com/dreamsoft/resources/images/" + menu.getIcono()).toExternalForm());
                        var btnicon = new ImageView(btnimg);
                        btnicon.setPreserveRatio(true);
                        btnicon.setFitHeight(30);
                        btnicon.setFitWidth(30);
                        boton.setGraphic(btnicon);
                        boton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        boton.setTooltip(new Tooltip(menu.getNombre()));
                        boton.setOnAction(event -> {
                            if(menuhandle!=null){
                                menuhandle.accept(menu);
                            }
                        });
                        VBox.setMargin(boton, new Insets(0, 0, 10, 0));
                        opcionpane.getChildren().add(boton);
                    }
                } catch (Exception e) {
                    DialogUtils.getInstance().mensaje("Error", "Error al obtener los menus de este grupo", e);
                    return;
                }
            }
        });
    }

    public ModuloModel getCurrentModulo() {
        return currentModulo.get();
    }

    public ObjectProperty<ModuloModel> currentModuloProperty() {
        return currentModulo;
    }

    public void setCurrentModulo(ModuloModel currentModulo) {
        this.currentModulo.set(currentModulo);
    }

    public GrupoMenuModel getCurrentGrupo() {
        return currentGrupo.get();
    }

    public ObjectProperty<GrupoMenuModel> currentGrupoProperty() {
        return currentGrupo;
    }

    public void setCurrentGrupo(GrupoMenuModel currentGrupo) {
        this.currentGrupo.set(currentGrupo);
    }

    public void setMenuhandle(Consumer<MenuModel> menuhandle) {
        this.menuhandle = menuhandle;
    }
}
