package Controller;
import Model.Joueur;
import java.net.*;
import java.io.*;
import java.util.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;


// Classe associé à chaque client
// Il y aura autant d'instance de cette classe que de client connectés
public class SerThread implements Runnable
{   //Id du client
    private int _index;
    // contiendra le thread du client
    private Thread _thread; 
    // recevra le socket liant au client
    private Socket _socket;
    //Commande reçue par le client
    private String commande="";
    //Les canaux pour échanger les données avec le client
    protected static ObjectOutputStream _sortie;
    protected static ObjectInputStream _entree;
    private Joueur _joueur;
    //Instance de la classe GestionServeur pour utliser ses méthodes
    public static Timer time;

    //Le constructeur avec la socket client
  public SerThread(Socket pSocket)
  {  
    _socket=pSocket;
    try
    {
        //On instancie les entrées et sorties
        _entree=new ObjectInputStream(_socket.getInputStream());
        _sortie=new ObjectOutputStream(_socket.getOutputStream());
    }
    catch (IOException e){ 
    }
    //Une fois la sortie créée, on l'envoie dans la liste de sorties
    _index=GestionServeur.getId();
    //On utilise la méthode de GameBard pour placer notre joueur et on récupère sa position
    int[] posJoueur= GestionServeur._boardServeur.placerJoueur(_index);
    //On instancie notre joueur avec ces positions
    _joueur= new Joueur(_index, posJoueur[0] , posJoueur[1]);
    //on ajoute ensuite le joueur à la liste statique
    GestionServeur._listJoueurs.add(_joueur);
    // instanciation du thread
    _thread = new Thread(this); 
    // demarrage du thread, la fonction run() est ici lancée
    _thread.start(); 
  }

    //Traitement effectué ua démarrage du thread
  @Override
  public void run()
  {
    try
    {
        //On envoie une première fois le tableau de jeu et le joueur
        _sortie.writeObject(GestionServeur._boardServeur);
        _sortie.writeObject(_joueur);
        _sortie.reset();
        // on indique dans la console la connection d'un nouveau client
        System.out.println("Joueur n°" + _index + " s'est connecté");
        
        //Thread qui va écouter les commandes
        Thread ListenerThread = new Thread(new Runnable() {
            public void run() {
                try {
                    //Ce thread tourne en boucle pour écouter une éventuelle commande
                    //tant que l'utilisateur n'a pas quitté
                    while (!commande.equals("QUIT")) {
                        commande = (String) _entree.readObject();
                        System.out.println(commande);
                        //On lance la méthode de GameBoard pour modifier le tableau
                        //S'il a été modifié
                        
                        if(GestionServeur._boardServeur.game(commande, _joueur)){
                         //On appelle la méthode pour envoyer l'objet à tout le monde
                            GestionServeur._boardServeur.addBonusType();
                            GestionServeur.envoyer();
                            System.out.println("Je clear le controleur et reaffiche");
                            
                          }
                     
                        
                    }
                    //S'il a quitté le jeu, on l'annonce et on appelle la méthode pour le supprimer
                    System.out.println("Joueur n°" + _index + " a quitté le jeu");
                    GestionServeur.delClient(_index);
                    GestionServeur._listJoueurs.remove(_index);
                    _socket.close();
                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        });
        ListenerThread.start();
        
        
    }catch (Exception e){ 
    }
 }
  
    public void envoyer() {
        try {
            //On envoie une première fois le tableau de jeu et le joueur
            _sortie.writeObject(GestionServeur._boardServeur);
            _sortie.reset();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
