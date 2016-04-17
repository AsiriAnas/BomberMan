/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.Joueur;
import View.FXMLDocumentController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
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
    public static GameBoard _boardClient;
    public static Joueur _joueur;
    public static Group _root = new Group();
    
   
    public static void main(String[] args) throws ClassNotFoundException {
        //On créé la socket qui sera liée à celle du serveur
        Socket socket;
        try {
            //On se connecte au serveur
            socket = new Socket("localhost",18000);
                
            //On instancie les canaux
            _canalSortie=new ObjectOutputStream(socket.getOutputStream());
            _canalEntree=new ObjectInputStream(socket.getInputStream());
            //On récupère le tableau de jeu et le joueur
            _boardClient = (GameBoard)_canalEntree.readObject();
            _joueur = (Joueur)_canalEntree.readObject();
            //On affiche ce tableau
            _boardClient.afficheInterface();
            
            //Thread qui va écouter le serveur
            Thread ListenerThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        //il tourne en boucle attendant un éventuel changement
                        while (true) {
                            try {
                                _boardClient = (GameBoard) _canalEntree.readObject();
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                            _boardClient.afficheInterface();
                        }
                    } catch (Exception e) {
                        System.out.println("Listener Thread error : " + e.getMessage());
                    }

                }
            });
        ListenerThread.start();
            
        } catch (Exception ioe){
            ioe.printStackTrace();
            System.out.println("Main Error " + ioe.getMessage());
        }
        
        Application.launch(Client.class, args);
    }
    
   
    @Override
    public void start(Stage stage) throws Exception {
        
    //On créé l'interface graphique
    Parent parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLInterface.fxml"));
    stage.setTitle("Bomber Woman");
    //Le joueur est créé à partir de l'id reçu
    //joueur = new Joueur(idJoueur, 5, 5);
    _root.getChildren().add(parent);
    Scene scene = new Scene(_root);
   
    //Cette partie gère l'interaction avec l'utilisateur
    scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
        public void handle(KeyEvent key){
            //On envoie le code de la touche appuyée au serveur
            try {
                _canalSortie.writeObject(key.getCode().toString());
                 refreshScreen();
            } catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    });    
    stage.setScene(scene);  
    stage.show();
    //Si le joueur quitte le jeu, on envoie au serveur la commande QUIT pour stoper la connexion
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
    
    public void refreshScreen()
    {
        try
        {
         Parent parentreload = FXMLLoader.load(getClass().getResource("/fxml/FXMLInterface.fxml"));
         Client._root.getChildren().add(parentreload);
        }
        catch(Exception ex)
        {
            System.out.println("Refresh Gui Failed :" + ex.getMessage() );
        }
    }
}
