/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL;

import java.sql.*;

//this will be packages we created being imported
import Global.Lists;
import Global.Game;
import Global.CartItem;
import Global.Report;
import Global.Variables;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Karson
 * 
 * A class that holds SQL related methods for the storefront
 */
public class SQLStore extends GeneralSQL {

    /**
     * This will run a query that loads the list of games
     */
    public static void loadGames() {

        //we should erase the previous list as well
        Lists.games.clear();

        //When this is called we should execute a query that will bring up all the games on the table
        try {

            //Get connection and run statement
            GeneralSQL.getConnection();

            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT Title, Price, Genres.Genre, Consoles.Console, Quantity, GameID, Description, RestockThreshold, IsEnabled "
                    + "FROM TestGames3 JOIN Genres "
                    + "ON TestGames3.GenreID = Genres.GenreID "
                    + "JOIN Consoles ON TestGames3.ConsoleID = Consoles.ConsoleID");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                //Make the objects
                Game newGame = new Game(rs.getString(1), rs.getFloat(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9));

                //add the object to the list
                Lists.games.add(newGame);

                //System.out.println(rs.getString(1));
                //System.out.println(rs.getString(2));
            }
            
            //Close connection
            con.close();

            //Now we can display the games
            //That should be done soimewhere else, keep this exclusively to queries as much as you can
        } catch (SQLException ex) {

            System.out.println("Cant load games");
            System.out.println(ex);

        }

    }

    /**
     * This will add a specific item to the cart
     * 
     * @param name
     * The name of the game
     * 
     * @param console
     * The console the game is on
     * 
     * @param amountDesired
     * The amount desired by the customer
     * 
     * @param gameID
     * The ID of the game
     * 
     * @param userID
     * The ID of the user who added it to their cart
     * 
     * @param price
     * The price for one copy of the game
     * 
     * @return True if saved to cart successfully. False if not.
     */
    public static boolean addCart(String name, String console, int amountDesired, int gameID, int userID, float price) {

        //Here we will try to check if we can take away from the databse and act acocrdingly
        try {
            System.out.println("The desired amount is: " + amountDesired);

            //Get the connection
            GeneralSQL.getConnection();

            //First check if the amount can be added to the cart
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT Quantity, Consoles.Console FROM TestGames3 "
                    + "JOIN Consoles ON TestGames3.ConsoleID = Consoles.ConsoleID "
                    + "WHERE Title = \"" + name + "\" AND Consoles.Console = '" + console + "'");
            ResultSet rs = stmt.executeQuery();

            rs.next();

            if (rs.getInt(1) < amountDesired) {

                //return a flase statement that will help display
                con.close();
                return false;

            //If it can be added to the cart now we start trying to add it to the database
            } else {

                //Now update the database since there is enough in stock
                //stmt = GeneralSQL.con.prepareStatement("UPDATE TestGames2 SET Quantity = " + (rs.getInt(1) - amountDesired) + " WHERE Title = '" + name + "' AND TestGames2.System = '" + console + "'");
                //stmt.execute();
                //keep this here for reference, but do not use it unless for testing
                //quantity should be changed if it already exists in the cart
                //we will add it to the cart if it has not been added before
                //first check if its in the cart
                //save the max quantity first
                int max = rs.getInt(1);

                stmt = GeneralSQL.con.prepareStatement("SELECT * FROM Cart WHERE UserID = " + userID + " AND ItemID = " + gameID);
                rs = stmt.executeQuery();

                if (rs.next()) {

                    //first we need to make sure we dont go past the cap
                    if (rs.getInt(4) < (rs.getInt(3) + amountDesired)) {

                        amountDesired = rs.getInt(4);

                    } else {

                        amountDesired = rs.getInt(3) + amountDesired;

                    }

                    stmt = GeneralSQL.con.prepareStatement("UPDATE Cart SET Quantity = " + amountDesired + " WHERE ItemID = " + gameID + " AND UserID = " + userID);
                    stmt.execute();

                } else {

                    stmt = GeneralSQL.con.prepareStatement("INSERT INTO Cart VALUES(" + userID + ", " + gameID + ", " + amountDesired + ", " + max + ", " + price + ", '" + console + "')");
                    stmt.execute();

                }

                //now create the cart again
                createCart(userID);

                //We will still use this method but we might not should make a cart table
                //keep it for reference or jsut incase but for now just make the cart item here
                //We need to check if the game already exists in the cart
//                CartItem item = new CartItem(userID, gameID, amountDesired, max, price, console);
//
//                boolean exists = false;
//
//                for (CartItem tempItem : Lists.cart) {
//
//                    if (tempItem.itemID == gameID) {
//
//                        tempItem.updateQuantity(tempItem.quantity + amountDesired);
//
//                        if (tempItem.quantity > tempItem.maxQuantity) {
//
//                            tempItem.quantity = tempItem.maxQuantity;
//
//                        }
//
//                        exists = true;
//                        break;
//
//                    }
//
//                }
//
//                if (exists == false) {
//                    Lists.cart.add(item);
//                }
//
                //Close the connection
                con.close();
                return true;

            }

        } catch (SQLException ex) {

            System.out.println(ex);
            return false;

        }

    }

    /**
     * This is here to edit the quantity of an item in the cart
     * 
     * @param userID
     * The ID of the user who added it to their cart
     * 
     * @param gameID
     * The ID of the game
     * 
     * @param amountDesired
     * The new desired amount of the game
     */
    public static void editCart(int userID, int gameID, int amountDesired) {

        PreparedStatement stmt;
        try {
            //Get connection
            GeneralSQL.getConnection();
            
            //Update database
            stmt = GeneralSQL.con.prepareStatement("UPDATE Cart SET Quantity = " + amountDesired + " WHERE ItemID = " + gameID + " AND UserID = " + userID);
            stmt.execute();

            //Create the cart again and then close the connection
            createCart(userID);
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(SQLStore.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Extracts info from the database to create a cart for the specific user
     * 
     * @param userID
     * The ID of the chosen or logged in user to create the cart for
     */
    public static void createCart(int userID) {

        //This is to make the cart list for the current user
        //first clear the current list
        //this is so we can run this function multiple times
        Lists.cart.clear();

        try {

            //connection is gotten before this method runs
            //Get the cart data of the specific user
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT * FROM Cart WHERE UserID = " + userID);
            ResultSet rs = stmt.executeQuery();

            //Fill up the cart list from the result set
            while (rs.next()) {

                //first create the cartItem
                System.out.println(rs.getInt(3));
                CartItem item = new CartItem(userID, rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getFloat(5), rs.getString(6));
                Lists.cart.add(item);
            }

            //Close the connection
            con.close();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }
    
    
    /**
     * This is to fetch the item name
     * 
     * @param itemID
     * The itemID of the desired item to get the name of
     * 
     * @return the item's name
     */
    public static String getItemName(int itemID) {

        try {

            //Get the connection
            GeneralSQL.getConnection();

            //Fetch the item name
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT Title FROM TestGames3 WHERE GameID = " + itemID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                String newString = rs.getString(1);

                //Close connection
                con.close();

                return newString;

            } else {

                //Close connection
                con.close();
                
                return "NONE";

            }

        } catch (SQLException ex) {

            System.out.println(ex);
            return "NONE";

        }

    }

    /**
     * Deletes an item from the cart in the database
     * 
     * @param itemID
     * The ID of the item
     * 
     * @param userID
     * The ID of the user who added the item to their cart
     * 
     * @return the result of the method in the form of a string
     */
    public static String removeFromCart(int itemID, int userID) {

        try {
            //PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT Quantity FROM TestGames2 WHERE GameID = " + itemID);
            //ResultSet rs = stmt.executeQuery();

            //Get connection
            GeneralSQL.getConnection();

            //Delete from the database
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("DELETE FROM Cart WHERE ItemID = " + itemID + " AND UserID = " + userID);
            stmt.execute();

            //Close the connection
            con.close();

            return "Success";

            //this is if we ever need to add it back
            //keep it here for reference
            //or incase we may need to alter it back
//            if(rs.next()){
//            stmt = GeneralSQL.con.prepareStatement("UPDATE TestGames2 SET Quantity = " + (rs.getInt(1) + quantity) + " WHERE GameID = " + itemID);
//            stmt.execute();
//            
//            
//            }
        } catch (SQLException ex) {

            System.out.println(ex);
            return "Failed";

        }

    }

    /**
     * This is to extract data from tables that will be used to create filters by adding the data to lists
     */
    public static void createFilterLists() {

        //here we will create lists that will allow ua to create filters
        try {

            //Get the connection
            GeneralSQL.getConnection();

            //Now extract from the genres and consoles tables and input them into their appropiate lists
            
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT Genre FROM Genres");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Lists.genres.add(rs.getString(1));

            }

            stmt = GeneralSQL.con.prepareStatement("SELECT Console FROM Consoles");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Lists.consoles.add(rs.getString(1));

            }

            //Close the connection
            con.close();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    /**
     * This is to search for a discount based on its code and check if it can be used
     * 
     * @param enteredCode
     * The code given by the customer for a discount
     * 
     * @param userID
     * The ID of the user using the discount code
     * 
     * @return information over the discount
     * @throws ParseException 
     */
    public static ArrayList<Object> searchForCoupon(String enteredCode, int userID) throws ParseException {

        //we want to make an array of different objects so we can use what we need in a specific context
        ArrayList<Object> discountInfo = new ArrayList<Object>();

        //first execute the query
        try {

            //Get the connection and get the desired information
            GeneralSQL.getConnection();

            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT DiscountCode, DiscountType, DiscountPercent, DiscountDollar, Active, EndDate, DiscountID, DiscountLevel, GameTitle From Discounts WHERE DiscountCode = '" + enteredCode + "'");
            ResultSet rs = stmt.executeQuery();

            //first check if we pulled up any results
            if (rs.next()) {

                if (rs.getBoolean(5)) {

                    //now we know its active
                    //now we need to check if its expired or not
                    Date currentDate = new Date();

                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

                    //we need to make the date
                    //extracting the date doesnt work too well on sqlite
                    Date endDate = dateFormatter.parse(rs.getString(6));

                    if (!endDate.before(currentDate)) {

                        //its not expired
                        //Last thing to check if the customer has already used the code
                        stmt = GeneralSQL.con.prepareStatement("SELECT UserID, DiscountID From Orders WHERE UserID = " + userID);
                        ResultSet rs2 = stmt.executeQuery();

                        boolean discountUsed = false;

                        while (rs2.next()) {

                            if (rs2.getInt(2) == rs.getInt(7)) {

                                discountUsed = true;
                            }
                        }

                        if (discountUsed) {

                            discountInfo.add("Discount code already used by this user");

                        } else {

                            //Code was not used, now proceed with the discount
                            //Add the correct information to the list to use later
                            discountInfo.add("Accepted");
                            discountInfo.add(rs.getInt(2));

                            if (rs.getInt(2) == 0) {

                                discountInfo.add(rs.getFloat(3));

                            } else {

                                discountInfo.add(rs.getFloat(4));

                            }

                            discountInfo.add(rs.getInt(7));
                            discountInfo.add(rs.getInt(8));
                            discountInfo.add(rs.getString(9));

                        }

                    } else {

                        discountInfo.add("The discount code is expired");

                    }

                } else {

                    discountInfo.add("The discount code is not active at this time");

                }

            } else {

                discountInfo.add("Invalid Code");

            }

            //Close connection
            con.close();

        } catch (SQLException ex) {

            System.out.println(ex);
            discountInfo.clear();
            discountInfo.add("An Error Occured, please try again");

        }

        return discountInfo;
    }

    //We are getting ready to let the user checkout
    //these methods will be used for checking out
    /**
     * This is used to check if the cart has any games it can't buy with the quantity desired
     * 
     * @return the list of games that can not be bough
     */
    public static ArrayList<CartItem> checkCart() {

        //we will loop through the items in the cart and compare them in a query
        //what we wull need to do is creat an array list of all the games that can not be ought at the current time
        ArrayList<CartItem> noBuy = new ArrayList<CartItem>();
        ArrayList<Integer> indexes = new ArrayList<Integer>();

        //go ahead and make a prepared statement and rs variable
        try {

            //Get the connection to
            GeneralSQL.getConnection();

            PreparedStatement stmt = null;
            ResultSet rs = null;

            for (int x = 0; x < Lists.cart.size(); x++) {

                stmt = GeneralSQL.con.prepareStatement("SELECT Quantity FROM TestGames3 WHERE GameID = " + Lists.cart.get(x).itemID);
                rs = stmt.executeQuery();

                rs.next();

                //check if the game can be bought
                if (rs.getInt(1) < Lists.cart.get(x).quantity) {

                    noBuy.add(Lists.cart.get(x));
                    indexes.add(x);

                }

            }

            //now after that we must go throught the indexes and delete the list
            //we could jsut go through cart reversed but I would like to show the customer in the order they had their cart instead what games are beign delted
            for (int y = indexes.size() - 1; y >= 0; y--) {

                Lists.cart.remove((int) indexes.get(y));

            }

            //Close the connection
            con.close();

            return noBuy;

        } catch (SQLException ex) {

            noBuy.clear();
            noBuy.add(new CartItem(0, 0, 0, 0, 0, "ERROR"));
            return noBuy;

        }

    }

    /**
     * This is a method that will allow the user to purchase the games
     * 
     * @param cardNum
     * The credit card number
     * 
     * @param expDate
     * The cards expiration date
     * 
     * @param cvv
     * The CVV digits of the credit card
     * 
     * @param discountID
     * The ID of the discount
     * 
     * @param discounted
     * The amount discounted
     * 
     * @param tax
     * The tax amount
     * 
     * @param finalTotal
     * The final total
     * 
     * @param subtotal
     * The subtotal
     * 
     * @param discountString
     * A string related to the discount to help with the report display
     */
    public static void purchase(String cardNum, String expDate, String cvv, int discountID, float discounted, float tax, float finalTotal, float subtotal, String discountString) {

        //this is a variable to hold the order ID
        int orderID = 0;

        //the first thing we are doing is checking the discount
        //if the id is 0 then it is null
        try {

            //Get connection
            GeneralSQL.getConnection();

            PreparedStatement stmt = null;
            ResultSet rs = null;

            //We need to get the prvious ID to help with auto incrementing
            int idNum = 0;

            stmt = GeneralSQL.con.prepareStatement("SELECT OrderID FROM Orders");

            rs = stmt.executeQuery();

            while (rs.next()) {

                idNum = rs.getInt(1);

            }

            //Make sure to get the current date
            Date currentDate = new Date();

            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

            //now format the current date
            String newDate = dateFormatter.format(currentDate);

            //Check what the discount ID is and then check if the one making the purchase is the customer or a manger for a customer
            //Run the correct query for whichever is needed
            if (discountID == 0) {

                if (Variables.currentLevel.equals("Customer")) {
                    stmt = GeneralSQL.con.prepareStatement("INSERT INTO Orders VALUES(" + (idNum + 1) + ", " + Variables.userID + ", NULL, '" + newDate + "', '"
                            + cardNum + "', '" + expDate + "', '" + cvv + "', " + finalTotal + ", " + discounted + ")");
                } else {
                    stmt = GeneralSQL.con.prepareStatement("INSERT INTO Orders VALUES(" + (idNum + 1) + ", " + Variables.customerID + ", NULL, '" + newDate + "', '"
                            + cardNum + "', '" + expDate + "', '" + cvv + "', " + finalTotal + ", " + discounted + ")");
                }
                stmt.execute();

            } else {

                if (Variables.currentLevel.equals("Customer")) {
                    stmt = GeneralSQL.con.prepareStatement("INSERT INTO Orders VALUES(" + (idNum + 1) + ", " + Variables.userID + ", " + discountID + ", '" + newDate + "', '"
                            + cardNum + "', '" + expDate + "', '" + cvv + "', " + finalTotal + ", " + discounted + ")");
                } else {
                    stmt = GeneralSQL.con.prepareStatement("INSERT INTO Orders VALUES(" + (idNum + 1) + ", " + Variables.customerID + ", " + discountID + ", '" + newDate + "', '"
                            + cardNum + "', '" + expDate + "', '" + cvv + "', " + finalTotal + ", " + discounted + ")");
                }
                stmt.execute();

            }

            //now we go through the cart and add them to order details
            //we need to get the orderID as well
            //search by user id and then take the latest one in the list
            if (Variables.currentLevel.equals("Customer")) {
                stmt = GeneralSQL.con.prepareStatement("SELECT OrderID FROM Orders WHERE UserID = " + Variables.userID);
            } else {
                stmt = GeneralSQL.con.prepareStatement("SELECT OrderID FROM Orders WHERE UserID = " + Variables.customerID);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {

                orderID = rs.getInt(1);

            }

            stmt = GeneralSQL.con.prepareStatement("SELECT OrderDetailID FROM OrderDetails");

            rs = stmt.executeQuery();
            
            //Get ID again hoiwever each query will add a higher number to the ID since there will be multiple rows added

            while (rs.next()) {

                idNum = rs.getInt(1);

            }

            for (CartItem item : Lists.cart) {

                if (!item.discountActive) {
                    stmt = GeneralSQL.con.prepareStatement("INSERT INTO OrderDetails VALUES(" + (idNum + 1) + ", " + orderID + ", " + item.itemID + ", " + item.quantity + ", " + item.total + ")");
                } else {

                    stmt = GeneralSQL.con.prepareStatement("INSERT INTO OrderDetails VALUES(" + (idNum + 1) + ", " + orderID + ", " + item.itemID + ", " + item.quantity + ", " + item.discountedTotal + ")");

                }
                stmt.execute();

                //now remove the necessary stock
                stmt = GeneralSQL.con.prepareStatement("UPDATE TestGames3 SET Quantity = Quantity - " + item.quantity + " WHERE GameID = " + item.itemID);
                stmt.execute();
                
                idNum++;

            }

            //and then we will make a report
            //first we need to get a result set
            stmt = GeneralSQL.con.prepareStatement("SELECT TestGames3.Title, TestGames3.Price, OrderDetails.Quantity, (TestGames3.Price * OrderDetails.Quantity) AS Total "
                    + "FROM Orders JOIN OrderDetails ON Orders.OrderID = OrderDetails.OrderID "
                    + "JOIN TestGames3 ON OrderDetails.InventoryID = TestGames3.GameID "
                    + "WHERE Orders.OrderID = " + orderID);
            rs = stmt.executeQuery();

            makeReport(rs, discountID, discounted, tax, finalTotal, subtotal, discountString);

            //clear the cart
            stmt = GeneralSQL.con.prepareStatement("DELETE FROM Cart WHERE UserID = " + Variables.customerID);
            stmt.execute();

            //Close connection
            con.close();

            Lists.cart.clear();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    /**
     * This is to make the receipt
     * 
     * @param results
     * The result set of the purchase
     * 
     * @param discountID
     * The ID of the discount
     * 
     * @param discounted
     * The discounted amount
     * 
     * @param tax
     * The tax amount
     * 
     * @param finalTotal
     * The final total
     * 
     * @param subtotal
     * The subtotal of the purchase
     * 
     * @param discountString
     * A string relating to the discount to help with the report display
     */
    public static void makeReport(ResultSet results, int discountID, float discounted, float tax, float finalTotal, float subtotal, String discountString) {

        try {
            Report report = new Report("Your Recepit", results, discountID, discounted, tax, finalTotal, subtotal, discountString);
        } catch (Exception ex) {
            Logger.getLogger(SQLStore.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
