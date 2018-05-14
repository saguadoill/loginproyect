package com.saguado.enums;

public enum ListaDrivers {

    MYSQL("com.mysql.jdbc.Driver","jdbc:mysql://"),
    MICROSOFT_SQL("com.microsoft.sqlserver.jdbc.SQLServerDriver","jdbc:sqlserver://"),
    ORACLE_SQL("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@");

    private String driver;
    private String conexion;

    ListaDrivers(String driver, String conexion) {
        this.driver = driver;
        this.conexion = conexion;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getConexion() {
        return conexion;
    }

    public void setConexion(String conexion) {
        this.conexion = conexion;
    }
}
