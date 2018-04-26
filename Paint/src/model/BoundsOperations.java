/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.HashMap;


public class BoundsOperations {
    public static HashMap<Integer, int[]> boundMap = new HashMap<>();
    private  int[] boundArr;
   
     public  BoundsOperations(int x1,int y1,int x2,int y2,int counter){
         boundArr = new int[4];
        boundArr[0] = x1;
        boundArr[1] = y1;
        boundArr[2] = x2;
        boundArr[3] = y2;
        boundMap.put(counter, boundArr);
        
        counter++;
    }
    

    public void setBoundMap(HashMap<Integer, int[]> boundMap) {
        this.boundMap = boundMap;
    }
   

    
    

}
