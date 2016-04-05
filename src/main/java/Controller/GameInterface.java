/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Donat
 */
public class GameInterface {

    
    private Case _tableInterface[][];
    private int _pwidth;
    private int _pheight; 
    private Joueur _joueur;
    
    public GameInterface(int pwidth, int pheight,Joueur pjouer)
    {
        this._pwidth = pwidth;
        this._pheight = pheight;
        _joueur = pjouer;
        
        this.initialiseInterface();
    }
    public GameInterface(int pwidth, int pheight)
    {
        this._pwidth = pwidth;
        this._pheight = pheight;
        this.initialiseInterface();
    }
    
    
    public void game(String keyPressed )
    {
        boolean jeux = true;
        
        switch(keyPressed){
            
            case "RIGHT" :
                _joueur.setPosition_y( _joueur.getPosition_y()+1,_pheight );
                break;
            case "LEFT" : 
                 _joueur.setPosition_y( _joueur.getPosition_y()- 1,_pheight );
                break;
            case "UP" : 
                  _joueur.setPosition_x( _joueur.getPosition_x()-1, _pwidth );
                break;
            case "DOWN" : 
                 _joueur.setPosition_x( _joueur.getPosition_x()+1 , _pwidth);

                break;
        }

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
