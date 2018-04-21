package model;

import java.awt.Color;
import java.awt.Graphics;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * This class inherits from MyBoundedShape and is responsible for drawing a
 * rectangle
 */
public class Rectangle extends BoundedShape {

    /**
     * No parameter constructor which calls the no parameter constructor in
     * MyBoundedShape
     */
    public Rectangle() {
        super();
    }

    /**
     * Overloaded constructor that takes coordinates, color and fill. It passes
     * them into MyBoundedShape's constructor
     */
    public Rectangle(int x1, int y1, int x2, int y2, Paint paint, boolean fill, Paint fillpaint,Double lw) {
        super(x1, y1, x2, y2, paint, fill, fillpaint, lw);
    }

    /**
     * Overrides the draw method in MyBoundedShape. It sets the gets the color
     * from MyBoundedShape to set the color and the values it needs to draw from
     * MyBoundedShape as well.
     */
    @Override
    public void draw(GraphicsContext g) {
        //g.setLineWidth(this.getLineWidth());
        g.setStroke(this.getPaint()); //sets the color
        if (getFill()) //determines whether fill is true or false
        {
            g.setFill(this.getFillPaint());
            g.strokeRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
            g.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight()); //draws a filled rectangle
        } else {
            g.strokeRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight()); //draws a regular rectangle
        }
    }

    @Override
    public void update(GraphicsContext gc) {
        gc.clearRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        gc.strokeRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());

    }

}
