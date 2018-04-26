/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import model.Oval;
import model.Shape;
import model.Shapes;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class LoadJSON { 
    public static  ArrayList<Shape> array;
    
    
    public static void Load(String path) throws FileNotFoundException, org.json.simple.parser.ParseException{
      //JSONParser parser = new JSONParser();
  array=new ArrayList<>();
  
//JSONParser parser = new JSONParser();
 JSONParser parser = new JSONParser();
        try
        {
          
            
            Object object;
      object = parser.parse(new FileReader(path));
            
            //convert Object to JSONObject
            JSONObject jsonObject = (JSONObject)object;

            long Y1 =  (long)jsonObject.get("Y1");
            System.out.println(Y1);

           long X1 =  (long)jsonObject.get("X1");
            System.out.println(X1);

           long Y2 =  (long)jsonObject.get("Y2");
            System.out.println(Y2);
            long X2 =  (long)jsonObject.get("X2");
            System.out.println(X2);
          /*  obj.put("Paint", s.getPaint());
          obj.put("fillPaint", s.getFillPaint());
           obj.put("lW", s.getLineWidth());*/
          String paint =  (String)jsonObject.get("Paint");
            System.out.println(paint);
            String fillpaint =  (String)jsonObject.get("fillPaint");
            System.out.println(fillpaint);
            double lw = (double)jsonObject.get("lW");
            System.out.println(lw);
            String name = (String)jsonObject.get("Name");
            System.out.println(name);
           
            System.out.println("Ameeeeeeeeeeeeer");
                  
               Oval o = new Oval((int)X1, (int)Y1, (int)X2, (int)Y2,Paint.valueOf(paint), true, Paint.valueOf(fillpaint), lw);
            
            
            array.add( (Shape) o);
          
               

     
            

            // loop array
         /*   JSONArray arr = (JSONArray) jsonObject.get("list");
            Iterator<String> iterator = arr.iterator();
            while (iterator.hasNext()) {
                System.out.println("yes");
                
            }*/
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
      
}

   
}
