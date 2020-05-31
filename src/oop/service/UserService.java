/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oop.dao.UserDao;
import oop.model.Account;
import oop.model.User;

/**
 *
 * @author thao
 */
public class UserService {
    private UserDao userDao;
    
    public UserService(){
        userDao = new UserDao();
    }
    
    public Account validateLogin(String username, String password){
        User usr;
        Account acc;  
        
        usr = userDao.verifyLogin(username, password);
        if(usr == null)  return null;
        acc = new Account(username,password, usr);
        
        return acc;
    }
    
    public boolean addUser(String username, String password){
        return userDao.addUser(username, password);
    }
    
    public Account checkExistedUser(String username){
        return userDao.checkExistedUser(username);
    }
    
}
