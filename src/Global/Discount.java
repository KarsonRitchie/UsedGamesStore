/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Global;

import SQL.SQLManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is meant to be for discount objects.
 * 
 * <br><br>
 * Each instance of this class is a unique discount object that can be used or even edited if a manager is logged in.
 * <br>
 * It has the necessary information that each discount will need. For instance ID, the code, and the description.
 * 
 * <br><br>
 * There are a bit more complex variables though, like the type of discount is is (represented by an integer), the amount of the discount, its start and end date, 
 * and wether it is active or not (represented by an integer).
 * 
 * <br>
 * Each discount will need a level. This program requires there to be discounts that could be at a cart level, or at an item elvel, which means only one item is discounted. 
 * The level is represented by an integer, and if it is at item level, then there will be a specific game it discounts.
 * 
 * <br><br>
 * There are also save methods to save changed information.
 * 
 */
public class Discount {
    
    public int discountID = 0;
    public String discountCode = "";
    public String description = "";
    public int discountType = 0;
    public float discountAmount = 0.00f;
    public Date startDate = null;
    public Date endDate = null;
    public int active = 0;
    public int level = 0;
    public String game = "";
    
    //make a date format object to make the string into a date
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * A constructor to make a discount object
     * 
     * @param discountID
     * The ID of the discount from the database
     * 
     * @param discountCode
     * The discounts code
     * 
     * @param description
     * THe description of the discount
     * 
     * @param discountType
     * The type of discount (represented as an integer, 0 for percent, and 1 for cash)
     * 
     * @param discountAmount
     * The amount the discount will take off
     * 
     * @param startDate
     * When the discount will be able to be used
     * 
     * @param endDate
     * The day the discount expires
     * 
     * @param active
     * Wether it is active or not (represented by an integer. 0 for not active, 1 for active)
     * 
     * @param level
     * An integer that represents the level (0 for cart, 1 for item)
     * 
     * @param game
     * This is the game that is being discounted if it is item level
     * @throws ParseException 
     */
    public Discount(int discountID, String discountCode, String description, int discountType, float discountAmount, String startDate, String endDate, int active, int level, String game) throws ParseException{
    
        this.discountID = discountID;
        this.discountCode = discountCode;
        this.description = description;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        
        this.startDate = dateFormatter.parse(startDate);
        this.endDate = dateFormatter.parse(endDate);
        
        
        this.active = active;
        this.level = level;
        this.game = game;
        
    }
    
    /**
     * 
     * A method to save an edited discount
     * 
     * @param code
     * The discount code
     * @param active
     * wether it is active or not
     * @param description
     * Discounts description
     * @param discountAmount
     * The discount amount
     */
    public void save(String code, boolean active, String description, String discountAmount){
    
        discountCode = code;
        this.description = description;
        
        if(active){
        
            this.active = 1;
        
        }else{
        
            this.active = 0;
        
        }
        
        //Discount amount will be handled differently depending on its type
        if(discountType == 0){
        
            this.discountAmount = Float.parseFloat(discountAmount) / 100;
        
        }else{
        
            this.discountAmount = Float.parseFloat(discountAmount);
        
        }
        
    }
    
    /**
     * 
     * saves the discount information to the database
     * 
     * @return true if saved false if saving failed
     */
    public boolean databaseSave(){
    
    
            return SQLManager.saveDiscount(this);
        
    }
    
    /**
     * 
     * deletes the discount
     * 
     * @deprecated
     * @return true if deletion was successful
     */
    @Deprecated
    public boolean delete(){
    
        //first we should check if we can delete it from the database
        boolean success = SQLManager.deleteDiscount(discountID);
        
        if(success){
        
            //Now we can delete this from the list
            return true;
        
        }else{
        
            return false;
        
        }
    
    }
    
}
