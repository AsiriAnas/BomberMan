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
public enum TypeCase {
    Vide("0"),    
    MurIncasable("1"),
    MurCassable("2"),
    Personnage("4"),
    Explosion("5");
    
    private String _typeTag ;  
      
    private TypeCase(String ptypeTag) 
    {  
         this._typeTag = ptypeTag ;  
    }  
      
    public String getTagType()
    {  
         return  this._typeTag ;  
    }  
    
}
