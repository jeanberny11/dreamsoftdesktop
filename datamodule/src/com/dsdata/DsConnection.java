package com.dsdata;

import java.sql.*;

public class DsConnection {
    private Connection connection;
    private ConnectionData connectionData;
    private boolean isOpen;
    private boolean work;

    public DsConnection(ConnectionData connectionData) {
        this.connectionData = connectionData;
        isOpen=false;
        work=false;
    }

    public Connection getConnection() throws Exception {
        if (!isOpen || connection==null){
            throw new Exception("Esta conexion esta cerrada!");
        }
        return connection;
    }

    public void open() throws SQLException {
        if(!work && !isOpen){
            var url=connectionData.getJdbcurl()+connectionData.getHost()+":"+connectionData.getPort()+"/"+connectionData.getDatabase();
           connection= DriverManager.getConnection(url, connectionData.getUser(), connectionData.getPassword());
           isOpen=true;
        }
    }

    public void close() throws SQLException {
        if(!work && connection!=null){
            connection.close();
            isOpen=false;
        }
    }

    public void beginwork() throws Exception {
        if(work){
            throw new Exception("Ya hay una transaccion iniciada");
        }
        open();
        work = true;
        var pps = connection.prepareStatement("BEGIN WORK");
        pps.execute();
        pps.close();
    }

    public void rollback() throws Exception {
        if(!work){
            throw new Exception("No hay una transaccion iniciada");
        }
        var pps = connection.prepareStatement("ROLLBACK");
        pps.execute();
        pps.close();
        work = false;
        close();
    }

    public void commit() throws Exception {
        if(!work){
            throw new Exception("No hay una transaccion iniciada");
        }
        var pps = connection.prepareStatement("COMMIT WORK");
        pps.execute();
        pps.close();
        work = false;
        close();
    }

    public ResultSet query(String sql) throws Exception {
        if (!isOpen){
            throw new Exception("La conexion esta cerrada no se puede ejecutar ese query!");
        }
        var stm=connection.createStatement();
        return stm.executeQuery(sql);
    }

    public ResultSet queryEdit(String sql) throws Exception {
        if (!isOpen){
            throw new Exception("La conexion esta cerrada no se puede ejecutar ese query!");
        }
        var stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return stmt.executeQuery(sql);
    }

    public ResultSet executeprocedure(String nombreproc, String param) throws Exception {
        if (!isOpen){
            throw new Exception("La conexion esta cerrada no se puede ejecutar ese query!");
        }
        String query = "{? = call " + nombreproc + "(" + param + ") }";
        var cs = connection.prepareCall(query);
        cs.registerOutParameter(1, Types.OTHER);
        cs.execute();
        return (ResultSet) cs.getObject(1);
    }

        public int ejecutarquery(String sql) throws Exception {
        if (!isOpen){
            throw new Exception("La conexion esta cerrada no se puede ejecutar ese query!");
        }
        int result=0;
        var stmt = connection.createStatement();
        result = stmt.executeUpdate(sql);
        stmt.close();
        return result;
    }
}
