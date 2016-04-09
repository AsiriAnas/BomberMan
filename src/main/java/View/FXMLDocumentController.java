/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Controller.GameBoard;
import Model. *;


//import java.net.URL;
//import java.util.ResourceBundle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;



/**
 *
 * @author marie
 */
public class FXMLDocumentController implements Initializable {
   
@FXML private GridPane gridpane;
Label casemap= new Label();
Joueur joueur = new Joueur(545158, "Donat", 5, 5);
final GameBoard game = new GameBoard(10, 10,joueur);
private Case _tableInterface[][];



/*public  void    display_terrain(GameBoard game)
{
   for(int lig = 0; lig < 30; lig++ )
        {
            for(int col = 0; col< 30; col++)
            {
                            switch(_tableInterface[lig][col])
                    {
                        case    1:
                           gridpane.setStyle("-fx-background-color: black");
                        case    2:
                            gridpane.setStyle("-fx-background-color: black");
                        case    3:
                            gridpane.setStyle("-fx-background-color: black");
                        case    4:
                            gridpane.setStyle("-fx-background-color: black");
                        
                        break;
                       
                    }
         
                   gridpane.getChildren().addAll(casemap);
 
            }
            
           
       
    }    
            }*/
           
       @Override
    public void initialize(URL url, ResourceBundle rb) {
       // game.getTableInterface(); 
  
    }

}
