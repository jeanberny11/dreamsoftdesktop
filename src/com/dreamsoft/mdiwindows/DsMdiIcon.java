package com.dreamsoft.mdiwindows;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;

import java.util.Optional;

public class DsMdiIcon extends Button {
    private JFXButton btnClose;
    private Label lblName;
    private DsMDICanvas mdiCanvas;
    private EventHandler<MouseEvent> handleMaximize = (event) -> {
        DsMDIWindows win = this.mdiCanvas.getItemFromMDIContainer(this.getId());
        if (win != null) {
            win.setVisible(true);
            win.toFront();
            this.removeIcon();
        }

    };
    private EventHandler<MouseEvent> handleClose = (event) -> {
        if(closeConfirmation()){
            this.removeMDIWindow();
            this.removeIcon();
        }
    };

    public DsMdiIcon(ImageView logo, DsMDICanvas mdiCanvas, String name) throws Exception {
        HBox hBox = new HBox();
        hBox.setStyle("-fx-alignment:center-left");
        getStyleClass().add("iconbutton");
        hBox.setSpacing(10.0D);
        hBox.setPadding(new Insets(0.0D, 10.0D, 0.0D, 10.0D));
        this.mdiCanvas = mdiCanvas;
        this.lblName = new Label(name);
        //lblName.setStyle("-fx-font-family:\"Roboto\";-fx-text-fill: white;-fx-font-weight: bold;");
        this.lblName.getStyleClass().add("tilelabe");
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, this.handleMaximize);
        this.btnClose = new JFXButton("", this.getImageFromResource("closeformwhite.png"));
        btnClose.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        DropShadow shadowCloseBtn = new DropShadow();
        shadowCloseBtn.setHeight(10.0D);
        shadowCloseBtn.setWidth(10.0D);
        this.btnClose.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> {
            this.btnClose.setEffect(shadowCloseBtn);
        });
        this.btnClose.addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> {
            this.btnClose.setEffect((Effect) null);
        });
        this.btnClose.addEventHandler(MouseEvent.MOUSE_CLICKED, this.handleClose);
        hBox.getChildren().addAll(this.lblName, this.btnClose);
        this.setGraphic(hBox);
    }

    public Label getLblName() {
        return this.lblName;
    }

    private void removeMDIWindow() {
        var win = this.mdiCanvas.getItemFromMDIContainer(this.getId());
        if (win != null) {
            this.mdiCanvas.getPaneMDIContainer().getChildren().remove(win);
        }

    }

    public boolean closeConfirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText(null);
        alert.setContentText("Desea cerrar esta ventana?");
        alert.initStyle(StageStyle.UTILITY);
        Optional<ButtonType> result=alert.showAndWait();
        return result.get()==ButtonType.OK;
    }

    private void removeIcon() {
        var icon = this.mdiCanvas.getItemFromToolBar(this.getId());
        if (icon != null) {
            this.mdiCanvas.getTbWindows().getChildren().remove(icon);
        }

    }

    private ImageView getImageFromResource(String imageName) throws Exception {
        var imagen = new Image(getClass().getResource("/com/dreamsoft/resources/images/" + imageName).toExternalForm());
        return new ImageView(imagen);
    }

    public Button getBtnClose() {
        return this.btnClose;
    }

}
