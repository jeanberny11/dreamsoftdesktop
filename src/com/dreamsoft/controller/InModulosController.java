package com.dreamsoft.controller;

import com.dreamsoft.utils.DialogConfirmationType;
import com.dreamsoft.utils.DialogUtils;
import com.dreamsoft.utils.WindowMode;
import com.dscomponent.DsTextField;
import com.dscomponent.DsToggleButton;
import com.dscomponent.DsToolBar;
import com.dscomponent.IntegerTextField;
import io.reactivex.functions.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class InModulosController extends DsController implements Initializable {
    @FXML
    private DsToolBar toolbar;

    @FXML
    private AnchorPane contentpane;

    @FXML
    private IntegerTextField tmoduloid;

    @FXML
    private DsTextField tnombre;

    @FXML
    private IntegerTextField torden;

    @FXML
    private DsTextField ticono;

    @FXML
    private DsToggleButton tactivo;

    public InModulosController() {
        super("modulos");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindToolbar(toolbar);

        getWindowModeObservable().subscribe(new Consumer<WindowMode>() {
            @Override
            public void accept(WindowMode windowMode) throws Exception {
                switch (windowMode){
                    case Inicial:
                        tmoduloid.setDisable(false);
                        tmoduloid.setEmptyValidate(true);
                        break;
                    case Creando:
                        tmoduloid.setValor(0);
                        tmoduloid.setDisable(true);
                        tmoduloid.setEmptyValidate(false);
                        break;
                    case Info:
                        break;
                    case Editando:
                        tmoduloid.setDisable(false);
                        tmoduloid.setEmptyValidate(true);
                        break;
                }
            }
        });
    }

    @Override
    protected void salvar() {
       if(!validar()){
           return;
       }
        if(!DialogUtils.getInstance().getConfirmationMsg(DialogConfirmationType.SaveConfirmation)){
            return;
        }
    }
}
