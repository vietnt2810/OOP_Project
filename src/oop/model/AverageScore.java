/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.model;

/**
 *
 * @author thao
 */
public class AverageScore {
    private float level1Score;
    private float level2Score;
    private float level3Score;

    public AverageScore(float level1Score, float level2Score, float level3Score) {
        this.level1Score = level1Score;
        this.level2Score = level2Score;
        this.level3Score = level3Score;
    }

    public float getLevel1Score() {
        return level1Score;
    }

    public void setLevel1Score(float level1Score) {
        this.level1Score = level1Score;
    }

    public float getLevel2Score() {
        return level2Score;
    }

    public void setLevel2Score(float level2Score) {
        this.level2Score = level2Score;
    }

    public float getLevel3Score() {
        return level3Score;
    }

    public void setLevel3Score(float level3Score) {
        this.level3Score = level3Score;
    }
    
}
