/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import model.*;

/**
 *
 * @author Omar
 */
public class SaveJSON implements SaveStrategy{
   public  void save(HashMap HashMapOfShapes,String path){
     
        JSONObject obj = new JSONObject();
         JSONArray list = new JSONArray();
        

		
          FileWriter file;
       try {
           
    BufferedWriter writer = new BufferedWriter(new FileWriter("lool.json"));
            for(int i=0;i<HashMapOfShapes.size();i++){
       
        Shape s = (Shape) HashMapOfShapes.get(i);
       obj.put("Y1", s.getY1());
        obj.put("X1", s.getX1());
        obj.put("Y2", s.getY2());
        obj.put("X2", s.getX2());
        
        
         obj.put("Paint", s.getPaint());
          obj.put("fillPaint", s.getFillPaint());
           obj.put("lW", s.getLineWidth());
                    

        obj.put("Name",s.toString());
        
        
            writer.write(obj.toJSONString());
            writer.newLine();
             writer.flush();

       

        System.out.print(obj);
         }
            writer.close();
       } catch (IOException ex) {
           Logger.getLogger(SaveJSON.class.getName()).log(Level.SEVERE, null, ex);
       }
        
       
   }
    
    
}
