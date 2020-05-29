/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static oop.dao.JDBCConnection.getJDBCConnection;
import static oop.utils.Utils.checkRememberMe;
import oop.view.Home;
import oop.view.Login;

/**
 *
 * @author thao
 */
public class MiniProjectOOP extends JFrame{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(checkRememberMe() != null && getJDBCConnection() != null){
            Home homeScreen = new Home();
            homeScreen.setVisible(true);
        }else{
            Login login = new Login();
            login.setVisible(true);
        }
    }
    
}
