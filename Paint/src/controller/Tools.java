/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import javafx.scene.canvas.GraphicsContext;
import model.*;
import view.PaintController;

/**
 *
 * @author Tommy
 */
public abstract class Tools implements DrawingEngine {

    public static void parse(HashMap Shapeshm, GraphicsContext gc) {
        for (int i = 0; i <= PaintController.priority; i++) {
            Shape s = (Shape) Shapeshm.get(i);
            s.draw(gc);

        }
    }
    //public static void refresh();

}
