package com.logica.repositories;

import com.dsdata.Server;
import com.dsdata.SessionManager;
import com.dsdata.entities.Grupomenu;

import java.util.ArrayList;
import java.util.List;

public class GrupoMenuRepository extends AbstractRepository<Grupomenu, Integer> {
    /*public GrupoMenuRepository() throws Exception {
        super();
    }

    @Override
    String getTableName() {
        return "grupomenu";
    }

    @Override
    String getProcedureName() {
        return "get_grupomenu";
    }

    @Override
    public Grupomenu findByID(Integer id) throws Exception {
        begin();
        Grupomenu grupomenu = null;
        final var result = connection.executeprocedure(getProcedureName(), String.valueOf(id));
        if (result.next()) {
            grupomenu = Grupomenu.fromResultSet(result);
        }
        result.close();
        commit();
        return grupomenu;
    }

    @Override
    public List<Grupomenu> findAll() throws Exception {
        List<Grupomenu> grupomenu = new ArrayList<>();
        begin();
        final var result = connection.executeprocedure(getProcedureName(), "");
        while (result.next()) {
            grupomenu.add(Grupomenu.fromResultSet(result));
        }
        result.close();
        commit();
        return grupomenu;
    }

    public List<Grupomenu> findAllPerfil(int perfilid, int moduloid) throws Exception {
        List<Grupomenu> grupomenu = new ArrayList<>();
        begin();
        final var result = connection.executeprocedure("get_grupomenuxperfil", String.valueOf(perfilid) + "," + String.valueOf(moduloid));
        while (result.next()) {
            grupomenu.add(Grupomenu.fromResultSet(result));
        }
        result.close();
        commit();
        return grupomenu;
    }

    public List<Grupomenu> findAllModulo(int moduloid) throws Exception {
        List<Grupomenu> grupomenu = new ArrayList<>();
        begin();
        final var result = connection.executeprocedure("get_grupomenuxmodulo", String.valueOf(moduloid));
        while (result.next()) {
            grupomenu.add(Grupomenu.fromResultSet(result));
        }
        result.close();
        commit();
        return grupomenu;
    }*/
    public List<Grupomenu> findAllPerfil(int perfilid, int moduloid) throws Exception {
        var session= SessionManager.getInstance().getSession(Server.Server1).openSession();
        session.beginTransaction();
        var query=session.createNamedQuery("get_grupomenuxperfil",Grupomenu.class);
        query.setParameter("xperfilid",perfilid);
        query.setParameter("xmoduloid",moduloid);
        var result=query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Grupomenu> findAllModulo(int moduloid) throws Exception {
        var session= SessionManager.getInstance().getSession(Server.Server1).openSession();
        session.beginTransaction();
        var query=session.createNamedQuery("get_grupomenuxmodulo",Grupomenu.class);
        query.setParameter("xmoduloid",moduloid);
        var result=query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}

