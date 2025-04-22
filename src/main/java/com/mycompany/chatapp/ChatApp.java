/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;

import javax.swing.JOptionPane;

/**
 *
 * @author lab_services_student
 * 
 * References:
 * “ChatGPT.” Chatgpt.com, 2025, chatgpt.com/c/6806296a-6590-8001-80c9-c2e66c27bd00. Accessed 22 Apr. 2025.
 * Farrell, Joyce. Java Programming. 2025. Tenth Edition ed., Cengage Learning.
 * THE IIE. INTRODUCTION to PROGRAMMING LOGIC MODULE MANUAL 2024. FIRST EDITION: 2024 ed., Accessed 22 Apr. 2025.
 *
 */
public class ChatApp {

    public static void main(String[] args) {
        
        //Declaration
        String firstName = JOptionPane.showInputDialog("Please enter your First Name:");
        String lastName = JOptionPane.showInputDialog("Please enter your Last Name:");
        
        String username = JOptionPane.showInputDialog("Please create a username (must include '_' and be <= 5 characters):");
        String password = JOptionPane.showInputDialog("Please create a password (minimum 8 characters, 1 capital letter, 1 number, 1 special character):");
        String cellphone = JOptionPane.showInputDialog("Please enter your South African phone number (e.g., +27831234567):");
          
        Login log1 = new Login(username, password, cellphone, firstName, lastName);
        String registrationMessage = log1.registerUser();
            
            if (registrationMessage.equals("User registered successfully!")) {
                JOptionPane.showMessageDialog(null, registrationMessage);
                
                //Proceed with logging in or to login
                String usernameEntered = JOptionPane.showInputDialog("Login: Please enter the Username:");
                String passwordEntered = JOptionPane.showInputDialog("Login: Please enter the Password:");
                
                boolean loginSuccess = log1.loginUser(usernameEntered, passwordEntered);
                String loginMessage = log1.returnLoginStatus(loginSuccess);
                JOptionPane.showMessageDialog(null, loginMessage);    
        }
            else{
                JOptionPane.showMessageDialog(null, registrationMessage + "\n Exiting the app.");
            }
    }
}
