/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Global;

import SQL.SQLLogin;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

//Import other packages here
//we need to import other pages to help call on display methods
import Customer.StorePage;
import Manager.ManagerView;
import SQL.SQLManager;

/**
 *
 * @author karso
 */
public class Methods {

    //For some methods we may need a variable or two
    //we will store them here as long as there is not too many and it stays only needed for methods needed globally
    //Variables may change in other pages, but all that is needed is to keep track of information for these methods to work properly
    public static String currentPage = "None";

    //This is to store methods we may use on a global scale
    //Folr example there may be many packages that need the password to be checked and if they do we just import this apckage
    //Then use the method
    public static String getPassword(char[] password) {

        String SPass = "";

        //We will go through the list and create the password
        for (int x = 0; x < password.length; x++) {

            SPass += password[x];

        }

        System.out.println(SPass);

        return SPass;

    }

    //These gloabal methods are for input validation
    //We mayh use these in a lot of cases so lets make them global
    public static ArrayList<String> checkUsername(String username, boolean userCheck, boolean compare) {

        ArrayList<String> allErrors = new ArrayList<String>();

        //USERNAME CHECK
        //Check if it exists
        String userExists;

        if (compare) {

            if (username.equalsIgnoreCase(Variables.currentUser)) {

                return allErrors;
                
            }

        }

        if (userCheck == true) {

            userExists = SQLLogin.checkUsername(username);

        } else {

            //Since we dont need to check for usenrame yet, just validate it like normally
            userExists = "Not";

        }
        //we want to check if the username exists
        //But only when the form is submitted
        //Each letter causing a database request can cause some issues

        if (userExists.equals("Found")) {

            allErrors.add("Username is already taken");

            //Make sure we add spaces to spread out errors appropiatly
            allErrors.add("");

        } else if (userExists.equals("Not")) {

            //Now we check for the specifics of the username and validate it
            //Some of it is similair to the password so it can be copy and pasted and edited accordingly
            //In this else statement create a new if else
            //this test if there is anything in the username field and if so then it will validate the data inside
            //Sure there are going to be a lot of potential errors, but its best to limit them as much as possible
            //no sense in validating data that does not exist yet
            if (username.equals("")) {

                allErrors.add("Username is required");

                allErrors.add("");

            } else {

                //There is data in the username field, validate the data accordingly
                //Since in the program in its current ste can only show one error for a certain field at a time
                //We should sort them by importance
                //This is so we display what should be changed first
                //With that in mind i think we should demonstrate what the fields should have and then show what they should
                //Now we check if it begins with a number
                Pattern numCharacter = Pattern.compile("^[0-9]");

                Matcher hasNumber = numCharacter.matcher(username);

                if (hasNumber.find() == true) {

                    allErrors.add("The username can not begin with a digit");

                }

                Pattern spaceCharacter = Pattern.compile(" ");

                Matcher hasSpace = spaceCharacter.matcher(username);

                if (hasSpace.find() == true) {

                    allErrors.add("The username should not have a space");

                }

                Pattern specialCharacter = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

                Matcher hasSpecial = specialCharacter.matcher(username);

                if (hasSpecial.find() == true) {

                    allErrors.add("The username should not have a special character");

                }

                //Now tell the user what it should have
                if (username.length() < 8 || username.length() > 20) {

                    allErrors.add("The username can only be 8-20 characters long");

                }

            }

        } else {

            allErrors.add("An error occured checking the username. Please resubmit to try again");

        }

        return allErrors;

    }

    public static ArrayList<String> checkPassword(String password) {

        ArrayList<String> allErrors = new ArrayList<String>();

        if (password.equals("")) {

            allErrors.add("Password is required");

            allErrors.add("");

        } else {

            //password is the same as username
            Pattern spaceCharacter = Pattern.compile(" ");

            Matcher hasSpace = spaceCharacter.matcher(password);

            if (hasSpace.find() == true) {

                allErrors.add("The password should not have a space");

            }

            if (password.length() < 8 || password.length() > 20) {

                allErrors.add("The password can only be 8-20 characters long");

            }

            //Now we check if it has a capital letter or a lowercase letter
            //To do thatI will use regex
            Pattern letterCharacter = Pattern.compile("[A-Za-z]");

            Matcher hasLetter = letterCharacter.matcher(password);

            if (hasLetter.find() != true) {

                allErrors.add("The password needs at least 1 capital or lowercase letter");

            }

            //and now check for a number
            Pattern numCharacter = Pattern.compile("[0-9]");

            Matcher hasNumber = numCharacter.matcher(password);

            if (hasNumber.find() != true) {

                allErrors.add("The password needs at least 1 digit");

            }

            //Keeping it seperate incase its just numbers
            //Now we will see if there are any special character
            Pattern specialCharacter = Pattern.compile("[!@#$%&*()\\^]");

            Matcher hasSpecial = specialCharacter.matcher(password);

            if (hasSpecial.find() != true) {

                allErrors.add("The password needs at least 1 special character");

            }

        }

        return allErrors;

    }

