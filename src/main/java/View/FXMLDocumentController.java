/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Controller.*;
import Model. *;


//import java.net.URL;
//import java.util.ResourceBundle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.GridPane;



/**
 *
 * @author marie
 */
public class FXMLDocumentController implements Initializable {
   
@FXML private GridPane gridpane;
@FXML private AnchorPane anchorpane;
//Joueur joueur = new Joueur(545158, "Donat", 5, 5);
GameBoard game = new GameBoard(10, 10);
//public Case _tableInterface[][];
private int _pwidth;
private int _pheight; 



public  void displayTerrain( )
{
    Case [][] tableInterface = Client.boardClient.getTableInterface(); // recuperation table 
   
    for(int lig = 0; lig < 10; lig++ )
        {
            for( int col = 0; col< 10; col++)
                
            {
                Label casemap= new Label();
                Case casee = tableInterface[lig][col];
     
                if( casee.getType().equals(ETypeCase.Bombe) )
                {
                    casemap.setStyle("-fx-background-color: black");
                }
                else if( casee.getType().equals(ETypeCase.MurCassable) )
                {
                    casemap.setStyle("-fx-background-color: grey");
                }
                else if( casee.getType().equals(ETypeCase.Vide) ) 
                {
                    casemap.setStyle("-fx-background-color: yellow");
                }
                else if( casee.getType().equals(ETypeCase.MurIncasable) ) 
                {
                    casemap.setStyle("-fx-background-color: red");
                }
                else if( casee.getType().equals(ETypeCase.Personnage) ) 
                {
                    casemap.setStyle("-fx-background-color: green");
                }
                casemap.setMinHeight(35);
                casemap.setMinWidth(35);
                casemap.setText("");
                 gridpane.add(casemap, col, lig);
               
            }
                
           
           
       
    }    
            }
           
       @Override
    public void initialize(URL url, ResourceBundle rb) {
      //afficheInterface();
        displayTerrain();
    }

}
