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
public class NDITest {
    
    public NDITest() {
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
     * Test of calculateNDI method, of class NDI.
     */
    @Test
    public void testCalculateNDI() throws Exception {
        System.out.println("calculateNDI");
        double _currNDM = 0.0;
        double _prevNDM = 0.0;
        NDI instance = null;
        double expResult = 0.0;
        double result = instance.calculateNDI(_currNDM, _prevNDM);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPreviousValue method, of class NDI.
     */
    @Test
    public void testSetPreviousValue() {
        System.out.println("setPreviousValue");
        double _prev = 0.0;
        NDI instance = null;
        instance.setPreviousValue(_prev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrevValue method, of class NDI.
     */
    @Test
    public void testGetPrevValue() {
        System.out.println("getPrevValue");
        NDI instance = null;
        double expResult = 0.0;
        double result = instance.getPrevValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTodaysHigh method, of class NDI.
     */
    @Test
    public void testSetTodaysHigh() {
        System.out.println("setTodaysHigh");
        int high = 0;
        NDI instance = null;
        instance.setTodaysHigh(high);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTodaysLow method, of class NDI.
     */
    @Test
    public void testSetTodaysLow() {
        System.out.println("setTodaysLow");
        int low = 0;
        NDI instance = null;
        instance.setTodaysLow(low);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrevClosing method, of class NDI.
     */
    @Test
    public void testSetPrevClosing() {
        System.out.println("setPrevClosing");
        int preC = 0;
        NDI instance = null;
        instance.setPrevClosing(preC);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
