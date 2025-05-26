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
    private boolean checkMessageID() {
        return String.valueOf(messageID).length()<= 10;
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
}

 

    
    
