package com.dreamsoft;


import com.dreamsoft.models.PreferenciasUsuariosModel;
import com.dreamsoft.models.UsuarioModel;
import javafx.stage.Screen;

public class DsSystem {
    private static DsSystem instance;
    private UsuarioModel currentUsuario;
    private PreferenciasUsuariosModel userPreference;
    public static final double SCREENWIDTH= Screen.getPrimary().getVisualBounds().getWidth();
    public static final double SCREENHEIGTH= Screen.getPrimary().getVisualBounds().getHeight();

    private DsSystem(){}

    public static DsSystem getInstance(){
        if(instance == null){
            synchronized (DsSystem.class) {
                if(instance == null){
                    instance = new DsSystem();
                }
            }
        }
        return instance;
    }

    public UsuarioModel getCurrentUsuario() {
        return currentUsuario;
    }

    public void setCurrentUsuario(UsuarioModel currentUsuario) {
        this.currentUsuario = currentUsuario;
    }

    public PreferenciasUsuariosModel getUserPreference() {
        return userPreference;
    }

    public void setUserPreference(PreferenciasUsuariosModel userPreference) {
        this.userPreference = userPreference;
    }
}
