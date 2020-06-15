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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static oop.dao.JDBCConnection.getJDBCConnection;

/**
 *
 * @author thao
 */
public class StatisticDao {
    private Connection conn;
    
    public StatisticDao(){
        conn = getJDBCConnection();
    }
    
    public ArrayList<LinkedHashMap<Date,Float>> getInfoForScoreChart(int usrId){
        ArrayList<LinkedHashMap<Date,Float>> arr = new ArrayList<LinkedHashMap<Date,Float>>();
        LinkedHashMap<Date,Float> lv1 = new LinkedHashMap<Date,Float>();
        LinkedHashMap<Date,Float> lv2 = new LinkedHashMap<Date,Float>();
        LinkedHashMap<Date,Float> lv3 = new LinkedHashMap<Date,Float>();
        
        PreparedStatement prdStatement;
        ResultSet rs = null;
        
        String query = "SELECT * FROM avg_score WHERE usrId = ? ORDER BY id asc";
        
         try {
            prdStatement = conn.prepareStatement(query);
            prdStatement.setInt(1, usrId);
            rs = prdStatement.executeQuery();
            while(rs.next()){
                Date date = rs.getDate("date");
//                System.out.println(date);
                lv1.put(date, rs.getFloat("level1Score"));
                lv2.put(date, rs.getFloat("level2Score"));
                lv3.put(date, rs.getFloat("level3Score"));
                System.out.println(lv1);
            }
            arr.add(lv1);
            arr.add(lv2);
            arr.add(lv3);
         } catch (SQLException ex) {
             Logger.getLogger(StatisticDao.class.getName()).log(Level.SEVERE, null, ex);
         }
      return arr;
    }
    
    public LinkedHashMap<Date,Float> getInfoForTimeChart(int usrId){
        ArrayList<HashMap<Date,Float>> arr = new ArrayList<HashMap<Date,Float>>();
        LinkedHashMap<Date,Float> trainingTime = new LinkedHashMap<Date,Float>();
        
        PreparedStatement prdStatement;
        ResultSet rs = null;
        
        String query = "SELECT conpletedDate as date, sum(completeTime) as totalTime FROM test_history "
                + "WHERE usrId = ? "
                + "GROUP BY CAST(conpletedDate AS DATE) "
                + "ORDER BY id ASC "
                + "LIMIT 7";
        
         try {
            prdStatement = conn.prepareStatement(query);
            prdStatement.setInt(1, usrId);
            rs = prdStatement.executeQuery();
            while(rs.next()){
                trainingTime.put(rs.getDate("date"), rs.getFloat("totalTime")/60);
            }
         } catch (SQLException ex) {
             Logger.getLogger(StatisticDao.class.getName()).log(Level.SEVERE, null, ex);
         }
      return trainingTime;
    }
    
    public float[] calculateAvgScore(int usrId){
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
        
        query = "UPDATE user SET level1Score=?, level2Score=?, level3Score=? "
                + "WHERE id = ?";
        try {
            prdStatement = conn.prepareStatement(query);
            prdStatement.setFloat(1, avg[0]);
            prdStatement.setFloat(2, avg[1]);
            prdStatement.setFloat(3, avg[2]);
            prdStatement.setInt(4, usrId);
            prdStatement.executeUpdate();
            return avg;
         } catch (SQLException ex) {
             Logger.getLogger(StatisticDao.class.getName()).log(Level.SEVERE, null, ex);
         }
        return avg;
    }
    
    public void createAvgScoreForDay(int usrId){
        PreparedStatement prdStatement;
        ResultSet rs = null;
        java.sql.Date date = new java.sql.Date((new Date()).getTime());
        int i = 0;
        float[] avg = this.calculateAvgScore(usrId);
        String query = "SELECT count(*) as count "
                + "FROM avg_score "
                + "WHERE usrId = ? "
                + "AND date = ?";
    
        try {
            prdStatement = conn.prepareStatement(query);
            prdStatement.setInt(1, usrId);
            prdStatement.setDate(2, date);
            rs = prdStatement.executeQuery();
            
            rs.next();
            if(rs.getInt("count")>0){
                query = "UPDATE avg_score SET level1Score = ?, level2Score = ?, level3Score = ? "
                        + "WHERE date = ?";
                
                prdStatement = conn.prepareStatement(query);
                prdStatement.setFloat(1, avg[0]);
                prdStatement.setFloat(2, avg[1]);
                prdStatement.setFloat(3, avg[2]);
                prdStatement.setDate(4, date);
                prdStatement.executeUpdate();
            }else{
                query = "INSERT INTO avg_score(usrId, level1Score,level2Score,level3Score,date) "
                        + "VALUES(?,?,?,?,?)";
                
                prdStatement = conn.prepareStatement(query);
                prdStatement.setInt(1, usrId);
                prdStatement.setFloat(2, avg[0]);
                prdStatement.setFloat(3, avg[0]);
                prdStatement.setFloat(4, avg[0]);
                prdStatement.setDate(5, date);
                prdStatement.executeUpdate();
            }
         } catch (SQLException ex) {
             Logger.getLogger(StatisticDao.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
