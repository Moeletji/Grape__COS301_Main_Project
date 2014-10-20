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
public class ATRTest {
    MarketEntryAttemptBook book;
    public ATRTest() {
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
     * Test of calculateATR method, of class ATR.
     */
    @Test
    public void testCalculateATR() {
        System.out.println("calculateATR");
        book = new MarketEntryAttemptBook();
        ATR instance = ATR.getInstance(book,14);
        double expResult;
        double currentHigh = 0.51;
        double currentLow = 0.48;
        double previousATR = 0.45;
        int numDays = 14;
        
        //*******************************
        // Excpected result calculation
        //*******************************
        double trueRange = currentHigh - currentLow;
        expResult = (previousATR * (numDays - 1)) + (trueRange / numDays);

        //*******************************
        // Observed results calculation
        //*******************************
        instance.setCurrentHight(currentHigh);
        instance.setCurrentLow(currentLow);
        instance.setPreviousATR(previousATR);
        double result = instance.calculateATR();
        
        //assertEquals(expResult, result, 2);
    }

    /**
     * Test of getTrueRange method, of class ATR.
     */
    @Test
    public void testGetTrueRange() {
        System.out.println("getTrueRange");
        book = new MarketEntryAttemptBook();
        ATR instance = ATR.getInstance(book,14);
        double expResult = 0.0;
        double result = instance.getTrueRange();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTrueRange method, of class ATR.
     */
    @Test
    public void testSetTrueRange() {
        System.out.println("setTrueRange");
        book = new MarketEntryAttemptBook();
        ATR instance = ATR.getInstance(book,14);
        instance.setTrueRange();
    }

    /**
     * Test of setPreviousATR method, of class ATR.
     */
    @Test
    public void testSetPreviousATR() {
        System.out.println("setPreviousATR");
        double prev = 0.0;
        book = new MarketEntryAttemptBook();
        ATR instance = ATR.getInstance(book,14);
        instance.setPreviousATR(prev);
    }

    /**
     * Test of setCurrentHight method, of class ATR.
     */
    @Test
    public void testSetCurrentHight() {
        System.out.println("setCurrentHight");
        double val = 0.0;
        book = new MarketEntryAttemptBook();
        ATR instance = ATR.getInstance(book,14);
        instance.setCurrentHight(val);
    }

    /**
     * Test of setCurrentLow method, of class ATR.
     */
    @Test
    public void testSetCurrentLow() {
        System.out.println("setCurrentLow");
        double val = 0.0;
        book = new MarketEntryAttemptBook();
        ATR instance = ATR.getInstance(book,14);
        instance.setCurrentLow(val);
    }

    /**
     * Test of setPreviousClosing method, of class ATR.
     */
    @Test
    public void testSetPreviousClosing() {
        System.out.println("setPreviousClosing");
        double val = 0.0;
        book = new MarketEntryAttemptBook();
        ATR instance = ATR.getInstance(book,14);
        instance.setPreviousClosing(val);
    }
    
}
