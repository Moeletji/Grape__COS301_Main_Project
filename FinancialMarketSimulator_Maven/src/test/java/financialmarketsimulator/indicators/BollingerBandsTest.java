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
public class BollingerBandsTest {
    
    public BollingerBandsTest() {
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
     * Test of getSMA method, of class BollingerBands.
     */
    @Test
    public void testGetSMA() {
        System.out.println("getSMA");
        BollingerBands instance = new BollingerBands();
        double expResult = 0.0;
        double result = instance.getSMA();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateSMA method, of class BollingerBands.
     */
    @Test
    public void testCalculateSMA() throws Exception {
        System.out.println("calculateSMA");
        BollingerBands instance = new BollingerBands();
        instance.calculateSMA();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateSD method, of class BollingerBands.
     */
    @Test
    public void testCalculateSD() throws Exception {
        System.out.println("calculateSD");
        BollingerBands instance = new BollingerBands();
        instance.calculateSD();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getSD method, of class BollingerBands.
     */
    @Test
    public void testGetSD() {
        System.out.println("getSD");
        BollingerBands instance = new BollingerBands();
        double expResult = 0.0;
        double result = instance.getSD();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setSMA method, of class BollingerBands.
     */
    @Test
    public void testSetSMA() {
        System.out.println("setSMA");
        double _sma = 0.0;
        BollingerBands instance = new BollingerBands();
        instance.setSMA(_sma);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateUpperBand method, of class BollingerBands.
     */
    @Test
    public void testCalculateUpperBand() throws Exception {
        System.out.println("calculateUpperBand");
        BollingerBands instance = new BollingerBands();
        double expResult = 0.0;
        double result = instance.calculateUpperBand();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateLowerBand method, of class BollingerBands.
     */
    @Test
    public void testCalculateLowerBand() throws Exception {
        System.out.println("calculateLowerBand");
        BollingerBands instance = new BollingerBands();
        double expResult = 0.0;
        double result = instance.calculateLowerBand();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getFactor method, of class BollingerBands.
     */
    @Test
    public void testGetFactor() {
        System.out.println("getFactor");
        BollingerBands instance = new BollingerBands();
        int expResult = instance.getFactor();
        int result = instance.getFactor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStandardDeviation method, of class BollingerBands.
     */
    @Test
    public void testSetStandardDeviation() {
        System.out.println("setStandardDeviation");
        double sd = 0.0;
        BollingerBands instance = new BollingerBands();
        instance.setStandardDeviation(sd);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getLowerBand method, of class BollingerBands.
     */
    @Test
    public void testGetLowerBand() {
        System.out.println("getLowerBand");
        BollingerBands instance = new BollingerBands();
        double expResult = 0.0;
        double result = instance.getLowerBand();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getUpperBand method, of class BollingerBands.
     */
    @Test
    public void testGetUpperBand() {
        System.out.println("getUpperBand");
        BollingerBands instance = new BollingerBands();
        double expResult = 0.0;
        double result = instance.getUpperBand();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getBandWidth method, of class BollingerBands.
     */
    @Test
    public void testGetBandWidth() {
        System.out.println("getBandWidth");
        BollingerBands instance = new BollingerBands();
        double expResult = 0.0;
        double result = instance.getBandWidth();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
