package com.logica.repositories;

import com.dsdata.Server;
import com.dsdata.SessionManager;
import com.dsdata.entities.Usuarios;

import javax.persistence.criteria.Root;

public class UsuariosRepository extends AbstractRepository<Usuarios, Integer> {

    public Usuarios authUsuario(String username, String password) throws Exception {
        open();
        var cb = session.getCriteriaBuilder();
        var cr = cb.createQuery(Usuarios.class);
        Root<Usuarios> root = cr.from(Usuarios.class);
        var result=session.createQuery(cr.select(root)
                .where(cb.equal(root.get("usuario"),username))
                .where(cb.equal(root.get("clave"),password))).getResultList();
        close();
        return result.isEmpty()?null:result.get(0);
    }
}