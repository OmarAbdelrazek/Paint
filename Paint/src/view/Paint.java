/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Tommy
 */
public class Paint extends Application {

    static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void start(Stage stage) throws Exception {
       FXMLLoader loader= new FXMLLoader(getClass().getResource("Paint.fxml"));
        Parent root = loader.load();
        PaintController controller = (PaintController)loader.getController();
        controller.init(stage);
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
stage.getIcons().add( new Image(    Paint.class.getResourceAsStream( "icon.png" ))); 
        
        stage.setTitle("Paint++");        
       
        
        stage.setResizable(false);
       // stage.setMaximized(true);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
