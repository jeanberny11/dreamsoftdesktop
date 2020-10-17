package com.dsdata;

public class ConnectionData {
    private String jdbcurl;
    private String port;
    private String host;
    private String database;
    private String user;
    private String password;

    public ConnectionData(String jdbcurl, String port, String host, String database, String user, String password) {
        this.jdbcurl=jdbcurl;
        this.port=port;
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public String getJdbcurl() {
        return jdbcurl;
    }

    public void setJdbcurl(String jdbcurl) {
        this.jdbcurl = jdbcurl;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
