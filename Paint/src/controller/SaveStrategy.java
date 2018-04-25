/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import model.Shape;

/**
 *
 * @author Omar
 */
public interface SaveStrategy {

    /**
     *
     * @param hmap
     * @param path
     */
    public void save(java.util.HashMap hmap , String path);
    
}
