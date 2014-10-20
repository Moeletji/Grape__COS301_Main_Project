/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;
import java.util.Vector;
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
public class PDMTest {
    MarketEntryAttemptBook book;
    public PDMTest() {
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
     * Test of calculatePDM method, of class PDM.
     */
    @Test
    public void testCalculatePDM() {
        System.out.println("calculatePDM");
        PDM instance = PDM.getInstance(book, 14);
        double expResult =instance.calculatePDM() ;
        double result = instance.calculateIndicator();
        assertEquals(expResult, result,0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPDMValue method, of class PDM.
     */
    @Test
    public void testGetPDMValue() {
        System.out.println("getPDMValue");
        PDM instance = PDM.getInstance(book, 14);
        Vector<Double> expResult = null;
        Vector<Double> result = instance.getPDMValue();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateIndicator method, of class PDM.
     */
    @Test
    public void testCalculateIndicator() throws Exception {
        System.out.println("calculateIndicator");
        PDM instance = PDM.getInstance(book, 14);
        double expResult = 0.0;
        double result = instance.calculateIndicator();
        assertEquals(expResult, result,0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
