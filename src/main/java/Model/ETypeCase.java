/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Donat
 */
public enum ETypeCase {
    Vide(" "),    
    MurIncasable("1"),
    MurCassable("2"),
    Personnage("4"),
    Explosion("5"),
    BonusPortee("E"),
    Bombe("B");
    
    private String _typeTag ;  
      
    private ETypeCase(String ptypeTag) 
    {  
         this._typeTag = ptypeTag ;  
    }  
      
    public String getTagType()
    {  
         return  this._typeTag ;  
    }  
    
}
