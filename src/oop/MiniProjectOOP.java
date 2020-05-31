/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import javax.swing.JFrame;
import static oop.dao.JDBCConnection.getJDBCConnection;
import oop.model.Account;
import oop.service.UserService;
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
        String username = checkRememberMe();
        Account acc = null;
        if(username != null && getJDBCConnection() != null){
            UserService userService = new UserService();
            acc = userService.checkExistedUser(username);
            Home homeScreen = new Home(acc);
            homeScreen.setVisible(true);
        }else{
            Login login = new Login();
            login.setVisible(true);
        }
    }
    
}
