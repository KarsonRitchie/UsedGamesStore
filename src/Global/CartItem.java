/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Global;

import SQL.SQLStore;

/**
 * This class is meant to be an item of the cart.
 * 
 * <br><br>
 * The cart itself is a lit of these items, each item being an object.
 * <br>
 * Each object has the necessary information. It has the normal quantity, price, system, and total spent for that specific item. However we can 
 * also see the item ID and its max quantity. The ID is mean to be able to know the specific item we have here and max quantity is to help us not go 
 * over the amount that was displayed to the user when added to the cart.
 * <br>
 * The user ID is just to know what user has this item and the information in the cart.
 * 
 * <br><br>
 * There is also discount info but this is to get a more accurate price and will be edited within this class based on the given discount if the discount 
 * item level.
 * 
 * <br><br>
 * This class also contains methods to remove itself from the cart, update this specific objects quantity, and even add or remove as discount from itself.
 * It even can return the item name by calling on a SQL method.
 */
public class CartItem {

    //here some variables to hold information we need for specific cart items
    public int userID = 0;
    public int itemID = 0;
    public int quantity = 0;
    public float price = 0.00f;
    public float total = 0.00f;
    public String system = "";
    public int maxQuantity = 0;
    //public String description;
    //we are saving everything we may need because we may also sue the cart item data to save the final order data in the database

    //this is objects to make discounts work
    public boolean discountActive = false;
    public float discountedTotal = 0.00f;
    public float amountDiscounted = 0.00f;
    public int typeDiscount = 0;

    /**
     * This is a constructor to create a new item in the cart
     * 
     * @param userID
     * The ID of the user who has this item and the specifications in their cart
     * 
     * @param itemID
     * The ID of the specific item
     * 
     * @param quantity
     * The amount desired by the user
     * 
     * @param maxQuantity
     * The highest the quantity is able to go and was able to go at the time the user put it into their cart.
     * 
     * @param price
     * The price for just one of these items
     * 
     * @param system
     * The system the game is on to help with displays
     */
    public CartItem(int userID, int itemID, int quantity, int maxQuantity, float price, String system) {

        this.userID = userID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.maxQuantity = maxQuantity;
        this.price = price;
        total = quantity * price;
        this.system = system;
        //this.description = description;

    }

    /**
     * 
     * uses the ID of the item to get the name of the item from the database
     * 
     * @return the name of the item
     */
    public String getProduct() {

        //all we will do is call an sql store method
        //this is mostly here so we can take adavantage of it belonging to an instnaced object
        String itemName = SQLStore.getItemName(itemID);

        return itemName;

    }

   /**
    * removes the CartItem object of the index given from the cart list
    * 
    * @param index
    * The index of the CartItem in the cart list you wish to remove
    */
    public void remove(int index) {

        //SQLStore.removeFromCart(itemID, userID);
        
        //Remove from the list
        Lists.cart.remove(index);

    }

    /**
     * 
     * updates the specific CartItem object's quantity
     * 
     * @param quantity 
     */
    public void updateQuantity(int quantity) {

        //what we need to do is update the quantity and the price as well
        this.quantity = quantity;
        total = this.quantity * price;
        
        if(discountActive){
        
            addDiscount(amountDiscounted, typeDiscount);
        
        }
    }

    /**
     * Removes discount pricing off the specific CartItem object
     */
    public void removeDiscount() {

        discountActive = false;
        discountedTotal = 0.00f;

    }

    /**
     * Adds discount pricing off the specific CartItem object
     * 
     * @param discountAmount
     * The amount discounted (either .## for percent or a raw number for non percent discounts)
     * @param discountType
     * The type of discount (0 is percent and 1 is cash)
     */
    public void addDiscount(float discountAmount, int discountType) {

        discountActive = true;

        //we only want one item if there are multiple in stock
        int nonDiscount = quantity - 1;
        
        //we need to store the discount variables
        amountDiscounted = discountAmount;
        typeDiscount = discountType;

        if (discountType == 0) {

            
            //its a percent discount
            float discounted = price - (price * discountAmount);
            
            discountedTotal = (nonDiscount * price) + discounted;
            
        } else {

           //its a dollar discount
           float discounted = price - discountAmount;
           
           if(discounted <= 0){
           
               discounted = 0.00f;
           
           }
           
               discountedTotal = (nonDiscount * price) + discounted;
          
            
        }

    }

}
