/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import oop.dao.StatisticDao;

/**
 *
 * @author thao
 */
public class StatisticService {
   private StatisticDao statisticDao;
    
   public StatisticService(){
       this.statisticDao = new StatisticDao();
   }
    
   public ArrayList<HashMap<Date,Float>> getInfoForScoreChart(int usrId){
     return statisticDao.getInfoForScoreChart(usrId);
   }
   
   public HashMap<Date,Float> getInfoForTimeChart(int usrId){
       return statisticDao.getInfoForTimeChart(usrId);
   }
   
   public void calculateAvgScore(int usrId){
       statisticDao.calculateAvgScore(usrId);
   }
}
