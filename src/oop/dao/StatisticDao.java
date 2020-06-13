/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.dao;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static oop.dao.JDBCConnection.getJDBCConnection;
import oop.model.History;

/**
 *
 * @author thao
 */
public class StatisticDao {
     private Connection conn;
    public StatisticDao(){
        conn = getJDBCConnection();
    }
    public ArrayList<HashMap<Date,Float>> getInfoForScoreChart(int usrId){
        ArrayList<HashMap<Date,Float>> arr = new ArrayList<HashMap<Date,Float>>();
        HashMap<Date,Float> lv1 = new HashMap<Date,Float>();
        HashMap<Date,Float> lv2 = new HashMap<Date,Float>();
        HashMap<Date,Float> lv3 = new HashMap<Date,Float>();
        
        PreparedStatement prdStatement;
        ResultSet rs = null;
        
        String query = "SELECT * FROM avg_score WHERE usrId = ?";
        
         try {
            prdStatement = conn.prepareStatement(query);
            prdStatement.setInt(1, usrId);
            rs = prdStatement.executeQuery();
            while(rs.next()){
                lv1.put(rs.getDate("date"), rs.getFloat("level1Score"));
                lv2.put(rs.getDate("date"), rs.getFloat("level2Score"));
                lv3.put(rs.getDate("date"), rs.getFloat("level3Score"));
            }
            arr.add(lv1);
            arr.add(lv2);
            arr.add(lv3);
         } catch (SQLException ex) {
             Logger.getLogger(StatisticDao.class.getName()).log(Level.SEVERE, null, ex);
         }
      return arr;
    }
    
    public HashMap<Date,Float> getInfoForTimeChart(int usrId){
        ArrayList<HashMap<Date,Float>> arr = new ArrayList<HashMap<Date,Float>>();
        HashMap<Date,Float> trainingTime = new HashMap<Date,Float>();
        
        PreparedStatement prdStatement;
        ResultSet rs = null;
        
        String query = "SELECT conpletedDate as date, sum(completeTime) as totalTime FROM test_history "
                + "WHERE usrId = ? "
                + "GROUP BY conpletedDate "
                + "ORDER BY id ASC";
        
         try {
            prdStatement = conn.prepareStatement(query);
            prdStatement.setInt(1, usrId);
            rs = prdStatement.executeQuery();
            while(rs.next()){
                trainingTime.put(rs.getDate("date"), rs.getFloat("totalTime"));
            }
         } catch (SQLException ex) {
             Logger.getLogger(StatisticDao.class.getName()).log(Level.SEVERE, null, ex);
         }
      return trainingTime;
    }
    
    public void calculateAvgScore(int usrId){
        float[] avg = new float[3];
        PreparedStatement prdStatement;
        ResultSet rs = null;
        int i = 0;
        String query = "SELECT avg(th.score) as avgScore "
                + "FROM test_history th "
                + "INNER JOIN test t "
                + "ON t.id = th.testId "
                + "WHERE usrId = ? "
                + "GROUP BY t.testLevel";
        
        try {
            prdStatement = conn.prepareStatement(query);
            prdStatement.setInt(1, usrId);
            rs = prdStatement.executeQuery();
            while(rs.next()){
                avg[i] = rs.getFloat("avgScore");
                i++;
            }
         } catch (SQLException ex) {
             Logger.getLogger(StatisticDao.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
