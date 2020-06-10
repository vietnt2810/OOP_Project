/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
//     */
    public static void main(String[] args)  {
        String username = checkRememberMe();
        UserService userService = new UserService();
        Account acc = userService.checkExistedUser(username);
        if(username != null && getJDBCConnection() != null && acc != null){
            
            Home homeScreen = new Home(acc);
            homeScreen.setVisible(true);
        }else{
            Login login = new Login();
            login.setVisible(true);
        }
        
        
    }
}