    public static ArrayList<String> checkName(String firstName, String lastName) {

        ArrayList<String> allErrors = new ArrayList<String>();

        if (firstName.equals("") || lastName.equals("")) {

            allErrors.add("First and Last Name are required");

        }

        return allErrors;

    }

    public static ArrayList<String> checkAddress(String address) {

        ArrayList<String> allErrors = new ArrayList<String>();

        if (address.equals("")) {

            allErrors.add("Address is required");

        }

        return allErrors;

    }

    public static ArrayList<String> checkCity(String city) {

        ArrayList<String> allErrors = new ArrayList<String>();

        if (city.equals("")) {

            allErrors.add("City is required");

        }

        return allErrors;

    }

    public static ArrayList<String> checkState(int chosenIndex) {

        ArrayList<String> allErrors = new ArrayList<String>();

        if (chosenIndex == 0) {

            allErrors.add("Please choose a state");

        }

        return allErrors;

    }

    public static ArrayList<String> checkZip(String zip) {

        ArrayList<String> allErrors = new ArrayList<String>();

        if (zip.equals("")) {

            allErrors.add("Zipcode is required");

        } else {

            //This zipcode error needs to be seperate so it runs either way
            //This will run to check if it has any letters
            Pattern zipPattern = Pattern.compile("^[0-9]{5}(?:-[0-9]{4})?$");
            Matcher zipMatcher = zipPattern.matcher(zip);

            if (!zipMatcher.find()) {

                allErrors.add("The zipcode you entered is not valid");

            }
        }

        return allErrors;

    }

