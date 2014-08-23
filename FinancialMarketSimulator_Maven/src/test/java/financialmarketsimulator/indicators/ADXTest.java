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
public class ADXTest {
    
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
     * Test of calulateADX method, of class ADX.
     */
    @Test
    public void testCalulateADX() throws Exception {
        System.out.println("calulateADX");
        double prevPDI = 0.0;
        double prevNDI = 0.0;
        double currPDM = 0.0;
        double currNDM = 0.0;
        double prevPDM = 0.0;
        double prevNDM = 0.0;
        ADX instance = null;
        double expResult = 0.0;
        double result = instance.calulateADX(prevPDI, prevNDI, currPDM, currNDM, prevPDM, prevNDM);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getADX method, of class ADX.
     */
    @Test
    public void testGetADX() {
        System.out.println("getADX");
        ADX instance = null;
        double expResult = 0.0;
        double result = instance.getADX();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
