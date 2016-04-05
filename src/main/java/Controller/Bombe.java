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
public class Bombe {

    private boolean _explosed;
    private boolean _posed;
    private int _range;
    
    
    public Bombe()
    {
        _explosed = false;
        _posed = false;
        _range = 1;
    }
    
    public boolean isExplosed() {
        return _explosed;
    }

    public void setExplosed(boolean _explosed) {
        this._explosed = _explosed;
    }

    public boolean isPosed() {
        return _posed;
    }

    public void setPosed(boolean _posed) {
        this._posed = _posed;
    }
    
        public int getRange() {
        return _range;
    }

    public void setRange(int _range) {
        this._range = _range;
    }
    
    public void autoPoseBombe()
    {
        
    }
}
