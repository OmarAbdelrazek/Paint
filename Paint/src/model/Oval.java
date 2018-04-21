package model;

import java.awt.Color;
import java.awt.Graphics;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * This class inherits from MyBoundedShape and is responsible for drawing a
 * oval.
 */
public class Oval extends BoundedShape {

    /**
     * No parameter constructor which calls the no parameter constructor in
     * MyBoundedShape.
     */
    public Oval() {
        super();
    }

    /**
     * Overloaded constructor that takes coordinates, color and fill. It passes
     * them into MyBoundedShape's constructor.
     */
    public Oval(int x1, int y1, int x2, int y2,Paint paint, boolean isfill,Paint fillpaint,Double lw) {
        super(x1, y1, x2, y2, paint, isfill ,fillpaint,lw);
    }

    /**
     * Overrides the draw method in MyBoundedShape. It sets the gets the color
     * from MyBoundedShape to set the color and the values it needs to draw from
     * MyBoundedShape as well.
     */
    @Override
    public void draw(GraphicsContext g) {

        g.setStroke(this.getPaint()); //sets the color
        //g.setLineWidth(this.getLineWidth());
        if (getFill()) //determines whether fill is true or false
        {   g.setFill(this.getFillPaint());
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
