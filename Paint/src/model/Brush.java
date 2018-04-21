/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Omar
 */
public class Brush  {
    private double brushSize,startX,startY;
   private  Color color;
    public Brush(double bSize,double x , double y , Color color){
        brushSize = bSize;
        startX = x;
        startY = y;
        this.color = color;
        
    }

    
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRoundRect(startX, startY, brushSize, brushSize, brushSize, brushSize);
    }

    
    
    
}
