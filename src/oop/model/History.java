/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.model;

import java.util.Date;



/**
 *
 * @author thao
 */
public class History {
    private int userId;
    private int testId;
    private Date time;
    private float score;
    private String testTitle;
    private float timeConsuming;
    
    public History(int userId, int testId, float score) {
        this.userId = userId;
        this.testId = testId;
        this.time = new Date();
        this.score = score;
    }
    public History(int userId, int testId, Date time, float score) {
        this.userId = userId;
        this.testId = testId;
        this.time = time;
        this.score = score;
    }

    public History(int userId, int testId, Date time, float score, String title) {
        this.userId = userId;
        this.testId = testId;
        this.time = time;
        this.score = score;
        this.testTitle = title;
    }
    
    public History(int userId, int testId, float score, float timeConsuming) {
        this.userId = userId;
        this.testId = testId;
        this.time = new Date();
        this.score = score;
        this.timeConsuming = timeConsuming;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }
    
    
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
    
    
}
