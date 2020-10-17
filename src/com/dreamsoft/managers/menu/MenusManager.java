package com.dreamsoft.managers.menu;


import com.dreamsoft.models.GrupoMenuModel;
import com.dreamsoft.models.MenuModel;
import com.dreamsoft.models.ModuloModel;
import com.dreamsoft.models.UsuarioModel;
import com.dsdata.entities.Modulos;
import com.logica.repositories.GrupoMenuRepository;
import com.logica.repositories.MenusRepository;
import com.logica.repositories.ModulosRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.stream.Collectors;

public class MenusManager {
    private static MenusManager instance;
    private final MenusRepository menusRepository = new MenusRepository();
    private final ModulosRepository modulosRepository = new ModulosRepository();
    private final GrupoMenuRepository grupoMenuRepository = new GrupoMenuRepository();

    private MenusManager() throws Exception {
    }

    public static MenusManager getInstance() throws Exception {
        if (instance == null) {
            synchronized (MenusManager.class) {
                if (instance == null) {
                    instance = new MenusManager();
                }
            }
        }
        return instance;
    }

    public ModuloModel findModuloById(int moduloid) throws Exception {
        return new ModuloModel(modulosRepository.findByID(Modulos.class,moduloid));
    }

    public ObservableList<ModuloModel> getModulos(UsuarioModel usuario) throws Exception {
        final ObservableList<ModuloModel> modulos = FXCollections.observableArrayList();
        var result = (usuario.getLevelid() == 1) ? modulosRepository.findAllActive() : modulosRepository.findAllPerfil(usuario.getPerfilid());
        modulos.addAll(result.stream().map(ModuloModel::new).collect(Collectors.toList()));
        return modulos;
    }

    public ModuloModel getInicialModulo(UsuarioModel usuario,int moduloid) throws Exception {
        return getModulos(usuario).stream().filter(moduloModel -> moduloModel.getModuloid()==moduloid).collect(Collectors.toList()).get(0);
    }

    public ObservableList<GrupoMenuModel> getGrupos(UsuarioModel usuario, ModuloModel modulos) throws Exception {
        return FXCollections.observableArrayList(
                (usuario.getLevelid() == 1 ? grupoMenuRepository.findAllModulo(modulos.getModuloid()) : grupoMenuRepository.findAllPerfil(usuario.getPerfilid(), modulos.getModuloid()))
                        .stream().map(GrupoMenuModel::new).collect(Collectors.toList())
        );
    }

    public ObservableList<MenuModel> getMenus(UsuarioModel usuario, ModuloModel modulo, GrupoMenuModel grupo) throws Exception {
        return FXCollections.observableArrayList(
                (usuario.getLevelid() == 1 ? menusRepository.findAllModulo(modulo.getModuloid(), grupo.getGrupomenuid()) : menusRepository.findAllPerfil(usuario.getPerfilid(), modulo.getModuloid(), grupo.getGrupomenuid()))
                        .stream().map(MenuModel::new).collect(Collectors.toList())
        );
    }
}

