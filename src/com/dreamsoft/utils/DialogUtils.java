package com.dreamsoft.utils;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

public class DialogUtils {
    private static DialogUtils instance;

    private DialogUtils(){

    }

    public static DialogUtils getInstance(){
        if(instance == null){
            synchronized (DialogUtils.class) {
                if(instance == null){
                    instance = new DialogUtils();
                }
            }
        }
        return instance;
    }

    public void mensaje(String header, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("AVISO");
        alert.setHeaderText(header);
        alert.setContentText(mensaje);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }

    public void mensaje(String header, String mensaje, Exception ex){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Mensaje de error");
        alert.setHeaderText(header);
        alert.setContentText(mensaje);
// Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("Los detalles del error:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

// Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
    }

    public boolean confirmaraviso(String mensaje){
        boolean ok=false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mensaje de confirmacion");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.initStyle(StageStyle.UTILITY);
        Optional<ButtonType> result=alert.showAndWait();
        if (result.get()== ButtonType.OK){
            ok=true;
        }
        return ok;
    }

    public boolean getConfirmationMsg(DialogConfirmationType type){
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UTILITY);
        switch (type){
            case SaveConfirmation:
            {
                alert.setTitle("Confirmacion de guardado");
                alert.setContentText("¿Desea salvar este registro?");
                break;
            }
            case EditConfirmation:
            {
                alert.setTitle("Confirmacion de editado");
                alert.setContentText("¿Desea editar este registro?");
                break;
            }
            case DeleteConfirmation:
            {
                alert.setTitle("Confirmacion de borrado");
                alert.setContentText("¿Desea borrar este registro?");
                break;
            }
        }
        var result=alert.showAndWait();
        return result.get()== ButtonType.OK;
    }

    public int opmensaje(String mensaje, String op1, String op2){
        // esta funcion devuelve 1 si se elige la opcion 1, 2 por la opcion 2 y 0 si se cancela.
        int opcion=0;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mensaje de confirmacion");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        ButtonType buttonTypeOne = new ButtonType(op1);
        ButtonType buttonTypeTwo = new ButtonType(op2);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            opcion=1;
        } else if (result.get() == buttonTypeTwo) {
            opcion=2;
        }
        return opcion;
    }
}
