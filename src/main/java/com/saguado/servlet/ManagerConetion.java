package com.saguado.servlet;

import com.saguado.enums.ListaDrivers;

import java.sql.*;

/***
 * Clase encargada de gestionar la conexion con la base de datos
 */
public class ManagerConetion {

    private ListaDrivers DRIVER;
    private String HOST;
    private String PUERTO;
    private String DB;
    private String user;
    private String pass;
    private Connection connection;
    private boolean estaConectado;

    public ManagerConetion() {
        this.DRIVER = ListaDrivers.MYSQL;
        this.HOST = "localhost";
        this.PUERTO = "8889";
        this.DB = "pruebas";
        this.user = "root";
        this.pass = "root";
        this.estaConectado = false;
    }

    public ManagerConetion(ListaDrivers DRIVER, String HOST, String PUERTO, String DB, String user, String pass) {
        this.DRIVER = DRIVER;
        this.HOST = HOST;
        this.PUERTO = PUERTO;
        this.DB = DB;
        this.user = user;
        this.pass = pass;
        this.estaConectado = false;
    }

    /***
     * Genera una url a partir de los campos suministrados en el constructor
     * @return url de conexion
     */
    private String generarURL(){
        String url = null;
        url = DRIVER.getConexion()+HOST+":"+PUERTO+"/"+DB;
        return url;
    }


    private void conectar(){
        try {
            Class.forName(DRIVER.getDriver());
            connection = DriverManager.getConnection(generarURL(),user,pass);
            System.out.println(generarURL());
            estaConectado = true;
        } catch (ClassNotFoundException e) {
            System.out.println("No se ha podido cargar el driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se ha podido conectar con la base de datos.");
        }
    }

    private void desconectar(){
        try {
            connection.close();
            estaConectado = false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUpdate(String sql, String[] campos){
        try {
            conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < campos.length; i++) {
                preparedStatement.setString(i+1,campos[i]);
            }
            preparedStatement.execute();
            preparedStatement.close();
            desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean consultar(String sql, String[] campos){
        boolean loginCorrecto= false;
        PreparedStatement preparedStatement = null;
        try {
            conectar();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < campos.length; i++) {
                preparedStatement.setString(i+1,campos[i]);
            }
            ResultSet resultado = preparedStatement.executeQuery();

            if (resultado.first()){
                loginCorrecto = true;
            }else{
                loginCorrecto = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                desconectar();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        return loginCorrecto;
    }
}
