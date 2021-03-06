package model;

import controller.MydrawingEngine;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.HashMap;
import static javafx.print.PrintColor.COLOR;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Paint;
import static view.PaintController.priority;
import static view.PaintController.target;

/**
 * This class contains int coordinates and a Color color. It has accessor and mutator methods for them.
 */
public abstract class Shape implements Serializable ,Cloneable
{
    private int x1,y1,x2,y2; //coordinates of shape
    private Paint paint,fillpaint; // color of shape
    Double lw;
    /**
    * public constructor which takes no variables and
    * sets coordinates to zero and color to black
    */
    public Shape()
    {
        x1=0;
        y1=0;
        x2=0;
        y2=0;
    }
    
    /**
    * overloaded constructor which takes variables for coordinates and 
    * color assigning them to the instance variables in the class
    */
    public Shape(int x1, int y1, int x2, int y2, Paint paint)
    {
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.paint=paint;
    }

    public Shape(int x1, int y1, int x2, int y2, Paint paint, Paint fillpaint, Double lw) {
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.paint=paint;
        this.fillpaint=fillpaint;
        this.lw=lw;
    }

    public Shape(int x1, int y1, int x2, int y2, Paint paint,Double lw) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.paint = paint;
        this.lw = lw;
    }

    public Double getLineWidth() {
        return lw;
    }

    public void setLineWidth(Double lw) {
        this.lw = lw;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Paint getFillPaint() {
        return fillpaint;
    }

    public void setFillPaint(Paint fill) {
        this.fillpaint = fill;
    }


    
    //Mutator methods
    
    /**
     * Mutator method for x1
     */
    public void setX1(int x1)
    {
        this.x1=x1;
    }   
    
    /**
     * Mutator method for y1
     */
    public void setY1(int y1)
    {
        this.y1=y1;
    }   
    
    /**
     * Mutator method for x2
     */
    public void setX2(int x2)
    {
        this.x2=x2;
    }   
    
    /**
     * Mutator method for y2
     */
    public void setY2(int y2)
    {
        this.y2=y2;
    }   
    
    /**
     * Mutator method for color
     */
    
    public int getWidth(int x1,int x2){
        return Math.abs(x1-x2);
    }
    public int getHeight(int y1, int y2){
    return Math.abs(y1-y2);
            
}
    
    
    //Accessor methods
    
    /**
     * Accessor method for x1
     */
    public int getX1()
    {
        return x1;
    }
    
    /**
     * Accessor method for y1
     */
    public int getY1()
    {
        return y1;
    }
    
    /**
     * Accessor method for x2
     */
    public int getX2()
    {
        return x2;
    }
    
    /**
     * Accessor method for y2
     */
    public int getY2()
    {
        return y2;
    }
    
    /**
     * Accessor method for color
     */
    
    abstract public void draw(GraphicsContext g);
    public void addShape(HashMap Shapeshm ){
    int order = view.PaintController.priority;
    Shapeshm.put(order, this);
    view.PaintController.priority++;
    }
    public void HighLightShape(GraphicsContext gc)
    {
        Rectangle r = new Rectangle();
        r.setX1(this.x1);
        r.setY1(this.y1);
        r.setX2(this.x2);
        r.setY2(this.y2);
        r.setLineWidth(10.0);
        r.setFillPaint(javafx.scene.paint.Paint.valueOf("#ffffff"));
        r.setFill(false);
        r.setPaint(javafx.scene.paint.Paint.valueOf("#000000"));
        gc.setLineDashes(50);
        r.draw(gc);
        gc.setLineDashes(0);
        
    }

    
    


    
    /**
     * Abstract method for drawing the shape that must be overriden
     */
    //public abstract Shape createCopy();


}