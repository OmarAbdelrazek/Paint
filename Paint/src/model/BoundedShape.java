package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import static view.PaintController.priority;

/**
 * This is an abstract class with an abstract draw method. It inherits from MyShape
 * and contains methods needed for drawing ovals and rectangles. It also contains an instance variable called fill.
 */
abstract class BoundedShape extends Shape
{
    private boolean fill; //boolean variable that determines whether the shape is filled or not
    
    /**
     * public constructor that takes no parameters and calls the no parameter constructor for Myshape.
     * It sets fill to false.
     */
    public BoundedShape()
    {
        super();
        fill=false;
    }
    
    /**
     * public overloaded constructor that takes int coordinates and a boolean for fill.
     * It passes the coordinates and color into the constructor for Myshape and assigns
     * the fill to an instance variable fill.
     */
    public BoundedShape(int x1, int y1, int x2, int y2, Paint paint, boolean fill, Paint fillpaint, Double lw)
    {
        super(x1, y1, x2, y2, paint,fillpaint,lw);
        this.fill=fill;
    }
    
    //Mutator methods
    
    /**
     * set fill
     */
    public void setFill(boolean fill)
    {
        this.fill=fill;
    }
    
    /**
     * gets the upper left x
     */
    public int getUpperLeftX()
    {
        return Math.min(getX1(),getX2());
    }
    public int getLowerRightX()
    {
        return Math.max(getX1(),getX2());
    }
    /**
     * gets the upper left y
     */
    public int getUpperLeftY()
    {
        return Math.min(getY1(),getY2());
    }
    public int getLowerRightY()
    {
        return Math.max(getY1(),getY2());
    }
    
    /**
     * gets width
     */
    public int getWidth()
    {
        return Math.abs(getX1()-getX2());
    }
    
    //Accessor methods
    
    /**
     * gets height
     */
    public int getHeight()
    {
        return Math.abs(getY1()-getY2());
    }
    
    /**
     * return fill
     */
    public boolean getFill()
    {
        return fill;
    }
        public void updateShape(HashMap shapehm,Boolean isFilled ,Paint fillpaint, double startX,double startY, double currentX,double currentY ,Paint paint , Double lw)
    {
            this.setFill(isFilled);
            this.setFillPaint(fillpaint);
            this.setX1((int) startX);
            this.setY1((int) startY);
            this.setX2((int) currentX);
            this.setY2((int) currentY);
            this.setPaint(paint);
            this.setLineWidth(lw);
            this.addShape(shapehm);
            priority--;


    }
        public void updateShapeinfo(HashMap shapehm, Boolean isFilled, Paint fillpaint, double startX,double startY, double currentX,double currentY ,Paint paint ,Double lw)
    {       
            this.setFill(isFilled);
            this.setFillPaint(fillpaint);
            this.setX1((int) startX);
            this.setY1((int) startY);
            this.setX2((int) currentX);
            this.setY2((int) currentY);
            this.setPaint(paint);
            this.setLineWidth(lw);
    }
    
    /**
     * Abstract method for drawing the shape that must be overriden
     */
        public void Clone (BoundedShape s)
    {   
        this.setLineWidth(s.getLineWidth());
        this.setX1(s.getX1()+50);
        this.setY1(s.getY1()+50);
        this.setX2(s.getX2()+50);
        this.setY2(s.getY2()+50);
        this.setFillPaint(s.getFillPaint());
        this.setPaint(this.getPaint());
        this.setFill(s.getFill());
    }
} // end class MyBoundedShape