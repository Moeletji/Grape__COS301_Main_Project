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
public class PDMTest {
    
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
     * Test of setCurrValue method, of class PDM.
     */
    @Test
    public void testSetCurrValue() {
        System.out.println("setCurrValue");
        double val = 0.0;
        PDM instance = null;
        instance.setCurrValue(val);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrevValue method, of class PDM.
     */
    @Test
    public void testSetPrevValue() {
        System.out.println("setPrevValue");
        int prev = 0;
        PDM instance = null;
        instance.setPrevValue(prev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrevValue method, of class PDM.
     */
    @Test
    public void testGetPrevValue() {
        System.out.println("getPrevValue");
        PDM instance = null;
        double expResult = 0.0;
        double result = instance.getPrevValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrValue method, of class PDM.
     */
    @Test
    public void testGetCurrValue() {
        System.out.println("getCurrValue");
        PDM instance = null;
        double expResult = 0.0;
        double result = instance.getCurrValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
