/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;

import javax.swing.JOptionPane;

/**
 *
 * @author lab_services_student
 */


public class ChatApp {

    public static void main(String[] args) {
        // Declaration
        String firstName = JOptionPane.showInputDialog("Please enter your First Name:");
        String lastName = JOptionPane.showInputDialog("Please enter your Last Name:");

        String username = JOptionPane.showInputDialog("Please create a username (must include '_' and be <= 5 characters):");
        String password = JOptionPane.showInputDialog("Please create a password (minimum 8 characters, 1 capital letter, 1 number, 1 special character):");
        String cellphone = JOptionPane.showInputDialog("Please enter your South African phone number (e.g., +27831234567):");

        Login log1 = new Login(username, password, cellphone, firstName, lastName);
        String registrationMessage = log1.registerUser();

        if (!registrationMessage.equals("User has been registered successfully!")) {
            JOptionPane.showMessageDialog(null, registrationMessage);
            return; // Exit only if registration fails
        }

        JOptionPane.showMessageDialog(null, registrationMessage); // Successful registration message

        // Proceed with login
        boolean loginSuccess = false;
        while (!loginSuccess) {
            String usernameEntered = JOptionPane.showInputDialog("Login: Please enter your Username:");
            String passwordEntered = JOptionPane.showInputDialog("Login: Please enter your Password:");

            loginSuccess = log1.loginUser(usernameEntered, passwordEntered);
            JOptionPane.showMessageDialog(null, log1.returnLoginStatus(loginSuccess));

            if (!loginSuccess) {
                JOptionPane.showMessageDialog(null, "Incorrect credentials. Try again.");
            }
        }

       
        startQuickChat();
    }

    private static void startQuickChat() {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");
        
        //Ask how many messages the user wants to send 
        int messageCount = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of messages to send:"));
        int totalMessages = 0;

        while (true) {
            String option = JOptionPane.showInputDialog("Choose an option:\n 1. Send Messages\n 2. Show Recently Sent Messages\n 3. Quit");

            switch (option) {
                case "1":
                    
                    //Allows the sending messages until the messageCount is reached
                    if (totalMessages < messageCount) {
                        sendMessage(totalMessages + 1);
                        totalMessages++;
                    } else {
                        JOptionPane.showMessageDialog(null, "Message limit reached.");
                    }
                    break;

                case "2":
                    JOptionPane.showMessageDialog(null, "Coming Soon.");
                    break;

                case "3":
                    
                    // Exit app after showing total messages sent
                    JOptionPane.showMessageDialog(null, "Total messages sent: " + totalMessages);
                    JOptionPane.showMessageDialog(null, "Exiting QuickChat.");
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Try again.");
            }
        }
    }

    private static void sendMessage(int messageNumber) {
        
        //Prompt for the recipient
        String recipient = JOptionPane.showInputDialog("Enter recipient's cellphone (+XXXXXXXXXX):");

        //Validates recipient format
        if (!recipient.matches("\\+27\\d{9}")) {
            JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
            return;
        }

        // Gets the message content and check length
        String message = JOptionPane.showInputDialog("Please enter message (Max 250 characters):");

        if (message.length() > 250) {
            JOptionPane.showMessageDialog(null, "Message exceeds 250 characters by " + (message.length() - 250) + ", please reduce the size.");
            return;
        }

        Message newMessage = new Message(messageNumber, recipient, message);

        //Asks what to do with the message
        String choice = JOptionPane.showInputDialog("Choose an option: \n 1. Send Message\n 2. Disregard Message\n 3. Store Message to send later");

        //Handle user action
        switch (choice) {
            case "1":
                
                //Send the message
                newMessage.printMessage();
                break;
            case "2":
                JOptionPane.showMessageDialog(null, "Message disregarded.");
                break;
            case "3":
                
                //Store message in JSON
                newMessage.storeMessage();
                JOptionPane.showMessageDialog(null, "Message stored successfully.");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid option.");
        }
    }
}

 //References: * “ChatGPT.” Chatgpt.com, 2025, chatgpt.com/c/6806296a-6590-8001-80c9-c2e66c27bd00. Accessed 22 Apr. 2025.
  //Farrell, Joyce. Java Programming. 2025. Tenth Edition ed., Cengage Learning.//
  //THE IIE. INTRODUCTION to PROGRAMMING LOGIC MODULE MANUAL 2024. FIRST EDITION: 2024 ed., Accessed 22 Apr. 2025.//
