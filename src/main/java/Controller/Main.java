/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.Joueur;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Anas
 */
public class Main extends Application{
    
    protected static ObjectOutputStream canalSortie;
    protected static ObjectInputStream canalEntree;
    
    public static void main(String[] args) {
        Socket socket;

        try {
            socket = new Socket("localhost",18000);
                
            canalSortie=new ObjectOutputStream(socket.getOutputStream());
            canalEntree=new ObjectInputStream(socket.getInputStream());
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        
        /*
        Joueur joueur = new Joueur(8, "Donat", 5, 5);
        GameInterface game = new GameInterface(10, 10,joueur);
        */
        
        Application.launch(Main.class, args);
    }
    
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLView.fxml"));
    primaryStage.setTitle("Bomber Woman");
    
    Scene scene = new Scene(root);
    
    // tu t'en fou dureste
    /****/
    Joueur joueur = new Joueur(545158, "Donat", 5, 5);
    final GameBoard game = new GameBoard(10, 10,joueur);
    // game.getTableInterface();  // pour marie
    /***/
   
    scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
          public void handle(KeyEvent ke){
          
              System.out.println("Key pressed : " + ke.getCode());
              game.game( ke.getCode().toString() );
              
          }
        });

    primaryStage.setScene(scene);
        
    primaryStage.show();
    
    }
    
}
