/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;
import static java.lang.Math.abs;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Madimetja
 */
public class ADXTest {
    MarketEntryAttemptBook book;
    public ADXTest() {
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
     * Test of calculateADX method, of class ADX.
     */
    @Test
    public void testCalculateADX() throws Exception {
        System.out.println("calculateADX");
        ADX instance = null;
        double expResult = 0.0;
        //double result = instance.calculateADX();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPreviousADX method, of class ADX.
     */
    @Test
    public void testGetPreviousADX() {
        System.out.println("getPreviousADX");
        ADX instance = null;
        double expResult = 0.0;
        //double result = instance.getPreviousADX();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateIndicator method, of class ADX.
     */
    @Test
    public void testCalculateIndicator() throws Exception {
        System.out.println("calculateIndicator");
        ADX instance = null;
        Double expResult = null;
       // Double result = instance.calculateIndicator();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
