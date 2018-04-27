/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.HashMap;
import javafx.scene.canvas.GraphicsContext;


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
    
    
    public static Shape copyShape(Shape s){
        Shape copy;
        switch(s.toString()){
            case "Oval":
                copy = new Oval();
                break;
             case"rectangle":
                 copy = new Rectangle(); 
                         break;
             case "square":
                 copy = new Square();
                 break;
             case "triangle":
                 copy = new Triangle();
                 break;
                 default: copy = new Rectangle(); {
           
        }
                 
        }
        
        copy.setLineWidth(s.getLineWidth());
        copy.setX1(s.getX1()+50);
        copy.setY1(s.getY1()+50);
        copy.setX2(s.getX2()+50);
        copy.setY2(s.getY2()+50);
        copy.setFillPaint(s.getFillPaint());
        copy.setPaint(s.getPaint());
       // copy.setFillPaint(s.getFillPaint());
    
   

    
    
return copy;
}
}
