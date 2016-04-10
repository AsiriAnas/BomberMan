
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
    private Vector _listClients=new Vector();
    private int _index=0;
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

            new SerThread(ss.accept(),gServeur);
          }
        }
        catch (Exception e) { }
    }
  
  //Méthode ajoutant un client à la liste et renvoyant son id
  public int addClient(ObjectOutputStream pOut){
      _index++;
      _listClients.addElement(pOut);
      return _index;
  }
  
  //Méthode supprimant le client de l'index reçu en paramètre
  public void delClient(int pIndex){
     _index--;
    if (_listClients.elementAt(pIndex) != null)
    {
      _listClients.removeElementAt(pIndex);
    }
  }
  
  
  //Méthode envoyant l'objet à tous les clients
  public void envoyer(){
      ObjectOutputStream _sortie;
      //on parcourt toutes les sorties des sockets
        for(int i=0; i<_listClients.size(); i++){
            try{
            //on utilise leur sortie pour envoyer l'objet
           _sortie=(ObjectOutputStream)_listClients.elementAt(i);
           _sortie.writeObject(_boardServeur);
           _sortie.flush();
           _sortie.reset();
            }catch(IOException e){ }
      }
  }
}
