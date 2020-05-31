/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.utils;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author thao
 */
public class Utils {
    //Ham hien thi anh vao label
    public static void setImageForLabel(String url, javax.swing.JLabel label){
        ImageIcon imgIcon = new ImageIcon(url);
        Image img = imgIcon.getImage();
        Image imgFit = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconFit = new ImageIcon(imgFit);
        label.setIcon(iconFit);
    }
    
    //Lay absolute path cua anh do nguoi dung chon
    public static String getImageUrl(){
        JFileChooser fileChooser = new JFileChooser();
        String getSelectedImage = "";
        FileNameExtensionFilter filter = new FileNameExtensionFilter("3 Extensions Supported", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int selectedFile = fileChooser.showOpenDialog(null);
        
        if (selectedFile == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            //Get Path of the selected image.
            getSelectedImage = file.getAbsolutePath();      
        }
        return getSelectedImage;
    }
   
    public static void rememberMe(String username){
        File file = null;
        BufferedWriter bw = null;
        try{
            file = new File("./txt/remember.txt");
            if(!file.exists())  file.createNewFile();
            
            bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
            bw.write(username);        
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }finally{
            try {
                bw.close(); 
            } catch (IOException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static String checkRememberMe(){
        File file = null;
        Scanner scan = null;
        try {
          file = new File("./txt/remember.txt");
          if(file.exists()){
            scan = new Scanner(file);
            try{
                return scan.nextLine();
            }catch(Exception e){
                return null;
            }
          }
        }catch(FileNotFoundException e){
            return null;
        }finally{
            try{
                scan.close();
            }catch(NullPointerException e){
                return null;
            }
        }
        return null;
    }
    
    public static void setTextForLabel(JLabel label, String text){
        label.setText(text);
    }
    
}
