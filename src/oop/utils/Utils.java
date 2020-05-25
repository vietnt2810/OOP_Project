/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.utils;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
   
    
}
