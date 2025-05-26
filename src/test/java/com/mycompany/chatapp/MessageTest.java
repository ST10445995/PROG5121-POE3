/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

/**
 *
 * @author lab_services_student
 */
public class MessageTest {

    private Message shortMessage;
    private Message longMessage;
    private Message hashMessage;

    @BeforeEach
    public void setUp() {
        shortMessage = new Message(1, "+27831234567", "Hi to night");
        longMessage = new Message(2, "+27831234567", "A".repeat(260)); // deliberately over 250
        hashMessage = new Message(0, "+27831234567", "HI TO NIGHT");
    }

    @Test
    public void testMessageLengthSuccess() {
        assertEquals("Message ready to send.", shortMessage.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        String result = longMessage.checkMessageLength();
        assertTrue(result.startsWith("Message exceeds 250 characters by"), "Should detect excess length.");
        assertTrue(result.contains("10"), "Excess should be 10 characters.");
    }

    @Test
    public void testRecipientPhoneNumberSuccess() {
        assertEquals(1, shortMessage.checkRecipientCell());
    }

    @Test
    public void testRecipientPhoneNumberFailure() {
        Message m = new Message(3, "0731234567", "Hello");
        assertEquals(0, m.checkRecipientCell());
    }

    @Test
    public void testRecipientPhoneNumberMessageSuccess() {
        String result = (shortMessage.checkRecipientCell() == 1)
                ? "Cell phone number successfully captured."
                : "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testRecipientPhoneNumberMessageFailure() {
        Message m = new Message(3, "0731234567", "Hello");
        String result = (m.checkRecipientCell() == 1)
                ? "Cell phone number successfully captured."
                : "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }

    @Test
    public void testMessageHashCorrectness() {
        String expectedHash = "0.0.HIHT"; // Derived from: ID = 0xxxxxxx, messageNum = 0, message = HI TO NIGHT
        String[] words = "HI TO NIGHT".split(" ");
        String hash = hashMessage.createMessageHash();
        assertTrue(hash.contains("0.0"), "Hash should start with 0.0");
        assertTrue(hash.toUpperCase().contains(words[0]) || hash.toUpperCase().contains(words[words.length - 1]), "Hash should contain first or last word");
    }

    @Test
public void testMessageIDGenerated() {
    assertTrue(shortMessage.checkMessageID(), "Message ID should be 10 digits or fewer.");
}

    @Test
    public void testSentMessageOptions() {
        assertEquals("Message has been successfully sent.", shortMessage.sentMessage(1));
        assertEquals("Message disregraded.", shortMessage.sentMessage(2));
        assertEquals("Message has been successfully stored.", shortMessage.sentMessage(3));
        assertEquals("Invalid option.", shortMessage.sentMessage(0));
    }
@Test
    public void testStoreMessageCreatesFile() {
        File file = new File("messages.json");
        if (file.exists()) file.delete();

        shortMessage.storeMessage();

        assertTrue(file.exists(), "JSON file should be created.");
        assertTrue(file.length() > 0, "JSON file should contain content.");
    }
    
   }
