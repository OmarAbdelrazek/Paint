/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 *
 * @author Tommy
 */
public class Elipse extends BoundedShape {

    public Elipse(int x1, int y1, int x2, int y2, Paint paint, boolean fill, Paint fillpaint,Double lw) {
        super(x1, y1, x2, y2, paint, fill, fillpaint,lw);
    }

    /**
     * Overrides the draw method in MyBoundedShape. It sets the gets the color
     * from MyBoundedShape to set the color and the values it needs to draw from
     * MyBoundedShape as well.
     *
     * @param g
     */
    @Override
    public void draw(GraphicsContext g) {
       // g.setLineWidth(this.getLineWidth());
        g.setStroke(this.getPaint()); //sets the color
        if (getFill()) //determines whether fill is true or false
        {
            g.setFill(this.getFillPaint());
            g.strokeOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
            g.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight()); //draws a filled oval
        } else {
            g.strokeOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight()); //draws a regular oval
        }
    }

    @Override
    public void update(GraphicsContext gc) {

        gc.clearRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        gc.strokeOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
    }

}
