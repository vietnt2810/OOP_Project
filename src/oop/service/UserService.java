/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
        Account acc;  
        
        acc = userDao.verifyLogin(username, password);
        if(acc == null)  return null;
        return acc;
    }
    
    public boolean addUser(String username, String password){
        return userDao.addUser(username, password);
    }
    
    public Account checkExistedUser(String username){
        return userDao.checkExistedUser(username);
    }
    
    public boolean saveAvatarUrl(String url, int accId){
        try {
            if(url.isEmpty())
                return false;
            String newUrl = url.replace("\\", "/");
            String[] arr = newUrl.split("/");
            String imageUrl = "./avatar/" + arr[arr.length-1];
            Files.copy(Paths.get(newUrl), Paths.get("./avatar/"+arr[arr.length-1]), StandardCopyOption.REPLACE_EXISTING);
            return userDao.saveAvatarUrl(imageUrl, accId);
        } catch (IOException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
