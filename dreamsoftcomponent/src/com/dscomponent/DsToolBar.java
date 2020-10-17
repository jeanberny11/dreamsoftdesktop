package com.dscomponent;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DsToolBar extends HBox {
    private JFXButton btnguardar;
    private JFXButton btnbuscar;
    private JFXButton btncancelar;
    private JFXButton btneditar;
    private JFXButton btncrear;
    private JFXButton btnborrar;
    private final ObjectProperty<ToolBarMode> toolBarMode=new SimpleObjectProperty<>(this,"toolBarMode");
    private final ObjectProperty<ToolBarType> toolBarType=new SimpleObjectProperty<>(this,"toolBarType");

    public DsToolBar(){
        getStyleClass().add("toolbar");
        setPrefHeight(50.0);
        init();
        initListeners();
        toolBarType.set(ToolBarType.MainForm);
        toolBarMode.set(ToolBarMode.ModoInicial);
    }

    private void init(){
        btnguardar=new JFXButton("");
        btnbuscar=new JFXButton("");
        btncancelar=new JFXButton("");
        btneditar=new JFXButton("");
        btncrear=new JFXButton("");
        btnborrar=new JFXButton("");

        // boton guardar//
        btnguardar.setPrefSize(50.0, 50.0);
        var iconguardar = new ImageView(new Image(getClass().getResource("/com/dscomponent/resources/save.png").toExternalForm()));
        iconguardar.setPreserveRatio(true);
        iconguardar.setFitHeight(36.0);
        iconguardar.setFitWidth(36.0);
        btnguardar.setGraphic(iconguardar);
        btnguardar.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnguardar.setTooltip(new Tooltip("Guardar"));
        // END //
        // boton buscar//
        btnbuscar.setPrefSize(50.0, 50.0);
        var iconbuscar = new ImageView(new Image(getClass().getResource("/com/dscomponent/resources/search.png").toExternalForm()));
        iconbuscar.setPreserveRatio(true);
        iconbuscar.setFitHeight(36.0);
        iconbuscar.setFitWidth(36.0);
        btnbuscar.setGraphic(iconbuscar);
        btnbuscar.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnbuscar.setTooltip(new Tooltip("Buscar"));
        // END //
        // boton cancelar//
        btncancelar.setPrefSize(50.0, 50.0);
        var iconcancel = new ImageView(new Image(getClass().getResource("/com/dscomponent/resources/clear.png").toExternalForm()));
        iconcancel.setPreserveRatio(true);
        iconcancel.setFitHeight(36.0);
        iconcancel.setFitWidth(36.0);
        btncancelar.setGraphic(iconcancel);
        btncancelar.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btncancelar.setTooltip(new Tooltip("Limpiar"));
        // END //
        // boton editar//
        btneditar.setPrefSize(50.0, 50.0);
        var iconedit = new ImageView(new Image(getClass().getResource("/com/dscomponent/resources/editar.png").toExternalForm()));
        iconedit.setPreserveRatio(true);
        iconedit.setFitHeight(36.0);
        iconedit.setFitWidth(36.0);
        btneditar.setGraphic(iconedit);
        btneditar.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btneditar.setTooltip(new Tooltip("Editar"));
        // END //
        // boton crear//
        btncrear.setPrefSize(50.0, 50.0);
        var iconnew = new ImageView(new Image(getClass().getResource("/com/dscomponent/resources/nuevo.png").toExternalForm()));
        iconnew.setPreserveRatio(true);
        iconnew.setFitHeight(36.0);
        iconnew.setFitWidth(36.0);
        btncrear.setGraphic(iconnew);
        btncrear.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btncrear.setTooltip(new Tooltip("Crear"));
        // END //
        // boton crear//
        btnborrar.setPrefSize(50.0, 50.0);
        var icodelete = new ImageView(new Image(getClass().getResource("/com/dscomponent/resources/borrar.png").toExternalForm()));
        icodelete.setPreserveRatio(true);
        icodelete.setFitHeight(36.0);
        icodelete.setFitWidth(36.0);
        btnborrar.setGraphic(icodelete);
        btnborrar.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnborrar.setTooltip(new Tooltip("Eliminar"));
        // END //
    }

    private void initListeners(){
        toolBarType.addListener((observableValue, toolBarType, t1) -> {
            getChildren().clear();
            switch (t1){
                case MainForm:
                {
                    getChildren().add(btncrear);
                    getChildren().add(btnbuscar);
                    getChildren().add(btnguardar);
                    getChildren().add(btneditar);
                    getChildren().add(btncancelar);
                    break;
                }
                case SearchForm:
                {
                    getChildren().add(btnbuscar);
                    getChildren().add(btnborrar);
                    break;
                }
                case ReportForm:
                {
                    getChildren().add(btnbuscar);
                    break;
                }
            }
        });

        toolBarMode.addListener((observable, oldValue, toolBarMode) -> {
            switch (toolBarMode) {
                case ModoInicial:
                    btncrear.setDisable(false);
                    btnbuscar.setDisable(false);
                    btnguardar.setDisable(true);
                    btneditar.setDisable(true);
                    btncancelar.setDisable(true);
                    break;
                case ModoCreando:
                    btncrear.setDisable(true);
                    btnbuscar.setDisable(true);
                    btnguardar.setDisable(false);
                    btneditar.setDisable(true);
                    btncancelar.setDisable(false);
                    break;
                case ModoEditar:
                    btncrear.setDisable(true);
                    btnbuscar.setDisable(true);
                    btnguardar.setDisable(true);
                    btneditar.setDisable(false);
                    btncancelar.setDisable(false);
                    break;
            }
        });
    }

    public JFXButton getBtnguardar() {
        return btnguardar;
    }

    public void setBtnguardar(JFXButton btnguardar) {
        this.btnguardar = btnguardar;
    }

    public JFXButton getBtnbuscar() {
        return btnbuscar;
    }

    public void setBtnbuscar(JFXButton btnbuscar) {
        this.btnbuscar = btnbuscar;
    }

    public JFXButton getBtncancelar() {
        return btncancelar;
    }

    public void setBtncancelar(JFXButton btncancelar) {
        this.btncancelar = btncancelar;
    }

    public JFXButton getBtneditar() {
        return btneditar;
    }

    public void setBtneditar(JFXButton btneditar) {
        this.btneditar = btneditar;
    }

    public JFXButton getBtncrear() {
        return btncrear;
    }

    public void setBtncrear(JFXButton btncrear) {
        this.btncrear = btncrear;
    }

    public JFXButton getBtnborrar() {
        return btnborrar;
    }

    public void setBtnborrar(JFXButton btnborrar) {
        this.btnborrar = btnborrar;
    }

    public ToolBarMode getToolBarMode() {
        return toolBarMode.get();
    }

    public ObjectProperty<ToolBarMode> toolBarModeProperty() {
        return toolBarMode;
    }

    public void setToolBarMode(ToolBarMode toolBarMode) {
        this.toolBarMode.set(toolBarMode);
    }

    public ToolBarType getToolBarType() {
        return toolBarType.get();
    }

    public ObjectProperty<ToolBarType> toolBarTypeProperty() {
        return toolBarType;
    }

    public void setToolBarType(ToolBarType toolBarType) {
        this.toolBarType.set(toolBarType);
    }
}
