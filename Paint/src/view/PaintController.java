/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Shape;
import java.net.URL;
import static java.sql.JDBCType.NULL;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import model.*;

/**
 FXML Controller class

 @author Omar's PC
 */
public class PaintController implements Initializable {

    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private ColorPicker colorPicker;
    double startX,startY,endX,endY;
    private String shape;
    private double hght,wdth;
    @FXML
    private Button circleBtn;
    GraphicsContext gc ;
    @FXML
    private CheckBox Filled;
    private boolean isFilled = false;
    
    /**
     Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         gc = canvas.getGraphicsContext2D();
    } 

    public ColorPicker getColorPicker() {
        return colorPicker;
    }

   
    @FXML
    private Canvas canvas;
    
    @FXML
    private void canvasOnMousePressed(MouseEvent e) {
        startX =  e.getX();
        startY =  e.getY();
       
        
    }
   

       

    @FXML
    private void circleBtn(ActionEvent event) {
        shape = "circle";
    }
    
    private void drawCircle(){
        gc.strokeOval(startX, startY, hght, wdth);
    }

    @FXML
    private void canvasOnMouseRe(MouseEvent e) {
        endX = e.getX();
        endY =  e.getY();
        hght = Math.abs(endY-startY);
        wdth = Math.abs(endX - startX);
        
        if(shape.compareTo("circle") == 0)
        {
           if(!isFilled)
            gc.setStroke(colorPicker.getValue());
            gc.setFill(colorPicker.getValue());
        
        Oval c = new Oval();
        c.setFill(isFilled);
        c.setX1((int) startX);
        c.setY1((int) startY);
        c.setX2((int) endX);
        c.setY2((int) endY);
        c.draw(gc);
        }
        else if(shape.compareTo("rectangle") == 0)
        {
            if(!isFilled)
            gc.setStroke(colorPicker.getValue());
            gc.setFill(colorPicker.getValue());
            Rectangle r = new Rectangle();
            r.setFill(isFilled);
            r.setX1((int) startX);
            r.setY1((int) startY);
            r.setX2((int) endX);
            r.setY2((int) endY);
            r.draw(gc);
        }
        else if(shape.compareTo("triangle") == 0)
        {
             if(!isFilled)
            gc.setStroke(colorPicker.getValue());
            gc.setFill(colorPicker.getValue());
            Triangle t = new Triangle();
            t.setFill(isFilled);
            t.setX1((int) startX);
            t.setY1((int) startY);
            t.setX2((int) endX);
            t.setY2((int) endY);
            t.draw(gc);
        }
        startX = 0;
        startY=0;
        endX=0;
        endY=0;
        
    }

    @FXML
    private void rectangleBtn(ActionEvent event) {
        shape = "rectangle";
    }

    @FXML
    private void triangleBtn(ActionEvent event) {
        shape = "triangle";
    }

    @FXML
    private void isFilled(ActionEvent event) {
        isFilled =! isFilled;
    }

    
}
