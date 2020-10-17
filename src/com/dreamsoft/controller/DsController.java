package com.dreamsoft.controller;

import com.dreamsoft.utils.DialogUtils;
import com.dreamsoft.utils.WindowMode;
import com.dscomponent.AbstractTextField;
import com.dscomponent.DsBaseComponent;
import com.dscomponent.DsToolBar;
import com.dscomponent.ToolBarMode;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public abstract class DsController extends AnchorPane {
    private final ObjectProperty<WindowMode> windowMode=new SimpleObjectProperty<>(this,"windowMode");
    private final Observable<WindowMode> windowModeObservable= JavaFxObservable.valuesOf(windowMode);

    public DsController(String viewname){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/com/dreamsoft/resources/fxml/"+viewname+".fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    protected void bindToolbar( final DsToolBar toolBar){
        toolBar.getBtncrear().setOnAction(event -> crear());
        toolBar.getBtnbuscar().setOnAction(event->buscar());
        toolBar.getBtnguardar().setOnAction(event -> salvar());
        toolBar.getBtncancelar().setOnAction(event -> cancelar());
        toolBar.getBtnborrar().setOnAction(evtn -> anular());
        windowModeObservable.subscribe(windowMode -> {
            switch (windowMode) {
                case Inicial:
                    toolBar.setToolBarMode(ToolBarMode.ModoInicial);
                    break;
                case Creando:
                    toolBar.setToolBarMode(ToolBarMode.ModoCreando);
                    break;
                case Info:
                    toolBar.setToolBarMode(ToolBarMode.ModoEditar);
                    break;
                case Editando:
                    toolBar.setToolBarMode(ToolBarMode.ModoCreando);
                    break;
            }
        });
    }

    protected void crear(){
        activar(true);
        setWindowMode(WindowMode.Creando);
    }

    protected void salvar(){
    }

    protected boolean validar(){
        var isvalid=true;
        for(Node node:this.getChildren()){
            if(node instanceof AnchorPane || node instanceof GridPane){
                if(!validar((Parent) node)){
                    isvalid=false;
                }
            }else if (node instanceof TabPane){
                for (Tab tab :((TabPane) node).getTabs()){
                    if(!validar((Parent) tab.getContent())){
                        isvalid=false;
                    }
                }
            }else if(node instanceof SplitPane){
                for(Node item:((SplitPane) node).getItems()){
                    if(item instanceof AnchorPane){
                        if(!validar((Parent) item)){
                            isvalid=false;
                        }
                    }
                }
            }
        }
        return isvalid;
    }

    protected void editar(){

    }

    protected void anular(){

    }

    protected void buscar(){

    }

    protected void cancelar(){
        if(DialogUtils.getInstance().confirmaraviso("Esta seguro que desea limpiar todos los campos de esta ventana? \nSe perderan todos los datos que no haya salvado!")){
            limpiar();
            activar(false);
            setWindowMode(WindowMode.Inicial);
        }
    }

    protected void activar(boolean estado){
        for(Node node:this.getChildren()){
            if(node instanceof AnchorPane || node instanceof GridPane){
                activar((Parent) node,estado);
            }else if (node instanceof TabPane){
                for (Tab tab :((TabPane) node).getTabs()){
                    activar((Parent) tab.getContent(),estado);
                }
            }else if(node instanceof SplitPane){
                for(Node item:((SplitPane) node).getItems()){
                    if(item instanceof AnchorPane){
                        activar((Parent) item,estado);
                    }
                }
            }
        }
    }

    private void activar(Parent parent, boolean state){
        for (Node child:parent.getChildrenUnmodifiable()){
            if(child instanceof DsBaseComponent){
                if(((DsBaseComponent) child).isActivar()){
                    child.setDisable(!state);
                }
            }
        }
    }

    protected void limpiar(){
        for(Node node:this.getChildren()){
            if(node instanceof AnchorPane || node instanceof GridPane){
                limpiar((Parent) node);
            }else if (node instanceof TabPane){
                for (Tab tab :((TabPane) node).getTabs()){
                    limpiar((Parent) tab.getContent());
                }
            }else if(node instanceof SplitPane){
                for(Node item:((SplitPane) node).getItems()){
                    if(item instanceof AnchorPane){
                        limpiar((Parent) item);
                    }
                }
            }
        }
    }

    private void limpiar(Parent parent){
        for (Node child:parent.getChildrenUnmodifiable()){
            if(child instanceof DsBaseComponent){
                if(((DsBaseComponent) child).isLimpiar()){
                    ((DsBaseComponent) child).limpiar();
                }
            }
        }
    }

    protected boolean validar(Parent parent){
        var isvalid=true;
        for (Node child:parent.getChildrenUnmodifiable()){
            if(child instanceof AbstractTextField){
                if(((AbstractTextField) child).isEmptyValidate()){
                    if(!((AbstractTextField) child).validar()){
                        isvalid=false;
                    }
                }
            }
        }
        return isvalid;
    }

    public WindowMode getWindowMode() {
        return windowMode.get();
    }

    public ObjectProperty<WindowMode> windowModeProperty() {
        return windowMode;
    }

    public void setWindowMode(WindowMode windowMode) {
        this.windowMode.set(windowMode);
    }

    public Observable<WindowMode> getWindowModeObservable() {
        return windowModeObservable;
    }
}
