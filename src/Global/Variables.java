/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Global;

/**
 *
 * @author Karson
 * 
 * A class to host variables that will be needed throughout the program
 */
public class Variables {
    
    //static varaibles to host things we need at a global scale or may need at a global scale
    
    //this variable is used to host the ID of the current user logged in
    public static int userID = 0;
    
    //we need to keep a customer ID so that the manager can buy for a customer
    public static int customerID = 0;
    
    //we need a string to also keep track of the current level so that when a amanger has different functionality when buying for a customer than a customer
    //it will be small differences mostly if not only, but its something that is very cirtical to the program
    public static String currentLevel = "";
    
    //a variable used to store the game selected index of lists
    public static int chosenGame = -1;
    
    //Stores discount code
    public static String selectedCode = "";
    
    //The current user or manager of the program
    public static String currentUser = "";
    public static String currentManager = "";
    
}
