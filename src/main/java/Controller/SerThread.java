package Controller;
import Model.Joueur;
import java.net.*;
import java.io.*;
import java.util.*;


// Classe associé à chaque client
// Il y aura autant d'instance de cette classe que de client connectés
public class SerThread implements Runnable
{
    private int _index;
    // contiendra le thread du client
    private Thread _thread; 
    // recevra le socket liant au client
    private Socket _socket; 
    private String commande="";
    //Les canaux pour échanger les données avec le client
    protected static ObjectOutputStream _sortie;
    protected static ObjectInputStream _entree;
    public static Joueur _joueur;
    public static GestionServeur _serveur;
    public static Timer time;

    //Le constructeur avec la socket client
  public SerThread(Socket pSocket, GestionServeur pServeur)
  {  
    _socket=pSocket;
    _serveur=pServeur;

    try
    {
        //On instancie les entrées et sorties
        _entree=new ObjectInputStream(_socket.getInputStream());
        _sortie=new ObjectOutputStream(_socket.getOutputStream());
    }
    catch (IOException e){ 
    }
    
    _index=pServeur.addClient(_sortie);
    int[] posJoueur= GestionServeur._boardServeur.placerJoueur(_index);
    _joueur= new Joueur(_index, posJoueur[0] , posJoueur[1]);
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
        _sortie.writeObject(GestionServeur._boardServeur);
        _sortie.writeObject(_index);
        _sortie.reset();
        // on indique dans la console la connection d'un nouveau client
        System.out.println("Joueur n°" + _index + " s'est connecté");
        
        //Thread qui va écouter les commandes
        Thread ListenerThread = new Thread(new Runnable() {
            public void run() {
                try {
                    while (!commande.equals("QUIT")) {

                        commande = (String) _entree.readObject();
                        System.out.println(commande);
                        if(GestionServeur._boardServeur.game(commande)){
                         _serveur.envoyer();
                        }
                    }
                    System.out.println("Joueur n°" + _index + " a quitté le jeu");
                    _socket.close();
                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        });
        ListenerThread.start();
        
        
    }catch (Exception e){ 
    }

//    try
//    {
//        //Puis on ferme le socket
//        //_socket.close();   
//    }
//    catch (Exception e){ }
 }
}
