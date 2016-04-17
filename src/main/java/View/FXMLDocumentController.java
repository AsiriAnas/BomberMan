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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
   
@FXML public GridPane gridpane;

public  void displayTerrain(  )
{
    Case [][] tableInterface = Client._boardClient.getTableInterface();
    
    for(int lig = 0; lig < Client._boardClient.getPheight() ; lig++ )
        {
            for( int col = 0; col< Client._boardClient.getPwidth() ; col++)
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
      displayTerrain();
      System.out.println("initialisation interface");
    }
    

 
}
