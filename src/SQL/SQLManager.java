/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL;

import Global.Discount;
import Global.Game;
import Global.User;
import Global.Lists;
import Global.Order;
import Global.Report;
import Global.Variables;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Karson
 * 
 * A class to host SQL related methods that concern manger functions
 */
public class SQLManager extends GeneralSQL {

    /**
     * 
     * This method loads users into a list so the managers can view and alter them
     * 
     */
    public static void loadUsers() {

        //Clear the previous list
        Lists.users.clear();

        try {

            //Get connection
            GeneralSQL.getConnection();

            //Now retrieve the users
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT Level, UserID, FirstName, LastName, Email, Phone, AddressLine1, AddressLine2, AddressLine3, City, State, Zipcode, Username, Password, Enabled "
                    + "FROM User");
            ResultSet rs = stmt.executeQuery();

            //Now iteriate through the result set and create new users
            while (rs.next()) {

                //Make the objects
                User newUser = new User(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getInt(15));

                //add the object to the list
                Lists.users.add(newUser);

                //System.out.println(rs.getString(1));
                //System.out.println(rs.getString(2));
            }

            //Close connection
            con.close();

        } catch (SQLException ex) {

            System.out.println("Cant load Users");
            System.out.println(ex);

        }

    }

    /**
     * 
     * Used to delete the user from the database
     * 
     * @param userID
     * The ID of the user in the database
     * 
     * @return True if deletion was successful. False if not.
     * 
     * @deprecated 
     */
    @Deprecated
    public static boolean deleteUser(int userID) {

        try {

            PreparedStatement stmt = GeneralSQL.con.prepareStatement("DELETE FROM User WHERE UserID = " + userID);
            stmt.execute();

            return true;

        } catch (SQLException ex) {

            System.out.println("Cant delete user");
            System.out.println(ex);
            return false;

        }

    }

    /**
     * Saves the user
     * 
     * @param user
     * The specific user object we are trying to save
     * 
     * @return True if saved successfully. False if not.
     */
    public static boolean saveUser(User user) {

        try {

            //Get connection
            GeneralSQL.getConnection();

            //Execute an update statement
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("UPDATE User SET Username = '" + user.userName + "', "
                    + "Password = '" + user.password + "', "
                    + "FirstName = '" + user.userFirstName + "', "
                    + "LastName = '" + user.userLastName + "', "
                    + "AddressLine1 = '" + user.address1 + "', "
                    + "AddressLine2 = '" + user.address2 + "', "
                    + "AddressLine3 = '" + user.address3 + "', "
                    + "City = '" + user.city + "', "
                    + "State = '" + user.state + "', "
                    + "Zipcode = '" + user.zip + "', "
                    + "Phone = '" + user.phoneNum + "', "
                    + "Email = '" + user.userEmail + "', "
                    + "Enabled = '" + user.enabled + "' "
                    + "WHERE UserID = " + user.userID);

            stmt.execute();

            //Close connection
            con.close();

            return true;

        } catch (SQLException ex) {

            System.out.println("Cant save user");
            System.out.println(ex);

            return false;

        }

    }

    /**
     * Retrieves the orders of a specified user
     * 
     * @param userID
     * The ID of the specified user.
     * 
     */
    public static void retrieveOrders(int userID) {

        //Clear the previous list first
        Lists.orders.clear();

        PreparedStatement stmt;

        try {
            
            //Get connection
            GeneralSQL.getConnection();

            //Execute query
            stmt = GeneralSQL.con.prepareStatement("SELECT OrderID, OrderDate, DiscountID, DiscountedOff "
                    + "FROM Orders "
                    + "WHERE UserID = " + userID);
            ResultSet rs = stmt.executeQuery();

            //Now iteriate over the result set
            while (rs.next()) {

                int discountID = 0;

                if (rs.getInt(3) > 0) {

                    discountID = rs.getInt(3);

                }

                //Create object that will then be added to the list
                Order newOrder = new Order(rs.getString(2), discountID, rs.getFloat(4));
                System.out.println(rs.getFloat(4));

                //we need a new query that will allow us to see a specific order
                stmt = GeneralSQL.con.prepareStatement("SELECT TestGames3.Title, OrderDetails.Quantity, TestGames3.Price, OrderDetails.total "
                        + "FROM OrderDetails JOIN TestGames3 ON OrderDetails.InventoryID = TestGames3.GameID "
                        + "WHERE OrderID = " + rs.getInt(1));
                ResultSet rs2 = stmt.executeQuery();

                while (rs2.next()) {

                    //keep adding up the games
                    newOrder.addGame(rs2.getString(1), rs2.getFloat(3), rs2.getInt(2), rs2.getFloat(4));

                }

                //Finalize the price data
                newOrder.finalizeData();

                //and now we add it to the orders list
                Lists.orders.add(newOrder);

            }

            //Close connection
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    /**
     * Loads the discounts into a list
     * 
     * @throws ParseException 
     */
    public static void loadDiscounts() throws ParseException {

        Lists.discounts.clear();
        //Clears previous list

        try {

            //Get connection and then fetch all of the needed discount data
            GeneralSQL.getConnection();

            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT DiscountID, DiscountCode, Description, DiscountType, DiscountPercent, DiscountDollar, StartDate, EndDate, Active, DiscountLevel, GameTitle FROM Discounts");
            ResultSet rs = stmt.executeQuery();

            //Now go through the result set
            while (rs.next()) {

                //Check what the discount type is
                //this will help determine what amount we need
                if (rs.getInt(4) == 0) {

                    //Create the object
                    Discount newDiscount = new Discount(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11));

                    //And add it to the list
                    Lists.discounts.add(newDiscount);

                    //System.out.println(rs.getString(7));
                } else {

                    //Create the object
                    Discount newDiscount = new Discount(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11));

                    //add it the list
                    Lists.discounts.add(newDiscount);
                    //System.out.println(rs.getString(7));

                }

                //System.out.println(rs.getString(1));
                //System.out.println(rs.getString(2));
            }

            //Close connection
            con.close();

        } catch (SQLException ex) {

            System.out.println("Cant load Discounts");
            System.out.println(ex);

        }

    }

    /**
     * Saves a specific discount object to the database
     * 
     * @param discount
     * the specified discount object being saved
     * 
     * @return True if save was successful. False if not
     */
    public static boolean saveDiscount(Discount discount) {

        try {

            //Get connection
            GeneralSQL.getConnection();

            //Check the type and save properly
            if (discount.discountType == 0) {
                PreparedStatement stmt = GeneralSQL.con.prepareStatement("UPDATE Discounts SET DiscountCode = \"" + discount.discountCode.toUpperCase() + "\", "
                        + "Active = " + discount.active + ", Description = \"" + discount.description + "\", DiscountPercent = " + discount.discountAmount + " "
                        + "WHERE DiscountID = " + discount.discountID + ";");

                stmt.execute();
            } else {

                PreparedStatement stmt = GeneralSQL.con.prepareStatement("UPDATE Discounts SET DiscountCode = \"" + discount.discountCode.toUpperCase() + "\", "
                        + "Active = " + discount.active + ", Description = \"" + discount.description + "\", DiscountDollar = " + discount.discountAmount + " "
                        + "WHERE DiscountID = " + discount.discountID + ";");

                stmt.execute();

            }

            //Close connection
            con.close();

            return true;

        } catch (SQLException ex) {

            System.out.println("Cant save discount");
            System.out.println(ex);

            return false;

        }

    }

    /**
     * Deletes discount from the database
     * 
     * @param discountID
     * The id of the discount
     * 
     * @return True if deletion was successful. False if not.
     * 
     * @deprecated 
     */
    @Deprecated
    public static boolean deleteDiscount(int discountID) {

        try {

            PreparedStatement stmt = GeneralSQL.con.prepareStatement("DELETE FROM Discounts WHERE DiscountID = " + discountID);
            stmt.execute();
            return true;

        } catch (SQLException ex) {

            System.out.println("Cant delete Discount");
            System.out.println(ex);
            return false;

        }

    }

    /**
     * A method to create a discount
     * 
     * @param type
     * The discount type (0 for percent, 1 for cash)
     * 
     * @param code
     * The string to represent the discount code
     * 
     * @param description
     * The string to represent the discount's description
     * 
     * @param amount
     * This is the amount for the discount (.## for percent, raw number for cash)
     * 
     * @param startDate
     * The date when this discount becomes usable
     * 
     * @param endDate
     * The date when this discount expires
     * 
     * @param isActive
     * If the discount is active or not (0 for not active, 1 for active. inactive discounts cant be used)
     * 
     * @param discountLevel
     * The string to represent the discount level
     * 
     * @param title
     * The title of the game if the discount is item level
     * 
     * @return True if discount was created successfully. False if not.
     */
    public static boolean createDiscount(int type, String code, String description, float amount, String startDate, String endDate, int isActive, String discountLevel, String title) {

        try {

            //Get connection
            GeneralSQL.getConnection();

            PreparedStatement stmt = null;
            ResultSet rs = null;

            //Get the previous id first so we can create a new discount with an auto increment ID
            int idNum = 0;

            stmt = GeneralSQL.con.prepareStatement("SELECT DiscountID FROM Discounts");

            rs = stmt.executeQuery();

            while (rs.next()) {

                idNum = rs.getInt(1);

            }

            //check the level and then create the discount needed (0 is cart level, 1 is item level)
            if (discountLevel.equals("Cart")) {

                int level = 0;

                if (type == 0) {

                    stmt = GeneralSQL.con.prepareStatement("INSERT INTO Discounts VALUES(" + (idNum + 1) + ", \"" + code + "\", \"" + description + "\", " + type + ", " + level + ", \"N/A\", " + amount + ", NULL, \"" + startDate + "\", \"" + endDate + "\", " + isActive + ")");

                } else {

                    stmt = GeneralSQL.con.prepareStatement("INSERT INTO Discounts VALUES(" + (idNum + 1) + ", \"" + code + "\", \"" + description + "\", " + type + ", " + level + ", \"N/A\", NULL, " + amount + ", \"" + startDate + "\", \"" + endDate + "\", " + isActive + ")");

                }

            } else {

                int level = 1;

                if (type == 0) {

                    stmt = GeneralSQL.con.prepareStatement("INSERT INTO Discounts VALUES(" + (idNum + 1) + ", \"" + code + "\", \"" + description + "\", " + type + ", " + level + ", \"" + title + "\", " + amount + ", NULL, \"" + startDate + "\", \"" + endDate + "\", " + isActive + ")");

                } else {

                    stmt = GeneralSQL.con.prepareStatement("INSERT INTO Discounts VALUES(" + (idNum + 1) + ", \"" + code + "\", \"" + description + "\", " + type + ", " + level + ", \"" + title + "\", NULL, " + amount + ", \"" + startDate + "\", \"" + endDate + "\", " + isActive + ")");

                }

            }

            stmt.execute();

            //close connection
            con.close();

            return true;

        } catch (SQLException ex) {

            System.out.println("Cant create Discount");
            System.out.println(ex);
            return false;

        }

    }

    /**
     * This is to check for a discounts existence to make sure we do not get multiple of the same code
     * 
     * @param code
     * the given discount code
     * 
     * @return a string that represents the results of the query
     */
    public static String checkDiscount(String code) {

        try {

            //Get connection and then run a query
            GeneralSQL.getConnection();

            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT DiscountCode FROM Discounts WHERE DiscountCode = \"" + code + "\"");
            ResultSet rs = stmt.executeQuery();
            
            //Check if it returns anything and close the connection
            //Then return the correct status
            if (rs.next()) {

                con.close();
                return "Discount exists";

            } else {

                con.close();

                return "Discount doesnt exist";

            }

        } catch (SQLException ex) {

            System.out.println(ex);
            return "ERROR";

        }

    }

    /**
     * Saves a specified game object to the database
     * 
     * @param game
     * The game specified that was edited
     * 
     * @return True if save was successful. False if not.
     */
    public static boolean saveGame(Game game) {

        try {

            //Get the connection and then update the specific game
            GeneralSQL.getConnection();

            PreparedStatement stmt = GeneralSQL.con.prepareStatement("UPDATE TestGames3 SET Title = \"" + game.name + "\", "
                    + "Price = \"" + String.format("%.2f", game.price) + "\", "
                    + "Quantity = " + game.quantity + ", "
                    + "Description = \"" + game.description + "\", "
                    + "RestockThreshold = " + game.restock + ", "
                    + "IsEnabled = " + game.active + " "
                    + "WHERE GameID = " + game.gameID);

            stmt.execute();
            
            //Close the connection
            con.close();

            return true;

        } catch (SQLException ex) {

            System.out.println("Cant save game");
            System.out.println(ex);

            return false;

        }

    }

    /**
     * A method to add a game to the database
     * 
     * @param title
     * A string that represents the games title
     * 
     * @param price
     * The games price for 1 copy
     * 
     * @param genre
     * A string to represent the games genre
     * 
     * @param console
     * A string that represents the games console
     * 
     * @param quantity
     * The amount in stock
     * 
     * @param description
     * The game's description
     * 
     * @param restock
     * The restock threshold
     * 
     * @param isActive
     * Wether the game is inactive or active (0 for inactive, 1 for active. Inactive games can not be viewed by customers)
     * 
     * @return True if game was added successfully. False if not.
     */
    public static boolean addGame(String title, float price, String genre, String console, int quantity, String description, int restock, int isActive) {

        try {

            //Get connection
            GeneralSQL.getConnection();
            ResultSet rs = null;

            //Get the id so we can have a unique ID
            int idNum = 0;

            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT GameID FROM TestGames3");

            rs = stmt.executeQuery();

            while (rs.next()) {

                idNum = rs.getInt(1);

            }

            //Insert into database
            stmt = GeneralSQL.con.prepareStatement("INSERT INTO TestGames3 VALUES(" + (idNum + 1) + ", \"" + title + "\", "
                    + String.format("%.2f", price) + ", "
                    + "(SELECT GenreID FROM Genres WHERE Genre = \"" + genre + "\"), (SELECT ConsoleID FROM Consoles WHERE Console = \"" + console + "\"), " + quantity + ", "
                    + "\"" + description + "\", " + restock + ", " + isActive + ")");

            stmt.execute();

            //Close connection
            con.close();

            return true;

        } catch (SQLException ex) {

            System.out.println("Cant create Game");
            System.out.println(ex);
            return false;

        }

    }

    /**
     * This is to check if the game already exists or not
     * 
     * @param title
     * The games title
     * 
     * @param system
     * The system the game is on
     * 
     * @return A string that represents the result of the query
     */
    public static String checkGame(String title, String system) {

        try {

            //Get connection and use the title and system to find if the game exists in the database
            GeneralSQL.getConnection();

            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT Title, Consoles.Console FROM TestGames3 "
                    + "JOIN Consoles ON TestGames3.ConsoleID = Consoles.ConsoleID "
                    + "WHERE Title = \"" + title + "\" AND Console = \"" + system + "\"");
            //Both title and system must be checked because a game can have the same title but be on a different system

            ResultSet rs = stmt.executeQuery();

            //Check if anything is returned, close the connection, and return the appropiate string
            if (rs.next()) {

                con.close();

                return "Exists";

            } else {

                con.close();

                return "No";

            }

        } catch (SQLException ex) {

            System.out.println("Cant search for game");
            System.out.println(ex);
            return "ERROR";

        }

    }

    /**
     * Uses the title and system to recieve the items ID
     * 
     * @param title
     * The title of the game
     * 
     * @param system
     * The system the game is on
     * 
     * @return the games ID
     */
    public static int recieveID(String title, String system) {

        try {

            //Get connection
            GeneralSQL.getConnection();

            //Fetch ID
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT GameID FROM TestGames3 "
                    + "WHERE Title = \"" + title + "\" AND ConsoleID = (SELECT ConsoleID FROM Consoles WHERE Console = \"" + system + "\")");

            ResultSet rs = stmt.executeQuery();

            //Progress the database so we can actally get our cursor on the proper information
            rs.next();

            int newInt = rs.getInt(1);
            
            //Close the connection
            con.close();

            return newInt;

        } catch (SQLException ex) {

            return 0;

        }

    }

    /**
     * Deletes the game from the database
     * 
     * @param gameID
     * The id of the desired game to delete
     * 
     * @return True if deletion was successful. False if not.
     * 
     */
    public static boolean deleteGame(int gameID) {

        try {
            
            //Get connection
            GeneralSQL.getConnection();

            //Delete the specific game
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("DELETE FROM TestGames3 WHERE GameID = " + gameID);
            stmt.execute();

            //Close connection
            con.close();

            return true;

        } catch (SQLException ex) {

            System.out.println("Cant delete game");
            System.out.println(ex);
            return false;

        }

    }
    
    /**
     * A method that will create insert details for trade-ins in the database
     * 
     * @param addedGames
     * An array list of the games that have an updated quantity
     * 
     * @param newGames
     * An array list of all the games now added to the system
     * 
     * @param cost
     * The amount spent on the trade-in
     * 
     * @return True if report was successfully made. False if not.
     */
    public static boolean tradeReport(ArrayList<Game> addedGames, ArrayList<Game> newGames, float cost){
    
    
         try {

             //Get the connection
            GeneralSQL.getConnection();
            ResultSet rs = null;

            int idNum = 0;
            
            //Get the current date 
            Date currentDate = new Date();

            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            
            //now format the current date
            String newDate = dateFormatter.format(currentDate);

            //Get the previous ID number to be able to autoincrement in the database
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT TradeID FROM Trades");

            rs = stmt.executeQuery();

            while (rs.next()) {

                idNum = rs.getInt(1);

            }
            
            //Now create the main trade in row

            stmt = GeneralSQL.con.prepareStatement("INSERT INTO Trades VALUES(" + (idNum + 1) + ", " + cost + ", '" + newDate + "')");

            stmt.execute();
            
            //and then afterwards we need to insert the details of the trade
            //first we get the games added to the inventory then do the games just added to the system
            
            //an imteger to keep track of how far we are in the current database to help with id
            int rowAmount = 0;
            int detailID = 0;
        
            //Get the previous ID to again help with auto incrementing
            
            stmt = GeneralSQL.con.prepareStatement("SELECT DetailsID FROM TradeDetails");
            rs = stmt.executeQuery();
            
            while (rs.next()) {

                detailID = rs.getInt(1);

            }
            
            //Now add each game to the database of the specific trade
            for(Game game: addedGames){
            
                stmt = GeneralSQL.con.prepareStatement("INSERT INTO TradeDetails VALUES(" + (detailID + 1 + rowAmount) + ", " + (idNum + 1) + ", " + game.gameID + ", " + game.quantity + ")");
                stmt.execute();
                rowAmount++;
                
            }
            
            for(Game game: newGames){
            
                stmt = GeneralSQL.con.prepareStatement("INSERT INTO TradeDetails VALUES(" + (detailID + 1 + rowAmount) + ", " + (idNum + 1) + ", " + game.gameID + ", " + game.quantity + ")");
                stmt.execute();
                rowAmount++;
                
            }


            //Close the connection
            con.close();

            return true;

        } catch (SQLException ex) {

            System.out.println("Cant save trade");
            System.out.println(ex);
            return false;

        }
    
    }

    /**
     * Creates an inventory report
     * @throws Exception 
     */
    public static void inventoryReport() throws Exception {

        try {

            //Get connection
            GeneralSQL.getConnection();

            //Get all the inventory data
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT Title, Price, Consoles.Console, Quantity, RestockThreshold, IsEnabled "
                    + "FROM TestGames3 "
                    + "JOIN Consoles ON TestGames3.ConsoleID = Consoles.ConsoleID");
            ResultSet rs = stmt.executeQuery();

            //create the report
            Report inventoryReport = new Report(rs, true, false);

            //Close the connection
            con.close();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    /**
     * Creates a sales report for the specified dates
     * 
     * @param specific
     * Wether or not it concerns a specific user
     * 
     * @param start
     * Start date
     * 
     * @param end
     * End date
     * 
     * @throws Exception 
     */
    public static void salesReport(boolean specific, String start, String end) throws Exception {

        //Check if it is a specific user
        //Then get the connection, execute the select statement, create the report
        //Afterwards close the connection
        if (specific) {

            GeneralSQL.getConnection();

            //only get orders from a specific customer
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT TestGames3.Title, Consoles.Console, SUM(OrderDetails.Quantity) AS 'Total Sold', SUM(OrderDetails.Total) AS 'Total Earned', OrderDate FROM OrderDetails "
                    + "JOIN TestGames3 ON TestGames3.GameID = OrderDetails.InventoryID  JOIN Consoles ON TestGames3.ConsoleID = Consoles.ConsoleID JOIN Orders ON OrderDetails.OrderID = Orders.OrderID "
                    + "WHERE UserID = " + Variables.customerID + " AND OrderDate BETWEEN \"" + start + "\" AND \"" + end + "\" "
                    + "GROUP BY Orders.OrderDate, TestGames3.GameID "
                    + "ORDER BY Orders.OrderDate");

            ResultSet rs = stmt.executeQuery();

            Report salesReport = new Report(rs, false, true);

            con.close();

        } else {

            //do a query for all orders
            try {

                System.out.println(start);
                GeneralSQL.getConnection();

                System.out.println(start);

                PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT TestGames3.Title, Consoles.Console, SUM(OrderDetails.Quantity) AS 'Total Sold', SUM(OrderDetails.Total) AS 'Total Earned', OrderDate FROM OrderDetails "
                        + "JOIN TestGames3 ON TestGames3.GameID = OrderDetails.InventoryID JOIN Consoles ON TestGames3.ConsoleID = Consoles.ConsoleID JOIN Orders ON OrderDetails.OrderID = Orders.OrderID "
                        + "WHERE OrderDate BETWEEN \"" + start + "\" AND \"" + end + "\" "
                        + "GROUP BY Orders.OrderDate, TestGames3.GameID "
                        + "ORDER BY Orders.OrderDate");

                ResultSet rs = stmt.executeQuery();

                Report salesReport = new Report(rs, false, false);

                con.close();

            } catch (SQLException ex) {
                System.out.println(ex);
                System.out.println("Cant make report");

            }

        }

    }

    /**
     * Creates a sum for the daily total of the sales report
     * 
     * @param day
     * The date of the sales
     * 
     * @param specific
     * Wether or not its a specific user
     * 
     * @return the sum of the daily sales
     * 
     */
    public static float dailyCheck(LocalDate day, boolean specific) {

        try {

            //Get connection
            GeneralSQL.getConnection();

            PreparedStatement stmt = null;
            
            //Check if its a specified user and then get the sum of the daily earnings/spending
            if (!specific) {
                stmt = GeneralSQL.con.prepareStatement("SELECT SUM(Total) FROM Orders WHERE OrderDate = \"" + day.toString() + "\" GROUP BY OrderDate");
            } else {
                stmt = GeneralSQL.con.prepareStatement("SELECT SUM(Total) FROM Orders WHERE UserID = " + Variables.customerID + " AND OrderDate = \"" + day.toString() + "\" GROUP BY OrderDate");
            }

            ResultSet rs = stmt.executeQuery();

            rs.next();
            
            //save total to return
            float newFloat = rs.getFloat(1);

            //close connection
            con.close();

            return newFloat;

        } catch (SQLException ex) {
            System.out.println(ex);

            return 0.00f;

        }

    }

    /**
     * generates a weekly total for the sales report
     * 
     * @param start
     * Start date
     * 
     * @param end
     * End date
     * 
     * @param specific
     * If it is a specific user or not
     * 
     * @return The weekly total
     */
    public static float weekCheck(LocalDate start, LocalDate end, boolean specific) {

        try {

            //get connection
            GeneralSQL.getConnection();

            PreparedStatement stmt = null;

            //Check if its a specified user and then get the sum of the weekly earnings/spending
            if (!specific) {

                stmt = GeneralSQL.con.prepareStatement("SELECT SUM(Total) FROM Orders WHERE OrderDate BETWEEN \"" + start + "\" AND \"" + end + "\"");

            } else {

                stmt = GeneralSQL.con.prepareStatement("SELECT SUM(Total) FROM Orders WHERE UserID = " + Variables.customerID + " AND OrderDate BETWEEN \"" + start + "\" AND \"" + end + "\"");

            }

            ResultSet rs = stmt.executeQuery();

            rs.next();

            System.out.println("Weekly earnings: " + rs.getFloat(1));
            
            //Save total
            float newFloat = rs.getFloat(1);

            //Close connection
            con.close();

            return newFloat;

        } catch (SQLException ex) {
            System.out.println(ex);

            return 0.00f;

        }

    }

    /**
     * Generates a monthly total for the sales report
     * 
     * @param start
     * Start date
     * 
     * @param end
     * End date
     * 
     * @param specific
     * If it is a specific user or not
     * 
     * @return The monthly total
     */
    public static float monthCheck(LocalDate start, LocalDate end, boolean specific) {

        try {
            
            //Get connection
            GeneralSQL.getConnection();

            PreparedStatement stmt = null;

            //Check if its a specified user and then get the sum of the monthly earnings/spending
            if (!specific) {

                stmt = GeneralSQL.con.prepareStatement("SELECT SUM(Total) FROM Orders WHERE OrderDate BETWEEN \"" + start + "\" AND \"" + end + "\"");

            } else {

                stmt = GeneralSQL.con.prepareStatement("SELECT SUM(Total) FROM Orders WHERE UserID = " + Variables.customerID + " AND OrderDate BETWEEN \"" + start + "\" AND \"" + end + "\"");

            }
            ResultSet rs = stmt.executeQuery();

            rs.next();
            
            //Save total
            float newFloat = rs.getFloat(1);

            //Close connection
            con.close();

            return newFloat;

        } catch (SQLException ex) {
            System.out.println(ex);

            return 0.00f;

        }

    }

    /**
     * Generates a yearly total for the sales report
     * 
     * @param start
     * Start date
     * 
     * @param end
     * End date
     * 
     * @param specific
     * If it is a specific user or not
     * 
     * @return The yearly total
     */
    public static float yearCheck(LocalDate start, LocalDate end, boolean specific) {

        try {

            //Get connection
            GeneralSQL.getConnection();

            PreparedStatement stmt = null;

            //Check if its a specified user and then get the sum of the yearly earnings/spending
            if (!specific) {

                stmt = GeneralSQL.con.prepareStatement("SELECT SUM(Total) FROM Orders WHERE OrderDate BETWEEN \"" + start + "\" AND \"" + end + "\"");

            } else {

                stmt = GeneralSQL.con.prepareStatement("SELECT SUM(Total) FROM Orders WHERE UserID = " + Variables.customerID + " AND OrderDate BETWEEN \"" + start + "\" AND \"" + end + "\"");

            }
            ResultSet rs = stmt.executeQuery();

            rs.next();

            //save total
            float newFloat = rs.getFloat(1);

            //Close connection
            con.close();

            return newFloat;

        } catch (SQLException ex) {
            System.out.println(ex);

            return 0.00f;

        }

    }

}
