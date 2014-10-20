/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttempt;
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
    MarketEntryAttemptBook data;
    int numDays = 14;
    double[] prices = {44.34,
44.09,
44.15,
43.61,
44.33,
44.83,
45.10,
45.42,
45.84,
46.08,
45.89,
46.03,
45.61,
46.28,
46.28
};
    public RSITest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    
    public void setUp() {
        data = new MarketEntryAttemptBook();
        
        for(int i=0;i<prices.length;i++)
        {
            MarketEntryAttempt temp1 = new MarketEntryAttempt();
            temp1.setPrice(prices[i]);
            temp1.setSide(MarketEntryAttempt.SIDE.BID);
            temp1.setNumOfShares(i+1);
            data.placeOrder(temp1);
            
            MarketEntryAttempt temp2 = new MarketEntryAttempt();
            temp2.setPrice(prices[i]);
            temp2.setSide(MarketEntryAttempt.SIDE.OFFER);
            temp2.setNumOfShares(i+1);
            data.placeOrder(temp2);
        }
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
        data = new MarketEntryAttemptBook();
        RSI instance = RSI.getInstance(data,numDays);
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
        EMA emaUp = EMA.getInstance(data,numDays);
        EMA emaDown = EMA.getInstance(data,numDays);
        
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
        double result = instance.calculateRSI();
        
        //assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calculateRS method, of class RSI.
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateRS() throws Exception {
        System.out.println("calculateRS");
        data = new MarketEntryAttemptBook();
        RSI instance = RSI.getInstance(data,numDays);
        double previousUpClose;
        double currentUpClose;
        double previousDownClose;
        double currentDownClose;
        double currentClose;
        double previousClose;
        double expResult;
        
        //Empirical
        EMA emaUp = EMA.getInstance(data,numDays);
        EMA emaDown = EMA.getInstance(data,numDays);
        
        previousClose = 0.0;
        currentClose = data.getLastTradePrice();
        currentUpClose = data.getHighestTradePrice(numDays);
        currentDownClose = data.getLowestTradePrice(numDays);
        
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
        double result = instance.calculateRS();
        
        //assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getRSI method, of class RSI.
     */
    @Test
    public void testGetRSI() {
        System.out.println("getRSI");
        this.setUp();
        //data = new MarketEntryAttemptBook();
        RSI instance = RSI.getInstance(data,numDays);
        double expResult = instance.calculateIndicator();
        double result = instance.getRSI();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getRS method, of class RSI.
     */
    @Test
    public void testGetRS() {
        System.out.println("getRS");
        setUp();
        //data = new MarketEntryAttemptBook();
        RSI instance = RSI.getInstance(data,numDays);
        double expResult = instance.calculateRS();
        double result = instance.getRS();
        assertEquals(expResult, result, 0.0);
    }
    
}
