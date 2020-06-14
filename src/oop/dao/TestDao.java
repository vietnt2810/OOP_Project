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
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import static oop.dao.JDBCConnection.getJDBCConnection;
import oop.model.Account;
import oop.model.History;
import oop.model.TestLesson;

/**
 *
 * @author thao
 */
public class TestDao {
    private Connection conn;
    public TestDao(){
        conn = getJDBCConnection();
    }
    
    public ArrayList<String> getTestList(int level){
        try {
            ResultSet rs=null;
            PreparedStatement prdStatement;
            String query;
            ArrayList<String> testList = new ArrayList<>();
            
            
            query = "SELECT testName FROM test WHERE testLevel = ?";
            prdStatement = conn.prepareStatement(query);
            prdStatement.setInt(1, level);
            
            rs = prdStatement.executeQuery();
            
            while(rs.next()){
                testList.add(rs.getString("testName"));
                
            };           
            return testList;
        } catch (SQLException ex) {
            Logger.getLogger(TestDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public TestLesson getTestDetail(String testName){
        TestLesson testLesson = null;
        ResultSet rs=null;
        PreparedStatement prdStatement;
        String query;
        ArrayList<String[]> script = new ArrayList<String[]>();
        ArrayList<String> hint = new ArrayList<String>();
        
        try {
            query = "SELECT * FROM test WHERE testName = ?";
            prdStatement = conn.prepareStatement(query);
            prdStatement.setString(1, testName);
            
            rs = prdStatement.executeQuery();
            rs.next();
            script = getScript(rs.getString("script"));
            hint = getHint(rs.getString("hint"));
            testLesson = new TestLesson(rs.getInt("id"), rs.getString("testName"), rs.getInt("totalTime"),rs.getInt("testLevel"), rs.getString("demoScript"),
            rs.getString("mp3Url"), script, hint);
            
            return testLesson;
        } catch (SQLException ex) {
            Logger.getLogger(TestDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private ArrayList<String[]> getScript(String script){
        Iterator<String[]> iter = null;
        String word;
        String [] sentence;
        ArrayList<String[]> listWord = new ArrayList<>();

        String[] sentenceArr = script.split(" ,, ");

        for(int i = 0; i < sentenceArr.length; i++){
            listWord.add(sentenceArr[i].split(" "));
        }
        
        return listWord;
    }
    
    private ArrayList<String> getHint(String hint){
        ArrayList<String> hintList = new ArrayList<>();
        String[] arr = hint.split(" ");
        for(int i = 0; i < arr.length; i++){
            hintList.add(arr[i]);
        }
        return hintList;
    }
    
    
    public int saveHistory(History history){
        PreparedStatement prdStatement;
        String query;
        
        try {
            query = "INSERT INTO test_history(usrId, testId, conpletedDate, score, completeTime) "
                    + "VALUES(?,?,?,?,?)";
            
            prdStatement = conn.prepareStatement(query);
            prdStatement.setInt(1, history.getUserId());
            prdStatement.setInt(2, history.getTestId());
            prdStatement.setTimestamp(3, new java.sql.Timestamp(history.getTime().getTime()));
           System.out.println(new java.sql.Timestamp(history.getTime().getTime()));
            prdStatement.setFloat(4, history.getScore());
             prdStatement.setFloat(5, history.getTimeConsuming());
            
            int affectedRow = prdStatement.executeUpdate();
           
            return affectedRow;
        } catch (SQLException ex) {
            Logger.getLogger(TestDao.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public ArrayList<History> getHistory(Account acc){
        ArrayList<History> historyList = new ArrayList<History>();
        PreparedStatement prdStatement;
        ResultSet rs;
        String query;
        
        try{
            query = "SELECT * FROM test_history th "
                    + "INNER JOIN test t "
                    + "ON th.testId = t.id "
                    + "WHERE th.usrId = ? "
                    + "ORDER BY th.id DESC";
                    
            
            prdStatement = conn.prepareStatement(query);
            prdStatement.setInt(1, acc.getUser().getId());
            rs = prdStatement.executeQuery();
            
            while(rs.next()){
                History history = new History(rs.getInt("usrId"), rs.getInt("testId"), 
                        new Date(rs.getTimestamp("conpletedDate").getTime()), rs.getFloat("score"),
                        rs.getString("t.testName"), rs.getInt("th.completeTime"));
                historyList.add(history);
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
        return historyList;
    }
}
