/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import jdk.nashorn.internal.runtime.regexp.joni.constants.StackType;

/**
 *
 * @author Donat
 */
public class Joueur {

    private int _id;
    private String _name;
    private int _position_x;
    private int _position_y;
    
    
    public Joueur(int pId , String pName, int pPos_x, int pPos_y)
    {
       this._id = pId;
       this._name = pName;
       this._position_x = pPos_x;
       this._position_y = pPos_y;
    }
    
    public void deplacement(int dep)
    {
        this._position_x += dep;
    }
    
    public void deplacementEnY(int dep)
    {
        this._position_y += dep;
    }
    
    
    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public int getPosition_x() {
        return _position_x;
    }

    public int getPosition_y() {
        return _position_y;
    }
    
    public void setId(int _id) {
        this._id = _id;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public void setPosition_x(int _position_x) {
        this._position_x = _position_x;
    }

    public void setPosition_y(int _position_y) {
        this._position_y = _position_y;
    }
    
    public void setPosition_x(int _position_x, int pmax_X) {
        System.out.println("Max :" + (pmax_X - 1) );
        
        if( _position_x  > pmax_X - 1){
            System.out.println("Max :" + (pmax_X - 1) );
            this._position_x = pmax_X - 1;
        
        }
        else if(_position_x  < 0 )
             this._position_x = 0;
        else 
            this._position_x = _position_x;
    }

    public void setPosition_y(int _position_y, int pmax_Y) {
        if( _position_y  > pmax_Y - 1){
         
            this._position_y = pmax_Y - 1;
        }
        else if(_position_y  < 0 )
             this._position_y = 0;
        else 
            this._position_y = _position_y;
    }
    

}
