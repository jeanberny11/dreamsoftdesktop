package com.dreamsoft.managers.menu.events;

public abstract class MenuOptionEvent implements Cloneable {
    private MenuState state;

    public MenuOptionEvent(MenuState state) {
        this.state = state;
    }

    public MenuState getState() {
        return state;
    }

    public void setState(MenuState state) {
        this.state = state;
    }

    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }


}
