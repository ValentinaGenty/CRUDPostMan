package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private static Connection connection=null;
    private ConnectionFactory(){

    }

    public static Connection getConnection(){
        if(connection==null){
            try{
                Class.forName("org.postgresql.Driver");
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
            ResourceBundle bundle =ResourceBundle.getBundle("jdbc");
            String db_url= bundle.getString("url");//";
            String db_username=bundle.getString("username");
            String db_password=bundle.getString("password");
            //String db_url ="jdbc:postgresql://localhost:5432/crudpostman";
            //String db_username = "postgres" ;
            //String db_password = "K4^*eFOl3f$3";
            try {
                connection = DriverManager.getConnection(db_url, db_username, db_password);
            } catch(SQLException e){
                System.out.println("Something went wrong when creating the connection!");
                e.printStackTrace();
            }

        }
        return connection;
    }
}
