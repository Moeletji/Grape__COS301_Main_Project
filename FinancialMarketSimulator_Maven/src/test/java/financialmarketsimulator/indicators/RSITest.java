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
public class RSITest {
    
    public RSITest() {
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
     * Test of calculateRSI method, of class RSI.
     */
    @Test
    public void testCalculateRSI() throws Exception {
        System.out.println("calculateRSI");
        RSI instance = null;
        double expResult = 0.0;
        double result = instance.calculateRSI();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateRS method, of class RSI.
     */
    @Test
    public void testCalculateRS() throws Exception {
        System.out.println("calculateRS");
        RSI instance = null;
        double expResult = 0.0;
        double result = instance.calculateRS();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRSI method, of class RSI.
     */
    @Test
    public void testGetRSI() {
        System.out.println("getRSI");
        RSI instance = null;
        double expResult = 0.0;
        double result = instance.getRSI();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRS method, of class RSI.
     */
    @Test
    public void testGetRS() {
        System.out.println("getRS");
        RSI instance = null;
        double expResult = 0.0;
        double result = instance.getRS();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
