/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Controller.GameInterface;


/**
 *
 * @author Anas
 */
public class Main {
    
    public static void main(String[] args) {
        GameInterface game = new GameInterface(10, 10);
        game.affiche();
        
        
    }
}
