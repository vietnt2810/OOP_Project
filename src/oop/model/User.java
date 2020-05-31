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
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private int level = 1;
    
    public User(){
    }
    public User(int id) {
        this.id = id;
    }
    public User(int id, String firstName, String lastName) {
        this(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public User(int id, String firstName, String lastName, int level){
        this(id, firstName, lastName);
        this.level = level;
    }
    
    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if(level > 0 && level <4 )  this.level = level;
        else
            System.err.println("0<Level<4");
    }
    
    
}
