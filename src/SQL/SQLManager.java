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
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author karso
 */
public class SQLManager extends GeneralSQL {

    //this method will be used to create a list of users
    public static void loadUsers() {

        Lists.users.clear();

        try {
            
            GeneralSQL.getConnection();

            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT Level, UserID, FirstName, LastName, Email, Phone, AddressLine1, AddressLine2, AddressLine3, City, State, Zipcode, Username, Password, Enabled "
                    + "FROM User");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                //Make the objects
                User newUser = new User(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getInt(15));

                //add the object to the list
                Lists.users.add(newUser);

                //System.out.println(rs.getString(1));
                //System.out.println(rs.getString(2));
            }

        } catch (SQLException ex) {

            System.out.println("Cant load Users");
            System.out.println(ex);

        }

    }

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

    public static boolean saveUser(User user) {

        try {
            
            GeneralSQL.getConnection();

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
                    + "PhoneNumber = '" + user.phoneNum + "', "
                    + "Email = '" + user.userEmail + "', "
                    + "Enabled = '" + user.enabled + "' "
                    + "WHERE UserID = " + user.userID);

            stmt.execute();

            return true;

        } catch (SQLException ex) {

            System.out.println("Cant save user");
            System.out.println(ex);

            return false;

        }

    }

    public static void retrieveOrders(int userID) {

        Lists.orders.clear();

        PreparedStatement stmt;

        try {

            GeneralSQL.getConnection();
            
            stmt = GeneralSQL.con.prepareStatement("SELECT OrderID, OrderDate, DiscountID, DiscountedOff "
                    + "FROM Orders "
                    + "WHERE UserID = " + userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                int discountID = 0;

                if (rs.getInt(3) > 0) {

                    discountID = rs.getInt(3);

                }

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

                newOrder.finalizeData();

                //and now we add it to the orders list
                Lists.orders.add(newOrder);

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void loadDiscounts() throws ParseException {

        Lists.discounts.clear();

        try {
            
            GeneralSQL.getConnection();

            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT DiscountID, DiscountCode, Description, DiscountType, DiscountPercent, DiscountDollar, StartDate, EndDate, Active, DiscountLevel, GameTitle FROM Discounts");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                //Check what the discount type is
                //this will help determine what amount we need
                if (rs.getInt(4) == 0) {

                    Discount newDiscount = new Discount(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11));

                    Lists.discounts.add(newDiscount);
                    
                    //System.out.println(rs.getString(7));

                } else {

                    Discount newDiscount = new Discount(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11));

                    Lists.discounts.add(newDiscount);
                    //System.out.println(rs.getString(7));
                    

                }

                //System.out.println(rs.getString(1));
                //System.out.println(rs.getString(2));
            }

        } catch (SQLException ex) {

            System.out.println("Cant load Discounts");
            System.out.println(ex);

        }

    }

    public static boolean saveDiscount(Discount discount) {

        try {
            
            GeneralSQL.getConnection();

            if(discount.discountType == 0){
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("UPDATE Discounts SET DiscountCode = \"" + discount.discountCode.toUpperCase() + "\", "
                    + "Active = " + discount.active + ", Description = \"" + discount.description + "\", DiscountPercent = " + discount.discountAmount + " "
                    + "WHERE DiscountID = " + discount.discountID + ";");

            stmt.execute();
            }else{
            
                PreparedStatement stmt = GeneralSQL.con.prepareStatement("UPDATE Discounts SET DiscountCode = \"" + discount.discountCode.toUpperCase() + "\", "
                    + "Active = " + discount.active + ", Description = \"" + discount.description + "\", DiscountDollar = " + discount.discountAmount + " "
                    + "WHERE DiscountID = " + discount.discountID + ";");

            stmt.execute();
            
            }

            return true;

        } catch (SQLException ex) {

            System.out.println("Cant save discount");
            System.out.println(ex);

            return false;

        }

    }

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

    public static boolean createDiscount(int type, String code, String description, float amount, String startDate, String endDate, int isActive, String discountLevel, String title) {

        try {
            
            GeneralSQL.getConnection();

            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            int idNum = 0;
            
            stmt = GeneralSQL.con.prepareStatement("SELECT DiscountID FROM Discounts");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                idNum = rs.getInt(1);
            
            }
            
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
            return true;

        } catch (SQLException ex) {

            System.out.println("Cant create Discount");
            System.out.println(ex);
            return false;

        }

    }
    
    public static String checkDiscount(String code){
    
        
        try {
            
            GeneralSQL.getConnection();

            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT DiscountCode FROM Discounts WHERE DiscountCode = \"" + code + "\"");
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
            
                return "Discount exists";
                
            }else{
            
                return "Discount doesnt exist";
                
            }

        } catch (SQLException ex) {
            
            System.out.println(ex);
            return "ERROR";

        }
        
    }

    public static boolean saveGame(Game game) {

        try {
            
            GeneralSQL.getConnection();

            PreparedStatement stmt = GeneralSQL.con.prepareStatement("UPDATE TestGames3 SET Title = \"" + game.name + "\", "
                    + "Price = \"" + String.format("%.2f", game.price) + "\", "
                    + "Quantity = " + game.quantity + ", "
                    + "Description = \"" + game.description + "\", "
                    + "RestockThreshold = " + game.restock + ", "
                    + "IsEnabled = " + game.active + " "
                    + "WHERE GameID = " + game.gameID);

            stmt.execute();
            return true;

        } catch (SQLException ex) {

            System.out.println("Cant save game");
            System.out.println(ex);
            
            return false;

        }

    }

    public static boolean addGame(String title, float price, String genre, String console, int quantity, String description, int restock, int isActive) {

        try {
            
            GeneralSQL.getConnection();
            ResultSet rs = null;
            
            int idNum = 0;
            
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT GameID FROM TestGames3");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                idNum = rs.getInt(1);
            
            }

            stmt = GeneralSQL.con.prepareStatement("INSERT INTO TestGames3 VALUES(" + (idNum + 1) + ", \"" + title + "\", "
                    + String.format("%.2f", price) + ", "
                    + "(SELECT GenreID FROM Genres WHERE Genre = \"" + genre + "\"), (SELECT ConsoleID FROM Consoles WHERE Console = \"" + console + "\"), " + quantity + ", "
                    + "\"" + description + "\", " + restock + ", " + isActive + ")");

            stmt.execute();
            return true;

        } catch (SQLException ex) {

            System.out.println("Cant create Game");
            System.out.println(ex);
            return false;

        }

    }
    
    public static String checkGame(String title, String system){
    
        try {
            
            GeneralSQL.getConnection();

            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT Title, Consoles.Console FROM TestGames3 "
                    + "JOIN Consoles ON TestGames3.ConsoleID = Consoles.ConsoleID "
                    + "WHERE Title = \"" + title + "\" AND Console = \"" + system +"\"");

            ResultSet rs = stmt.executeQuery();
            
            
            if(rs.next()){
            
                return "Exists";
            
            }else{
            
                return "No";
            
            }

        } catch (SQLException ex) {

            System.out.println("Cant search for game");
            System.out.println(ex);
            return "ERROR";

        }
    
    }

    public static int recieveID(String title, String system) {

        try {
            
            GeneralSQL.getConnection();

            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT GameID FROM TestGames3 "
                    + "WHERE Title = \"" + title + "\" AND ConsoleID = (SELECT ConsoleID FROM Consoles WHERE Console = \"" + system + "\")");

            ResultSet rs = stmt.executeQuery();

            rs.next();

            return rs.getInt(1);

        } catch (SQLException ex) {

            return 0;

        }

    }

    public static boolean deleteGame(int gameID) {

        try {

            PreparedStatement stmt = GeneralSQL.con.prepareStatement("DELETE FROM TestGames3 WHERE GameID = " + gameID);
            stmt.execute();
            return true;

        } catch (SQLException ex) {

            System.out.println("Cant delete game");
            System.out.println(ex);
            return false;

        }

    }

    public static void inventoryReport() throws Exception {

        try {
            
            GeneralSQL.getConnection();

            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT Title, Price, Consoles.Console, Quantity, RestockThreshold, IsEnabled "
                    + "FROM TestGames3 "
                    + "JOIN Consoles ON TestGames3.ConsoleID = Consoles.ConsoleID");
            ResultSet rs = stmt.executeQuery();

            Report inventoryReport = new Report(rs, true, false);

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    public static void salesReport(boolean specific, String start, String end) throws Exception {

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

            } catch (SQLException ex) {
                System.out.println(ex);
                System.out.println("Cant make report");

            }

        }

    }

    public static float dailyCheck(LocalDate day, boolean specific) {

        try {
            
            GeneralSQL.getConnection();

            PreparedStatement stmt = null;
            if (!specific) {
                stmt = GeneralSQL.con.prepareStatement("SELECT SUM(Total) FROM Orders WHERE OrderDate = \"" + day.toString() + "\" GROUP BY OrderDate");
            } else {
                stmt = GeneralSQL.con.prepareStatement("SELECT SUM(Total) FROM Orders WHERE UserID = " + Variables.customerID + " AND OrderDate = \"" + day.toString() + "\" GROUP BY OrderDate");
            }

            ResultSet rs = stmt.executeQuery();

            rs.next();

            return rs.getFloat(1);

        } catch (SQLException ex) {
            System.out.println(ex);

            return 0.00f;

        }

    }

    public static float weekCheck(LocalDate start, LocalDate end, boolean specific) {

        try {
            
            GeneralSQL.getConnection();

            PreparedStatement stmt = null;
            
            if(!specific){
                
                stmt = GeneralSQL.con.prepareStatement("SELECT SUM(Total) FROM Orders WHERE OrderDate BETWEEN \"" + start + "\" AND \"" + end + "\"");
                
            }else{
            
                stmt = GeneralSQL.con.prepareStatement("SELECT SUM(Total) FROM Orders WHERE UserID = " + Variables.customerID + " AND OrderDate BETWEEN \"" + start + "\" AND \"" + end + "\"");
                
            }
            
            ResultSet rs = stmt.executeQuery();

            rs.next();

            System.out.println("Weekly earnings: " + rs.getFloat(1));

            return rs.getFloat(1);

        } catch (SQLException ex) {
            System.out.println(ex);

            return 0.00f;

        }

    }

    public static float monthCheck(LocalDate start, LocalDate end, boolean specific) {

        try {
            
            GeneralSQL.getConnection();

            PreparedStatement stmt = null;
            
            if(!specific){
                
                stmt = GeneralSQL.con.prepareStatement("SELECT SUM(Total) FROM Orders WHERE OrderDate BETWEEN \"" + start + "\" AND \"" + end + "\"");
                
            }else{
            
                stmt = GeneralSQL.con.prepareStatement("SELECT SUM(Total) FROM Orders WHERE UserID = " + Variables.customerID + " AND OrderDate BETWEEN \"" + start + "\" AND \"" + end + "\"");
                
            }
            ResultSet rs = stmt.executeQuery();

            rs.next();

            return rs.getFloat(1);

        } catch (SQLException ex) {
            System.out.println(ex);

            return 0.00f;

        }

    }

    public static float yearCheck(LocalDate start, LocalDate end, boolean specific) {

        try {
            
            GeneralSQL.getConnection();

            PreparedStatement stmt = null;
            
            if(!specific){
                
                stmt = GeneralSQL.con.prepareStatement("SELECT SUM(Total) FROM Orders WHERE OrderDate BETWEEN \"" + start + "\" AND \"" + end + "\"");
                
            }else{
            
                stmt = GeneralSQL.con.prepareStatement("SELECT SUM(Total) FROM Orders WHERE UserID = " + Variables.customerID + " AND OrderDate BETWEEN \"" + start + "\" AND \"" + end + "\"");
                
            }
            ResultSet rs = stmt.executeQuery();

            rs.next();

            return rs.getFloat(1);

        } catch (SQLException ex) {
            System.out.println(ex);

            return 0.00f;

        }

    }

}
