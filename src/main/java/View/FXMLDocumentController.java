



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
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;



/**
 *
 * @author marie
 */
public class FXMLDocumentController implements Initializable {
   
@FXML private GridPane gridpane;
@FXML private AnchorPane anchorpane;
//public Case _tableInterface[][];
private int _pwidth;
private int _pheight; 



public  void displayTerrain( )
        
{
    Case [][] tableInterface = Client._boardClient.getTableInterface(); // recuperation table 
   
    for(int lig = 0; lig < 11; lig++ )
        {
            for( int col = 0; col< 11; col++)
                
            {
                 Label casemap= new Label();

                Case casee = tableInterface[lig][col];
     
                if( casee.getType().equals(ETypeCase.Bombe) )
                {
                   
                    casemap.setStyle("-fx-background-color: black");
                }
                else if( casee.getType().equals(ETypeCase.MurCassable) )
                {
          
                   casemap.setStyle("-fx-background-image: url('fxml/murCassable.png')");
                    
                }
                else if( casee.getType().equals(ETypeCase.Vide) ) 
                {
                    casemap.setStyle("-fx-background-color: green");
                }
                else if( casee.getType().equals(ETypeCase.MurIncasable) ) 
                {
                    casemap.setStyle("-fx-background-image: url('fxml/murIncassable.png')");
                  
                }
                else if( casee.getPlayerId()==1 ) {
                casemap.setStyle("-fx-background-image: url('fxml/jouer.png')");
                
                 } 
                
                 else if( casee.getPlayerId()==1 ) {
                
                casemap.setStyle("-fx-background-image: url('fxml/jouer1.png')");
                 } 
                   else if( casee.getPlayerId()==3 ) {
                       
                
                casemap.setStyle("-fx-background-image: url('fxml/jouer1.png')");
                 } 
                
                
                    /*else if( casee.getType().equals(ETypeCase.Personnage) )
                    {
                    if(_id.equals(1))  casemap.setStyle("-fx-background-image: url('fxml/joueur.png')");
                    if(_id.equals(2))  casemap.setStyle("-fx-background-image: url('fxml/joueur.png')");
                    //casemap.setStyle("-fx-background-image: url('fxml/joueur.png')");
                    }*/
                casemap.setMinHeight(28); 
                casemap.setMinWidth(28);
                casemap.setText("");
                 gridpane.add(casemap, col, lig);
                 }
               
               
            }    

}


       @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        displayTerrain();
    }

     




}