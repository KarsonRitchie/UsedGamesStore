/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author karso
 */
//This class will be used as a superclass
//It will have a default query run for any random queries we may need either for testing or in use but doesnt need its own method
//It will also host the connection varibles and methods
public abstract class GeneralSQL {

    //First lets create the connection to the SQL table
    static String mySQLURL = "jdbc:sqlite:UsedGamesData.sqlite";
    static String myPassword = "UltraKill#2023";
    static String myUsername = "RichieKarson24SP233x";

    static Connection con = null;

    //Create a method to start the connection
    public static void startConnection() throws ClassNotFoundException {
        try {

            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(mySQLURL);
            System.out.println(con.toString());

            System.out.println("Connection secured");

        } catch (SQLException ex) {

            con = null;

            System.out.println(ex);

            //A while loop to secure the connection
            while (con == null) {

                try {

                    con = DriverManager.getConnection(mySQLURL);

                    System.out.println("Connection secured");

                } catch (SQLException ex2) {

                    con = null;

                    System.out.println(ex);
                }

            }
        }
    }
    
    //This will be running in the background to constantly check the connection
    public static void getConnection() {
        try {

            con = DriverManager.getConnection(mySQLURL);

            System.out.println("Connection secured");
            

        } catch (SQLException ex) {

            con = null;

            System.out.println("Connection failed");

            //A while loop to secure the connection
            while (con == null) {

                try {

                    con = DriverManager.getConnection(mySQLURL);

                    System.out.println("Connection secured");
                   

                } catch (SQLException ex2) {

                    con = null;

                    System.out.println(ex);
                }

            }
        }
    }
}

