/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Donat
 */
public class Joueur implements Serializable {

    private int _id;
    private String _name;
    private int _position_x;
    private int _position_y;
    private List<Bombe> _bombes;
    private boolean _reverse;
    private int _numberLife ;
    
    public Joueur(int pId , int pPos_x, int pPos_y)
    {
       this._id = pId;
       this._position_x = pPos_x;
       this._position_y = pPos_y;
       _bombes = new ArrayList<Bombe>();
       _bombes.add(new Bombe(1, this));
       _reverse = false;
       _numberLife = 3;
    }

  
    
    public void deplacementEnX(int dep)
    {
        this._position_x += dep;
    }
    
    public void addBombe(int pRange){
        _bombes.add(new Bombe(pRange, this));
    }
    
    public void deplacementEnY(int dep)
    {
        this._position_y += dep;
    }
    
    public void baisserVie(){
        this._numberLife--;
    }
    
    public void augmenter(){
        this._numberLife++;
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
    
    //Méthode posant la bombe du joueur
    //Elle renvoie true s'il possède une bombe
    public boolean poserBombe(){
        boolean possible= false;
        //on vérifie la liste de ses bombes s'il possède une bombe valide
        for(Bombe b: _bombes){
            if(b.isPosed()==false){
                //Si oui, on appelle la méthode la bombe trouvée
                //System.out.println("on en a une");
               b.poser(_position_x, _position_y);
               possible=true;
               break;
            }
        }
        return possible;
    }
    

}
