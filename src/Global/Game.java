/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Global;

import SQL.SQLManager;

/**
 *
 * @author karso
 */
public class Game {
    
    //Make variables to store
    public String name = "";
    public float price = 0.00f;
    public String genre = "";
    public String system = "";
    public int quantity = 0;
    public int gameID = 0;
    public String description = "";
    public int restock = 0;
    public int active = 0;
    
    //Now make a constructor
    public Game(String name, float price, String genre, String system, int quantity, int gameID, String description, int restock, int active){
    
        this.name = name;
        this.price = price;
        this.genre = genre;
        this.system = system;
        this.quantity = quantity;
        this.gameID = gameID;
        this.description = description;
        this.restock = restock;
        this.active = active;
    
    }
    
    public void save(String name, float price, int quantity, String description, int restock, int active){
    
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.restock = restock;
        this.active = active;
        
    }
    
    public boolean databaseSave(){
    
        return SQLManager.saveGame(this);
    
    }
    
    public boolean delete(){
    
        //we need to check if we can delete the game or if it succeeded
        if(SQLManager.deleteGame(gameID)){
        
            //now we can return true and delete it
            return true;
        
        }else{
        
            return false;
        
        }
    
    }
    
}
