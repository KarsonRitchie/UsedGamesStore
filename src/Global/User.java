/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Global;

import SQL.SQLManager;

/**
 *
 * @author Karson
 * 
 * A class to create and store information of users
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
