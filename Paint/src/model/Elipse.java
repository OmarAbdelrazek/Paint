/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Tommy
 */
public class Elipse extends BoundedShape {

    public Elipse( int x1, int y1, int x2, int y2, Color color, boolean fill )
    {
        super(x1, y1, x2, y2, color,fill);
    }
     
    /**
     * Overrides the draw method in MyBoundedShape. It sets the gets the color from MyBoundedShape
     * to set the color and the values it needs to draw from MyBoundedShape as well.
     * @param g
     */
    @Override
    public void draw( GraphicsContext g)
    {
        g.setStroke( g.getStroke() ); //sets the color
        if (getFill()) //determines whether fill is true or false
            g.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() ); //draws a filled oval
        else
            g.strokeOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() ); //draws a regular oval
        
    }


}
