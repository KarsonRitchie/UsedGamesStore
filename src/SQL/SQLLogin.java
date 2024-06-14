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
 * @author karso
 */
public class SQLLogin extends GeneralSQL {

    //In this class we will create methods that will aid us in logging in and creating an account
    //Basically we just need to make it and call it when we need it
    //LOGIN
    public static String checkCredentials(String username, String password) {

        String userLevel = "";

        try {
            
            GeneralSQL.getConnection();

            //con = DriverManager.getConnection(mySQLURL, myUsername, myPassword);
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT Level, Password, UserID, Enabled, Username FROM User WHERE Username = '" + username + "' AND Password = '" + password + "'");
            ResultSet rs = stmt.executeQuery();

            //Now check if it returns anyting
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
                
                }

            } else {

                userLevel = "None";
            }

            //con.close();

        } catch (SQLException ex) {

            System.out.println(ex);
            userLevel = "ERROR";

        }

        return userLevel;

    }

    //CREATING ACCOUNT
    //First we need to check if the username exists
    public static String checkUsername(String username) {

        String status = "";
        //This will help us keep track of the status

        try {
            
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
                //we will verify things of the password and other ifnormation in the form
            }

            //con.close();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return status;

    }

    public static ArrayList<String> questionSet(int setOfQuestions) {

        ArrayList<String> Questions = new ArrayList<String>();

        try {
            
            GeneralSQL.getConnection();

            //con = DriverManager.getConnection(mySQLURL, myUsername, myPassword);
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT Question FROM SecurityQuestions WHERE SetID = " + setOfQuestions + ";");
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();

            //Now check if it returns anyting
            while (rs.next()) {

                Questions.add(rs.getString(1));

            }

            //con.close();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return Questions;

    }

    public static String getQuestion(int questionNum, String username) {

        //This will be similair to the QuestionSet but only return the one question needed
        String question = "";

        try {
            
            GeneralSQL.getConnection();

            //con = DriverManager.getConnection(mySQLURL, myUsername, myPassword);
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT SQ" + questionNum + " FROM User WHERE Username = '" + username + "';");
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();

            //Now check if it returns anyting
            while (rs.next()) {

                question = rs.getString(1);

            }

            //con.close();

        } catch (SQLException ex) {

            System.out.println(ex);

        }
        return question;

    }

    public static String changePassword(String password, String username, String A1, String A2, String A3) {
        
        String status = "";

        try {
            
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

            //con.close();

        } catch (SQLException ex) {
            
            status = "";

            System.out.println(ex);

        }
        
        return status;

    }

    public static String createAccount(String username, String password, String fName, String lName, String address1, String address2, String address3,
            String city, String state, String zip, String phone, String email, String q1, String q2, String q3, String a1, String a2,
            String a3, String level) {

        try {
            
            GeneralSQL.getConnection();
            
            //con = DriverManager.getConnection(mySQLURL, myUsername, myPassword);
            
            //first get the previous id
            PreparedStatement stmt = GeneralSQL.con.prepareStatement("SELECT UserID FROM User");
            ResultSet rs = stmt.executeQuery();
            
            int tempID = 0;
            
            while(rs.next()){
            
                tempID = rs.getInt(1);
                
            }
            
            stmt = GeneralSQL.con.prepareStatement("INSERT INTO User VALUES(" + (tempID + 1) + ", \"" + level + "\", \"" + username + "\", \"" + password + "\", "
                    + "\"" + fName + "\", \"" + lName + "\", \"" + address1 + "\", \"" + address2 + "\", \"" + address3 + "\", \"" + city + "\", \"" + state + "\", \""
                    + zip + "\", \"" + phone + "\", \"" + email + "\", \"" + q1 + "\", \"" + q2 + "\", \"" + q3 + "\", \"" + a1 + "\", \"" + a2 + "\", \"" + a3 + "\", 1);");
            stmt.execute();
            return "Account Created";
            //con.close();
        } catch (SQLException ex) {

            System.out.println(ex);
            return "Account Not Created";

        }

    }

}
