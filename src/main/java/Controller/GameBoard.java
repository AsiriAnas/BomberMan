/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Donat
 */
public class GameBoard implements Serializable{
    
    //Tableau représentant le plateau de jeu
    public Case _tableInterface[][];
    private int _pwidth;
    private int _pheight; 
    /**
     *
     * @param pWidth
     * @param pHeight
     * @param pJouer
     */
    
    //Constructeur initialisant le tableau avec les mur incassables et cassables
    public GameBoard(int pWidth, int pHeight)
    {
        this._pwidth = pWidth;
        this._pheight = pHeight;      
        this.initialiseInterface();
        this.afficheInterface();
    }
    
    public Case[][] getTableInterface()
    {
        return _tableInterface;
    }
    private void addBonus()
    {
        
//        // Bonus alonge la portee de la bombe (+1)
//
//        _timerBonus.schedule( new TimerTask() {
//        @Override
//        public void run() {
//            
//            
//            
//            
//        }
//      }, 10000);
    }
    private void addBonusType(ETypeCase type)
    {
        Random rand = new Random();
        int aleaX = rand.nextInt(_pwidth - 0 + 1) + 0;
        int aleaY = rand.nextInt(_pheight - 0 + 1) + 0;
        
        boolean isCaseEmpty = false;
        
        do
        {
            if(_tableInterface[aleaX][aleaX].getType() != ETypeCase.Vide)
            {
                aleaX = rand.nextInt(_pwidth - 0 + 1) + 0;
                aleaY = rand.nextInt(_pheight - 0 + 1) + 0;
            }
            else
            {
                isCaseEmpty = true;
            }
            
        }while(isCaseEmpty == false);
        
         System.out.println("Bonus générer on : " + "( " + aleaX +" : " + aleaY +")" );
           
    }
    
    //Méthode gèrant les comandes de jeu et appelant les méthodes adéguates
    //La méthode renvoie true si un changement a été fait sur le tableau
    public boolean game(String keyPressed, Joueur pJoueur)
    { 
        boolean changement = false;
        switch(keyPressed){
            
            case "RIGHT" :
                //Si la case ciblée est vide
                if(_tableInterface[pJoueur.getPosition_x()][pJoueur.getPosition_y()+1].getType() == ETypeCase.Vide)
                {
                    System.out.println("Depl droite");
                    deplacerJoueur(0,1,pJoueur);
                    changement = true;
                }
                else
                {
                    System.out.println("Droite occupée");
                }
                
                break;
                
            case "LEFT" : 
                 //Si la case ciblée est vide
                 if( _tableInterface[pJoueur.getPosition_x()][pJoueur.getPosition_y()-1].getType() == ETypeCase.Vide)
                {
                    System.out.println("Depl gauche");
                    deplacerJoueur(0,-1,pJoueur);
                    changement = true;
                }
                else
                {
                    System.out.println("Gauche occupée");
                }
                 
                break;
            case "UP" :
                //Si la case ciblée est vide
                if( _tableInterface[pJoueur.getPosition_x()-1][pJoueur.getPosition_y()].getType() == ETypeCase.Vide)
                {
                    System.out.println("Depl haut");
                    deplacerJoueur(-1,0,pJoueur);
                    changement = true;
                }
                else
                {
                    System.out.println("Haut occupé");
                }
                  
                break;
            case "DOWN" : 
                //Si la case ciblée est vide
                if( _tableInterface[pJoueur.getPosition_x()+1][pJoueur.getPosition_y()].getType() == ETypeCase.Vide)
                {
                    System.out.println("Depl bas");
                    deplacerJoueur(1,0,pJoueur);
                    changement = true;
                }
                else
                {
                    System.out.println("Bas occupé");
                }

                break;
                
            case "SPACE" : 
                System.out.println("Tic Tac");
                if(pJoueur.poserBombe()) changement = true;
                break;
        }     
        //if(changement) notifyObservers(this.etat);
        
        return(changement);
    }
    

