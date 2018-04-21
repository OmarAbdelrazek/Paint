/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Tools;
import model.Brush;
import model.Oval;
import model.Eraser;
import model.Rectangle;
import model.Triangle;
import model.Line;
import java.awt.Color;
import static java.awt.Color.BLACK;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
        widthText.setText("1.0");
        widthText.textProperty().bindBidirectional(width.valueProperty(), NumberFormat.getNumberInstance());
        fillpick.setVisible(false);
        Paint p = new Paint();
        gc.setFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        colorPicker.setValue(javafx.scene.paint.Color.BLACK);

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
        Oval SampleOval = new Oval();
            SampleOval.setFill(isFilled);
            SampleOval.setX1(0);
            SampleOval.setY1(0);
            SampleOval.setX2(0);
            SampleOval.setY2(0);
            SampleOval.addShape(hmap);

    }

    @FXML
    private void canvasOnMOuseDragged(MouseEvent e) {
        if (shape.compareTo("brush") == 0) {
            //widthText.setText("8");

            double size = Double.parseDouble(widthText.getText());
            double x = e.getX() - (size / 2);
            double y = e.getY() - (size / 2);
            Brush b = new Brush(size, x, y, colorPicker.getValue());
            b.draw(gc);

        } else if (shape.compareTo("circle") == 0) {
            Oval c = new Oval();
            c.setFill(isFilled);
            c.setX1((int) startX);
            c.setY1((int) startY);
            c.setX2((int) e.getX());
            c.setY2((int) e.getY());
            c.setColor(colorPicker.getValue());
            temp.add(0,(Shape) c);


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
    }

    @FXML
    private void canvasOnMouseRe(MouseEvent e) {
        endX = e.getX();
        endY = e.getY();
        hght = Math.abs(endY - startY);
        wdth = Math.abs(endX - startX);

        if (shape.compareTo("circle") == 0) {
         
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
            r.setColor(colorPicker.getValue());
            r.setFillPaint(fillpick.getValue());
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
            t.setColor(colorPicker.getValue());
            t.setFillPaint(fillpick.getValue());
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
            l.setColor(colorPicker.getValue());
          //  l.setLineWidth(width.getValue());
            l.addShape(hmap);
        } /*else if (shape.compareTo("move") == 0) {
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
        javafx.scene.paint.Paint prev = gc.getFill();
        gc.setFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        controller.Tools.parse(hmap, gc);
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
        priority=0;
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

}
