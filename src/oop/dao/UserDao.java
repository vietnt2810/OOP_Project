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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static oop.dao.JDBCConnection.getJDBCConnection;
import oop.model.Account;
import oop.model.AvgScore;
import oop.model.User;

/**
 *
 * @author thao
 */
public class UserDao {
    public Account verifyLogin(String username, String password){
        Connection conn = null;
        ResultSet rs=null;
        PreparedStatement prdStatement;
        String query, avtUrl;
        User usr = null;
        Account acc = null;
        AvgScore avg = null;
        int accId;
        try{
            conn = getJDBCConnection();
            query = "SELECT * FROM account acc "
                    + "INNER JOIN user usr "
                    + "ON acc.id = usr.accId "
                    + "WHERE acc.username=? AND acc.password=?";
            
            prdStatement = conn.prepareStatement(query);
            prdStatement.setString(1,username);
            prdStatement.setString(2,password);
            rs = prdStatement.executeQuery();
            
            if(rs.next()){
                avg = new AvgScore(rs.getFloat("usr.level1Score"),rs.getFloat("usr.level2Score"),rs.getFloat("usr.level3Score"));
                usr = new User(rs.getInt("usr.id"), rs.getString("usr.firstName"), rs.getString("usr.lastName"), rs.getInt("usr.usrLevel"), avg);
                avtUrl = rs.getString("acc.avatarUrl");
                accId = rs.getInt("acc.id");
                return  acc = new Account(accId, username,password, usr, avtUrl);
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
        String query, avtUrl;
        User usr = null;
        Account acc = null;
        AvgScore avg = null;
        int accId;
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
                avg = new AvgScore(rs.getFloat("usr.level1Score"),rs.getFloat("usr.level2Score"),rs.getFloat("usr.level3Score"));
                usr = new User(rs.getInt("usr.id"), rs.getString("usr.firstName"), rs.getString("usr.lastName"), rs.getInt("usr.usrLevel"), avg);
                String password = rs.getString("acc.password");
                accId = rs.getInt("acc.id");
                if(usr == null)  return null;
                avtUrl = rs.getString("acc.avatarUrl");
                
                return acc = new Account(accId, username,password, usr, avtUrl);
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
        int query1 = 0;
        int query2 = 1;
        try{
            conn = getJDBCConnection();
            if(this.checkExistedUser(username) != null)    return false;
            
            query = "INSERT INTO account(username, password) VALUES(?,?)";     
            prdStatement = conn.prepareStatement(query);
            prdStatement.setString(1,username);
            prdStatement.setString(2,password);
            query1 = prdStatement.executeUpdate();
            System.out.println(getExistedAccountId(username));
            query = "INSERT INTO user(accId) VALUES(?)";      
            prdStatement = conn.prepareStatement(query);
            prdStatement.setInt(1, getExistedAccountId(username));
            query2 = prdStatement.executeUpdate();
            if(query1 > 0 && query2 > 0) return true;          
        }catch(SQLException e){
            System.out.println("Add user failed " + e.getMessage());
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
    
    private int getExistedAccountId(String username){
        Account acc = null;
        Connection conn = null;
        PreparedStatement prdStatement;
        String query;
        try{
            conn = getJDBCConnection();
            query = "SELECT * FROM account "
                    + "WHERE username=?";
            
            prdStatement = conn.prepareStatement(query);
            prdStatement.setString(1,username);
            ResultSet rs = prdStatement.executeQuery();
            
            if(rs.next()){
                return rs.getInt("id");
            }   
        }catch(SQLException e){
            System.out.println(e.getStackTrace());
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }
    
    public boolean saveAvatarUrl(String url, int accId){
        Connection conn = null;
        PreparedStatement prdStatement;
        String query;
        int result;
        try{
            conn = getJDBCConnection();
            query = "UPDATE account SET avatarUrl = ? "
                    + "WHERE id = ?";
            prdStatement = conn.prepareStatement(query);
            prdStatement.setString(1, url);
            prdStatement.setInt(2, accId);
            result = prdStatement.executeUpdate();
            
            if(result > 0){
                return true;
            }   
        }catch(SQLException e){
            System.out.print("save image fail ");
            System.out.println(e.getMessage());
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
     public boolean changePass(int accId, String newPass){
        Connection conn = null;
        PreparedStatement prdStatement;
        String query;
        int result;
        try{
            conn = getJDBCConnection();
            
            query = "UPDATE account SET password = ? WHERE id=?";     
            prdStatement = conn.prepareStatement(query);
            prdStatement.setString(1,newPass);
            prdStatement.setInt(2,accId);
            result = prdStatement.executeUpdate();
            
            return result > 0;
        }catch(SQLException e){
            System.out.println("update password fail" + e.getMessage());
            System.out.println(e.getStackTrace());
            return false;
        }finally{
             try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean updateUser(Account acc){
        Connection conn = null;
        PreparedStatement prdStatement;
        String query;
        int result;
        try{
            conn = getJDBCConnection();
            
            query = "UPDATE user SET firstName = ?, lastName = ? WHERE id=?";     
            prdStatement = conn.prepareStatement(query);
            prdStatement.setString(1,acc.getUser().getFirstName());
            prdStatement.setString(2,acc.getUser().getLastName());
            prdStatement.setInt(3,acc.getUser().getId());
            result = prdStatement.executeUpdate();
            
            return result > 0;
        }catch(SQLException e){
            System.out.println("update password fail" + e.getMessage());
            System.out.println(e.getStackTrace());
            return false;
        }finally{
             try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
}
