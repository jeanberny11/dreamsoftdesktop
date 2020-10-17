package com.logica.repositories;

import com.dsdata.Server;
import com.dsdata.SessionManager;
import com.dsdata.entities.Menus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenusRepository extends AbstractRepository<Menus, Integer> {
    /*public MenusRepository() throws Exception {
    }

    @Override
    String getTableName() {
        return "menus";
    }

    @Override
    String getProcedureName() {
        return "get_menus";
    }

    @Override
    public Menus findByID(Integer id) throws Exception {
        begin();
        Menus menus = null;
        final var result = connection.executeprocedure(getProcedureName(), String.valueOf(id));
        if (result.next()) {
            menus = Menus.fromResultSet(result);
        }
        result.close();
        commit();
        return menus;
    }

    @Override
    public List<Menus> findAll() throws Exception {
        List<Menus> menus = new ArrayList<>();
        begin();
        final var result = connection.executeprocedure(getProcedureName(), "");
        while (result.next()) {
            menus.add(Menus.fromResultSet(result));
        }
        result.close();
        commit();
        return menus;
    }

    public List<Menus> findAllActiveMenu() throws Exception {
        return findAll().stream().filter(Menus::getActivo).collect(Collectors.toList());
    }

    public List<Menus> findAllPerfil(int perfilid, int moduloid, int grupoid) throws Exception {
        List<Menus> menus = new ArrayList<>();
        begin();
        final var result = connection.executeprocedure(getProcedureName(), String.valueOf(perfilid)+","+ String.valueOf(moduloid)+","+ String.valueOf(grupoid));
        while (result.next()) {
            menus.add(Menus.fromResultSet(result));
        }
        result.close();
        commit();
        return menus;
    }

    public List<Menus> findAllModulo(int moduloid, int grupoid) throws Exception {
        return findAll().stream().filter(menus -> (menus.getActivo() && menus.getModuloid()==moduloid && menus.getGrupomenuid()==grupoid)).collect(Collectors.toList());
    }*/

    public List<Menus> findAllModulo(int moduloid, int grupoid) throws Exception {
        return findAll(Menus.class).stream().filter(menus -> (menus.getActivo() && menus.getModuloid()==moduloid && menus.getGrupomenuid()==grupoid)).collect(Collectors.toList());
    }

    public List<Menus> findAllPerfil(int perfilid, int moduloid, int grupoid) throws Exception {
        var session = SessionManager.getInstance().getSession(Server.Server1).openSession();
        session.beginTransaction();
        var query = session.createNamedQuery("get_menus",Menus.class);
        query.setParameter("xperfilid", perfilid);
        query.setParameter("xmoduloid", moduloid);
        query.setParameter("xgrupoid", grupoid);
        var result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
