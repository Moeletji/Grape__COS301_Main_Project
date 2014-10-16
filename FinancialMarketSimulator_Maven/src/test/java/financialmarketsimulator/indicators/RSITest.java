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
public class RSITest {
    MarketEntryAttemptBook book;
    public RSITest() {
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
     * Test of calculateRSI method, of class RSI.
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateRSI() throws Exception {
        System.out.println("calculateRSI");
        book = new MarketEntryAttemptBook();
        RSI instance = RSI.getInstance(book,14);
        double currentUpClose = 0.49;
        double currentDownClose = 0.44; 
        double currentClose = 0.48;
        double previousClose = 0.46;
        double previousUpClose;
        double previousDownClose;
        double expResult;
        
        //*****************************
        // Expected result calculation
        //*****************************
        EMA emaUp = EMA.getInstance(book,14);
        EMA emaDown = EMA.getInstance(book,14);
        
        previousUpClose = currentUpClose;
        previousDownClose = currentDownClose;
        currentUpClose = (currentClose > previousClose) ? currentClose-previousClose : 0;
        currentDownClose = (currentClose < previousClose) ? previousClose-currentClose : 0;
        
        emaUp.setCurrentPrice(currentUpClose);
        emaUp.setPreviousEMAValue(previousUpClose);
        emaDown.setCurrentPrice(currentDownClose);
        emaDown.setPreviousEMAValue(previousDownClose);
        
        double relativeStrength = emaUp.calculateEMA()/emaDown.calculateEMA();
        
        double RSvalue = 
        expResult = 100 - (100 / (1 + relativeStrength));
        
        //*****************************
        // Observed result calculation
        //*****************************
        //double result = instance.calculateRSI();
        
        //assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calculateRS method, of class RSI.
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateRS() throws Exception {
        System.out.println("calculateRS");
        book = new MarketEntryAttemptBook();
        RSI instance = RSI.getInstance(book,14);
        double previousUpClose;
        double currentUpClose;
        double previousDownClose;
        double currentDownClose;
        double currentClose;
        double previousClose;
        double expResult;
        
        //Empirical
        EMA emaUp = EMA.getInstance(book,14);
        EMA emaDown = EMA.getInstance(book,14);
        
        previousClose = 0.0;
        currentClose = book.getLastTradePrice();
        currentUpClose = book.getHighestTradePrice(14);
        currentDownClose = book.getLowestTradePrice(14);
        
        previousUpClose = currentUpClose;
        previousDownClose = currentDownClose;
        currentUpClose = (currentClose > previousClose) ? currentClose-previousClose : 0;
        currentDownClose = (currentClose < previousClose) ? previousClose-currentClose : 0;
        
        emaUp.setCurrentPrice(currentUpClose);
        emaUp.setPreviousEMAValue(previousUpClose);
        emaDown.setCurrentPrice(currentDownClose);
        emaDown.setPreviousEMAValue(previousDownClose);
        
        expResult = emaUp.calculateEMA()/emaDown.calculateEMA();
        
        //Theoretical
        //double result = instance.calculateRS();
        
        //assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getRSI method, of class RSI.
     */
    @Test
    public void testGetRSI() {
        System.out.println("getRSI");
        book = new MarketEntryAttemptBook();
        RSI instance = RSI.getInstance(book,14);
        double expResult = 0.0;
        double result = instance.getRSI();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getRS method, of class RSI.
     */
    @Test
    public void testGetRS() {
        System.out.println("getRS");
        book = new MarketEntryAttemptBook();
        RSI instance = RSI.getInstance(book,14);
        double expResult = 0.0;
        double result = instance.getRS();
        assertEquals(expResult, result, 0.0);
    }
    
}
