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
public class SMATest {
    
    public SMATest() {
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
     * Test of calculateSMA method, of class SMA.
     */
    @Test
    public void testCalculateSMA_0args() throws Exception {
        System.out.println("calculateSMA");
        SMA instance = null;
        double expResult = 0.0;
        //double result = instance.calculateSMA();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateSMA method, of class SMA.
     */
    @Test
    public void testCalculateSMA_double() {
        System.out.println("calculateSMA");
        double total = 0.0;
        SMA instance = null;
        double expResult = 0.0;
        //double result = instance.calculateSMA(total);
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPreviousSMAValue method, of class SMA.
     */
    @Test
    public void testGetPreviousSMAValue() {
        System.out.println("getPreviousSMAValue");
        SMA instance = null;
        double expResult = 0.0;
        //double result = instance.getPreviousSMAValue();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentSMAValue method, of class SMA.
     */
    @Test
    public void testGetCurrentSMAValue() {
        System.out.println("getCurrentSMAValue");
        SMA instance = null;
        double expResult = 0.0;
        //double result = instance.getCurrentSMAValue();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
