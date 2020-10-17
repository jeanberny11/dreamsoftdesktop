package com.dsdata;


import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class ConexionManager {
    public static final int SERVER1=1;
    public static final int SERVER2=2;
    private static ConexionManager instance;
    private ConnectionData connection1;
    private ConnectionData connection2;
    private boolean isInit;
    private boolean isMultiDb;

    private ConexionManager() throws IOException {
        var file=new File("config.json");
        var jsonstring= FileUtils.readFileToString(file,"utf-8");
        var jsonobject=new JSONObject(jsonstring);
        isMultiDb=jsonobject.getBoolean("multidb");
        connection1=new ConnectionData(jsonobject.getString("jdbcurl"),jsonobject.getString("port"),jsonobject.getString("server1"),jsonobject.getString("database1"),jsonobject.getString("userdb1"),jsonobject.getString("passworddb1"));
        connection2=new ConnectionData(jsonobject.getString("jdbcurl"),jsonobject.getString("port"),jsonobject.getString("server2"),jsonobject.getString("database2"),jsonobject.getString("userdb2"),jsonobject.getString("passworddb2"));
        isInit=true;
    }

    public static ConexionManager getInstance() throws IOException {
        if(instance == null){
            synchronized (ConexionManager.class) {
                if(instance == null){
                    instance = new ConexionManager();
                }
            }
        }
        return instance;
    }


    public DsConnection getConnection(int tipo) throws Exception {
        if(!isInit){
            throw new Exception("El manejador de conexiones no ha sido iniciado aun!");
        }
        if(!isMultiDb && tipo!=SERVER1){
            throw new Exception("La applicacion no esta configurada para varios bases de datos");
        }
        switch (tipo){
            case SERVER1:{
                return new DsConnection(connection1);
            }
            case SERVER2:{
                return new DsConnection(connection2);
            }
            default:{
                return new DsConnection(connection1);
            }
        }
    }
}
