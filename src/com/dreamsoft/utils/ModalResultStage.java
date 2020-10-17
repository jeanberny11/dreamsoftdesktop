package com.dreamsoft.utils;

import javafx.stage.Stage;

public class ModalResultStage<T> extends Stage {
    T result;
    public ModalResultStage(){
        super();
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
