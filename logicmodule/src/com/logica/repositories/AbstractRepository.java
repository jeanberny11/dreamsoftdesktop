package com.logica.repositories;


import com.dsdata.Server;
import com.dsdata.SessionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractRepository<T,ID> implements Repository<T,ID>{
    private Server server=Server.Server1;
    protected Session session;
    private boolean work;
    private boolean isOpen;

    protected void open() throws Exception {
        if(!work && !isOpen){
            session= SessionManager.getInstance().getSession(server).openSession();
            isOpen=true;
        }
    }

    protected void close() throws Exception {
        if(!work && session!=null){
            session.close();
            isOpen=false;
        }
    }

    public void beginTransaction() throws Exception {
        if(work){
            throw new Exception("Ya hay una transaccion iniciada");
        }
        open();
        work = true;
        session.beginTransaction();
    }

    public void rollback() throws Exception {
        if(!work){
            throw new Exception("No hay una transaccion iniciada");
        }
        session.getTransaction().rollback();
        work = false;
        close();
    }

    public void commit() throws Exception {
        if(!work){
            throw new Exception("No hay una transaccion iniciada");
        }
        session.getTransaction().commit();
        work = false;
        close();
    }

    public void switchServer(Server server) throws Exception {
        if(isOpen){
            throw  new Exception("Hay una conexion abierta no puede cambiar el servidor!");
        }
        this.server=server;
    }

    @Override
    public T findByID(Class<T> tClass, ID id) throws Exception {
        open();
        var res=session.get(tClass, (Serializable) id);
        close();
        return res;
    }

    @Override
    public List<T> findAll(Class<T> tClass) throws Exception {
        open();
        var cb = session.getCriteriaBuilder();
        var cr = cb.createQuery(tClass);
        var root=cr.from(tClass);
        cr.select(root);
        var result=session.createQuery(cr).getResultList();
        close();
        return result;
    }

    @Override
    public void create(T data) throws Exception {
        open();
        session.save(data);
        close();
    }

    @Override
    public void createAll(List<T> listdata) throws Exception {
        open();
        for(final var data:listdata){
            session.save(data);
        }
        close();
    }

    @Override
    public void update(T data) throws Exception {
        open();
        session.saveOrUpdate(data);
        close();
    }

    @Override
    public void delete(T data) throws Exception {
        open();
        session.delete(data);
        close();
    }
}
