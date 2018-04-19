package model;

import java.awt.Color;
import java.awt.Graphics;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class inherits from MyBoundedShape and is responsible for drawing a oval.
 */
public class Oval extends BoundedShape
{ 
    /**
     * No parameter constructor which calls the no parameter constructor in MyBoundedShape.
     */
    public Oval()
    {
        super();
    }
    
    /** 
     * Overloaded constructor that takes coordinates, color and fill. 
     * It passes them into MyBoundedShape's constructor.
     */
    public Oval( int x1, int y1, int x2, int y2, Color color, boolean isfill )
    {
        super(x1, y1, x2, y2, color,isfill);
    }
     
    /**
     * Overrides the draw method in MyBoundedShape. It sets the gets the color from MyBoundedShape
     * to set the color and the values it needs to draw from MyBoundedShape as well.
     */
    @Override
    public void draw(GraphicsContext g)
    {
        g.setStroke(g.getStroke()); //sets the color
        if (getFill() ) //determines whether fill is true or false
            g.fillOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() ); //draws a filled oval
        else
            g.strokeOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() ); //draws a regular oval
        
    }
    
} 