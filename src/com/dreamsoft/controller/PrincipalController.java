package com.dreamsoft.controller;

import com.dreamsoft.DsSystem;
import com.dreamsoft.managers.menu.events.MenuState;
import com.dreamsoft.models.MenuModel;
import com.dreamsoft.utils.DialogUtils;
import com.dreamsoft.utils.DsUtils;
import com.dreamsoft.mdiwindows.DsMDICanvas;
import com.dreamsoft.mdiwindows.DsMDIWindows;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class PrincipalController implements Initializable {
    private double MENU_OPEN_SIZE=0.0;
    private double MENU_CLOSE_SIZE=0.0;
    private MenuOpenController menuopen;
    private MenuCloseController menuclose;
    private HamburgerBackArrowBasicTransition hamburgertran;
    private final ObjectProperty<MenuState> menuState=new SimpleObjectProperty<>(this,"menuState");
    private final Observable<MenuState> menuStateObservable= JavaFxObservable.<MenuState>valuesOf(menuState);
    private final DsMDICanvas mdiCanvas=new DsMDICanvas();
    @FXML
    private BorderPane principalframe;

    @FXML
    private JFXHamburger btnmenu;

    @FXML
    private AnchorPane menupane;

    @FXML
    private AnchorPane contentpane;

    @FXML
    private JFXButton btnmensajes;

    @FXML
    private JFXButton btnsalir;

    @FXML
    void btncerrarclick(MouseEvent event) {
        salir();
    }

    @FXML
    void btnsalirclick(ActionEvent event) {
        salir();
    }

    @FXML
    void btnminimizarclick(MouseEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initformContent();

        double width= DsSystem.SCREENWIDTH;
        if (width>=1024 && width<1280){
            MENU_OPEN_SIZE=0.20;
            MENU_CLOSE_SIZE=0.05;
        }else if (width>=1280 && width<1366){
            MENU_OPEN_SIZE=0.17;
            MENU_CLOSE_SIZE=0.04;
        }else if(width>=1366 && width<1902){
            MENU_OPEN_SIZE=220;
            MENU_CLOSE_SIZE=45;
        }else if (width>=1920){
            MENU_OPEN_SIZE=0.155;
            MENU_CLOSE_SIZE=0.035;
        }

        menuopen=new MenuOpenController(DsSystem.getInstance().getCurrentUsuario());
        menuclose=new MenuCloseController(DsSystem.getInstance().getCurrentUsuario());
        menuclose.currentModuloProperty().bind(menuopen.currentModuloProperty());
        menuclose.currentGrupoProperty().bind(menuopen.currentGrupoProperty());
        final Consumer<MenuModel> menuhandle= menuModel -> {
            try {
                var controller=(DsController)Class.forName(menuModel.getFormulario()).getDeclaredConstructor().newInstance();
                if(menuModel.isDuplicable()){
                    mdiCanvas.addMDIWindow(new DsMDIWindows(menuModel.getFormulario(),new ImageView(DsUtils.getInstance().getImageResources(menuModel.getIcono())),menuModel.getNombre(),controller,!menuModel.isResizable(),menuModel.isMaximizable()));
                }else{
                    if(mdiCanvas.getItemFromMDIContainer(menuModel.getFormulario())==null && mdiCanvas.getItemFromToolBar(menuModel.getFormulario())==null){
                        mdiCanvas.addMDIWindow(new DsMDIWindows(menuModel.getFormulario(),new ImageView(DsUtils.getInstance().getImageResources(menuModel.getIcono())),menuModel.getNombre(),controller,!menuModel.isResizable(),menuModel.isMaximizable()));
                    }
                }
            } catch (Exception e) {
                DialogUtils.getInstance().mensaje("Menu Error","Error al abrir el menu\n"+menuModel.getNombre(),e);
            }
        };
        menuopen.setMenuhandle(menuhandle);
        menuclose.setMenuhandle(menuhandle);
        AnchorPane.setTopAnchor(menuopen,0.0);
        AnchorPane.setBottomAnchor(menuopen,0.0);
        AnchorPane.setLeftAnchor(menuopen,0.0);
        AnchorPane.setRightAnchor(menuopen,0.0);
        AnchorPane.setTopAnchor(menuclose,0.0);
        AnchorPane.setBottomAnchor(menuclose,0.0);
        AnchorPane.setLeftAnchor(menuclose,0.0);
        AnchorPane.setRightAnchor(menuclose,0.0);

        hamburgertran=new HamburgerBackArrowBasicTransition(btnmenu);

        menuStateObservable.subscribe(menuState -> {
            if(menuState==MenuState.open){
                hamburgertran.setRate(1);
                hamburgertran.play();
                contentpane.getChildren().clear();
                contentpane.getChildren().add(menuopen);
                btnmensajes.setContentDisplay(ContentDisplay.LEFT);
                btnsalir.setContentDisplay(ContentDisplay.LEFT);
                showmenu();
            }else{
                hamburgertran.setRate(-1);
                hamburgertran.play();
                contentpane.getChildren().clear();
                contentpane.getChildren().add(menuclose);
                btnmensajes.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                btnsalir.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                hidemenu();
            }
        });

        btnmenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(hamburgertran.getRate()==-1){
                    menuState.set(MenuState.open);
                }
                else{
                    menuState.set(MenuState.hide);
                }
            }
        });
        menuState.set(MenuState.open);
    }


    private void initformContent(){
        mdiCanvas.getStyleClass().add("formbackground");
        BorderPane.setAlignment(mdiCanvas, Pos.CENTER);
        principalframe.setCenter(mdiCanvas);
    }


    private void hidemenu(){
        var endvalue = new KeyValue(menupane.prefWidthProperty(), MENU_CLOSE_SIZE);
        var timeline=new Timeline(new KeyFrame(Duration.seconds(0.5),endvalue));
        timeline.play();
    }
    private void showmenu(){
        var endvalue = new KeyValue(menupane.prefWidthProperty(), MENU_OPEN_SIZE);
        var timeline=new Timeline(new KeyFrame(Duration.seconds(0.5),endvalue));
        timeline.play();
    }

    private void salir(){
        if(DialogUtils.getInstance().confirmaraviso("Desea salir de la aplicaccion?")){
            System.exit(0);
        }
    }
}
