/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author lab_services_student
 */


public class ChatApp {
    
    // Using ArrayLists for the messages
    static ArrayList<String> sentMessages = new ArrayList<>();
    static ArrayList<String> disregardedMessages = new ArrayList<>();
    static ArrayList<String> storedMessages = new ArrayList<>();
    static ArrayList<String> messageHashes = new ArrayList<>();
    static ArrayList<Integer> messageIDs = new ArrayList<>();

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

        populateTestData();
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
                    displaySenderAndRecipient();
                    break;

                case "3":
                    
                    // Exit app after showing total messages sent
                    JOptionPane.showMessageDialog(null, "Total messages sent: " + totalMessages);
                    JOptionPane.showMessageDialog(null, "Exiting QuickChat.");
                    return;
                    
                case "4":
                    String taskOption = JOptionPane.showInputDialog("Task Report Options:\n a. Display sender and recipient\n b. Display longest message\n c. Search by message ID\n d. Search by recipient\n e. Delete by hash\n f. Full report");
                    handleTaskOption(taskOption);
                    break;

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
                sentMessages.add("Sender: You\n Recipient:" + recipient + "\n Message:" + message);
                messageHashes.add(newMessage.getMessageHash());
                messageIDs.add(newMessage.getMessageID());
                break;
            case "2":
                JOptionPane.showMessageDialog(null, "Message disregarded.");
                disregardedMessages.add(message);
                break;
            case "3":
                
                //Store message in JSON
                newMessage.storeMessage();
                storedMessages.add(message);
                messageHashes.add(newMessage.getMessageHash());
                messageIDs.add(newMessage.getMessageID());
                JOptionPane.showMessageDialog(null, "Message stored successfully.");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid option.");
        }
    }
    
    private static void handleTaskOption(String option){
        switch (option.toLowerCase()){
            case "a": displaySenderAndRecipient();
            break;
            
            case "b": displayLongestMessage();
            break;
            
            case "c": searchByMessageID();
            break;
            
            case "d": searchByRecipient();
            break;
            
            case "e": deleteByHash();
            break;
            
            case "f": displayFullReport();
            break;
            
            default:
                JOptionPane.showMessageDialog(null,"Invalid Option");    
        }
    }
    
    private static void displaySenderAndRecipient(){
        if (sentMessages.isEmpty()){
            JOptionPane.showMessageDialog(null, "No sent messages.");
            return;
        }
        
        String result = "Sent Messages:\n\n";
        
        for (String mssg: sentMessages){
            result += mssg + "\n\n";
        }
        
        JOptionPane.showMessageDialog(null, result);
    }
    
    private static void displayLongestMessage(){
        String longest = "";
        
        for (String mssg: sentMessages) {
            int index = mssg.indexOf("Message:");
            
            if (index >=0){
                String actual = mssg.substring(index + 9);
                if (actual.length() > longest.length()){
                    longest = actual;
                }
            }
        }
        
        JOptionPane.showMessageDialog(null,"Longest message:\n" + longest);
    }
    
    private static void searchByMessageID(){
        String input = JOptionPane.showInputDialog("Please enter message ID:");
        try{
            int id = Integer.parseInt(input);
            
            for(int i = 0; i < messageIDs.size(); i++){
                if (messageIDs.get(i) == id){
                    JOptionPane.showMessageDialog(null, "Message ID:" + id + "\n" + sentMessages.get(i));
                    return;
                }
            }
            JOptionPane.showMessageDialog(null,"Message ID not found.");
        }
        catch (NumberFormatException e ){
            JOptionPane.showMessageDialog(null, "Invalid input");
        }
    }
    
    private static void searchByRecipient(){
        String recipient = JOptionPane.showInputDialog("Please enter recipient number");
        
        String result = "";
        boolean found = false;
        
        for (String mssg: sentMessages){
            if (mssg.contains("Recipient:" + recipient)){
                result += mssg + "\n\n";
                found = true;
            }
        }
        
        for (String mssg: storedMessages){
            if (mssg.contains("Recipient:" + recipient)){
                result += "Stored Message:\n" + mssg + "\n\n";
                found = true;
        }
    }
        if (found) {
            JOptionPane.showMessageDialog(null, "No messages found for this recipient.");
        }
        else {
            JOptionPane.showMessageDialog(null, result);
        }
    }
    
    private static void deleteByHash(){
        String hash = JOptionPane.showInputDialog("Please enter hash to delete:");
        
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(hash)) {
                sentMessages.remove(i);
                messageHashes.remove(i);
                messageIDs.remove(i);
                JOptionPane.showMessageDialog(null, "Message deleted successfully.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Hash not found.");
    }
    
    private static void displayFullReport(){
        //Display Full Report Arrays
       if (sentMessages.isEmpty()) {
           JOptionPane.showMessageDialog(null, "No messages to report.");
           return;
       }
       
       String report = "Full Sent Message Report: \n\n";
       
       for (int i = 0; i < sentMessages.size(); i++) {
           report += "ID:" + messageIDs.get(i) + "\n";
           report += "Hash:" + messageHashes.get(i) + "\n";
           report += sentMessages.get(i) + "\n\n";
       }
       
       JOptionPane.showMessageDialog(null, report);
    }
    
    private static void populateTestData(){
        //Populate your arrays
        Message mess1 = new Message (1, "+27834557896", "Did you get the cake?");
        sentMessages.add("Sender: You\n Recipient: +27834557896\n Message: Did you get the cake?");
        messageHashes.add(mess1.getMessageHash());
        messageIDs.add(mess1.getMessageID());
        
        Message mess2 = new Message (2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        storedMessages.add("Where are you? You are late! I have asked you to be on time");
        messageHashes.add(mess2.getMessageHash());
        messageIDs.add(mess2.getMessageID());
        
        Message mess3 = new Message (3, "+27834484567", "Yohoooo, I am at your gate.");
        sentMessages.add("Sender: You\n Recipient: +27834557896\n Message: Did you get the cake?");
        disregardedMessages.add("Yohoooo, I am at your gate."); 
        
        Message mess4 = new Message (4, "0838884567", "It is dinner time!");
        sentMessages.add("Sender: You\n Recipient: 0838884567\n Message: It is dinner time!");
        messageHashes.add(mess4.getMessageHash());
        messageIDs.add(mess4.getMessageID());
        
        Message mess5 = new Message (5, "+27838884567", "Ok, I am leaving without you.");
        storedMessages.add("Ok, I am leaving without you.");
        messageHashes.add(mess5.getMessageHash());
        messageIDs.add(mess5.getMessageID());
    }    
}

 //References: * “ChatGPT.” Chatgpt.com, 2025, chatgpt.com/c/6806296a-6590-8001-80c9-c2e66c27bd00. Accessed 22 Apr. 2025.
  //Farrell, Joyce. Java Programming. 2025. Tenth Edition ed., Cengage Learning.//
  //THE IIE. INTRODUCTION to PROGRAMMING LOGIC MODULE MANUAL 2024. FIRST EDITION: 2024 ed., Accessed 22 Apr. 2025.//
  //“Java String: How to Use the IsEmpty() Method | Java Tutorial  .” Youtu.be, 2025, youtu.be/yYDjqsvsor0?si=e0xZurZTWhOvX3zT. Accessed 24 June 2025.//
  //Youtu.be, 2025, youtu.be/cumxd5ed-uI?si=9mISsOVqW0vqRxvL. Accessed 3 Apr. 2025.//