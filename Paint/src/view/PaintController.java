/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.MydrawingEngine;

import static java.awt.Color.*;
import static java.awt.PageAttributes.ColorType.COLOR;
import java.net.URL;
import static java.sql.JDBCType.NULL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import model.*; 
import controller.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;

/**
 * FXML Controller class
 *
 * @author Omar's PC
 */
public class PaintController implements Initializable {

    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private ColorPicker colorPicker;
    double startX, startY, endX, endY;
    private String shape;
    private double hght, wdth;
    @FXML
    private Button circleBtn;
    GraphicsContext gc;
    @FXML
    private CheckBox Filled;
    private boolean isFilled = false;
    @FXML
    private Button lineBtn;
    @FXML
    private Slider width;
    @FXML
    private TextField widthText;
    @FXML
    private Button okBtn;
    @FXML
    private ColorPicker fillpick;
    @FXML
    private Button clearBtn;
    @FXML
    private Button Brush;
    //
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    @FXML
    private Button delete;
    @FXML
    private Button moveBtn;
    @FXML
    private Button eraser;
    HashMap<Integer, Shape> hmap = new HashMap<Integer, Shape>();
    ArrayList<Shape> temp = new ArrayList<Shape>();
    public static int priority = 0;
    public javafx.scene.paint.Paint prev = javafx.scene.paint.Paint.valueOf("#ffffff");
    public javafx.scene.paint.Paint currentfill = javafx.scene.paint.Paint.valueOf("#ffffff");
    public javafx.scene.paint.Paint currentcolor = javafx.scene.paint.Paint.valueOf("#ffffff");
    @FXML
    private Button square;
    @FXML
    private Label xCoordinate;
    @FXML
    private Label yCoordinates;
    @FXML
    private Button selectBtn;
    boolean isSelected = false;
    private Label selectLbl;
    private  ArrayList found;
    private int target;
    private int selectCounter = 0;
    boolean removeShape = false;
    private ChoiceBox<?> selectAction;
    @FXML
    private ComboBox<?> dropAction;
    
          
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
        widthText.setText("1.0");
        widthText.textProperty().bindBidirectional(width.valueProperty(), NumberFormat.getNumberInstance());
        fillpick.setVisible(false);
        gc.setFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
       found = new ArrayList();
       String[] arr = {"move","delete"};
       selectAction.getItems().removeAll(selectAction.getItems());
        
    
        
    }

    public ColorPicker getColorPicker() {
        return colorPicker;
    }

    @FXML
    private Canvas canvas;

    @FXML
    private void canvasOnMousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
        javafx.scene.paint.Paint currentfill = fillpick.getValue();
        javafx.scene.paint.Paint currentcolor = colorPicker.getValue();
         
       
        

    }

    @FXML
    private void canvasOnMOuseDragged(MouseEvent e) {
        double currentX = e.getX();
        double currentY = e.getY();
                javafx.scene.paint.Paint currentfill = fillpick.getValue();
        javafx.scene.paint.Paint currentcolor = colorPicker.getValue();
        if (shape.compareTo("brush") == 0) {
            //widthText.setText("8");

            double size = Double.parseDouble(widthText.getText());
            double x = e.getX() - (size / 2);
            double y = e.getY() - (size / 2);
            Brush b = new Brush(size, x, y, colorPicker.getValue());
            b.draw(gc);

        } else if (shape.compareTo("circle") == 0) {
            Oval c = new Oval();
            c.updateShape(hmap, currentfill, startX, startY, currentX, currentY, currentcolor);
            MydrawingEngine.refresh(gc, canvas, currentfill);
            MydrawingEngine.parse(hmap, gc);
            
           // gc.fillRect(c.getUpperLeftX(), c.getUpperLeftY(), c.getWidth(), c.getHeight() );

        } else if (shape.compareTo("rectangle") == 0) {
            Rectangle c = new Rectangle();
            c.updateShape(hmap, currentfill, startX, startY, currentX, currentY, currentcolor);
            MydrawingEngine.refresh(gc, canvas, currentfill);
            MydrawingEngine.parse(hmap, gc);
            

        } else if (shape.compareTo("triangle") == 0) {
            Triangle c = new Triangle();
            c.updateShape(hmap, currentfill, startX, startY, currentX, currentY, currentcolor);
            MydrawingEngine.refresh(gc, canvas, currentfill);
            MydrawingEngine.parse(hmap, gc);

        } else if (shape.compareTo("line") == 0) {
            Line c = new Line();
            c.updateShape(hmap, currentfill, startX, startY, currentX, currentY, currentcolor);
            MydrawingEngine.refresh(gc, canvas, currentfill);
            MydrawingEngine.parse(hmap, gc);
           

        } else if (shape.compareTo("square") == 0) {
            Square c = new Square();
            c.updateShape(hmap, currentfill, startX, startY, currentX, currentY, currentcolor);
            MydrawingEngine.refresh(gc, canvas, currentfill);
            MydrawingEngine.parse(hmap, gc);

        } else if (shape.compareTo("eraser") == 0) {
            double size = Double.parseDouble(widthText.getText());
            double x = e.getX() - (size / 2);
            double y = e.getY() - (size / 2);
            Eraser eraser = new Eraser(size, x, y);
            eraser.draw(gc);
            
        }
       
    }

    @FXML

    private void brushBtn(ActionEvent event) {
        shape = "brush";
    }

    @FXML
    private void circleBtn(ActionEvent event) {
        shape = "circle";
        Boolean mero = true;
        Filled.setDisable(!mero);
        fillpick.setDisable(!mero);
    }

    @FXML
    private void squareBtn(ActionEvent event) {
        shape = "square";
        Boolean mero = true;
        Filled.setDisable(!mero);
        fillpick.setDisable(!mero);
    }

    @FXML
    private void canvasOnMouseRe(MouseEvent e) {
        endX = e.getX();
        endY = e.getY();
        hght = Math.abs(endY - startY);
        wdth = Math.abs(endX - startX);

         if(shape.compareTo("select") ==0 && isSelected){
            
             for(int i=0 ; i<= BoundsOperations.boundMap.size() ; i++){
                  System.out.print("x1:  "+BoundsOperations.boundMap.get(i)[0]);
                 System.out.print("x2:   "+BoundsOperations.boundMap.get(i)[2]);
                 System.out.print("Y1:  "+BoundsOperations.boundMap.get(i)[1]);
                 System.out.print("y2:  "+BoundsOperations.boundMap.get(i)[3]);
                 System.out.println("");
                if(startX >= Math.min(BoundsOperations.boundMap.get(i)[0], BoundsOperations.boundMap.get(i)[2]) && startX <= Math.max(BoundsOperations.boundMap.get(i)[0], BoundsOperations.boundMap.get(i)[2]) && startY > Math.min(BoundsOperations.boundMap.get(i)[1], BoundsOperations.boundMap.get(i)[3]) && startY < Math.max(BoundsOperations.boundMap.get(i)[1], BoundsOperations.boundMap.get(i)[3]) && !hmap.isEmpty())
                 {
                    
                     found.add(i);
                     target = i;
                     System.out.println(target);
                 }
                if(found.size()>1)
                {
                    target = found.size()-1;
                }
                 System.out.println(target);
                   isSelected = false;
                   selectLbl.setText("Selection Mode: OFF");
                 
             }
             
         
    }
          else if (shape.compareTo("circle") == 0) {

            gc.setStroke(colorPicker.getValue());

            gc.setFill(fillpick.getValue());
            Oval c = new Oval();
            
            c.setFill(isFilled);
            c.setX1((int) startX);
            c.setY1((int) startY);
            c.setX2((int) endX);
            c.setY2((int) endY);
            c.setPaint(colorPicker.getValue());
            c.setFillPaint(fillpick.getValue());
            BoundsOperations b = new BoundsOperations(c.getUpperLeftX(), c.getUpperLeftY(), c.getLowerRightX(), c.getLowerRightY(),selectCounter);
            selectCounter++;
           
            // c.setLineWidth(width.getValue());
            c.addShape(hmap);
            
             

        } else if (shape.compareTo("rectangle") == 0) {

            gc.setStroke(colorPicker.getValue());
            gc.setFill(fillpick.getValue());
            Rectangle r = new Rectangle();
            r.setFill(isFilled);
            r.setX1((int) startX);
            r.setY1((int) startY);
            r.setX2((int) endX);
            r.setY2((int) endY);
            r.setPaint(colorPicker.getValue());
            r.setFillPaint(fillpick.getValue());
            BoundsOperations b = new BoundsOperations(r.getUpperLeftX(), r.getUpperLeftY(), r.getLowerRightX(), r.getLowerRightY(),selectCounter);
            selectCounter++;
            // r.setLineWidth(width.getValue());
            r.addShape(hmap);
           

        } else if (shape.compareTo("triangle") == 0) {
            if (!isFilled) {
                gc.setStroke(colorPicker.getValue());
            }
            gc.setFill(fillpick.getValue());
            Triangle t = new Triangle();
            t.setFill(isFilled);
            t.setX1((int) startX);
            t.setY1((int) startY);
            t.setX2((int) endX);
            t.setY2((int) endY);
            t.setPaint(colorPicker.getValue());
            t.setFillPaint(fillpick.getValue());
            BoundsOperations b = new BoundsOperations(t.getUpperLeftX(), t.getUpperLeftY(), t.getLowerRightX(), t.getLowerRightY(),selectCounter);
            selectCounter++;
            //  t.setLineWidth(width.getValue());
            t.addShape(hmap);
        } else if (shape.compareTo("line") == 0) {
            if (!isFilled) {
                gc.setStroke(colorPicker.getValue());
            }
            gc.setFill(fillpick.getValue());
            Line l = new Line();
            l.setX1((int) startX);
            l.setY1((int) startY);
            l.setX2((int) endX);
            l.setY2((int) endY);
            l.setPaint(colorPicker.getValue());
           // BoundsOperations.setNewBound(l.getUpperLeftX(), l.getUpperLeftY(), l.getWidth(), l.getHeight());
            //  l.setLineWidth(width.getValue());
            l.addShape(hmap);
        } else if (shape.compareTo("square") == 0) {

            gc.setStroke(colorPicker.getValue());
            gc.setFill(fillpick.getValue());
            Square s = new Square();
            s.setFill(isFilled);

            double l;
            l = Math.abs(startX - endX);

            s.setX1((int) startX);
            s.setY1((int) startY);
            s.setX2((int) endX);
            s.setY2((int) startY + (int) l);
            s.setPaint(colorPicker.getValue());
            s.setFillPaint(fillpick.getValue());
            BoundsOperations b = new BoundsOperations(s.getUpperLeftX(), s.getUpperLeftY(), s.getLowerRightX(), s.getLowerRightY(),selectCounter);
            selectCounter++;
            // r.setLineWidth(width.getValue());
            s.addShape(hmap);

        }/*else if (shape.compareTo("move") == 0) {
            EventHandler<MouseEvent> circleOnMousePressedEventHandler
                    = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((Circle) (t.getSource())).getTranslateX();
                    orgTranslateY = ((Circle) (t.getSource())).getTranslateY();
                }
            };
            EventHandler<MouseEvent> circleOnMouseDraggedEventHandler
                    = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    ((Circle) (t.getSource())).setTranslateX(newTranslateX);
                    ((Circle) (t.getSource())).setTranslateY(newTranslateY);
                }
            };
        }*/
        startX = 0;
        startY = 0;
        endX = 0;
        endY = 0;
        gc.setFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        MydrawingEngine.parse(hmap, gc);
        gc.setFill(prev);

    }

    @FXML
    private void rectangleBtn(ActionEvent event) {
        shape = "rectangle";
        Boolean mero = true;
        Filled.setDisable(!mero);
        fillpick.setDisable(!mero);
    }

    @FXML
    private void triangleBtn(ActionEvent event) {
        shape = "triangle";
        Boolean mero = true;
        Filled.setDisable(!mero);
        fillpick.setDisable(!mero);
    }

    @FXML
    private void isFilled(ActionEvent event) {
        isFilled = !isFilled;
        fillpick.setVisible(isFilled);
    }

    @FXML
    private void lineBtn(ActionEvent event) {
        shape = "line";
        Boolean mero = false;
        Filled.setDisable(!mero);
        fillpick.setDisable(!mero);
    }

    @FXML
    private void width(ActionEvent event) {

        gc.setLineWidth(width.getValue());
    }

    @FXML
    private void clearBtn(ActionEvent event) {
        gc.setFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        hmap.clear();
        priority = 0;
    }

    @FXML
    private void setfill(ActionEvent event) {
        gc.setFill(javafx.scene.paint.Paint.valueOf(colorPicker.getValue().toString()));

    }

    @FXML
    private void moveBtn(ActionEvent event) {
        shape = "move";
        Boolean mero = false;
        Filled.setDisable(!mero);
        fillpick.setDisable(!mero);
    }

    @FXML
    private void setstroke(ActionEvent event) {
        gc.setStroke(javafx.scene.paint.Paint.valueOf(colorPicker.getValue().toString()));

    }

    @FXML
    private void eraserBtn(ActionEvent event) {
        shape = "eraser";
    }

    

    @FXML
    private void onMouseMoved(MouseEvent event) {
        xCoordinate.setText(String.valueOf(event.getX()));
        yCoordinates.setText(String.valueOf(event.getY()));
    }

    @FXML
    private void selectBtnAction(ActionEvent event) {
        
        if(!isSelected){
            isSelected = true;
            shape = "select";
            selectLbl.setText("Selection Mode : ON");
        }
        else
        {
            isSelected = false;
            
            selectLbl.setText("Selection Mode : OFF");
            
        }
    }

    @FXML
    private void cmbChoose(ActionEvent event) {
    }

   


}
