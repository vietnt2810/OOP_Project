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
public class AvgScore {
    private float level1 = 0.0f;
    private float level2 = 0.0f;
    private float level3 = 0.0f;
    
    public AvgScore(){};
    public AvgScore(float level1){
        this.level1 = level1;
    };
    public AvgScore(float level1, float level2){
        this(level1);
        this.level2 = level2;
    };
    public AvgScore(float level1, float level2, float level3){
        this(level1, level2);
        this.level3 = level3;
    };

    public float getLevel1() {
        return level1;
    }

    public void setLevel1(float level1) {
        this.level1 = level1;
    }

    public float getLevel2() {
        return level2;
    }

    public void setLevel2(float level2) {
        this.level2 = level2;
    }

    public float getLevel3() {
        return level3;
    }

    public void setLevel3(float level3) {
        this.level3 = level3;
    }
    
    
}
