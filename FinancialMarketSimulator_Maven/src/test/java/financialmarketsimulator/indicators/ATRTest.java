/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.indicators;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Madimetja
 */
public class ATRTest {
    
    public ATRTest() {
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
     * Test of calculateATR method, of class ATR.
     */
    @Test
    public void testCalculateATR() {
        System.out.println("calculateATR");
        ATR instance = null;
        double expResult = 0.0;
        double result = instance.calculateATR();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTrueRange method, of class ATR.
     */
    @Test
    public void testGetTrueRange() {
        System.out.println("getTrueRange");
        ATR instance = null;
        double expResult = 0.0;
        double result = instance.getTrueRange();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTrueRange method, of class ATR.
     */
    @Test
    public void testSetTrueRange() {
        System.out.println("setTrueRange");
        ATR instance = null;
        instance.setTrueRange();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPreviousATR method, of class ATR.
     */
    @Test
    public void testSetPreviousATR() {
        System.out.println("setPreviousATR");
        double prev = 0.0;
        ATR instance = null;
        instance.setPreviousATR(prev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentHight method, of class ATR.
     */
    @Test
    public void testSetCurrentHight() {
        System.out.println("setCurrentHight");
        double val = 0.0;
        ATR instance = null;
        instance.setCurrentHight(val);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentLow method, of class ATR.
     */
    @Test
    public void testSetCurrentLow() {
        System.out.println("setCurrentLow");
        double val = 0.0;
        ATR instance = null;
        instance.setCurrentLow(val);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPreviousClosing method, of class ATR.
     */
    @Test
    public void testSetPreviousClosing() {
        System.out.println("setPreviousClosing");
        double val = 0.0;
        ATR instance = null;
        instance.setPreviousClosing(val);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
