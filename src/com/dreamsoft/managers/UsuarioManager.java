package com.dreamsoft.managers;

import com.dreamsoft.managers.menu.MenusManager;
import com.dreamsoft.models.PreferenciasUsuariosModel;
import com.dreamsoft.models.UsuarioModel;
import com.dsdata.entities.PreferenciasUsuarios;
import com.dsdata.entities.Usuarios;
import com.logica.repositories.PreferenciasUsuariosRepository;
import com.logica.repositories.UsuariosRepository;

public class UsuarioManager {
    private static UsuarioManager instance;
    private final UsuariosRepository usuariosRepository=new UsuariosRepository();
    private final PreferenciasUsuariosRepository userprefRepo=new PreferenciasUsuariosRepository();

    public  UsuarioModel usuario=new UsuarioModel();

    private UsuarioManager() throws Exception {
    }

    public static UsuarioManager getInstance() throws Exception {
        if(instance == null){
            synchronized (UsuarioManager.class) {
                if(instance == null){
                    instance = new UsuarioManager();
                }
            }
        }
        return instance;
    }

    public void save() throws Exception {
        Usuarios usuarios=new Usuarios();
        usuarios.setUsuarioid(usuario.getUsuarioid());
        usuariosRepository.create(usuarios);
    }

    public UsuarioModel getAuthUser(String usuario, String clave) throws Exception {
        var res=usuariosRepository.authUsuario(usuario,clave);
        return res!=null?new UsuarioModel(res):null;
    }

    public PreferenciasUsuariosModel getUserPreferences(UsuarioModel user) throws Exception {
        var pref=userprefRepo.findByID(PreferenciasUsuarios.class,user.getUsuarioid());
        var modulo= MenusManager.getInstance().findModuloById(pref.getModuloinicial());
        return new PreferenciasUsuariosModel(user.getUsuarioid(),modulo);
    }
}
