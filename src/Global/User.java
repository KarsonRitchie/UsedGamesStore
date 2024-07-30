/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Global;

import SQL.SQLManager;

/**
 * This class is meant to be for users. Each instance of this class is another user in the system.
 * 
 * <br><br>
 * This will contain a lot more personal information like their address, email, and phone number. It even has their own names.
 * 
 * <br>
 * Now not all users will have emails since that is not required, but everything else is required.
 * 
 * <br><br>
 * It is also important to know that each part of the address like the 3 lines, state, zip, and city are all separate. That is how they are in the database as well, 
 * and displaying them all in one part will require putting them into a string. However due to this we can display them separately in individual fields to help 
 * with creation and editing.
 * 
 * <br><br>
 * This class will also contain the user level, which is whether they are a manager or customer. It also has things like their ID from the database 
 * and more importantly their username and password.
 * 
 * <br><br>
 * Also there is an active state represented by an integer and if a user is inactive they will not be able to log in. It is meant to be in place of deletion 
 * because deleting a user from the database will cause massive issues if it can even be done due to them being able to order from the store.
 * 
 * <br><br>
 * This class also has the ability to save itself to the database and save the date in itself.
 * 
 */
public class User {
    
    //Variables that store specifric information of a user
    public String userLevel = "";
    public int userID = 0;
    public String userFirstName = "";
    public String userLastName = "";
    public String userEmail = "";
    public String phoneNum = "";
    public String address1 = "";
    public String address2 = "";
    public String address3 = "";
    public String city = "";
    public String state = "";
    public String zip = "";
    public String userName = "";
    public String password = "";
    public int enabled = 0;

    /**
     * This is a constructor for a user
     * 
     * @param userLevel
     * The level of the user
     * 
     * @param userID
     * The users ID from the database
     * 
     * @param userFirstName
     * First name of the user
     * 
     * @param userLastName
     * Last name of the user
     * 
     * @param userEmail
     * The user's email
     * 
     * @param phoneNum
     * The user's phone number
     * 
     * @param address1
     * The first line of the address
     * 
     * @param address2
     * The second line of the address
     * 
     * @param address3
     * The third line of the address
     * 
     * @param city
     * The city where the user resides in
     * 
     * @param state
     * The state the user resides in
     * 
     * @param zip
     * The user's zip code
     * 
     * @param userName
     * The user's unique username
     * 
     * @param password
     * The user's password
     * 
     * @param enabled
     * An integer that shows wether the user is active or not (0 for inactive, 1 for active)
     */
    public User(String userLevel, int userID, String userFirstName, String userLastName, String userEmail, String phoneNum, String address1, String address2, String address3, String city, String state, String zip, String userName, String password, int enabled) {
    
        this.userLevel = userLevel;
        this.userID = userID;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.phoneNum = phoneNum;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.userName = userName;
        this.password = password;
        
        //we need to know if it enabled or not as well
        //i think we should use 0 and 1 to show so its a lot easier to write back to the database
        this.enabled = enabled;
    
    }
    
    /**
     * Saves information of the specific user object edited
     * 
     * @param userFirstName
     * First name
     * 
     * @param userLastName
     * Last name
     * 
     * @param userEmail
     * Email
     * 
     * @param phoneNum
     * Phone number
     * 
     * @param address1
     * The first line of the address
     * @param address2
     * The second line of the address
     * @param address3
     * The third line of the address
     * 
     * @param city
     * City of residence
     * 
     * @param state
     * State of residence
     * 
     * @param zip
     * User's zip
     * 
     * @param userName
     * Username
     * 
     * @param password
     * Password
     * 
     * @param enabledCheck
     * Wether the user is enabled or not (disabled users can not use the program)
     */
    public void save(String userFirstName, String userLastName, String userEmail, String phoneNum, String address1, String address2, String address3, String city, String state, String zip, String userName, String password, boolean enabledCheck){
    
        //Lets rewrite what we have
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.phoneNum = phoneNum;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.userName = userName;
        this.password = password;
        
        //now write the enabled
        if(enabledCheck){
        
            enabled = 1;
            
        }else{
        
            enabled = 0;
        
        }
    }
    
    /**
     * Saves the specific user object information to the database
     * 
     * @return  True if save was successful
     */
    public boolean databaseSave(){
    
        //Save the user to the database
        return SQLManager.saveUser(this);
    
    }
    
}