    //Méthode déplaçant le joueur, sur le tableau et dans l'objet joueur
    public void deplacerJoueur(int pDepX, int pDepY, Joueur pJoueur){

        //S'il a posé une bombe au moment du déplacement, on doit garder la bombe sur le tableau
        if( _tableInterface[pJoueur.getPosition_x()][pJoueur.getPosition_y()].getType()!= ETypeCase.Bombe){
           _tableInterface[pJoueur.getPosition_x()][pJoueur.getPosition_y()]= new Case(ETypeCase.Vide); 
        } 
         _tableInterface[pJoueur.getPosition_x()][pJoueur.getPosition_y()].setPlayerId(0);
        _tableInterface[pJoueur.getPosition_x()+pDepX][pJoueur.getPosition_y()+pDepY] = new Case(ETypeCase.Personnage, pJoueur.getId());
        if(pDepX!=0) pJoueur.deplacementEnX(pDepX);
        if(pDepY!=0) pJoueur.deplacementEnY(pDepY);


    }
    
    //Si on a le temps, on récupérera le tableau d'un fichier txt
    private void initialiseInterface()
    {
        //On créé le tableau
        _tableInterface = new Case[_pwidth][_pwidth];
        
        // ligne fond au dessus
        for(int col = 0;col < _pwidth; col++)
        {
            _tableInterface[0][col] = new Case(ETypeCase.MurIncasable);
        }
        // ligne fond en Dessous
        for(int col = 0;col < _pwidth; col++)
        {
            _tableInterface[_pheight - 1][col] = new Case(ETypeCase.MurIncasable);
        }       
        // table
        for(int lig = 1;lig < _pheight - 1; lig++)
        {
            for(int col = 0; col < _pwidth; col++)
            {
                if(col==0 || col == _pwidth - 1){
                    _tableInterface[lig][col] = new Case(ETypeCase.MurIncasable);
                }
                else if (col % 2 == 0 && lig % 2 == 0) {
                    
                    _tableInterface[lig][col] = new Case(ETypeCase.MurIncasable);
                }
                else{
                    _tableInterface[lig][col] = new Case(ETypeCase.Vide); 
                }
                //On place aléatoirement les murs cassables
                if(_tableInterface[lig][col].getType() == ETypeCase.Vide && (Math.random()*2>1)){
                    _tableInterface[lig][col] = new Case(ETypeCase.MurCassable); 
                }
            }
        }
    }   
    
    //Méthode affichant le tableau dans la console
    public void afficheInterface()
    {
        for(int lig = 0; lig < _pheight; lig++ )
        {
            for(int col = 0; col<_pwidth; col++)
            {
                System.out.print( _tableInterface[lig][col].getType().getTagType()+ " ");
            }
            System.out.println("");
        }
    }
     
    //Méthode plaçant le joueur créé dans le tableau
    public int[] placerJoueur(int pIndex){
        int[] _posJoueur = new int[2];
        boolean pose=false;
        //on parcourt le tableau à la recherche de place libre
        for(int lig = 1; lig < _pheight - 1; lig++ )
        {
            for(int col = 1; col<_pwidth - 1; col++)
            {
                if(verifierPlace(lig, col) && pose == false){
                    _tableInterface[lig][col] = new Case(ETypeCase.Personnage, pIndex);
                    _posJoueur[0]=lig;
                    _posJoueur[1]=col;
                    pose=true;
                }
            }
        } 
        afficheInterface();
        return _posJoueur;
    }
    
    //Cett méthode si la position proposée dispose d'un espace suffisant pour poser une bombe sans danger
    //Pour cela, on vérifie si l'espace est au moins en forme de L pour se cacher
    public boolean verifierPlace(int lig, int col){
        boolean test= false;
        
        //On vérifie si la case est vide et s'il y a de l'espace pour poser une première bombe sans mourir
        if(_tableInterface[lig][col].getType() == ETypeCase.Vide &&
       ((_tableInterface[lig+1][col].getType() == ETypeCase.Vide &&_tableInterface[lig][col+1].getType() == ETypeCase.Vide)||
        (_tableInterface[lig+1][col].getType() == ETypeCase.Vide &&_tableInterface[lig][col-1].getType() == ETypeCase.Vide)||
        (_tableInterface[lig-1][col].getType() == ETypeCase.Vide &&_tableInterface[lig][col+1].getType() == ETypeCase.Vide)||
        (_tableInterface[lig-1][col].getType() == ETypeCase.Vide &&_tableInterface[lig][col-1].getType() == ETypeCase.Vide))){
            test=true;
        }
        return test;
    }
}
