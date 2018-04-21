package model;

import java.awt.Color;
import java.awt.Graphics;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class inherits from MyShape and is responsible for drawing a line.
 */
public class Line extends Shape
{  
    /**
     * No parameter constructor which calls the no parameter constructor in MyShape
     */
     private double size;
    public Line(double size)
    {
        super();
        this.size = size;
    }
    
    /** 
     * Overloaded constructor that takes coordinates and color. It passes them to the constructor in MyShape
     */
  
    public Line( int x1, int y1, int x2, int y2, Color color , double size )
    {
        super(x1, y1, x2, y2, color);
        this.size = size;
    } 
     
    /**
     * Overrides the draw method in Myshape. It sets the gets the color from Myshape
     * and the coordinates it needs to draw from MyShape as well.
     */
    public void draw(GraphicsContext g)
    {
        g.setStroke(g.getStroke() ); //sets the color
        g.setLineWidth(size);
        g.strokeLine( getX1(), getY1(), getX2(), getY2() ); //draws the line
    } 
} 