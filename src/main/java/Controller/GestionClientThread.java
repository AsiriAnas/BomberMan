package Controller;
import java.net.*;
import java.io.*;


//** Classe associ�e � chaque client **
//** Il y aura autant d'instance de cette classe que de client connect�s **
//impl�mentation de l'interface Runnable (une des 2 m�thodes pour cr�er un thread)
class GestionClientThread implements Runnable
{
    // contiendra le thread du client
    private Thread vThread; 
    // recevra le socket liant au client
    private Socket vSocket; 
    //Les canaux pour échanger les données avec le client
    protected static ObjectOutputStream vSortie;
    protected static ObjectInputStream vEntree;


    //Le constructeur avec la socket client
  GestionClientThread(Socket pSocket)
  {  
    vSocket=pSocket;
    try
    {
        //On instancie les entrées et sorties
        vEntree=new ObjectInputStream(vSocket.getInputStream());
        vSortie=new ObjectOutputStream(vSocket.getOutputStream());
    }
    catch (IOException e){ 
    }
    // instanciation du thread
    vThread = new Thread(this); 
    // demarrage du thread, la fonction run() est ici lancée
    vThread.start(); 
  }

    //Traitement effectué ua démarrage du thread
  @Override
  public void run()
  {
   // on indique dans la console la connection d'un nouveau client
    System.out.println("Un nouveau client s'est connecté");
    try
    {
        //Puis on ferme le socket
        vSocket.close();   
    }
    catch (Exception e){ }
  }
}
