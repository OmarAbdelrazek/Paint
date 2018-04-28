/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
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
 * @author Ameer.Nasser88
 */
public class SaveJSON implements SaveStrategy{
   public static void save(HashMap HashMapOfShapes){
     
        JSONObject obj = new JSONObject();
         JSONArray list = new JSONArray();
        

		
          FileWriter file;
       try {
           
    BufferedWriter writer = new BufferedWriter(new FileWriter("lol.json"));
            for(int i=0;i<HashMapOfShapes.size();i++){
       
        Shape s = (Shape) HashMapOfShapes.get(i);
        
       obj.put("Y1", s.getY1());
        obj.put("X1", s.getX1());
        obj.put("Y2", s.getY2());
        obj.put("X2", s.getX2());
        
        
         obj.put("Paint", s.getPaint().toString());
          obj.put("fillPaint", s.getFillPaint().toString());
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
   public static String readFile() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader ("lol.json"));
    String         line = null;
    StringBuilder  stringBuilder = new StringBuilder();
    String         ls = System.getProperty("line.separator");

    try {
        while((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        return stringBuilder.toString();
    } finally {
        reader.close();
    }
}
    
    
}
