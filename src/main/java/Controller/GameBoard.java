/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Case;
import Model.ETypeCase;
import Model.Joueur;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Donat
 */
public class GameBoard {

    
    private Case _tableInterface[][];
    private int _pwidth;
    private int _pheight; 
    private Joueur _joueur;
    private Timer _timerBonus;

    /**
     *
     * @param pWidth
     * @param pHeight
     * @param pJouer
     */
    
    public GameBoard(int pWidth, int pHeight, Joueur pJouer)
    {
        this._pwidth = pWidth;
        this._pheight = pHeight;
        this._joueur = pJouer;
        
        this._timerBonus = new Timer();
        
        this.initialiseInterface();
        this.afficheInterface();
    }
    public GameBoard(int pwidth, int pheight)
    {
        this._pwidth = pwidth;
        this._pheight = pheight;
        this.initialiseInterface();
    }
    
    private void addBonus()
    {
        
        // Bonus alonge la portee de la bombe (+1)

        _timerBonus.schedule( new TimerTask() {
        @Override
        public void run() {
            
            
            
            
        }
      }, 10000);
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
    
    public void game(String keyPressed )
    {
        boolean jeux = true;
        
        switch(keyPressed){
            
            case "RIGHT" :
                
                System.out.println("Right");
                
                if( _tableInterface[_joueur.getPosition_x()][_joueur.getPosition_y()+1].getType() == ETypeCase.Vide
                    || _tableInterface[_joueur.getPosition_x()][_joueur.getPosition_y()+1].getType() == ETypeCase.Bombe  )
                {
                    System.out.println("Le next is vide");
                    _joueur.setPosition_y( _joueur.getPosition_y()+1,_pheight);
                }
                else
                {
                    System.out.println("Le next is not vide");
                    _joueur.setPosition_y( _joueur.getPosition_y(),_pheight );
                }
                
                break;
            case "LEFT" : 
                 
                 if( _tableInterface[_joueur.getPosition_x()][_joueur.getPosition_y()-1].getType() == ETypeCase.Vide
                    || _tableInterface[_joueur.getPosition_x()][_joueur.getPosition_y()-1].getType() == ETypeCase.Bombe  )
                {
                    System.out.println("Le next is vide");
                    _joueur.setPosition_y( _joueur.getPosition_y()-1,_pheight);
                }
                else
                {
                    System.out.println("Le next is not vide");
                    _joueur.setPosition_y( _joueur.getPosition_y(),_pheight );
                }
                 
                break;
            case "UP" : 
                if( _tableInterface[_joueur.getPosition_x()-1][_joueur.getPosition_y()].getType() == ETypeCase.Vide
                    || _tableInterface[_joueur.getPosition_x()-1][_joueur.getPosition_y()].getType() == ETypeCase.Bombe  )
                {
                    System.out.println("Le next is vide");
                    _joueur.setPosition_x( _joueur.getPosition_x()-1,_pwidth);
                }
                else
                {
                    System.out.println("Le next is not vide");
                    _joueur.setPosition_x( _joueur.getPosition_x(),_pwidth );
                }
                  
                break;
            case "DOWN" : 
                 
                if( _tableInterface[_joueur.getPosition_x()+1][_joueur.getPosition_y()].getType() == ETypeCase.Vide
                    || _tableInterface[_joueur.getPosition_x()+1][_joueur.getPosition_y()].getType() == ETypeCase.Bombe  )
                {
                    System.out.println("Le next is vide");
                    _joueur.setPosition_x( _joueur.getPosition_x()+1,_pwidth);
                }
                else
                {
                    System.out.println("Le next is not vide");
                    _joueur.setPosition_x( _joueur.getPosition_x(),_pwidth );
                }

                break;
        }
        
        this.addBonus();
        this.initialiseInterface();
        this.afficheInterface();
    }   
    
    
    private void initialiseInterface()
    {
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
        
        //ligne fond gauche 
        for(int lig = 0;lig < _pheight; lig++)
        {
            _tableInterface[lig][0] = new Case(ETypeCase.MurIncasable);
        }
        
        // ligne fond droit
        for(int lig = 0;lig < _pheight; lig++)
        {
            _tableInterface[lig][_pwidth-1] = new Case(ETypeCase.MurIncasable);
        }
        
        // table
        for(int lig = 1;lig < _pheight - 1; lig++)
        {
            for(int col = 1; col < _pwidth - 1; col++)
            {
             //   if( _tableInterface[lig][col] !=  _tableInterface[_joueur.getPosition_x()][_joueur.getPosition_y()]);
                {
                    _tableInterface[lig][col] = new Case(ETypeCase.Vide); 
                }
            }
        }

        _tableInterface[5][8] = new Case(ETypeCase.Personnage);
        _tableInterface[_joueur.getPosition_x()][_joueur.getPosition_y()] = new Case(ETypeCase.Personnage);

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
            
}
