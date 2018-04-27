/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import model.Line;
import model.Oval;
import model.Rectangle;
import model.Shape;
import model.Shapes;
import model.Square;
import model.Triangle;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class LoadJSON { 
    public static  ArrayList<Shape> array;
    public static  ArrayList<String> type = new ArrayList<>();
    
  public static ArrayList<JSONObject> json;
     
    /**
     *
     * @param path
     * @author Ameer.Nasser88
     */
    public static void jsonArray(String path) throws ParseException
      {
          json=new ArrayList<>();
          String line = null;
          JSONObject obj;
           try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = new FileReader(path);

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null) {
            obj = (JSONObject) new JSONParser().parse(line);
            json.add(obj);
            
        }
        // Always close files.
        bufferedReader.close();         
    }
    catch(FileNotFoundException ex) {
        System.out.println("Unable to open file '" + path + "'");                
    }
    catch(IOException ex) {
        System.out.println("Error reading file '" + path + "'");                  
        // Or we could just do this: 
        // ex.printStackTrace();
    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
          
      
      
      
      
      
    public static void Load() throws FileNotFoundException, org.json.simple.parser.ParseException{
      //JSONParser parser = new JSONParser();
  array=new ArrayList<>();

  
//JSONParser parser = new JSONParser();
 JSONParser parser = new JSONParser();
for(int i=0;i<json.size();i++){
    Object object;
    object = json.get(i);
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
    if(name.compareToIgnoreCase("oval")==0)
        
    {Oval o = new Oval((int)X1, (int)Y1, (int)X2, (int)Y2,Paint.valueOf(paint), true, Paint.valueOf(fillpaint), lw);
    type.add("oval");
    
    array.add( (Shape) o);}
    else if(name.compareToIgnoreCase("rectangle")==0)
    {
        Rectangle c = new Rectangle((int)X1, (int)Y1, (int)X2, (int)Y2,Paint.valueOf(paint), true, Paint.valueOf(fillpaint), lw);
        type.add("rectangle");
        array.add( (Shape) c);
    }
    else if(name.compareToIgnoreCase("triangle")==0)
    {
        Triangle t = new Triangle((int)X1, (int)Y1, (int)X2, (int)Y2,Paint.valueOf(paint), true, Paint.valueOf(fillpaint), lw);
        type.add("triangle");
        array.add( (Shape) t);
    }
    else if(name.compareToIgnoreCase("square")==0)
    {
        Square s = new Square((int)X1, (int)Y1, (int)X2, (int)Y2,Paint.valueOf(paint), true, Paint.valueOf(fillpaint), lw);
        type.add("square");
        array.add( (Shape) s);
    }
    else
    {
        Line l = new Line((int)X1, (int)Y1, (int)X2, (int)Y2,Paint.valueOf(paint),lw,Paint.valueOf(fillpaint));
        type.add("line");
        array.add( (Shape) l);
    }
    
    
    
    
    
    
    
    
    // loop array
    /*   JSONArray arr = (JSONArray) jsonObject.get("list");
    Iterator<String> iterator = arr.iterator();
    while (iterator.hasNext()) {
    System.out.println("yes");
    
    }*/
}
      
}

   
}
