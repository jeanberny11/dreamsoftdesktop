package com.logica.repositories;

import com.dsdata.Server;
import com.dsdata.SessionManager;
import com.dsdata.entities.Modulos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModulosRepository extends AbstractRepository<Modulos, Integer> {
    /*
        public ModulosRepository() throws Exception {
        }

        @Override
        String getTableName() {
            return "modulos";
        }

        @Override
        String getProcedureName() {
            return "get_modulos";
        }

        @Override
        public Modulos findByID(Integer moduloid) throws Exception {
            begin();
            Modulos modulos=null;
            final var result=connection.executeprocedure(getProcedureName(), String.valueOf(moduloid));
            if (result.next()){
                modulos= Modulos.fromResultSet(result);
            }
            result.close();
            commit();
            return modulos;
        }

        public List<Modulos> findAllPerfil(int perfilid) throws Exception {
            List<Modulos> modulos=new ArrayList<>();
            begin();
            final var result=connection.executeprocedure("get_modulosperfil", String.valueOf(perfilid));
            while (result.next()){
                modulos.add(Modulos.fromResultSet(result));
            }
            result.close();
            commit();
            return modulos;
        }

        @Override
        public List<Modulos> findAll() throws Exception {
            List<Modulos> modulos=new ArrayList<>();
            begin();
            final var result=connection.executeprocedure(getProcedureName(),"");
            while (result.next()){
                modulos.add(Modulos.fromResultSet(result));
            }
            result.close();
            commit();
            return modulos;
        }

        public List<Modulos> findAllActive() throws Exception {
            return findAll().stream().filter(modulos -> modulos.isActivo()).collect(Collectors.toList());
        }*/
    public List<Modulos> findAllActive() throws Exception {
        return findAll(Modulos.class).stream().filter(Modulos::isActivo).collect(Collectors.toList());
    }

    public List<Modulos> findAllPerfil(int perfilid) throws Exception {
        var session = SessionManager.getInstance().getSession(Server.Server1).openSession();
        session.beginTransaction();
        var query = session.createNamedQuery("get_modulosperfil",Modulos.class);
        query.setParameter("xperfilid", perfilid);
        var result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
