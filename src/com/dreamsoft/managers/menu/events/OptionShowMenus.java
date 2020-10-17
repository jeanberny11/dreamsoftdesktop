package com.dreamsoft.managers.menu.events;


import com.dreamsoft.models.GrupoMenuModel;
import com.dreamsoft.models.ModuloModel;

public class OptionShowMenus extends MenuOptionEvent{
    private GrupoMenuModel grupo;
    private ModuloModel modulo;
    public OptionShowMenus(MenuState state,GrupoMenuModel grupo,ModuloModel modulo) {
        super(state);
        this.grupo=grupo;
        this.modulo=modulo;
    }

    public GrupoMenuModel getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoMenuModel grupo) {
        this.grupo = grupo;
    }

    public ModuloModel getModulo() {
        return modulo;
    }

    public void setModulo(ModuloModel modulo) {
        this.modulo = modulo;
    }
}
