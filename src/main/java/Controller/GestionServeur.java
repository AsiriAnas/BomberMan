
package Controller;
import java.net.*;


//** Classe principale du serveur, gère les infos globales **
public class GestionServeur
{
  //** Methode : la première méthode exécutée, elle attend les connections **
  public static void main(String args[])
  {
    
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
        new GestionClientThread(ss.accept());
      }
    }
    catch (Exception e) { }
  }
}
