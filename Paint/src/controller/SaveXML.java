/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import model.Shape;
import model.Shapes;




/**
 *
 * @author Omar
 */
public class SaveXML {
     public static void save(HashMap HashMapOfShapes ) throws ParserConfigurationException, FileNotFoundException{

 try{
     FileOutputStream fos = new FileOutputStream(new File("xx.xml"));
     XMLEncoder encoder = new XMLEncoder(fos);
     
      
         
     encoder.writeObject(HashMapOfShapes);
     
     
     encoder.close();
     fos.close();
 }
 catch(Exception e)
 {
     e.printStackTrace();
 }
     
           }
         
         
      public static String readFile() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader ("xx.xml"));
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
    
