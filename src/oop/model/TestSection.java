/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.model;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author thao
 */
public class TestSection {
    private int order;   
    private int startIndex;
    private int endIndex;

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
    
    
    public TestSection(){
    }

    public TestSection(int order, int start, int end) {
        this.order = order;
        this.startIndex = start;
        this.endIndex = end;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

   
    
}
