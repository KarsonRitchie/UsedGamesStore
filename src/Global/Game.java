/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Global;

import SQL.SQLManager;

/**
 * This class is meant to be for games in the inventory. Each instance of this class will be a specific game.
 * 
 * <br><br>
 * Each game contains the information like the ID, price, its active state, genre and system, amount in stock, its description.
 * <br>
 * If a game is not active, then a customer will not be able to see it.
 * 
 * <br><br>
 * There is one interesting variable to bring to attention and its the restock variable. This is to hold the restock threshold, and if a game goes below it 
 * then the managers will be notified that the game needs to be restocked.
 * 
 * <br><br>
 * This class also hosts methods to save its quantity and even save itself to the database
 * 
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
    
    /**
     * This a constructor that will make a Game object
     * 
     * @param name
     * The game's title
     * 
     * @param price
     * The price for just one of this product
     * 
     * @param genre
     * The genre of the game
     * 
     * @param system
     * The system this specific game or item is playable on
     * 
     * @param quantity
     * The amount in stock
     * 
     * @param gameID
     * The ID of the game from the database
     * 
     * @param description
     * The description of the game
     * 
     * @param restock
     * The restock threshold of the game
     * 
     * @param active
     * An integer that shows wether the game is active or not (0 for inactive, 1 for active)
     * 
     */
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
    
    /**
     * This a constructor for a game object using a game object. We may need to make a temporary duplicate so this is here to make it easier on us
     * 
     * @param game
     * This is the game object that will be copied and made into a new object if we ever need a duplicate.
     */
    public Game(Game game){
    
        this.name = game.name;
        this.price = game.price;
        this.genre = game.genre;
        this.system = game.system;
        this.quantity = game.quantity;
        this.gameID = game.gameID;
        this.description = game.description;
        this.restock = game.restock;
        this.active = game.active;
    
    }
    
    /**
     * Saves an edited game
     * 
     * @param name
     * Title
     * @param price
     * Price
     * @param quantity
     * Amount in stock
     * @param description
     * Game's description
     * @param restock
     * Restock threshold
     * @param active
     * If it can be seen by customers or not
     */
    public void save(String name, float price, int quantity, String description, int restock, int active){
    
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.restock = restock;
        this.active = active;
        
    }
    
    /**
     * 
     * Only saves the quantity of the game
     * 
     * @param quantity
     * The amount now in stock
     */
    public void quantitySave(int quantity){
    
        //Save the quantity
        //If database save is ran afterwards you can save all the data including the quantity in the database
        this.quantity = quantity;
        
    }
    
    /**
     * 
     * A method to save the specific game object to the database
     * 
     * @return True if game saves to the database
     */
    public boolean databaseSave(){
    
        //Save to the database and return the boolean
        return SQLManager.saveGame(this);
    
    }
    
    /**
     * A method to delete the specific game object from the database
     * @return True if deletion was successful
     * @deprecated 
     */
    @Deprecated
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
