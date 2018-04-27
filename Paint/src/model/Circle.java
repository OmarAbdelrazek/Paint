/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.HashMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;


public class Circle extends BoundedShape{
     public Circle() {
        super();
    }

    /**
     * Overloaded constructor that takes coordinates, color and fill. It passes
     * them into MyBoundedShape's constructor.
     */
    public Circle(int x1, int y1, int x2, int y2,Paint paint, boolean isfill,Paint fillpaint,Double lw) {
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
        g.setLineWidth(this.lw);//g.setLineWidth(this.getLineWidth());
        if (getFill()) //determines whether fill is true or false
        {   g.setFill(this.getFillPaint());
            g.strokeOval(getUpperLeftX(), getUpperLeftY(), Math.min(getWidth(), getHeight()),Math.min(getWidth(), getHeight()) );
            g.fillOval(getUpperLeftX(), getUpperLeftY(), Math.min(getWidth(), getHeight()),Math.min(getWidth(), getHeight())); //draws a filled oval
        } else {
            g.strokeOval(getUpperLeftX(), getUpperLeftY(), getWidth() ,getWidth()); //draws a regular oval
        }
    }



    @Override
    public String toString(){
        return "Circle";
    }

}
