package com.dreamsoft.controller;

import java.util.function.Consumer;

public abstract class ModalController<T>extends DsController{
    protected final Consumer<T> dialogResult;
    public ModalController(String viewname,Consumer<T> dialogResult) {
        super(viewname);
        this.dialogResult=dialogResult;
    }

    protected void setResult(T result){
        this.dialogResult.accept(result);
    }
}
