/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.ResourceBundle;
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
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private ColorPicker colorPicker;
    double centerX,centerY,endX,endY;

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
    private void canvasOnMouseReleased(MouseEvent e) {
        endX = e.getX();
        endY =  e.getY();
        
        
        
        
        
        
        
    }

    /**
     Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
    }    
    
}
