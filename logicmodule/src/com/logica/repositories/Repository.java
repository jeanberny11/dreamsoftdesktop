package com.logica.repositories;

import java.util.List;

public interface Repository<T,ID> {
    T findByID(Class<T> tClass, ID id) throws Exception;
    List<T> findAll(Class<T> tClass)throws Exception;
    void create(T data)throws Exception;
    void createAll(List<T> listdata)throws Exception;
    void update(T data)throws Exception;
    void delete(T data)throws Exception;
}
