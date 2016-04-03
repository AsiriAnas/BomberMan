/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 *
 * @author Donat
 */
public class GameInterface {

    public int[][] getTableauJeu() {
        return _tableauJeu;
    }
    
    private int _tableauJeu [][];
    private int _pwidth;
    private int _pheight; 
    private Joueur _joueur;
    
    public GameInterface(int pwidth, int pheight,Joueur pjouer)
    {
        this._pwidth = pwidth;
        this._pheight = pheight;
        _joueur = pjouer;
        
        this.intitialiSationTable();
        
//        this.game();
    }
    public GameInterface(int pwidth, int pheight)
    {
        this._pwidth = pwidth;
        this._pheight = pheight;
        this.intitialiSationTable();
    }
    
    
    public void game(String keyPressed )
    {
        boolean jeux = true;
        
        switch(keyPressed){
            case "RIGHT" :
                _joueur.setPosition_y( _joueur.getPosition_y()+1 );
                break;
            case "LEFT" : 
                 _joueur.setPosition_y( _joueur.getPosition_y()- 1 );
                break;
            case "UP" : 
                  _joueur.setPosition_x( _joueur.getPosition_x()+1 );
                break;
            case "DOWN" : 
                 _joueur.setPosition_x( _joueur.getPosition_x()+1 );

                break;
        }
        this.intitialiSationTable();
        this.affiche();
        
    }

    
    private void intitialiSationTable()
    {
        _tableauJeu = new int[_pheight][_pheight];
        

        
        // ligne fond au dessus
        for(int col = 0;col < _pwidth; col++)
        {
            _tableauJeu[0][col] = 1;
        }
        // ligne fond en Dessous
        for(int col = 0;col < _pwidth; col++)
        {
            _tableauJeu[_pheight - 1][col] = 1;
        }
        //ligne fond gauche 
        for(int lig = 0;lig < _pheight; lig++)
        {
            _tableauJeu[lig][0] = 1;
        }
        // ligne fond droit
        for(int lig = 0;lig < _pheight; lig++)
        {
            _tableauJeu[lig][_pwidth-1] = 1;
        }
        
        // ligne du code
        for(int lig = 1;lig < _pheight - 1; lig++)
        {
            for(int col = 1; col < _pwidth - 1; col++)
            {
          //      if( _tableauJeu[lig][col] !=  _tableauJeu[_joueur.getPosition_x()][_joueur.getPosition_y()]);
                {
                    _tableauJeu[lig][col] = 0 ;
                }
            }
        }
        
        // initialysation.
               _tableauJeu[_joueur.getPosition_x()][_joueur.getPosition_y()] = 9;
        
        
    }
    
    public void affiche()
    {
        for(int lig = 0; lig < _pheight; lig++ )
        {
            for(int col = 0; col<_pwidth; col++)
            {
                System.out.print( _tableauJeu[lig][col] + " ");
            }
            System.out.println("");
        }
    }
}
