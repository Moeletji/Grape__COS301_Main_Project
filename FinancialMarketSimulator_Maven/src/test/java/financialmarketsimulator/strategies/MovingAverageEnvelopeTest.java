/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.strategies;

import financialmarketsimulator.market.MarketEntryAttempt;
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
public class MovingAverageEnvelopeTest {
    
    public MovingAverageEnvelopeTest() {
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
     * Test of getSMA method, of class MovingAverageEnvelope.
     */
    @Test
    public void testGetSMA() throws Exception {
        System.out.println("getSMA");
        MovingAverageEnvelope instance = null;
        double expResult = 0.0;
        double result = instance.getSMA();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateMarketEntryAttempt method, of class MovingAverageEnvelope.
     */
    @Test
    public void testGenerateMarketEntryAttempt() throws Exception {
        System.out.println("generateMarketEntryAttempt");
        MovingAverageEnvelope instance = null;
        MarketEntryAttempt expResult = null;
        MarketEntryAttempt result = instance.generateMarketEntryAttempt();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPreviousClosingPrice method, of class MovingAverageEnvelope.
     */
    @Test
    public void testGetPreviousClosingPrice() {
        System.out.println("getPreviousClosingPrice");
        MovingAverageEnvelope instance = null;
        double expResult = 0.0;
        double result = instance.getPreviousClosingPrice();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClosingPrice method, of class MovingAverageEnvelope.
     */
    @Test
    public void testSetClosingPrice() {
        System.out.println("setClosingPrice");
        double closing = 0.0;
        MovingAverageEnvelope instance = null;
        instance.setClosingPrice(closing);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClosingPrice method, of class MovingAverageEnvelope.
     */
    @Test
    public void testGetClosingPrice() {
        System.out.println("getClosingPrice");
        MovingAverageEnvelope instance = null;
        double expResult = 0.0;
        double result = instance.getClosingPrice();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPercentage method, of class MovingAverageEnvelope.
     */
    @Test
    public void testGetPercentage() {
        System.out.println("getPercentage");
        MovingAverageEnvelope instance = null;
        double expResult = 0.0;
        double result = instance.getPercentage();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class MovingAverageEnvelope.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        MovingAverageEnvelope instance = null;
        MovingAverageEnvelope.STRATEGY_TYPE expResult = null;
        MovingAverageEnvelope.STRATEGY_TYPE result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
