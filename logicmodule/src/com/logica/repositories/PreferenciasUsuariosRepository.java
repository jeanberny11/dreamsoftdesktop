package com.logica.repositories;

import com.dsdata.entities.PreferenciasUsuarios;

public class PreferenciasUsuariosRepository extends AbstractRepository<PreferenciasUsuarios, Integer> {
    /*public PreferenciasUsuariosRepository() throws Exception {
    }

    @Override
    String getTableName() {
        return "preferenciasusuarios";
    }
    @Override
    String getProcedureName() {
        return "get_preferenciasusuarios";
    }
    @Override
    public PreferenciasUsuarios findByID(Integer id) throws Exception {
        begin();
        PreferenciasUsuarios preferenciasusuarios=null;
        final var result=connection.executeprocedure(getProcedureName(), String.valueOf(id));
        if (result.next()){
            preferenciasusuarios= PreferenciasUsuarios.fromResultSet(result);
        }
        result.close();
        commit();
        return preferenciasusuarios;
    }

    @Override
    public List<PreferenciasUsuarios> findAll() throws Exception {
        List<PreferenciasUsuarios> preferenciasusuarios=new ArrayList<>();
        begin();
        final var result=connection.executeprocedure(getProcedureName(),"");
        while (result.next()){
            preferenciasusuarios.add(PreferenciasUsuarios.fromResultSet(result));
        }
        result.close();
        commit();
        return preferenciasusuarios;
    }

    public PreferenciasUsuarios findPreferenciaUsuario(int usuarioid)throws Exception {
        var res=findAll().stream().filter(pref->(pref.getUsuarioid()==usuarioid)).collect(Collectors.toList());
        if(!res.isEmpty()){
            return res.get(0);
        }
        return null;
    }*/
}
