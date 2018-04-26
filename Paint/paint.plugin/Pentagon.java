package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * This class inherits from MyBoundedShape and is responsible for drawing a
 * oval.
 */
public class Pentagon extends BoundedShape {

    /**
     * No parameter constructor which calls the no parameter constructor in
     * MyBoundedShape.
     */
    public Pentagon() {
        super();
    }

    /**
     * Overloaded constructor that takes coordinates, color and fill. It passes
     * them into MyBoundedShape's constructor.
     */
    public Pentagon(int x1, int y1, int x2, int y2, Paint paint, boolean isfill, Paint fillpaint, Double lw) {
        super(x1, y1, x2, y2, paint, isfill, fillpaint,lw);
    }

    /**
     * Overrides the draw method in MyBoundedShape. It sets the gets the color
     * from MyBoundedShape to set the color and the values it needs to draw from
     * MyBoundedShape as well.
     */
    public void draw(GraphicsContext g) {
        double[] Xcoord = {getX1() + Math.abs(getX2() - getX1()) / 2, getX1(), getX2()};
        double[] Ycoord = {getY1(), getY2(), getY2()};
       // g.setLineWidth(this.getLineWidth());
        g.setStroke(this.getPaint()); //sets the color
        if (getFill()) //determines whether fill is true or false
        {
            g.setFill(this.getFillPaint());
            g.strokePolygon(Xcoord, Ycoord, 5);
            g.fillPolygon(Xcoord, Ycoord, 5); //draws a filled triangle
        } else {
            g.strokePolygon(Xcoord, Ycoord, 5); //draws a regular triangle
        }

    }

    public void update(GraphicsContext gc) {
        double[] Xcoord = {getX1() + Math.abs(getX2() - getX1()) / 2, getX1(), getX2(),50,100};
        double[] Ycoord = {getY1(), getY2(), getY2(),200,300};
        gc.clearRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        gc.strokePolygon(Xcoord, Ycoord, 3);

    }


}
