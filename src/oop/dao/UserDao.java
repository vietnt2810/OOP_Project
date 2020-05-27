/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static oop.dao.JDBCConnection.getJDBCConnection;

/**
 *
 * @author thao
 */
public class UserDao {
    public boolean verifyLogin(String username, String password){
        Connection conn = null;
        PreparedStatement prdStatement;
        String query;
        try{
            conn = getJDBCConnection();
            query = "SELECT COUNT(*) AS number FROM account WHERE username=? AND password=?";
            
            prdStatement = conn.prepareStatement(query);
            prdStatement.setString(1,username);
            prdStatement.setString(2,password);
            ResultSet rs = prdStatement.executeQuery();
            rs.next();
            if(rs.getInt("number")>0)   return true;
        }catch(SQLException e){
            System.out.println("Login failed " + e.getMessage());
            System.out.println(e.getStackTrace());
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
