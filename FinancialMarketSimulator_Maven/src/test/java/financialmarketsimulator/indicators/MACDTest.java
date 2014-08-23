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
public class MACDTest {
    
    public MACDTest() {
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
     * Test of calculateMACDValue method, of class MACD.
     */
    @Test
    public void testCalculateMACDValue_0args() throws Exception {
        System.out.println("calculateMACDValue");
        MACD instance = new MACD();
        double expResult = 0.0;
        double result = instance.calculateMACDValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateMACDValue method, of class MACD.
     */
    @Test
    public void testCalculateMACDValue_EMA_EMA() throws Exception {
        System.out.println("calculateMACDValue");
        EMA _short = null;
        EMA _long = null;
        MACD instance = new MACD();
        double expResult = 0.0;
        double result = instance.calculateMACDValue(_short, _long);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateSignalValue method, of class MACD.
     */
    @Test
    public void testCalculateSignalValue() throws Exception {
        System.out.println("calculateSignalValue");
        MACD instance = new MACD();
        double expResult = 0.0;
        double result = instance.calculateSignalValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentMACDValue method, of class MACD.
     */
    @Test
    public void testGetCurrentMACDValue() {
        System.out.println("getCurrentMACDValue");
        MACD instance = new MACD();
        double expResult = 0.0;
        double result = instance.getCurrentMACDValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPreviousMACDValue method, of class MACD.
     */
    @Test
    public void testGetPreviousMACDValue() {
        System.out.println("getPreviousMACDValue");
        MACD instance = new MACD();
        double expResult = 0.0;
        double result = instance.getPreviousMACDValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentMACDValue method, of class MACD.
     */
    @Test
    public void testSetCurrentMACDValue() {
        System.out.println("setCurrentMACDValue");
        double curr = 0.0;
        MACD instance = new MACD();
        instance.setCurrentMACDValue(curr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPreviousMACDValue method, of class MACD.
     */
    @Test
    public void testSetPreviousMACDValue() {
        System.out.println("setPreviousMACDValue");
        double prev = 0.0;
        MACD instance = new MACD();
        instance.setPreviousMACDValue(prev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSignalValue method, of class MACD.
     */
    @Test
    public void testSetSignalValue() {
        System.out.println("setSignalValue");
        double signal = 0.0;
        MACD instance = new MACD();
        instance.setSignalValue(signal);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSignaValue method, of class MACD.
     */
    @Test
    public void testGetSignaValue() {
        System.out.println("getSignaValue");
        MACD instance = new MACD();
        double expResult = 0.0;
        double result = instance.getSignaValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
