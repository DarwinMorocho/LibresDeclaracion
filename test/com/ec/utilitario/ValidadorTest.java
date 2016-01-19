/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.utilitario;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gato
 */
public class ValidadorTest {
    
    public ValidadorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validateEmail method, of class Validador.
     */
    @Test
    public void testValidateEmail() {
        System.out.println("validateEmail");
        String email = "";
        Validador instance = new Validador();
        boolean expResult = false;
        boolean result = instance.validateEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of validateCedula method, of class Validador.
     */
    @Test
    public void testValidateCedula() {
        System.out.println("validateCedula");
        String cedula = "";
        Validador instance = new Validador();
        boolean expResult = true;
        boolean result = instance.validateCedula(cedula);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}