/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Global;

import SQL.SQLStore;

/**
 *
 * @author karso
 */
public class CartItem {

    //here some variables to hold
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

    //a method to get the item name
    public String getProduct() {

        //all we will do is call an sql store method
        //this is mostly here so we can take adavantage of it belonging to an instnaced object
        String itemName = SQLStore.getItemName(itemID);

        return itemName;

    }

    //a method to remove the item and add it back to stock
    public void remove(int index) {

        //SQLStore.removeFromCart(itemID, userID);
        Lists.cart.remove(index);

    }

    public void updateQuantity(int quantity) {

        //what we need to do is update the quantity and the price as well
        this.quantity = quantity;
        total = this.quantity * price;
        
        if(discountActive){
        
            addDiscount(amountDiscounted, typeDiscount);
        
        }
    }

    public void removeDiscount() {

        discountActive = false;
        discountedTotal = 0.00f;

    }

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
