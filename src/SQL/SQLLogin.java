package SQL;

import java.sql.*;
import java.util.ArrayList;

import Global.Variables;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Karson
 * 
 * A class to handle SQL related methods that have to do with users or logging in
 */
public class SQLLogin extends GeneralSQL {

    //In this class we will create methods that will aid us in logging in and creating an account
    //Basically we just need to make it and call it when we need it
    //LOGIN
    
    /**
     * This checks the username and password given
     * 
     * @param username
     * A string to represent the username
     * 
     * @param password
     * A string to represent the password
     * 
     * @return a String that represents the level of user
     */
    public static String checkCredentials(String username, String password) {

        String userLevel = "";

        try {
            
            //Get connection
            GeneralSQL.getConnection();

            //Start a query
            //con = DriverManager.getConnection(mySQLURL, myUsername, myPassword);
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT Level, Password, UserID, Enabled, Username FROM User WHERE Username = '" + username + "' AND Password = '" + password + "'");
            ResultSet rs = stmt.executeQuery();

            //Now check if it returns anyting
            
            //Whatever it returns represents the level of the user
            if (rs.next() && password.equals(rs.getString(2)) && rs.getInt(4) == 1) {

                userLevel = rs.getString(1);
                
                //also assign the userID to a global variable
                //this is so we know whos logged in on this application and can act accordingly
                Variables.userID = rs.getInt(3);
                
                Variables.currentLevel = userLevel;
                
                if(userLevel.equals("Manager")){
                
                    Variables.currentManager = rs.getString(5);
                
                }else{
                
                    Variables.currentUser = rs.getString(5);
                    Variables.customerID = rs.getInt(1);
                    
                    SQLStore.createCart(Variables.userID);
                
                }

            } else {

                userLevel = "None";
            }

            //Close connection
            con.close();

        } catch (SQLException ex) {

            System.out.println(ex);
            userLevel = "ERROR";

        }

        return userLevel;

    }

