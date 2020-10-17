package com.dsdata;

import com.dsdata.entities.*;
import org.apache.commons.io.FileUtils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class SessionManager {
    private static SessionManager instance;
    private ServiceRegistry serviceRegistry;
    private ServiceRegistry oserviceRegistry;
    private Configuration configuration1;
    private Configuration configuration2;
    private boolean isInit;
    private boolean isMultiDb;

    private SessionManager() throws IOException {
        init();
        serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration1.getProperties()).build();
        if(isMultiDb){
            oserviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration2.getProperties()).build();
        }
    }
    public static SessionManager getInstance() throws IOException {
        if(instance == null){
            synchronized (SessionManager.class) {
                if(instance == null){
                    instance = new SessionManager();
                }
            }
        }
        return instance;
    }

    private void init() throws IOException {
        var file=new File("config.json");
        var jsonstring= FileUtils.readFileToString(file,"utf-8");
        var jsonobject=new JSONObject(jsonstring);
        isMultiDb=jsonobject.getBoolean("multidb");
        var connection1=new ConnectionData(jsonobject.getString("jdbcurl"),jsonobject.getString("port"),jsonobject.getString("server1"),jsonobject.getString("database1"),jsonobject.getString("userdb1"),jsonobject.getString("passworddb1"));
        var connection2=new ConnectionData(jsonobject.getString("jdbcurl"),jsonobject.getString("port"),jsonobject.getString("server2"),jsonobject.getString("database2"),jsonobject.getString("userdb2"),jsonobject.getString("passworddb2"));
        configuration1=new Configuration();
        iniConfiguration(configuration1,connection1);
        addAnnotatedClass(configuration1);

        if(isMultiDb){
            configuration2=new Configuration();
            addAnnotatedClass(configuration2);
            iniConfiguration(configuration2,connection2);
        }
        isInit=true;
    }

    private void iniConfiguration(final Configuration configuration,ConnectionData connectionData){
        configuration.setProperty("hibernate.connection.driver_class","org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url",connectionData.getJdbcurl()+connectionData.getHost()+":"+connectionData.getPort()+"/"+connectionData.getDatabase());
        configuration.setProperty("hibernate.connection.username", connectionData.getUser());
        configuration.setProperty("hibernate.connection.password", connectionData.getPassword());
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");
        configuration.setProperty("hibernate.connection.pool_size","10");
        configuration.setProperty("hibernate.current_session_context_class", "thread");
    }

    private void addAnnotatedClass(final Configuration configuration){
        configuration.addAnnotatedClass(Usuarios.class);
        configuration.addAnnotatedClass(Grupomenu.class);
        configuration.addAnnotatedClass(Menus.class);
        configuration.addAnnotatedClass(Modulos.class);
        configuration.addAnnotatedClass(PerfilUsuarios.class);
        configuration.addAnnotatedClass(PreferenciasUsuarios.class);
        configuration.addAnnotatedClass(Usuariolevels.class);
    }

    public SessionFactory getSession(Server server) throws Exception {
        if(!isInit){
            throw new Exception("Las configuraciones de la conexion no se han iniciado!");
        }
        if(server==Server.Server2 && !isMultiDb){
            throw new Exception("La aplicacion no esta configurada para multiples conexiones!");
        }
        switch (server){
            case Server1:
            {
                return configuration1.buildSessionFactory(serviceRegistry);
            }
            case Server2:
            {
                return configuration2.buildSessionFactory(oserviceRegistry);
            }
            default:
                return configuration1.buildSessionFactory(serviceRegistry);
        }
    }
}
