package com.dreamsoft.utils;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DsUtils {

    private static DsUtils instance;

    private DsUtils(){

    }

    public static DsUtils getInstance(){
        if(instance == null){
            synchronized (DialogUtils.class) {
                if(instance == null){
                    instance = new DsUtils();
                }
            }
        }
        return instance;
    }

    public <S,T> HashMap<T, List<S>> groupBy(Iterable<S> listdata, GroupKey<S,T> keyfunc){
        HashMap<T, List<S>> result=new HashMap<>();
        for(var item : listdata){
            var list=result.putIfAbsent(keyfunc.getKey(item),new ArrayList<>());
            list.add(item);
        }
        return result;
    }

    public Image getImageResources(String imageName){
        return new Image(getClass().getResource("/com/dreamsoft/resources/images/" + imageName).toExternalForm());
    }
}

