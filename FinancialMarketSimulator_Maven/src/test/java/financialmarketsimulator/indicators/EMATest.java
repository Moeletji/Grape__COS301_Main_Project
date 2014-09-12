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
public class EMATest {
    
    public EMATest() {
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
     * Test of calculateEMA method, of class EMA.
     */
    @Test
    public void testCalculateEMA_0args() throws Exception {
        System.out.println("calculateEMA");
        EMA instance = null;
        double expResult = 0.0;
        double result = instance.calculateEMA();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateEMA method, of class EMA.
     */
    @Test
    public void testCalculateEMA_3args() throws Exception {
        System.out.println("calculateEMA");
        double prev = 0.0;
        double current = 0.0;
        int numDays = 0;
        EMA instance = null;
        double expResult = 0.0;
        double result = instance.calculateEMA(prev, current, numDays);
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfDays method, of class EMA.
     */
    @Test
    public void testGetNumberOfDays() {
        System.out.println("getNumberOfDays");
        EMA instance = null;
        int expResult = 0;
        int result = instance.getNumberOfDays();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setPreviousEMAValue method, of class EMA.
     */
    @Test
    public void testSetPreviousEMAValue() {
        System.out.println("setPreviousEMAValue");
        double previous = 0.0;
        EMA instance = null;
        instance.setPreviousEMAValue(previous);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentPrice method, of class EMA.
     */
    @Test
    public void testSetCurrentPrice() {
        System.out.println("setCurrentPrice");
        double current = 0.0;
        EMA instance = null;
        instance.setCurrentPrice(current);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPreviousEMAValue method, of class EMA.
     */
    @Test
    public void testGetPreviousEMAValue() {
        System.out.println("getPreviousEMAValue");
        EMA instance = null;
        double expResult = 0.0;
        double result = instance.getPreviousEMAValue();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentPrice method, of class EMA.
     */
    @Test
    public void testGetCurrentPrice() {
        System.out.println("getCurrentPrice");
        EMA instance = null;
        double expResult = 0.0;
        double result = instance.getCurrentPrice();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
