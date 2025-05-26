/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**'.
 *
 * @author lab_services_student
 */
public class Login {
    
    //Declaration
    private String username;
    private String password;
    private String cellphone;
    private String firstName;
    private String lastName;
    
    //Store login credentials 
    private String registeredUsername;
    private String registeredPassword;
    
    //Constructor
    public Login(String username, String password, String cellphone, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellphone = cellphone;
        this.firstName = firstName;
        this.lastName = lastName;
        
    } 
    
    
    //Username must contain an underscore ('_') and must be <= 5 characters
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }
    
    //The passwords must meet the following password complexity rules.
    public boolean checkPasswordComplexity() {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
    }
    
    //Cellphone number must be +27 and no more than 9 digits after the code
    public boolean checkCellPhoneNumber() {
        return cellphone.matches("\\+27\\d{9}");
    }
    
    //Method must return the necessary registration messages
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted,please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        if (!checkCellPhoneNumber()) {
            return "Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
        }
        
        //Store the user details for logging in later
        registeredUsername = username;
        registeredPassword = password;
        
        return "User has been registered successfully!";
    }
    
    //Checking login info
    public boolean loginUser (String usernameEntered, String passwordEntered) {
        return usernameEntered.equals(registeredUsername) && passwordEntered.equals(registeredPassword);
    }
    
    //The message must be based on the login state
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
             return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        else {
            return "Username or password incorrect, please try again.";
        }
    }
    
}


 //References:
 //* “ChatGPT.” Chatgpt.com, 2025, chatgpt.com/c/6806296a-6590-8001-80c9-c2e66c27bd00. Accessed 22 Apr. 2025.//
 //* Farrell, Joyce. Java Programming. 2025. Tenth Edition ed., Cengage Learning.//
 //* THE INDEPENDENT INSTITUTION OF EDUCATION. INTRODUCTION to PROGRAMMING LOGIC MODULE MANUAL 2024. FIRST EDITION: 2024 ed.,//