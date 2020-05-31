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
import oop.model.Account;
import oop.model.User;

/**
 *
 * @author thao
 */
public class UserDao {
    public User verifyLogin(String username, String password){
        Connection conn = null;
        ResultSet rs=null;
        PreparedStatement prdStatement;
        String query;
        User usr = null;
        try{
            conn = getJDBCConnection();
            query = "SELECT *, COUNT(*) AS number FROM account acc "
                    + "INNER JOIN user usr "
                    + "ON acc.id = usr.accId "
                    + "WHERE acc.username=? AND acc.password=?";
            
            prdStatement = conn.prepareStatement(query);
            prdStatement.setString(1,username);
            prdStatement.setString(2,password);
            rs = prdStatement.executeQuery();
            rs.next();
            if(rs.getInt("number")>0){
                return usr = new User(rs.getInt("usr.id"), rs.getString("usr.lastName"), rs.getString("usr.lastName"), rs.getInt("usr.usrLevel"));
            }
        }catch(SQLException e){
            System.out.println("Login failed " + e.getMessage());
            System.out.println(e.getStackTrace());
        }finally{
            try {
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    public Account checkExistedUser(String username){
        Connection conn = null;
        PreparedStatement prdStatement;
        String query;
        User usr = null;
        Account acc = null;
        try{
            conn = getJDBCConnection();
            query = "SELECT *, COUNT(*) AS number FROM account acc "
                    + "INNER JOIN user usr "
                    + "ON acc.id = usr.accId "
                    + "WHERE acc.username=?";
            
            prdStatement = conn.prepareStatement(query);
            prdStatement.setString(1,username);
            ResultSet rs = prdStatement.executeQuery();
            rs.next();
            if(rs.getInt("number")>0){
                usr = new User(rs.getInt("usr.id"), rs.getString("usr.lastName"), rs.getString("usr.lastName"), rs.getInt("usr.usrLevel"));
                String password = rs.getString("acc.password");
                if(usr == null)  return null;
                return acc = new Account(username,password, usr);
            }   
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
        return null;
    }
    public boolean addUser(String username, String password){
        Connection conn = null;
        PreparedStatement prdStatement;
        String query;
        
        try{
            conn = getJDBCConnection();
            if(this.checkExistedUser(username) != null)    return false;
            
            query = "INSERT INTO account(username, password) VALUES(?,?)";
            
            prdStatement = conn.prepareStatement(query);
            prdStatement.setString(1,username);
            prdStatement.setString(2,password);
            int res = prdStatement.executeUpdate();
            
            if(res > 0) return true;
        }catch(SQLException e){
            System.out.println("Login failed " + e.getMessage());
            System.out.println(e.getStackTrace());
            return false;
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
