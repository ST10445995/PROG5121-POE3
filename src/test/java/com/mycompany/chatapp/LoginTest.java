/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lab_services_student
 */
public class LoginTest {
    /**
     * Test of checkUserName method, of class Login.
     */
    @Test
    public void testCheckUserName() {
        Login log1 = new Login("kyl_1", "Test@1234", "+27831234567", "Tshego", "Doe");
        assertTrue(log1.checkUserName());
        
        Login log2 = new Login("kyle!!!!!!!", "Test@1234", "+27831234567","Tshego","Doe");
        assertFalse(log2.checkUserName());
    }   

    /**
     * Test of checkPasswordComplexity method, of class Login.
     */
    @Test
    public void testCheckPasswordComplexity() {
        Login log1 = new Login("kyl_1", "Ch&&sec@ke99!", "+27831234567", "Tshego", "Doe");
        assertTrue(log1.checkPasswordComplexity());
        
        Login log2 = new Login("kyl!!!!!!!", "password", "+27831234567", "Tshego", "Doe");
        assertFalse(log2.checkPasswordComplexity());
    }

    /**
     * Test of checkCellPhoneNumber method, of class Login.
     */
    @Test
    public void testCheckCellPhoneNumber() {
        //Valid
        Login log1 = new Login("kyl_1", "Test@1234", "+27831234567", "Tshego", "Doe");
        assertTrue(log1.checkCellPhoneNumber());
        
        //Invalid
        Login log2 = new Login("kyle!!!!!!!", "Test@1234", "08966553","Tshego","Doe");
        assertFalse(log2.checkCellPhoneNumber());
    }

}