    //CREATING ACCOUNT
    /**
     * This is used to check if the username already exists
     * 
     * @param username
     * A string that represents the desired username
     * 
     * @return A string that answers if the username exists or not
     */
    public static String checkUsername(String username) {

        String status = "";
        //This will help us keep track of the status

        try {
            
            //Get connection
            GeneralSQL.getConnection();

            //con = DriverManager.getConnection(mySQLURL, myUsername, myPassword);
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT * FROM User WHERE Username = '" + username + "';");
            ResultSet rs = stmt.executeQuery();

            //Now check if it returns anyting
            if (rs.next()) {

                //If we return any rows this will be true
                //This means the username exists
                status = "Found";

            } else {

                //If it doesnt return then we are free to make an account
                status = "Not";
                //we will verify things of the password and other information in the form
            }

            //Close the connection
            con.close();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return status;

    }

    /**
     * This fetches the security questions from the database
     * 
     * @param setOfQuestions
     * Used to get a more specific question set
     * 
     * @return The questions of the set in the form of an array list
     */
    public static ArrayList<String> questionSet(int setOfQuestions) {

        ArrayList<String> Questions = new ArrayList<String>();
        //This is used to store the questions of the set

        try {
            
            //Get connection
            GeneralSQL.getConnection();

            //con = DriverManager.getConnection(mySQLURL, myUsername, myPassword);
            //Get the questions
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT Question FROM SecurityQuestions WHERE SetID = " + setOfQuestions + ";");
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();

            //Now check if it returns anyting
            while (rs.next()) {

                Questions.add(rs.getString(1));

            }

            //Close connection
            con.close();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return Questions;

    }

    /**
     * Like the questionSet method this is used to get a more specific question. This is used for resetting the password
     * 
     * @param questionNum
     * the number of question out of 3
     * 
     * @param username
     * the username associated with the question
     * 
     * @return The question in the form of a string
     */
    public static String getQuestion(int questionNum, String username) {

        //This will be similair to the QuestionSet but only return the one question needed
        String question = "";

        try {
            
            //Get connection
            GeneralSQL.getConnection();

            //con = DriverManager.getConnection(mySQLURL, myUsername, myPassword);
            
            //Fetch the question
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT SQ" + questionNum + " FROM User WHERE Username = '" + username + "';");
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();

            //Now check if it returns anyting
            while (rs.next()) {

                question = rs.getString(1);

            }

            //Close connection
            con.close();

        } catch (SQLException ex) {

            System.out.println(ex);

        }
        return question;

    }

    /**
     * This method uses the username and answers given to find the right user and change the password
     * 
     * @param password
     * The string that represents the password
     * 
     * @param username
     * The string that represents the username
     * 
     * @param A1
     * The string that represents the answer for question 1
     * 
     * @param A2
     * The string that represents the answer for question 2
     * 
     * @param A3
     * The string that represents the answer for question 3
     * 
     * @return the result of the password change in the form of a string
     */
    public static String changePassword(String password, String username, String A1, String A2, String A3) {
        
        String status = "";

        try {
            
            //Get connection
            GeneralSQL.getConnection();

            //con = DriverManager.getConnection(mySQLURL, myUsername, myPassword);

            //The first thing we should do is run a query to check if we have a result
            PreparedStatement stmt1 = GeneralSQL.con.prepareStatement("SELECT * FROM User WHERE Username = '" + username
                    + "' AND Answer1 = '" + A1 + "' AND Answer2 = '" + A2 + "' AND Answer3 = '" + A3 + "';");
            ResultSet rs = stmt1.executeQuery();

            if (rs.next()) {

                PreparedStatement stmt2 = GeneralSQL.con.prepareStatement("UPDATE User SET Password = '" + password + "' WHERE Username = '" + username
                        + "' AND Answer1 = '" + A1 + "' AND Answer2 = '" + A2 + "' AND Answer3 = '" + A3 + "';");

                stmt2.execute();
                
                //Ok after it executes chege the status
                status = "Changed";
            }else{
            
                status = "Not";
            
            }

            //Close connection
            con.close();

        } catch (SQLException ex) {
            
            status = "";

            System.out.println(ex);

        }
        
        return status;

    }

    /**
     * A method to create the account
     * 
     * @param username
     * The string that represents the desired username
     * 
     * @param password
     * The string that represents the password
     * 
     * @param fName
     * The string that represents the first name
     * 
     * @param lName
     * The string that represents the last name
     * 
     * @param address1
     * The string that represents the first line of the address
     * 
     * @param address2
     * The string that represents the second line of the address
     * 
     * @param address3
     * The string that represents the third line of the address
     * 
     * @param city
     * The string that represents the city
     * 
     * @param state
     * The string that represents the state
     * 
     * @param zip
     * The string that represents the zip
     * 
     * @param phone
     * The string that represents the phone number
     * 
     * @param email
     * The string that represents the email
     * 
     * @param q1
     * The first chosen question
     * 
     * @param q2
     * The second chosen question
     * 
     * @param q3
     * The third chosen question
     * 
     * @param a1
     * The answer to question 1
     * 
     * @param a2
     * The answer to question 2
     * 
     * @param a3
     * The answer to question 3
     * 
     * @param level
     * The level of the user
     * 
     * @return A string that represents the result
     */
    public static String createAccount(String username, String password, String fName, String lName, String address1, String address2, String address3,
            String city, String state, String zip, String phone, String email, String q1, String q2, String q3, String a1, String a2,
            String a3, String level) {

        try {
            
            //Get connection
            GeneralSQL.getConnection();
            
            con = DriverManager.getConnection(mySQLURL, myUsername, myPassword);
            
            //first get the previous id
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT UserID FROM User");
            ResultSet rs = stmt.executeQuery();
            
            //This is to get a temporary ID since SQLite has trouble with autoincrementing
            int tempID = 0;
            
            while(rs.next()){
            
                tempID = rs.getInt(1);
                
            }
            
            stmt = GeneralSQL.con.prepareStatement("INSERT INTO User VALUES(" + (tempID + 1) + ", \"" + level + "\", \"" + username + "\", \"" + password + "\", "
                    + "\"" + fName + "\", \"" + lName + "\", \"" + address1 + "\", \"" + address2 + "\", \"" + address3 + "\", \"" + city + "\", \"" + state + "\", \""
                    + zip + "\", \"" + phone + "\", \"" + email + "\", \"" + q1 + "\", \"" + q2 + "\", \"" + q3 + "\", \"" + a1 + "\", \"" + a2 + "\", \"" + a3 + "\", 1);");
            stmt.execute();
            
            //Close connection
            con.close();
            
            return "Account Created";
        } catch (SQLException ex) {

            System.out.println(ex);
            return "Account Not Created";

        }

    }

}
