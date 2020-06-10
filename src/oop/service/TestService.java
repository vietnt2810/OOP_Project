/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.service;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import oop.dao.TestDao;
import oop.model.History;
import oop.model.TestLesson;

/**
 *
 * @author thao
 */
public class TestService {
    private TestDao testDao;
    
    public TestService(){
        this.testDao = new TestDao();
    }
    
//    public ArrayList<String> getTestList(int level){
//        return this.testDao.getTestList(level);
//    }
    
    
    public ListModel getTestList(int level){
        DefaultListModel list = new DefaultListModel();
        ArrayList<String> arrList = new ArrayList<String>();
        Iterator<String> iter = null;
        arrList = this.testDao.getTestList(level);
        iter = arrList.iterator();
        while(iter.hasNext()){
           String data = iter.next();
           list.addElement(data);
        }
        return list;
    }
    
    
    public TestLesson getTestDetail(String testName){
        return this.testDao.getTestDetail(testName);
    }
    
    public boolean saveHistory(History history){
        int affectedRow = testDao.saveHistory(history);
        return affectedRow > 0;
    }
}
