/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Global;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class is meant to be a way for objects to be created an instanced to represent an order a customer made. This is going to be used when 
 * looking at a specific customers order history
 * 
 * <br><br>
 * It comprises of information important to an order liking pricing and discount information. Ir also contains the date as well the order was placed.
 * 
 * <br><br>
 * The most important variable though is the array list for array lists. The games bought will comprise of a lot of needed information so its best to create 
 * an array list of strings for each game bought and then add that list to the gamesBought array list.
 * 
 * <br><br>
 * This class contains methods to add games to the list and even finalize data that needs to be finalized like the discounts.
 */
public class Order {

    //Make all the info for a single order
    //this will include date and all the games bought along with the quanitity
    public String orderDate = null;
    public ArrayList<ArrayList> gamesBought = new ArrayList<ArrayList>();
    public int discountID = 0;
    public float discountedOff;

    //some other varibales for the totals
    public float subtotal = 0.00f;
    public String discountAmount = "";
    public float takenOff = 0.00f;
    public float discounted = 0.00f;
    public float tax = 0.00f;
    public float finalTotal = 0.00f;
    
    //some discount info
    public int discountLevel = 0;

    /**
     * The constructor for an order object
     * 
     * @param date
     * A string that represents the date the order was placed
     * 
     * @param discountID
     * The ID of the discount
     * 
     * @param discountedOff
     * The amount discounted off
     */
    public Order(String date, int discountID, float discountedOff) {

        orderDate = date;
        this.discountID = discountID;
        this.discountedOff = discountedOff;

    };
    
    /**
     * A method to get information over a game bought
     * 
     * @param gameTitle
     * Title
     * @param price
     * Price for 1 copy of specific game
     * @param quantity
     * Amount Bought
     * @param total
     * Total spent
     */
    public void addGame(String gameTitle, float price, int quantity, float total) {

        ArrayList<String> game = new ArrayList<String>();

        game.add(gameTitle);
        game.add(String.format("%.2f", price));
        game.add("" + quantity);
        game.add(String.format("%.2f", total));

        subtotal += total;

        gamesBought.add(game);

    }

    /**
     * A method used to finalize the price data for the entire order instead of just one game.
     */
    public void finalizeData() {

        //the first thing we should do is get the discount info
        if (discountID != 0) {

            //we should search for the proper ID
            for (Discount discount : Lists.discounts) {

                if (discount.discountID == discountID) {

                    //we found the discount we needed
                    if (discount.level == 0) {
                        
                        discountLevel = 0;
                        
                        //Depending on the type the discount amount should be handled differently
                        if (discount.discountType == 0) {

                            discountAmount = String.format("%.0f", discount.discountAmount * 100) + "%";
                            takenOff = subtotal * discount.discountAmount;
                            discounted = subtotal - takenOff;
                            tax = discounted * .0825f;
                            finalTotal = discounted + tax;

                        } else {

                            discountAmount = "$" + String.format("%.2f", discountedOff);
                            takenOff = discount.discountAmount;
                            discounted = subtotal - takenOff;
                            tax = discounted * .0825f;
                            finalTotal = discounted + tax;

                        }
                    }else{
                        
                        discountLevel = 1;
                    
                        if (discount.discountType == 0) {

                            discountAmount = String.format("%.0f", discount.discountAmount * 100) + "% off game";
                            discounted = 0.00f;
                            
                        } else {

                            discountAmount = "$" + String.format("%.2f", discountedOff) + " off game";
                            discounted = 0.00f;

                        }
                        
                       tax = subtotal * .0825f;
                       finalTotal = subtotal + tax; 
                    
                    }

                }

            }

        } else {

            tax = subtotal * .0825f;
            finalTotal = subtotal + tax;

        }

    }

}
