package com.dreamsoft.controller;

import com.dreamsoft.DsSystem;
import com.dreamsoft.managers.menu.MenusManager;
import com.dreamsoft.models.GrupoMenuModel;
import com.dreamsoft.models.MenuModel;
import com.dreamsoft.models.ModuloModel;
import com.dreamsoft.models.UsuarioModel;
import com.dreamsoft.utils.DialogUtils;
import com.dreamsoft.utils.DsUtils;
import com.dreamsoft.utils.ModalResultStage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MenuOpenController extends AnchorPane implements Initializable {
    private MenusManager _manager;
    @FXML
    private JFXButton modulobutton;

    @FXML
    private Label lbltitulo;

    @FXML
    private VBox grouppane;

    @FXML
    private ScrollPane optionpane;

    @FXML
    void btnmodulo_click(ActionEvent event) {
        final var modulestage=new ModalResultStage<ModuloModel>();
        modulestage.setX(3);
        modulestage.setY(65);
        modulestage.initModality(Modality.WINDOW_MODAL);
        modulestage.initStyle(StageStyle.UNDECORATED);
        modulestage.setResizable(false);
        modulestage.initOwner(((Node)event.getSource()).getScene().getWindow());
        var controller=new MenuModuloController(usuario, moduloModel -> {
            modulestage.setResult(moduloModel);
            modulestage.close();
        });
        modulestage.setScene(new Scene(controller));
        modulestage.setResizable(false);
        modulestage.showAndWait();
        var result=modulestage.getResult();
        if(result!=null){
            currentModulo.set(result);
        }
    }

    private final UsuarioModel usuario;
    private final ObjectProperty<ModuloModel> currentModulo=new SimpleObjectProperty<>(this,"currentModulo");
    private final ObjectProperty<GrupoMenuModel> currentGrupo=new SimpleObjectProperty<>(this,"currentGrupo");
    private Consumer<MenuModel> menuhandle;

    public MenuOpenController(UsuarioModel usuario) {
        this.usuario = usuario;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/com/dreamsoft/resources/fxml/menuopen.fxml"));
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

        currentModulo.addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends ModuloModel> observable, ModuloModel oldValue, ModuloModel newValue) {
                //actualizao el boton de modulos//
                lbltitulo.setText(newValue.getNombre());
                var imagen = new Image(getClass().getResource("/com/dreamsoft/resources/images/" + newValue.getIcono()).toExternalForm());
                var icon = new ImageView(imagen);
                icon.setPreserveRatio(true);
                icon.setFitHeight(20);
                icon.setFitWidth(20);
                modulobutton.setGraphic(icon);
                modulobutton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                // end //

                // actualizo el pane de los grupos //
                try {
                    var grupos = _manager.getGrupos(usuario, newValue);
                    grouppane.getChildren().clear();
                    for (final var grupo : grupos) {
                        var boton = new JFXButton();
                        var btnimg = new Image(getClass().getResource("/com/dreamsoft/resources/images/" + grupo.getIcono()).toExternalForm());
                        var btnicon = new ImageView(btnimg);
                        btnicon.setPreserveRatio(true);
                        btnicon.setFitHeight(24);
                        btnicon.setFitWidth(24);
                        boton.setGraphic(btnicon);
                        boton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        boton.setTooltip(new Tooltip(grupo.getNombre()));
                        boton.getStyleClass().add("grupoboton");
                        VBox.setMargin(boton, new Insets(0, 0, 10, 0));
                        boton.setOnAction(event -> currentGrupo.set(grupo));
                        grouppane.getChildren().add(boton);
                    }
                    currentGrupo.set(grupos.get(0));
                } catch (Exception e) {
                    DialogUtils.getInstance().mensaje("Error", "Error al obtener los grupos de este modulo", e);
                    return;
                }
            }
        });

        currentGrupo.addListener(new ChangeListener<GrupoMenuModel>() {
            @Override
            public void changed(ObservableValue<? extends GrupoMenuModel> observable, GrupoMenuModel oldValue, GrupoMenuModel newValue) {
                try {
                    var menus=_manager.getMenus(usuario,currentModulo.get(),newValue);
                    var content=new VBox();
                    content.setFillWidth(true);
                    for(final var menu:menus){
                        var boton=new JFXButton();
                        var btnimg = new Image(getClass().getResource("/com/dreamsoft/resources/images/" + menu.getIcono()).toExternalForm());
                        var btnicon = new ImageView(btnimg);
                        btnicon.setPreserveRatio(true);
                        btnicon.setFitHeight(30);
                        btnicon.setFitWidth(30);
                        boton.setGraphic(btnicon);
                        boton.setPrefWidth(getPrefWidth());
                        boton.setContentDisplay(ContentDisplay.LEFT);
                        boton.setTooltip(new Tooltip(menu.getNombre()));
                        boton.setText(menu.getNombre());
                        boton.getStyleClass().add("menubutton");
                        boton.setAlignment(Pos.CENTER_LEFT);
                        boton.setOnAction(event -> {
                            if(menuhandle!=null){
                                menuhandle.accept(menu);
                            }
                        });
                        VBox.setMargin(boton, new Insets(0, 5, 10, 5));
                        content.getChildren().add(boton);
                    }
                    optionpane.setContent(content);
                } catch (Exception e) {
                    DialogUtils.getInstance().mensaje("Error", "Error al obtener los menus de este grupo", e);
                    return;
                }
            }
        });

        try {
            var inicialmodule= _manager.getInicialModulo(usuario,DsSystem.getInstance().getUserPreference().getModuloinicial().getModuloid());
            if(inicialmodule==null){
                DialogUtils.getInstance().mensaje("Modulo Error","El modulo inicial es incorrecto!");
                return;
            }
            currentModulo.set(inicialmodule);
        } catch (Exception e) {
            DialogUtils.getInstance().mensaje("Error","Error al obtener el modulo inicial del usuario",e);
            return;
        }

    }

    public void setMenuhandle(Consumer<MenuModel> menuhandle) {
        this.menuhandle = menuhandle;
    }

    public Observable<ModuloModel> currentModuloObservable(){
        return JavaFxObservable.valuesOf(currentModulo);
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
}
