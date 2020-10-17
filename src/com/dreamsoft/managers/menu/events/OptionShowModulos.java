package com.dreamsoft.managers.menu.events;

import com.dreamsoft.models.UsuarioModel;

public class OptionShowModulos extends MenuOptionEvent{
    private UsuarioModel usuario;
    public OptionShowModulos(MenuState state,UsuarioModel usuarios) {
        super(state);
        this.usuario=usuarios;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

}
