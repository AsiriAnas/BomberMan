/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author marie
 */
public class FXMLInterfaceController implements Initializable {
    
    @FXML private Canvas mainCanvas;
    public GraphicsContext gc;
    
    
    
    /*public void start() {
    gc = mainCanvas.getGraphicsContext2D();
      }*/

     
   @FXML
   
    public void drawShapes(ActionEvent event)
    {
       //gc = mainCanvas.getGraphicsContext2D(); 
       //gc.setFill(Color.AQUA);
       //gc.fillRect(10,10,100,100); 

       
    }

   
   /* @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }*/
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gc = mainCanvas.getGraphicsContext2D();
        Image image4 = new Image("file:C:/Users/marie/Documents/GitHub/BomberMan/src/main/resources/fxml/ECE_COUL_RVB.jpg", 0, 100, false, false);
        gc.drawImage(image4, 100, 100, 100, 100);    
       
    }    
    
}
