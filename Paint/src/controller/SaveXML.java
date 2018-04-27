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
import java.io.FileWriter;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.Shape;
import model.Shapes;




/**
 *
 * @author Ameer.Nasser88
 */
public class SaveXML {

    /**
     *
     * @param HashMapOfShapes
     */
    public static void Save(HashMap HashMapOfShapes) {

    try {

        DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
        DocumentBuilder build = dFact.newDocumentBuilder();
        Document doc = build.newDocument();

        Element root = doc.createElement("Shapeinfo");
        doc.appendChild(root);

        Element Details = doc.createElement("Details");
        root.appendChild(Details);


       for(int i=0;i<HashMapOfShapes.size();i++){
           Shape s = (Shape) HashMapOfShapes.get(i);

            Element X1 = doc.createElement("X1");
            X1.appendChild(doc.createTextNode( String.valueOf(s.getX1()) ));
            
                   
            Details.appendChild(X1);

            Element Y1 = doc.createElement("Y1");
            Y1.appendChild(doc.createTextNode( String.valueOf(s.getY1()) ));
                            
            Details.appendChild(Y1);

            Element X2 = doc.createElement("X2");
            X2.appendChild(doc.createTextNode( String.valueOf(s.getX2()) ));
                            
            Details.appendChild(X2);
            
            Element Y2 = doc.createElement("Y2");
            Y2.appendChild(doc.createTextNode( String.valueOf(s.getY2()) ));
                            
            Details.appendChild(Y2);
            
            
            Element Paint = doc.createElement("Paint");
            Paint.appendChild(doc.createTextNode( s.getPaint().toString() ));
                            
            Details.appendChild(Paint);
            
            
            Element fillPaint = doc.createElement("fillPaint");
            fillPaint.appendChild(doc.createTextNode( s.getFillPaint().toString() ));
                            
            Details.appendChild(fillPaint);
            
            
               Element lw  = doc.createElement("lw");
            fillPaint.appendChild(doc.createTextNode( s.getLineWidth().toString() ));
                            
            Details.appendChild(lw);
            
            
              Element name  = doc.createElement("name");
            name.appendChild(doc.createTextNode( s.toString() ));
                            
            Details.appendChild(name);
            
            
            
            
            
            
            
            
            
            

        }

        // Save the document to the disk file
        TransformerFactory tranFactory = TransformerFactory.newInstance();
        Transformer aTransformer = tranFactory.newTransformer();

        // format the XML nicely
        aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        aTransformer.setOutputProperty(
                "{http://xml.apache.org/xslt}indent-amount", "4");
        aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        try {
            // location and name of XML file you can change as per need
            FileWriter fos = new FileWriter("./mero.xml");
            StreamResult result = new StreamResult(fos);
            aTransformer.transform(source, result);

        } catch (IOException e) {

            e.printStackTrace();
        }

    } catch (TransformerException ex) {
        System.out.println("Error outputting document");

    } catch (ParserConfigurationException ex) {
        System.out.println("Error building document");
    }
}
    
    
    public static String readFile() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader ("mero.xml"));
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
    
