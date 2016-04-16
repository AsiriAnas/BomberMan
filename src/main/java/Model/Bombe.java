/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Controller.*;
import java.io.Serializable;
/**
 *
 * @author Donat
 */
public class Bombe implements Serializable{

    private boolean _explosed;
    private boolean _posed;
    private int _range;
    private int _posX;
    private int _posY;
    private Joueur _possesseur;
    
    
    public Bombe(int pRange, Joueur pJoueur)
    {
        _explosed = false;
        _posed = false;
        _range = pRange;
        _possesseur= pJoueur;
    }
    
    public boolean isExplosed() {
        return _explosed;
    }

    public void setExplosed(boolean _explosed) {
        this._explosed = _explosed;
    }

    public boolean isPosed() {
        return _posed;
    }

    public void setPosed(boolean _posed) {
        this._posed = _posed;
    }
    
        public int getRange() {
        return _range;
    }

    public void setRange(int _range) {
        this._range = _range;
    }
    
    public void autoPoseBombe()
    {
        
    }
    
    //Méthode qui gère toute l'action lorsque le joueur pose une bombe
    public void poser(int pX, int pY){
        _posX=pX;
        _posY=pY;
         _posed=true;
         
        //On affiche une bombe dans l'interface
        GestionServeur._boardServeur._tableInterface[_posX][_posY].setType(ETypeCase.Bombe);

        //Thread qui se lance en faisant une pause de 3 secondes 
        //pour simuler le retardement de l'explosion 
        Thread explose = new Thread() {
            @Override
            public void run() {
                //Retardement
                try{
                    Thread.sleep(3000);
               }catch(Exception e){}
                
               _explosed=true;
               //On appelle méthode qui gère l'explosion
               explose();
               //On remet une bome dans la poche du joueur
               _possesseur.addBombe(1);
               //On envoie ce changement à tous les joueurs
               GestionServeur.envoyer();
               //Nouveau thread qui arrete l'explosion après une pause de 1 seconde
               Thread stopExplosion = new Thread() {
                    @Override
                    public void run() {
                        try{
                        Thread.sleep(1000);
                        }catch(Exception e){}
                        //On appelle la méthode qui stoppe l'affichage de l'explosion
                        stopExplosion();
                        
                        GestionServeur.envoyer();
                     }
                };
               stopExplosion.start();         
            }
        };
        explose.start();

    }

    //Méthode modifiant le tableau en affichant l'explosion
    public void explose(){
        //Case centrale
        GestionServeur._boardServeur._tableInterface[_posX][_posY].setType(ETypeCase.Explosion);
        //On appelle la méthode qui cherche les joueurs dans le champs d'explosion
        checkJoueurDansLeChamp(_posX, _posY);
        //On parcourt les alentours
        for(int i=1; i<_range+1; i++){
            //Si on tombe sur un mur incassable, on ne fait rien
           if(GestionServeur._boardServeur._tableInterface[_posX+i][_posY].getType() != ETypeCase.MurIncasable){
               checkJoueurDansLeChamp(_posX+i, _posY);
               //Sinon on explose
               GestionServeur._boardServeur._tableInterface[_posX+i][_posY].setType(ETypeCase.Explosion);
           }
           if(GestionServeur._boardServeur._tableInterface[_posX-i][_posY].getType() != ETypeCase.MurIncasable){
               checkJoueurDansLeChamp(_posX-i, _posY);
               GestionServeur._boardServeur._tableInterface[_posX-i][_posY].setType(ETypeCase.Explosion);
           } 
           if(GestionServeur._boardServeur._tableInterface[_posX][_posY+i].getType() != ETypeCase.MurIncasable){
               checkJoueurDansLeChamp(_posX, _posY+i);
               GestionServeur._boardServeur._tableInterface[_posX][_posY+i].setType(ETypeCase.Explosion);
           } 
           if(GestionServeur._boardServeur._tableInterface[_posX][_posY-i].getType() != ETypeCase.MurIncasable){
               checkJoueurDansLeChamp(_posX, _posY-i);
               GestionServeur._boardServeur._tableInterface[_posX][_posY-i].setType(ETypeCase.Explosion);
           } 
        }
    }
    
    //Méthode enlever l'explosion
    public void stopExplosion(){
        //Case centrale
        if(GestionServeur._boardServeur._tableInterface[_posX][_posY].getPlayerId() != 0){
            GestionServeur._boardServeur._tableInterface[_posX][_posY].setType(ETypeCase.Personnage);  
        }
        else{
            GestionServeur._boardServeur._tableInterface[_posX][_posY].setType(ETypeCase.Vide);
        }
        //Cases latérales
        for(int i=1; i<_range+1; i++){
           if(GestionServeur._boardServeur._tableInterface[_posX+i][_posY].getType() != ETypeCase.MurIncasable){
               if(GestionServeur._boardServeur._tableInterface[_posX+i][_posY].getPlayerId() != 0){
                 GestionServeur._boardServeur._tableInterface[_posX+i][_posY].setType(ETypeCase.Personnage);  
               }
               else{
               GestionServeur._boardServeur._tableInterface[_posX+i][_posY].setType(ETypeCase.Vide);
               }
           }
           
           if(GestionServeur._boardServeur._tableInterface[_posX-i][_posY].getType() != ETypeCase.MurIncasable){
               if(GestionServeur._boardServeur._tableInterface[_posX-i][_posY].getPlayerId() != 0){
                 GestionServeur._boardServeur._tableInterface[_posX-i][_posY].setType(ETypeCase.Personnage);  
               }
               else{
               GestionServeur._boardServeur._tableInterface[_posX-i][_posY].setType(ETypeCase.Vide);
               }
           } 
           
           if(GestionServeur._boardServeur._tableInterface[_posX][_posY+i].getType() != ETypeCase.MurIncasable){
               if(GestionServeur._boardServeur._tableInterface[_posX][_posY+i].getPlayerId() != 0){
                 GestionServeur._boardServeur._tableInterface[_posX][_posY+i].setType(ETypeCase.Personnage);  
               }
               else{
               GestionServeur._boardServeur._tableInterface[_posX][_posY+i].setType(ETypeCase.Vide);
               }
           } 
           
           if(GestionServeur._boardServeur._tableInterface[_posX][_posY-i].getType() != ETypeCase.MurIncasable){
               if(GestionServeur._boardServeur._tableInterface[_posX][_posY-i].getPlayerId() != 0){
                 GestionServeur._boardServeur._tableInterface[_posX][_posY-i].setType(ETypeCase.Personnage);  
               }
               else{
               GestionServeur._boardServeur._tableInterface[_posX][_posY-i].setType(ETypeCase.Vide);
               }
           } 
        }
    }
    
    //Méthode vérifiant si la case ciblée contient un joueur
    public void checkJoueurDansLeChamp(int pX, int pY){
        if(GestionServeur._boardServeur._tableInterface[pX][pY].getPlayerId() != 0){
            //Si oui, on cherche l'id du joueur
            for(int i=0; i< GestionServeur._listJoueurs.size(); i++){
                if(GestionServeur._listJoueurs.get(i).getId()==GestionServeur._boardServeur._tableInterface[pX][pY].getPlayerId()){
                    //On réduit sa vie
                    GestionServeur._listJoueurs.get(i).baisserVie();
                }
            }
        }
    }
}
