package com.dreamsoft.managers.menu.events;

import com.dreamsoft.models.ModuloModel;

public class OptionShowGrupos extends MenuOptionEvent{
    private ModuloModel modulo;
    public OptionShowGrupos(MenuState state,ModuloModel modulo) {
        super(state);
        this.modulo=modulo;
    }

    public ModuloModel getModulo() {
        return modulo;
    }

    public void setModulo(ModuloModel modulo) {
        this.modulo = modulo;
    }
}
