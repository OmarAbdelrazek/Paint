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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.WritableImage;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;
import static javax.swing.SpringLayout.HEIGHT;
import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.parser.ParseException;

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
    int orgSceneX, orgSceneY;
    int orgTranslateX1, orgTranslateY1, orgTranslateX2, orgTranslateY2;
    @FXML
    private Button delete;
    @FXML
    private Button moveBtn;
    @FXML
    private Button eraser;
    HashMap<Integer, Shape> hmap = new HashMap<Integer, Shape>();
    HashMap<Integer, Brush> freehmap = new HashMap<Integer, Brush>();
    ArrayList<Shape> temp = new ArrayList<Shape>();
    public static int priority = 0;
    public static int freecount = 0;
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
    private ArrayList found;
    public static int target;
    private int selectCounter = 0;
    boolean removeShape = false;

    @FXML
    private MenuItem btnOpenJ;
    @FXML
    private MenuItem btnSaveJ;
    @FXML
    private Button resizeBtn;
    @FXML
    private Button copyBtn;
    boolean isCopy = false;
    public Stack<Shape> undoStack;
    public Stack<String> namesStack;
    @FXML
    private Button undoBtn;

    @FXML
    private Button redoBtn;
    boolean isDeleted = false;
    boolean isDeleteUndo = false;
    @FXML

    private Button upBtn;
    @FXML
    private Button downBtn;
    @FXML
    private Button rightBtn;
    @FXML
    private Button liftBtn;
    @FXML
    private Button exitMoveModeBtn;

    @FXML
    private Button sphereBtn;

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
        colorPicker.setValue(Color.BLACK);
        selectBtn.setDisable(true);
        
        found = new ArrayList();
        delete.setDisable(true);
        moveBtn.setDisable(true);
        resizeBtn.setDisable(true);
        copyBtn.setDisable(true);
        undoStack = new Stack<>();
        namesStack = new Stack<>();
        undoBtn.setDisable(true);
        redoBtn.setDisable(true);
        downBtn.setDisable(true);
        rightBtn.setDisable(true);
        upBtn.setDisable(true);
        liftBtn.setDisable(true);
        exitMoveModeBtn.setDisable(true);

    }

    public ColorPicker getColorPicker() {
        return colorPicker;
    }

    @FXML
    private Canvas canvas;
    private Stage stg;

    void init(Stage stage) {
        // throw new UnsupportedOperationException("Not supported yet."); 
        this.stg = stage;
    }

    @FXML
    private void openFile() throws FileNotFoundException, ParseException {
        hmap.clear();
        priority = 0;
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stg);
        System.out.println(file.getPath());
        gc.setFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        LoadJSON.jsonArray(file.getPath());
        LoadJSON.Load();
        for (int i = 0; i < LoadJSON.array.size(); i++) {
            if (LoadJSON.type.get(i).compareToIgnoreCase("oval") == 0) {
                Oval o = (Oval) LoadJSON.array.get(i);
                o.addShape(hmap);
            } else if (LoadJSON.type.get(i).compareToIgnoreCase("rectangle") == 0) {
                Rectangle r = (Rectangle) LoadJSON.array.get(i);
                r.addShape(hmap);
            } else if (LoadJSON.type.get(i).compareToIgnoreCase("triangle") == 0) {
                Triangle r = (Triangle) LoadJSON.array.get(i);
                r.addShape(hmap);
            } else if (LoadJSON.type.get(i).compareToIgnoreCase("square") == 0) {
                Square s = (Square) LoadJSON.array.get(i);
                s.addShape(hmap);
            } else if (LoadJSON.type.get(i).compareToIgnoreCase("line") == 0) {
                Line l = (Line) LoadJSON.array.get(i);
                hmap.put(priority, l);
                priority++;
            } else if (LoadJSON.type.get(i).compareToIgnoreCase("circle") == 0) {
                model.Circle c = (model.Circle) LoadJSON.array.get(i);
                c.addShape(hmap);
            }

        }
        MydrawingEngine.parse(hmap, gc);
    }
            
     @FXML
    private void saveJSON() throws IOException, ParserConfigurationException {
        SaveJSON.save(hmap);
           
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stg);
        if (file != null) {
            buffer(SaveJSON.readFile(), file);
        }
    }
    @FXML
    private void saveXML() throws IOException, ParserConfigurationException {
        SaveXML.Save(hmap);
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stg);
        if (file != null) {
            buffer(SaveXML.readFile(), file);
        }
    }

    @FXML
    private void saveFile() throws IOException, ParserConfigurationException {
       
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter2);
        FileChooser.ExtensionFilter extFilter3 = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter3);
        File file = fileChooser.showSaveDialog(stg);
      if (  fileChooser.getSelectedExtensionFilter().equals(extFilter) )
      {
          
        if (file != null) {
             SaveJSON.save(hmap);
            buffer(SaveJSON.readFile(), file);
        }
      }
      else if (  fileChooser.getSelectedExtensionFilter().equals(extFilter2) )
      {
           SaveXML.Save(hmap);
      
        if (file != null) {
            buffer(SaveXML.readFile(), file);
        }
          
      }
      else if (  fileChooser.getSelectedExtensionFilter().equals(extFilter3) )
      {
          
        WritableImage wi = new WritableImage((int)canvas.getWidth(),(int)canvas.getHeight()); //3'ayar de bl function ely
        try {                    ImageIO.write(SwingFXUtils.fromFXImage(canvas.snapshot(null,wi),null),"png",file);
        } catch (IOException e) {
        }
          
      }
        
        
    }

    private void buffer(String content, File file) {
        try {

            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "error!");

        }

    }

    @FXML
    private void canvasOnMousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
        javafx.scene.paint.Paint currentfill = fillpick.getValue();
        javafx.scene.paint.Paint currentcolor = colorPicker.getValue();

        if (shape.compareTo("move") == 0) {

        }

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
            freehmap.put(freecount, b);
            freecount++;
            MydrawingEngine.parsebrush(freehmap, gc);

        } else if (shape.compareTo("circle") == 0) {
            Oval c = new Oval();
            c.updateShape(hmap, isFilled, currentfill, startX, startY, currentX, currentY, currentcolor, width.getValue());
            MydrawingEngine.refresh(gc, canvas, currentfill);
            MydrawingEngine.parse(hmap, gc);
            MydrawingEngine.parsebrush(freehmap, gc);
            selectBtn.setDisable(false);
            undoBtn.setDisable(false);

            // gc.fillRect(c.getUpperLeftX(), c.getUpperLeftY(), c.getWidth(), c.getHeight() );
        } else if (shape.compareTo("rectangle") == 0) {
            Rectangle c = new Rectangle();
            c.updateShape(hmap, isFilled, currentfill, startX, startY, currentX, currentY, currentcolor, width.getValue());
            MydrawingEngine.refresh(gc, canvas, currentfill);
            MydrawingEngine.parse(hmap, gc);
            MydrawingEngine.parsebrush(freehmap, gc);
            selectBtn.setDisable(false);
            undoBtn.setDisable(false);

        } else if (shape.compareTo("triangle") == 0) {
            Triangle c = new Triangle();
            c.updateShape(hmap, isFilled, currentfill, startX, startY, currentX, currentY, currentcolor, width.getValue());
            MydrawingEngine.refresh(gc, canvas, currentfill);
            MydrawingEngine.parse(hmap, gc);
            MydrawingEngine.parsebrush(freehmap, gc);
            selectBtn.setDisable(false);
            undoBtn.setDisable(false);

        } else if (shape.compareTo("line") == 0) {
            Line l = new Line();
            l.setX1((int) startX);
            l.setY1((int) startY);
            l.setX2((int) currentX);
            l.setY2((int) currentY);
            l.setLineWidth(width.getValue());
            l.setPaint(colorPicker.getValue());
            l.addShape(hmap);
            priority--;
            MydrawingEngine.refresh(gc, canvas, currentfill);
            MydrawingEngine.parse(hmap, gc);
            MydrawingEngine.parsebrush(freehmap, gc);
            undoBtn.setDisable(false);

        } else if (shape.compareTo("square") == 0) {
            Square c = new Square();
            c.updateShape(hmap, isFilled, currentfill, startX, startY, currentX, currentY, currentcolor, width.getValue());
            MydrawingEngine.refresh(gc, canvas, currentfill);
            MydrawingEngine.parse(hmap, gc);
            MydrawingEngine.parsebrush(freehmap, gc);
            undoBtn.setDisable(false);

        } else if (shape.compareTo("sphere") == 0) {
            model.Circle c = new model.Circle();
            c.updateShape(hmap, isFilled, currentfill, startX, startY, currentX, currentY, currentcolor, width.getValue());
            MydrawingEngine.refresh(gc, canvas, currentfill);
            MydrawingEngine.parse(hmap, gc);
            MydrawingEngine.parsebrush(freehmap, gc);
            undoBtn.setDisable(false);

        } else if (shape.compareTo("eraser") == 0) {
            double size = Double.parseDouble(widthText.getText());
            double x = e.getX() - (size / 2);
            double y = e.getY() - (size / 2);
            Brush b = new Brush(size * 2, x, y, Color.WHITE);
            freehmap.put(freecount, b);
            freecount++;
            MydrawingEngine.parsebrush(freehmap, gc);

        }

    }

    @FXML

    private void brushBtn(ActionEvent event) {
        shape = "brush";
        isCopy = false;
        isDeleteUndo = false;
        isDeleted = false;
        isSelected = false;
    }

    @FXML
    private void circleBtn(ActionEvent event) {
        shape = "circle";
        Boolean mero = true;
        Filled.setDisable(!mero);
        fillpick.setDisable(!mero);
        delete.setDisable(true);
        moveBtn.setDisable(true);
        resizeBtn.setDisable(true);
        copyBtn.setDisable(true);
        isCopy = false;
        isDeleteUndo = false;
        isDeleted = false;
        isSelected = false;
    }

    @FXML
    private void squareBtn(ActionEvent event) {
        shape = "square";
        Boolean mero = true;
        Filled.setDisable(!mero);
        fillpick.setDisable(!mero);
        delete.setDisable(true);
        moveBtn.setDisable(true);
        resizeBtn.setDisable(true);
        copyBtn.setDisable(true);
        undoBtn.setDisable(true);
        redoBtn.setDisable(true);
        isCopy = false;
        isDeleteUndo = false;
        isDeleted = false;
        isSelected = false;
    }

    @FXML
    private void canvasOnMouseRe(MouseEvent e) {
        endX = e.getX();
        endY = e.getY();
        hght = Math.abs(endY - startY);
        wdth = Math.abs(endX - startX);

        if (shape.compareTo("select") == 0 && isSelected) {

            for (int i = BoundsOperations.boundMap.size() - 1; i >= 0; i--) {
                System.out.print("x1:  " + BoundsOperations.boundMap.get(i)[0]);
                System.out.print("x2:   " + BoundsOperations.boundMap.get(i)[2]);
                System.out.print("Y1:  " + BoundsOperations.boundMap.get(i)[1]);
                System.out.print("y2:  " + BoundsOperations.boundMap.get(i)[3]);
                System.out.println("");
                if (startX >= Math.min(BoundsOperations.boundMap.get(i)[0], BoundsOperations.boundMap.get(i)[2]) && startX <= Math.max(BoundsOperations.boundMap.get(i)[0], BoundsOperations.boundMap.get(i)[2]) && startY > Math.min(BoundsOperations.boundMap.get(i)[1], BoundsOperations.boundMap.get(i)[3]) && startY < Math.max(BoundsOperations.boundMap.get(i)[1], BoundsOperations.boundMap.get(i)[3]) && !hmap.isEmpty()) {

                    target = i;
                    System.out.println("tarrget" + target);
                    break;

                }

            }

        } else if (shape.compareTo("circle") == 0) {

            gc.setStroke(colorPicker.getValue());

            gc.setFill(fillpick.getValue());
            Oval c = new Oval();
            c.updateShapeinfo(hmap, isFilled, fillpick.getValue(), (int) startX, (int) startY, (int) endX, (int) endY, colorPicker.getValue(), width.getValue());
            BoundsOperations b = new BoundsOperations(c.getUpperLeftX(), c.getUpperLeftY(), c.getLowerRightX(), c.getLowerRightY(), selectCounter);
            selectCounter++;

            // c.setLineWidth(width.getValue());
            c.addShape(hmap);
            gc.setFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            MydrawingEngine.parse(hmap, gc);
            MydrawingEngine.parsebrush(freehmap, gc);
            gc.setFill(prev);

        } else if (shape.compareTo("rectangle") == 0) {

            gc.setStroke(colorPicker.getValue());
            gc.setFill(fillpick.getValue());
            Rectangle r = new Rectangle();
            r.updateShapeinfo(hmap, isFilled, fillpick.getValue(), (int) startX, (int) startY, (int) endX, (int) endY, colorPicker.getValue(), width.getValue());
            BoundsOperations b = new BoundsOperations(r.getUpperLeftX(), r.getUpperLeftY(), r.getLowerRightX(), r.getLowerRightY(), selectCounter);
            selectCounter++;
            // r.setLineWidth(width.getValue());
            r.addShape(hmap);
            gc.setFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            MydrawingEngine.parse(hmap, gc);
            MydrawingEngine.parsebrush(freehmap, gc);
            gc.setFill(prev);

        } else if (shape.compareTo("triangle") == 0) {
            if (!isFilled) {
                gc.setStroke(colorPicker.getValue());
            }
            gc.setFill(fillpick.getValue());
            Triangle t = new Triangle();
            t.updateShapeinfo(hmap, isFilled, fillpick.getValue(), (int) startX, (int) startY, (int) endX, (int) endY, colorPicker.getValue(), width.getValue());
            BoundsOperations b = new BoundsOperations(t.getUpperLeftX(), t.getUpperLeftY(), t.getLowerRightX(), t.getLowerRightY(), selectCounter);
            selectCounter++;
            //  t.setLineWidth(width.getValue());
            t.addShape(hmap);
            gc.setFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            MydrawingEngine.parse(hmap, gc);
            MydrawingEngine.parsebrush(freehmap, gc);
            gc.setFill(prev);
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
            l.setLineWidth(width.getValue());
            l.setPaint(colorPicker.getValue());
            // BoundsOperations.setNewBound(l.getUpperLeftX(), l.getUpperLeftY(), l.getWidth(), l.getHeight());
            //  l.setLineWidth(width.getValue());
            l.addShape(hmap);
            gc.setFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            MydrawingEngine.parse(hmap, gc);
            MydrawingEngine.parsebrush(freehmap, gc);
            gc.setFill(prev);
        } else if (shape.compareTo("square") == 0) {

            gc.setStroke(colorPicker.getValue());
            gc.setFill(fillpick.getValue());
            Square s = new Square();
            s.setFill(isFilled);
            s.updateShapeinfo(hmap, isFilled, fillpick.getValue(), (int) startX, (int) startY, (int) endX, (int) endY, colorPicker.getValue(), width.getValue());
            BoundsOperations b = new BoundsOperations(s.getUpperLeftX(), s.getUpperLeftY(), s.getLowerRightX(), s.getLowerRightY(), selectCounter);
            selectCounter++;
            // r.setLineWidth(width.getValue());
            s.addShape(hmap);
            gc.setFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            MydrawingEngine.parse(hmap, gc);
            MydrawingEngine.parsebrush(freehmap, gc);
            gc.setFill(prev);

        } else if (shape.compareTo("sphere") == 0) {
            model.Circle c = new model.Circle();
            c.updateShapeinfo(hmap, isFilled, fillpick.getValue(), (int) startX, (int) startY, (int) endX, (int) endY, colorPicker.getValue(), width.getValue());
            BoundsOperations b = new BoundsOperations(c.getUpperLeftX(), c.getUpperLeftY(), c.getLowerRightX(), c.getLowerRightY(), selectCounter);
            selectCounter++;
            // r.setLineWidth(width.getValue());
            c.addShape(hmap);

            undoBtn.setDisable(false);
            gc.setFill(javafx.scene.paint.Paint.valueOf("#ffffff"));
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            MydrawingEngine.parse(hmap, gc);
            MydrawingEngine.parsebrush(freehmap, gc);
            gc.setFill(prev);

        } else if (shape.compareTo("free") == 0) {

        }

        startX = 0;
        startY = 0;
        endX = 0;
        endY = 0;

    }

    @FXML
    private void rectangleBtn(ActionEvent event) {
        shape = "rectangle";
        Boolean mero = true;
        Filled.setDisable(!mero);
        fillpick.setDisable(!mero);
        delete.setDisable(true);
        moveBtn.setDisable(true);
        resizeBtn.setDisable(true);
        copyBtn.setDisable(true);
        undoBtn.setDisable(true);
        redoBtn.setDisable(true);
        isCopy = false;
        isDeleteUndo = false;
        isDeleted = false;
        isSelected = false;

    }

    @FXML
    private void triangleBtn(ActionEvent event) {
        shape = "triangle";
        Boolean mero = true;
        Filled.setDisable(!mero);
        fillpick.setDisable(!mero);
        delete.setDisable(true);
        moveBtn.setDisable(true);
        resizeBtn.setDisable(true);
        copyBtn.setDisable(true);
        undoBtn.setDisable(true);
        redoBtn.setDisable(true);
        isCopy = false;
        isDeleteUndo = false;
        isDeleted = false;
        isSelected = false;
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
        delete.setDisable(true);
        moveBtn.setDisable(true);
        resizeBtn.setDisable(true);
        copyBtn.setDisable(true);
        isCopy = false;
        isDeleteUndo = false;
        isDeleted = false;
        isSelected = false;
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
        target = 0;
        BoundsOperations.boundMap.clear();
        found.clear();
        undoBtn.setDisable(true);
        redoBtn.setDisable(true);
        isCopy = false;
        isDeleteUndo = false;
        isDeleted = false;
        isSelected = false;
    }

    @FXML
    private void setfill(ActionEvent event) {
        gc.setFill(javafx.scene.paint.Paint.valueOf(colorPicker.getValue().toString()));

    }

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
        isCopy = false;
    }

    @FXML
    private void onMouseMoved(MouseEvent event) {
        xCoordinate.setText(String.valueOf(event.getX()));
        yCoordinates.setText(String.valueOf(event.getY()));
    }

    @FXML
    private void selectBtnAction(ActionEvent event) {

        if (!isSelected) {
            isSelected = true;
            shape = "select";
            selectBtn.setDisable(true);
            delete.setDisable(false);
            moveBtn.setDisable(false);
            resizeBtn.setDisable(false);
            copyBtn.setDisable(false);

        } else {
            isSelected = false;

        }
    }

    @FXML
    private void moveBtnAction(ActionEvent event) {

        if (selectBtn.isDisable()) {
            shape = "move";

            delete.setDisable(true);
            moveBtn.setDisable(false);
            resizeBtn.setDisable(true);
            copyBtn.setDisable(true);
            downBtn.setDisable(false);
            rightBtn.setDisable(false);
            upBtn.setDisable(false);
            liftBtn.setDisable(false);
            exitMoveModeBtn.setDisable(false);
            shape = "move";

        }
    }

    @FXML
    private void resizeBtnAction(ActionEvent event) {
        if (selectBtn.isDisable()) {
            delete.setDisable(true);
            moveBtn.setDisable(true);
            resizeBtn.setDisable(false);
            copyBtn.setDisable(true);
            shape = "resize";
        }
        shape = "resize";

    }

    @FXML
    private void canvasOnMouseScroll(ScrollEvent event) {
        if (shape.compareTo("resize") == 0) {
            double deltaY = event.getDeltaY();
            Shape s = hmap.get(target);
            if (deltaY > 0) {
                System.out.println("up");
                // s.setX1(s.getX1() - 25);
                if (s.getX1() <= s.getX2() - 25) {
                    s.setX2(s.getX2() - 25);
                }
                // s.setY1(s.getY1() - 25);
                if (s.getY1() < s.getY2() - 25) {
                    s.setY2(s.getY2() - 25);
                }

                MydrawingEngine.refresh(gc, canvas, currentfill);
                MydrawingEngine.parse(hmap, gc);

            } else if (deltaY < 0) {
                System.out.println("down");
                // s.setX1(s.getX1() - 25);
                if (s.getX1() <= s.getX2() + 25) {
                    s.setX2(s.getX2() + 25);
                }
                // s.setY1(s.getY1() - 25);
                if (s.getY1() < s.getY2() + 25) {
                    s.setY2(s.getY2() + 25);
                }
                MydrawingEngine.refresh(gc, canvas, currentfill);
                MydrawingEngine.parse(hmap, gc);
            }

        }
    }

    @FXML
    private void copyBtnAction(ActionEvent event) {

        delete.setDisable(true);
        moveBtn.setDisable(true);
        resizeBtn.setDisable(true);
        copyBtn.setDisable(true);
        selectBtn.setDisable(false);

        //set offset
        Shape s = BoundsOperations.copyShape(hmap.get(target));

        System.out.println("beforeeee" + hmap.size());

        /*s.setX1(s.getX1()+50);
        s.setY1(s.getY1()+50);
        s.setX2(s.getX2()+50);
        s.setY2(s.getY2()+50);*/
        System.out.println("orignallll" + target);
        s.setFillPaint(hmap.get(target).getFillPaint());
        //  s.setFillPaint(hmap.get(target).getFillPaint());
        s.addShape(hmap);


        /* hena feh error 
                - bya5od offset sah , w by7ot el shape fe el hmap sah,
                    bas byrsem 8alat
         */
        BoundsOperations b = new BoundsOperations(s.getX1(), s.getY1(), s.getX2(), s.getX2(), selectCounter);
        selectCounter++;
        System.out.println(hmap.get(hmap.size() - 1).getX1());
        System.out.println(hmap.get(hmap.size() - 2).getX1());
        MydrawingEngine.parse(hmap, gc);
        System.out.println("beforeeee2" + hmap.size());

        isCopy = true;
        isDeleteUndo = false;
        isDeleted = false;
        isSelected = false;

    }

    @FXML
    private void deleteBtnAction(ActionEvent event) {
        if (selectBtn.isDisable()) {
            delete.setDisable(false);
            moveBtn.setDisable(true);
            resizeBtn.setDisable(true);
            copyBtn.setDisable(true);
        }
        undoBtn.setDisable(false);
        undoStack.push(hmap.get(target));
        redoBtn.setDisable(true);
        isDeleted = true;
        selectBtn.setDisable(false);
        MydrawingEngine.removeShape(hmap);
        priority--;
        MydrawingEngine.refresh(gc, canvas, currentfill);
        MydrawingEngine.parse(hmap, gc);

    }

    @FXML
    private void undoBtnAction(ActionEvent event) {
        if (isCopy) {
            // System.out.println(hmap.get(priority-2).toString());

            undoStack.push(hmap.get(hmap.size() - 1));
            System.out.println(hmap.size() - 1);

            hmap.remove(hmap.size() - 1);
            priority--;
            MydrawingEngine.refresh(gc, canvas, currentfill);
            MydrawingEngine.parse(hmap, gc);
            redoBtn.setDisable(false);
            delete.setDisable(true);
            moveBtn.setDisable(true);
            resizeBtn.setDisable(true);
            copyBtn.setDisable(true);

        } else if (isDeleted) {
            System.out.println("hiiiiii2");

            hmap.put(priority, undoStack.pop());

            priority++;
            MydrawingEngine.refresh(gc, canvas, currentfill);
            MydrawingEngine.parse(hmap, gc);
            undoBtn.setDisable(true);
            redoBtn.setDisable(false);
            isDeleteUndo = true;
            delete.setDisable(true);
            moveBtn.setDisable(true);
            resizeBtn.setDisable(true);
            copyBtn.setDisable(true);
        } else if (priority > 0) {
            ///  undoBtn.setDisable(false);
            redoBtn.setDisable(false);
            undoStack.push(hmap.get(priority - 1));
            hmap.remove(priority - 1);
            priority--;
            System.out.println(hmap.size());

            MydrawingEngine.refresh(gc, canvas, currentfill);
            MydrawingEngine.parse(hmap, gc);
            delete.setDisable(true);
            moveBtn.setDisable(true);
            resizeBtn.setDisable(true);
            copyBtn.setDisable(true);
        }

        if (hmap.size() == 0) {
            undoBtn.setDisable(true);
        }
        selectBtn.setDisable(false);
        delete.setDisable(true);
        delete.setDisable(true);
        moveBtn.setDisable(true);
        resizeBtn.setDisable(true);
        copyBtn.setDisable(true);

    }

    @FXML
    private void redoBtnAction(ActionEvent event) {

        if (isCopy) {
            hmap.put(priority, undoStack.pop());
            priority++;
            MydrawingEngine.refresh(gc, canvas, currentfill);
            MydrawingEngine.parse(hmap, gc);
        } else if (isDeleteUndo) {
            redoBtn.setDisable(true);
            undoStack.push(hmap.get(priority - 1));
            hmap.remove(priority - 1);
            priority--;
            System.out.println(hmap.size());

            MydrawingEngine.refresh(gc, canvas, currentfill);
            MydrawingEngine.parse(hmap, gc);
        } else if (undoStack.size() > 0) {
            redoBtn.setDisable(false);
            undoBtn.setDisable(false);
            System.out.println(priority);
            hmap.put(priority, undoStack.pop());

            priority++;
            MydrawingEngine.refresh(gc, canvas, currentfill);
            MydrawingEngine.parse(hmap, gc);
        }
        if (undoStack.size() <= 0) {
            redoBtn.setDisable(true);
        }
        selectBtn.setDisable(false);
        delete.setDisable(true);

    }

    @FXML
    public void sphereBtn() {
        shape = "sphere";
    }

    @FXML
    private void upBtnAction(ActionEvent event) {
        Shape s = hmap.get(target);
        s.setY1(s.getY1() - 25);
        s.setY2(s.getY2() - 25);
        MydrawingEngine.refresh(gc, canvas, currentfill);
        MydrawingEngine.parse(hmap, gc);
    }

    @FXML
    private void downBtnAction(ActionEvent event) {
        Shape s = hmap.get(target);
        s.setY1(s.getY1() + 25);
        s.setY2(s.getY2() + 25);
        MydrawingEngine.refresh(gc, canvas, currentfill);
        MydrawingEngine.parse(hmap, gc);
    }

    @FXML
    private void rightBtnAciton(ActionEvent event) {
        Shape s = hmap.get(target);
        s.setX1(s.getX1() + 25);
        s.setX2(s.getX2() + 25);
        MydrawingEngine.refresh(gc, canvas, currentfill);
        MydrawingEngine.parse(hmap, gc);
    }

    @FXML
    private void liftBtnAction(ActionEvent event) {
        Shape s = hmap.get(target);
        s.setX1(s.getX1() - 25);
        s.setX2(s.getX2() - 25);
        MydrawingEngine.refresh(gc, canvas, currentfill);
        MydrawingEngine.parse(hmap, gc);
    }

    @FXML
    private void exitMoveModeBtnAction(ActionEvent event) {
        downBtn.setDisable(true);
        rightBtn.setDisable(true);
        upBtn.setDisable(true);
        liftBtn.setDisable(true);
        selectBtn.setDisable(false);
        delete.setDisable(true);
        moveBtn.setDisable(true);
        resizeBtn.setDisable(true);
        copyBtn.setDisable(true);
        exitMoveModeBtn.setDisable(true);

    }

 @FXML
    private void savePNG() throws IOException {
         FileChooser fc = new FileChooser();
 
    fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG","*.png"));
  
    File file = fc.showSaveDialog(stg);
    if(file != null){
        WritableImage wi = new WritableImage((int)canvas.getWidth(),(int)canvas.getHeight()); //3'ayar de bl function ely
        try {                    ImageIO.write(SwingFXUtils.fromFXImage(canvas.snapshot(null,wi),null),"png",file);
        } catch (IOException e) {
        }
    }
  
   
}
}
