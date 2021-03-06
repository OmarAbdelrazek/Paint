/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import model.*;
import view.PaintController;
import static view.PaintController.freecount;
import static view.PaintController.priority;
import static view.PaintController.target;

/**
 *
 * @author Tommy
 */
public class MydrawingEngine implements DrawingEngine {
private static final MydrawingEngine instance = new MydrawingEngine() ;
    public static void parse(HashMap Shapeshm, GraphicsContext gc) {
        for (int i = 0; i <= PaintController.priority; i++) {
            Shape s = (Shape) Shapeshm.get(i);
            if (s==null)
                continue;
            s.draw(gc);


        }
    }
        public static void parsebrush(HashMap Shapeshm, GraphicsContext gc) {
        for (int i = 0; i <= freecount; i++) {
            Brush s = (Brush) Shapeshm.get(i);
            if (s==null)
                continue;
            s.draw(gc);


        }
    }
    public static MydrawingEngine getInstance(){
        return instance;
    }
    public static void refresh(GraphicsContext gc ,Canvas canvas ,Paint current)
    {
            gc.setFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.setFill(current);
    }
    


    public static void installPluginShape(String jarPath)

    {

        File file = new File("C:\\Users\\Tommy\\Documents\\GitHub\\Paint\\Paint\\paint.plugin\\Pentagon.java");
        URL url = null;
        try {
            url = file.toURL();
        } catch (MalformedURLException ex) {
            Logger.getLogger(MydrawingEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        URL[] urls = new URL[]{url};
        ClassLoader cl = new URLClassLoader(urls);
        try {
            Class cls = cl.loadClass("paint.model.Pentagon");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MydrawingEngine.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static void removeShape(HashMap shapeshm){
    shapeshm.remove(target);

    }

}
