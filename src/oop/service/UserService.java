/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.service;

import oop.dao.UserDao;

/**
 *
 * @author thao
 */
public class UserService {
    private UserDao userDao;
    public UserService(){
        userDao = new UserDao();
    }
    public boolean validateLogin(String username, String password){
        return userDao.verifyLogin(username, password);
    }
    public boolean addUser(String username, String password){
        return userDao.addUser(username, password);
    }
}
