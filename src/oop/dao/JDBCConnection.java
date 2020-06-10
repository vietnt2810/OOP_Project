/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.dao;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author thao
 */
public class JDBCConnection {
    public static Connection getJDBCConnection(){
        final String url = "jdbc:mysql://localhost:3306/oop";
        final String user = "root";
        final String password = "";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return (Connection) DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        
        return null;
    }
}
