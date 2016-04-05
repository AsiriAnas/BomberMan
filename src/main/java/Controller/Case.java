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

    private ETypeCase _type;
    private int _playerId;

    public Case(ETypeCase pType){
        this._type = pType;
        this._playerId = 0;
    }
    public Case(ETypeCase pType,int pplayerid){
        this._type = pType;
        this._playerId = pplayerid;
    }

    public ETypeCase getType() {
        return this._type;
    }

    public void setType(ETypeCase _type) {
        this._type = _type;
    }
    
    public int getPlayerId() {
        return this._playerId;
    }

    public void setPlayerId(int _playerId) {
        this._playerId = _playerId;
    }
    
}
