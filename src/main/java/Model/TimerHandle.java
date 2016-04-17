/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Donat
 */
public class TimerHandle extends Timer {

    private Timer _time;
    private boolean _timeSet;
    
    public TimerHandle()
    {
        _time = new Timer();
        _timeSet = false;
    }
    
    public void addBonus(int seconde)
    {
        _time.schedule( new TimerTask() {
            public void run() {
                System.out.println("add bonus");
            }
        }, 0, seconde*1000);
    }
    
    public Timer getTime() {
        return _time;
    }

    public boolean isTimeSet() {
        return _timeSet;
    }

    public void setTimeSet(boolean _isTimeSet) {
        this._timeSet = _isTimeSet;
    }
    
    
}