    public static ArrayList<String> checkEmail(String email) {

        ArrayList<String> allErrors = new ArrayList<String>();

        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[^-^\\.][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher emailMatcher = emailPattern.matcher(email);

        //Now that we got the email able to be checked, lets first see if its blank
        //if its not blank look and see if the password is valid
        if (!email.equals("")) {

            //now check if its valid
            if (!emailMatcher.find()) {

                allErrors.add("Email is invalid");

            }

        }

        return allErrors;

    }

    public static ArrayList<String> checkPhone(String phone) {

        ArrayList<String> allErrors = new ArrayList<String>();

        Pattern phonePattern = Pattern.compile("^[0-9]{3}\\-[0-9]{3}\\-[0-9]{4}$");
        Matcher phoneMatcher = phonePattern.matcher(phone);

        if (phone.equals("")) {

            allErrors.add("Phone is required");

        } else if (!phoneMatcher.find()) {

            allErrors.add("Phone Number needs to be formatted as ###-###-####");

        }

        return allErrors;

    }

    public static ArrayList<String> checkQuestions(ArrayList<JComboBox> questions, ArrayList<JTextField> answers) {

        ArrayList<String> allErrors = new ArrayList<String>();

        for (int x = 0; x < questions.size(); x++) {

            if (questions.get(x).getSelectedIndex() == 0) {

                allErrors.add("Please select a question in each box");

                x = questions.size();

            }

        }

        //now see if any answers are left blank
        for (int x = 0; x < answers.size(); x++) {

            if (answers.get(x).getText().isBlank()) {

                allErrors.add("Please give an answer to all the questions");

                x = answers.size();

            }

        }

        return allErrors;

    }

    //These methods will be for checking the credit cards
    public static ArrayList<String> checkCardNum(String num) {

        ArrayList<String> allErrors = new ArrayList<String>();

        Pattern numPattern = Pattern.compile("^[0-9]{4}\\-[0-9]{4}\\-[0-9]{4}\\-[0-9]{4}$");
        Matcher numMatcher = numPattern.matcher(num);

        if (num.equals("")) {

            allErrors.add("Card Number is required");

        } else if (!numMatcher.find()) {

            allErrors.add("Card Number needs to be formatted as ####-####-####-####");

        }

        return allErrors;

    }

    public static ArrayList<String> checkCVV(String num) {

        ArrayList<String> allErrors = new ArrayList<String>();

        Pattern numPattern = Pattern.compile("^[0-9]{3}$");
        Matcher numMatcher = numPattern.matcher(num);

        if (num.equals("")) {

            allErrors.add("CVV is required");

        } else if (!numMatcher.find()) {

            allErrors.add("CVV is invalid");

        }

        return allErrors;

    }

    //we have three errors for discounts that will be shown twice
    //so we will make them here to reuse them easier
    public static ArrayList<String> checkCode(JTextField codeField, boolean submitting, boolean comparing) {

        ArrayList<String> allErrors = new ArrayList<String>();

        //System.out.println(codeField.getText());
        if (submitting && !codeField.getText().isEmpty()) {

            //this means we are trying to finalize the data so we need to make sure we are not repeating it
            if (comparing) {

                if (!codeField.getText().equalsIgnoreCase(Variables.selectedCode)) {

                    String status = SQLManager.checkDiscount(codeField.getText());

                    //now we check if we can make this the code
                    if (!status.equals("Discount doesnt exist")) {

                        allErrors.add(status);

                    }

                }

            } else {

                String status = SQLManager.checkDiscount(codeField.getText());

                if (!status.equals("Discount doesnt exist")) {

                    allErrors.add(status);

                }

            }

        } else {

            if (codeField.getText().isBlank()) {

                allErrors.add("Code is required");

            }

        }

        return allErrors;
    }

    public static ArrayList<String> checkAmount(JTextField amountField) {

        ArrayList<String> allErrors = new ArrayList<String>();

        if (amountField.getText().isBlank()) {

            allErrors.add("Amount is required");

        } else {

            Pattern amountPattern = Pattern.compile("^[0-9]+(?:\\.[0-9]+)?$");
            Matcher amountMatcher = amountPattern.matcher(amountField.getText());

            if (!amountMatcher.find()) {
                allErrors.add("Amount should only have numbers and a decimal point like #.#");
            }

        }

        return allErrors;
    }

    public static ArrayList<String> checkDesc(JTextArea descField) {

        ArrayList<String> allErrors = new ArrayList<String>();

        if (descField.getText().isBlank()) {

            allErrors.add("Description is required");

        }

        return allErrors;
    }

    //This method will compile any errors into a string to be displayed to the user
//    public static String compileErrors(ArrayList<String> errorList){
//    
//        String compiledErrors = "";
//        
//        for(String error: errorList){
//            
//            compiledErrors += error;
//            compiledErrors += "\n";
//            //make sure we do this last string so we can make sure its not all one line
//        
//    }
//        
//    return compiledErrors;
//    }
    //This method will check for errors ina  given list and act accordingly
    
    public static ArrayList<String> checkLists(ArrayList<JList> lists) {

        ArrayList<String> allErrors = new ArrayList<String>();

        //we should make an integer to see how many lists are empty
        //if all are empty then we need to return an error
        int amountEmpty = 0;
        
        for(JList list: lists){
            
            if(list.getModel().getSize() == 0){
            
                amountEmpty++;
                
            }
        
        }
        
        if(amountEmpty == lists.size()){
        
            allErrors.add("At least one item must be added");
            
        }

        return allErrors;
    }
    
    public static void checkForErrors(ArrayList<String> errors, JLabel errorMessage) {

        if (errors.size() > 0) {

            ///errors have been found make the button visible
            errorMessage.setVisible(true);
            errorMessage.setText(errors.get(0));

            //get the first error because they are ordered by importance
            //While we may onyl need one string I am keeping them as array lsits for testing
            //And also just incase we need to show all errors
            //It is also easy to check for errors this way
        } else {

            //Ok no errors were found
            //We should check if the button is already visible
            //That means an error was fond before and needs to be removed
            if (errorMessage.isVisible()) {

                errorMessage.setVisible(false);

            }

        }

    }

    public static void displayItem(int index) {

        //Check what we have as the current page and call upon that pages function
        //first we change the clobal variable to hold the index
        Variables.chosenGame = index;

        if (currentPage.equals("Customer")) {

            StorePage.displayGame(Variables.chosenGame);

        } else if (currentPage.equals("Manager")) {

            ManagerView.displayGame(Variables.chosenGame);

        }

    }

}
