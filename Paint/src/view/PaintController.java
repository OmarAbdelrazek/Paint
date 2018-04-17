/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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
    double centerX,centerY,endX,endY;
    private String shape;
    private double hght,wdth;
    @FXML
    private Button circleBtn;
    GraphicsContext gc ;
    
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
        centerX =  e.getX();
        centerY =  e.getY();
       
        
    }
   

       

    @FXML
    private void circleBtn(ActionEvent event) {
        shape = "circle";
    }
    
    private void drawCircle(){
        gc.strokeOval(centerX, centerY, hght, wdth);
    }

    @FXML
    private void canvasOnMouseRe(MouseEvent e) {
        endX = e.getX();
        endY =  e.getY();
        hght = Math.abs(endY-centerY);
        wdth = Math.abs(endX - centerX);
        
        if(shape.compareTo("circle") == 0){
            gc.setFill(colorPicker.getValue());
            drawCircle();
        }
        
        
    }
    
}
