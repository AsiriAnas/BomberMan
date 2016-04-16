
package Controller;
import Model.Joueur;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.*;


//** Classe principale du serveur, gère les infos globales **
public class GestionServeur
{
    //On  instancie un tableau statique accessible par les autres classes gerant ce tableau
    public static GameBoard _boardServeur=new GameBoard(11, 11);
    //On créé une liste de joueurs pour gérer l'interaction 
    public static List<Joueur>_listJoueurs= new ArrayList<Joueur>();
    //une liste stockant la sortie des sockets créés pour utiliser la méthode "envoyer()"
    public static List<SerThread> _listClients=new ArrayList<>();
    private static int _index=0;
    public static void main(String args[])
    {
        GestionServeur gServeur = new GestionServeur();
        try
        {
          Integer port;
          // si pas d'arguments : port 18000 par défaut
          if(args.length<=0) port=new Integer("18000");
          // sinon il s'agit du numéro de port passé en argument
          else port = new Integer(args[0]);    
          //On créé le socket serveur avec le port spécifié
          ServerSocket ss = new ServerSocket(port); // ouverture d'un socket serveur sur port
          //On montre le port sur lequel est le socket
          System.out.println("Demarre sur le port : "+port.toString());
          // attente en boucle de connexion (bloquant sur ss.accept)
          while (true)
          {
            //A chaque nouveau client, on on créé un nouveau thread avec le socket créé en param
            
            _listClients.add(new SerThread(ss.accept()));
          }
        }
        catch (Exception e) { }
    }
  
  //Méthode ajoutant un client à la liste et renvoyant son id
  synchronized public static int getId(){
      _index++;
      return _index;
  }
  
  //Méthode supprimant le client de l'index reçu en paramètre
  synchronized public static void delClient(int pIndex){
     _index--;
    if (_listClients.get(pIndex) != null)
    {
      _listClients.remove(pIndex);
    }
  }
  
  
    //Méthode envoyant l'objet à tous les clients
    synchronized public static void envoyer() {

        //on parcourt toutes les sorties des sockets
        for (SerThread _tClient : _listClients) {

            //on utilise leur sortie pour envoyer l'objet         
            _tClient.envoyer();
        }
    }
}
