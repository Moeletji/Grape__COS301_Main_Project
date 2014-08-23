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
public class NDITest {
    MarketEntryAttemptBook book;
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
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateNDI() throws Exception {
        System.out.println("calculateNDI");
        double _currNDM = 0.0;
        double _prevNDM = 0.0;
        book = new MarketEntryAttemptBook();
        NDI instance = new NDI(book,14);
        double expResult;
        double currentNDM = 0.29;
        double previiousNDM = 0.31;
        book = new MarketEntryAttemptBook();
        //***********************
        // Expected result calculation
        //***********************
        EMA ema = new EMA(book,14);
        ATR atr = new ATR(book,14);
        
        ema.setCurrentPrice(currentNDM);
        ema.setPreviousEMAValue(previiousNDM);
        
        expResult = (100 * ema.calculateEMA() / atr.calculateATR());
        
        //***********************
        // Observed result calculation
        //***********************
        double result = instance.calculateNDI(_currNDM, _prevNDM);
        
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPreviousValue method, of class NDI.
     */
    @Test
    public void testSetPreviousValue() {
        System.out.println("setPreviousValue");
        double _prev = 0.0;
        book = new MarketEntryAttemptBook();
        NDI instance = new NDI(book,14);
        instance.setPreviousValue(_prev);
    }

    /**
     * Test of getPrevValue method, of class NDI.
     */
    @Test
    public void testGetPrevValue() {
        System.out.println("getPrevValue");
        book = new MarketEntryAttemptBook();
        NDI instance = new NDI(book,14);
        
        //Default result
        double expResult = 0.0;
        double result = instance.getPrevValue();
        assertEquals(expResult, result, 0.0);
        
        //Result after set
        expResult = 25;
        instance.setPreviousValue(expResult);
        result = instance.getPrevValue();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTodaysHigh method, of class NDI.
     */
    @Test
    public void testSetTodaysHigh() {
        System.out.println("setTodaysHigh");
        int high = 0;
        book = new MarketEntryAttemptBook();
        NDI instance = new NDI(book,14);
        instance.setTodaysHigh(high);
    }

    /**
     * Test of setTodaysLow method, of class NDI.
     */
    @Test
    public void testSetTodaysLow() {
        System.out.println("setTodaysLow");
        int low = 0;
        book = new MarketEntryAttemptBook();
        NDI instance = new NDI(book,14);
        instance.setTodaysLow(low);
    }

    /**
     * Test of setPrevClosing method, of class NDI.
     */
    @Test
    public void testSetPrevClosing() {
        System.out.println("setPrevClosing");
        int preC = 0;
        book = new MarketEntryAttemptBook();
        NDI instance = new NDI(book,14);
        instance.setPrevClosing(preC);
    }
    
}
