/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;
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
public class NDMTest {
    MarketEntryAttemptBook book;
    public NDMTest() {
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
     * Test of setCurrValue method, of class NDM.
     */
    @Test
    public void testSetCurrValue() {
        System.out.println("setCurrValue");
        double val = 22.5;
        book = new MarketEntryAttemptBook();
        NDM instance = new NDM(book,14);
        instance.setCurrValue(val);
        double result = instance.getCurrValue();
        assertEquals(val,result,0.0);
    }

    /**
     * Test of setPrevValue method, of class NDM.
     */
    @Test
    public void testSetPrevValue() {
        System.out.println("setPrevValue");
        int prev = 0;
        book = new MarketEntryAttemptBook();
        NDM instance = new NDM(book,14);
        instance.setPrevValue(prev);
        double result = instance.getCurrValue();
        assertEquals(prev,result,0.0);
    }

    /**
     * Test of getPrevValue method, of class NDM.
     */
    @Test
    public void testGetPrevValue() {
        System.out.println("getPrevValue");
        book = new MarketEntryAttemptBook();
        NDM instance = new NDM(book,14);
        int expResult = 22;
        instance.setPrevValue(expResult);
        double result = instance.getPrevValue();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getCurrValue method, of class NDM.
     */
    @Test
    public void testGetCurrValue() {
        System.out.println("getCurrValue");
        book = new MarketEntryAttemptBook();
        NDM instance = new NDM(book,14);
        double expResult = 0.0;
        instance.setCurrValue(expResult);
        double result = instance.getCurrValue();
        assertEquals(expResult, result, 0.0);
    }
    
}
