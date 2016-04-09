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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Anas
 */
public class Client extends Application{
    
    //Les canaux pour échanger avec le serveur
    protected static ObjectOutputStream _canalSortie;
    protected static ObjectInputStream _canalEntree;
    public static GameBoard boardClient;
    //Les objets suivants seront accessible par toutes les classes
    
    public static Joueur joueur;
    private static int idJoueur;
    
   
    public static void main(String[] args) throws ClassNotFoundException {
        Socket socket;
        try {
            //On se connecte au serveur
            socket = new Socket("localhost",18000);
                
            //On instancie les canaux
            _canalSortie=new ObjectOutputStream(socket.getOutputStream());
            _canalEntree=new ObjectInputStream(socket.getInputStream());
            //On récupère le tableau de jeu et l'id du joueur
            boardClient = (GameBoard)_canalEntree.readObject();
            idJoueur = (int)_canalEntree.readObject();
            
            boardClient.afficheInterface();
            
        //Thread qui va écouter le serveur
        Thread ListenerThread = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        boardClient = (GameBoard)_canalEntree.readObject();
                        boardClient.afficheInterface();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        });
        ListenerThread.start();
            
        } catch (Exception ioe){
            ioe.printStackTrace();
        }
        
        Application.launch(Client.class, args);
    }
    
   
    @Override
    public void start(Stage stage) throws Exception {
        
    //On créé l'interface graphique
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLView.fxml"));
    stage.setTitle("Bomber Woman");
    //Le joueur est créé à partir de l'id reçu
    //joueur = new Joueur(idJoueur, 5, 5);   
    
    Scene scene = new Scene(root);
   
    //Cette partie gère l'interaction avec l'utilisateur
    scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
        public void handle(KeyEvent key){
            //On envoie le code de la touche appuyée
            try {
                _canalSortie.writeObject(key.getCode().toString());
            } catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    });    
    stage.setScene(scene);  
    stage.show();
    stage.setOnCloseRequest(
        new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try {
                    _canalSortie.writeObject("QUIT");
                } catch (IOException ioe){
                    ioe.printStackTrace();
                }
          }
      });
    
    }
    
}
