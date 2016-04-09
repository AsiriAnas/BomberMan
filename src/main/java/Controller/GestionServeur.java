
package Controller;
import Model.Joueur;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.*;


//** Classe principale du serveur, gère les infos globales **
public class GestionServeur
{
    public static GameBoard _boardServeur=new GameBoard(11, 11);
    public static List<Joueur>_listJoueurs= new ArrayList<Joueur>();
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
  
  public int addClient(ObjectOutputStream pOut){
      _index++;
      _listClients.addElement(pOut);
      return _index;
  }
  
  public void envoyer(){
      ObjectOutputStream _sortie;
        for(int i=0; i<_listClients.size(); i++){
            try{
           _sortie=(ObjectOutputStream)_listClients.elementAt(i);
           _sortie.writeObject(_boardServeur);
           _sortie.flush();
           _sortie.reset();
            }catch(IOException e){ }
      }
  }
}
