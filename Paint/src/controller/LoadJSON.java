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
import model.Shape;
import model.Shapes;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class LoadJSON { 
    public static  ArrayList<Shapes> array;
    
    
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
          Paint paint =  (Paint)jsonObject.get("Paint");
            System.out.println(paint);
            Paint fillpaint =  (Paint)jsonObject.get("fillPaint");
            System.out.println(fillpaint);
            double lw = (double)jsonObject.get("lW");
            System.out.println(lw);
            System.out.println("Ameeeeeeeeeeeeer");
            Shape s = new Shape((int)X1, (int)Y1, (int)X2, (int)Y2, paint, fillpaint, lw) {
                   @Override
                   public void draw(GraphicsContext g) {
                       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   }
               } ;
            
            
            array.add((Shapes) s);
          
               

     
            

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
