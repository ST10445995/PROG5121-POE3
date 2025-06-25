/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Random;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author lab_services_student
 */
public class Message {
    
    //The unique ten-digit number for message ID
    private int messageID;
    
    //The order number of the message
    private int messageNumber;
    
    ///Recipient's phone number'
    private String recipient;
    
    //Message content
    private String message; 
    
    //The unique message hash
    private String messageHash; 

    // Constructor
    public Message(int messageNumber, String recipient, String message) {
        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
    }
    
    //Generate the unique message ID
    private int generateMessageID() {
        Random rand = new Random();
        return 100000000 + rand.nextInt(900000000);
    }
    
    //Ensures that the message ID  does not exceed ten characters 
    public boolean checkMessageID() {
    return String.valueOf(messageID).length() <= 10;
    }
    
    //Validates the recipient phone number format
    public int checkRecipientCell() {
        return recipient.matches("\\+\\d{10}") ? 1:0;
    }
    
    //Create a message hash  using the first digits of the ID, message number and the first or last words
    public String createMessageHash(){
        String[] words = message.split("");
        return messageID/100000000 + "." + messageNumber + "." + (words.length > 0 ? words[0]: "") + (words.length > 1 ? words[words.length - 1]: "").toUpperCase();
    }
    // Checks if message length is within 250 characters
    public String checkMessageLength() {
        if (message.length() <= 250) {
             return "Message ready to send.";
    } else {
        int excess = message.length() - 250;
        return "Message exceeds 250 characters by " + excess + ", please reduce size.";
    }
}
   
    //It handles message sending, storing and discarding 
    public String sentMessage(int option) {
        switch (option) {
            case 1:
                JOptionPane.showMessageDialog(null, "Message has been successfully sent.");
                return "Message has been successfully sent.";
                
            case 2:
                JOptionPane.showMessageDialog(null, "Message has been disregraded.");
                return "Message disregraded.";
                
            case 3:
                JOptionPane.showMessageDialog(null, "Message has been successfully stored.");
                return "Message has been successfully stored.";
                
            default:
                JOptionPane.showMessageDialog(null, "Invalid option");
                return "Invalid option.";
        }
    }
    
    //Displays the full message details 
    public void printMessage() {
        JOptionPane.showMessageDialog(null, "Message Sent!\n" + "Message ID:" + messageID + "\n" + "Message Hash:" + messageHash + "\n" + "Recipient:" + recipient + "\n" + "Message:" + message);
    }
    
    //Sotres message in the JSON format 
    public void storeMessage() {
        JSONObject messageObje = new JSONObject();
        messageObje.put("Message ID", messageID);
        messageObje.put("Message Hash", messageHash);
        messageObje.put("Recipient", recipient);
        messageObje.put("Message", message);
    
          try (FileWriter file = new FileWriter("messages.json", true)) {
        file.write(messageObje.toString(4)); // Pretty-print with indentation
        file.write("\n"); // Add a newline for better readability
        JOptionPane.showMessageDialog(null, "Message successfully stored in JSON file.");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error storing message: " + e.getMessage());
    }
}

    public int getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageHash() {
        return messageHash;
    }
    
}

//References: “How to Generate a Random Number with JavaScript Math.” Www.youtube.com, www.youtube.com/shorts/3thJnyTZ99U. Accessed 25 May 2025.//
//“YouTube.” Youtu.be, 2025, youtu.be/bvoGVoKcBa0?si=qMZCHN2__dGYeCgF. Accessed 25 May 2025.//
//Youtu.be, 2025, youtu.be/cumxd5ed-uI?si=9mISsOVqW0vqRxvL. Accessed 3 Apr. 2025.//
//THE INDEPENDENT INSTITUTION OF EDUCATION. INTRODUCTION to PROGRAMMING LOGIC MODULE MANUAL 2024. FIRST EDITION: 2024 ed., advtechonline.sharepoint.com/sites/TertiaryStudents/IIE%20Student%20Materials/Forms/Default%20View.aspx?id=%2Fsites%2FTertiaryStudents%2FIIE%20Student%20Materials%2FNew%20Student%20Materials%20CAT%2FIPRG5111%2F2024%2FIPRG5111MM%2Epdf&viewid=db15e059%2D4f93%2D487f%2Dabda%2De538b821c7b8&parent=%2Fsites%2FTertiaryStudents%2FIIE%20Student%20Materials%2FNew%20Student%20Materials%20CAT%2FIPRG5111%2F2024. Accessed 25 May. 2025.//
//Farrell, Joyce. Java Programming. 2025. Tenth Edition ed., Cengage Learning.//
//“ChatGPT.” Chatgpt.com, 2025, chatgpt.com/c/6834cc5d-5500-800f-be65-3024824af3c1. Accessed 26 May 2025.//
 

    
    
