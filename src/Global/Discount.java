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
 *
 * @author Karson
 * 
 * A class to create a discount with its associated information
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
