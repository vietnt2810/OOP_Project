/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.model;

/**
 *
 * @author thao
 */
public class Account {
    private int id;
    private String username;
    private String avatarUlr;
    private String password;
    private User user;

    public Account(){      
    }
    public Account(int id, String username, String password, User user, String avatarUrl) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.user = user;
        this.avatarUlr = avatarUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUlr() {
        return avatarUlr;
    }

    public void setAvatarUlr(String avatarUlr) {
        this.avatarUlr = avatarUlr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
