/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Controller.*;
import java.util.TimerTask;
/**
 *
 * @author Donat
 */
public class Bombe {

    private boolean _explosed;
    private boolean _posed;
    private int _range;
    private int _posX;
    private int _posY;
    
    
    public Bombe(int pRange)
    {
        _explosed = false;
        _posed = false;
        _range = pRange;
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
    
    public void poser(int pX, int pY){
        _posX=pX;
        _posY=pY;
        GestionServeur._boardServeur._tableInterface[_posX][_posY].setType(ETypeCase.Bombe);
         
        _posed=true;
        Thread explose = new Thread() {
            @Override
                public void run() {
                    Thread stopExplosion = new Thread() {
                        @Override
                        public void run() {
                            try{
                            Thread.sleep(1000);
                            }catch(Exception e){}
                            stopExplosion();
                            SerThread._serveur.envoyer();
                            
                         }
                    };
                    try{
                        Thread.sleep(3000);
                    }catch(Exception e){}
                   _explosed=true;
                   explose();
                   SerThread._joueur.addBombe(1);
                   SerThread._serveur.envoyer();
                   stopExplosion.start();
                }
        };
        explose.start();

    }
    public void stopExplosion(){
        if(GestionServeur._boardServeur._tableInterface[_posX][_posY].getPlayerId() != 0){
            GestionServeur._boardServeur._tableInterface[_posX][_posY].setType(ETypeCase.Personnage);  
        }
        else{
            GestionServeur._boardServeur._tableInterface[_posX][_posY].setType(ETypeCase.Vide);
        }
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
    
    public void explose(){
        GestionServeur._boardServeur._tableInterface[_posX][_posY].setType(ETypeCase.Explosion);
        checkJoueurDansLeChamp(_posX, _posY);
        for(int i=1; i<_range+1; i++){
           if(GestionServeur._boardServeur._tableInterface[_posX+i][_posY].getType() != ETypeCase.MurIncasable){
               checkJoueurDansLeChamp(_posX+i, _posY);
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
    
    public void checkJoueurDansLeChamp(int pX, int pY){
        if(GestionServeur._boardServeur._tableInterface[pX][pY].getPlayerId() != 0){
            for(int i=0; i< GestionServeur._listJoueurs.size(); i++){
                if(GestionServeur._listJoueurs.get(i).getId()==GestionServeur._boardServeur._tableInterface[pX][pY].getPlayerId()){
                    GestionServeur._listJoueurs.get(i).baisserVie();
                }
            }
        }
    }
}
