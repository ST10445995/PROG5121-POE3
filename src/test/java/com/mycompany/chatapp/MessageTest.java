/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JOptionPane;

/**
 *
 * @author lab_services_student
 */
public class MessageTest {

    private Message shortMessage;
    private Message longMessage;
    private Message hashMessage;
    private Message messageIDs;

    
    @Test
    public void testSentMessagesArrayPopulated(){
        Message mess1 = new Message(1, "+27838884567", "Did you get the cake?");
        Message mess4 = new Message (4, "0838884567", "It is dinner time!");
        String[] sentMessages =  {mess1.getMessage(), mess4.getMessage()};
        
        assertArrayEquals(new String[]{"Did you get the cake?","It is dinner time!"}, sentMessages);
    }
    @Test
    public void testLongestMessage(){
        Message mess2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        
        assertEquals("Where are you? You are late! I have asked you to be on time.", mess2. getMessage());
   }
    @Test
    public void testSearchNyMessageID(){
        Message mess4 = new Message(4, "0838884567", "It is dinner time!");
        int id = mess4.getMessageID();
        
        assertEquals("0838884567", mess4.getRecipient());
        assertEquals("It is dinner time!", mess4.getMessage());
   }
    @Test
    public void testMessageSentToRecipient(){
        Message mess2 = new Message(2, "+27838884567","Where are you? You are late! I have asked you to be on time." );
        Message mess5 = new Message (5,"+27838884567", "Ok, I am leaving without you." );
        String[] messagesSentToRecipient =  {mess2.getMessage(), mess5.getMessage()};
        
        assertArrayEquals(new String[]{"Where are you? You are late! I have asked you to be on time.", "Ok, I am leaving without you."},
            messagesSentToRecipient
            );
    }
    @Test
    public void testMessageDeletedByHash(){
        Message mess2 = new Message (2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        String targetHash = mess2.getMessageHash();      
    }
    @Test
    public void testDisplaySentMessageReport(){
        Message mess1 = new Message (1, "+27834557896", "Did you get the cake?");
        String report = "Hash:" + mess1.getMessageHash() + ",Recipient:" + mess1.getRecipient() + ",Message:" + mess1.getMessage();
        
        assertTrue(report.contains("Recipient:+27834557896"));
        assertTrue(report.contains("Did you get the cake?"));
    }
}
