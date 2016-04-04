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
public class Case {
    
    private TypeCase _type;

    public Case(TypeCase pType){
        this._type = pType;
    }

    public TypeCase getType() {
        return _type;
    }

    public void setType(TypeCase _type) {
        this._type = _type;
    }
    
}
