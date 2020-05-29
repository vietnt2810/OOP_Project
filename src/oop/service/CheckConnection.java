/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.service;

import static oop.dao.JDBCConnection.getJDBCConnection;

/**
 *
 * @author thao
 */
public class CheckConnection {
    public boolean checkConnection(){
        if(getJDBCConnection() == null) return false;
        return true;
    }
}
