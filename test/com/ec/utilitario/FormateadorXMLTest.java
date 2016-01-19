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
import static org.testng.Assert.assertNotEquals;

/**
 *
 * @author gato
 */
public class FormateadorXMLTest {
    
    public FormateadorXMLTest() {
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
     * Test of formatear method, of class FormateadorXML.
     */
      @org.testng.annotations.Test
    public void testFormatear() {
        System.out.println("formatear");
        String xml = "formatear xml en base a la informacion";
        String expResult = "";
        String result = FormateadorXML.formatear(xml);
        assertEquals(result,"");
        System.out.println("Formateado ");
        System.out.println(result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
   
}