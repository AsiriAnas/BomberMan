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
    
    public Case _tableInterface[][];
    private int _pwidth;
    private int _pheight; 
    /**
     *
     * @param pWidth
     * @param pHeight
     * @param pJouer
     */
    
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
    
    public boolean game(String keyPressed )
    {
        boolean changement = false;
        Bombe bombe; 
        switch(keyPressed){
            
            case "RIGHT" :
                
                System.out.println("Right");
                
                if(_tableInterface[SerThread._joueur.getPosition_x()][SerThread._joueur.getPosition_y()+1].getType() == ETypeCase.Vide)
                {
                    System.out.println("Le next is vide");
                    deplacerJoueur(0,1);
                    changement = true;
                }
                else
                {
                    System.out.println("Le next is not vide");
                }
                
                break;
            case "LEFT" : 
                 
                 if( _tableInterface[SerThread._joueur.getPosition_x()][SerThread._joueur.getPosition_y()-1].getType() == ETypeCase.Vide)
                {
                    System.out.println("Le next is vide");
                    deplacerJoueur(0,-1);
                    changement = true;
                }
                else
                {
                    System.out.println("Le next is not vide");
                }
                 
                break;
            case "UP" : 
                if( _tableInterface[SerThread._joueur.getPosition_x()-1][SerThread._joueur.getPosition_y()].getType() == ETypeCase.Vide)
                {
                    System.out.println("Le next is vide");
                    deplacerJoueur(-1,0);
                    changement = true;
                }
                else
                {
                    System.out.println("Le next is not vide");
                }
                  
                break;
            case "DOWN" : 
                 
                if( _tableInterface[SerThread._joueur.getPosition_x()+1][SerThread._joueur.getPosition_y()].getType() == ETypeCase.Vide)
                {
                    System.out.println("Le next is vide");
                    deplacerJoueur(1,0);
                    changement = true;
                }
                else
                {
                    System.out.println("Le next is not vide");
                }

                break;
                
            case "SPACE" : 
                 System.out.println("Tic Tac");
                if(SerThread._joueur.poserBombe()) changement = true;
                break;
        }     
       // this.addBonus();
       // this.initialiseInterface();
        //if(changement) this.afficheInterface();
        
        return(changement);
    }
    

    
        public void deplacerJoueur(int pDepX, int pDepY){
            
            if( _tableInterface[SerThread._joueur.getPosition_x()][SerThread._joueur.getPosition_y()].getType()!= ETypeCase.Bombe){
               _tableInterface[SerThread._joueur.getPosition_x()][SerThread._joueur.getPosition_y()]= new Case(ETypeCase.Vide); 
            } 
             _tableInterface[SerThread._joueur.getPosition_x()][SerThread._joueur.getPosition_y()].setPlayerId(0);
            _tableInterface[SerThread._joueur.getPosition_x()+pDepX][SerThread._joueur.getPosition_y()+pDepY] = new Case(ETypeCase.Personnage, SerThread._joueur.getId());
            if(pDepX!=0) SerThread._joueur.deplacementEnX(pDepX);
            if(pDepY!=0) SerThread._joueur.deplacementEnY(pDepY);
            
            
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
     
    public int[] placerJoueur(int pIndex){
        int[] _posJoueur = new int[2];
        boolean pose=false;
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
