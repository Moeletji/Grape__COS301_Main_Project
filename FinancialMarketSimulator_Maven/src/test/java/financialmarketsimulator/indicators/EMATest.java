/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttempt;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import java.util.Random;
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
public class EMATest {
    
    MarketEntryAttemptBook data;
    int numDays = 10;
    double[] prices = {22.27,22.19,22.0,22.17,22.18,22.13,22.23,22.43,22.24,22.29};

    public EMATest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    //@Before
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
    
    @Test
    public void testMultiplier()
    {
        System.out.println("calculateEMA");
        this.setUp();
        EMA instance = EMA.getInstance(data, numDays);
        double expResult = 2.0/(numDays+1);
        double result = instance.getMultiplier();
        assertEquals(expResult, result, 0.0);
    }
    /**
     * Test of calculateEMA method, of class EMA.
     */
    @Test
    public void testCalculateEMA_0args() throws Exception {
        System.out.println("calculateEMA");
        this.setUp();
        EMA instance = EMA.getInstance(data, numDays);
        double result;
        double currentPrice = data.getLastTradePrice();
        double previousEMAValue = instance.getPreviousEMAValue();
        if ((numDays <= 0) || (currentPrice == 0) || (previousEMAValue == 0)) {
           result= 0.0;
        }
        
        double k = 2.0 / (numDays + 1);
        result = ((currentPrice * k) + (previousEMAValue * (1 - k)));
        double expResult = instance.calculateEMA();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateEMA method, of class EMA.
     */
    @Test
    public void testCalculateEMA_3args() throws Exception {
        System.out.println("calculateEMA");
        setUp();
        double prev = 22.24;
        double current = 22.38;
        EMA instance = EMA.getInstance(numDays);
        double expResult = instance.calculateEMA(prev, current, numDays);
        double result = 0.0;
        if ((numDays <= 0) || (current == 0) || (prev == 0)) {
           result = 0.0;
        }
  
        double k = 2.0 / (numDays + 1);
        result = ((current * k) + (prev * (1 - k)));
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfDays method, of class EMA.
     */
    @Test
    public void testGetNumberOfDays() {
        System.out.println("getNumberOfDays");
        EMA instance = EMA.getInstance(data, numDays);
        int result = numDays;
        int expResult = instance.getNumberOfDays();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setPreviousEMAValue method, of class EMA.
     */
    @Test
    public void testSetPreviousEMAValue() {
        System.out.println("setPreviousEMAValue");
        double previous = new Random().nextDouble();
        EMA instance = EMA.getInstance(data, numDays);
        instance.setPreviousEMAValue(previous);
        double expected = instance.getPreviousEMAValue();
        double result = previous;
        assertEquals(expected, result,0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentPrice method, of class EMA.
     */
    @Test
    public void testSetCurrentPrice() {
        System.out.println("setCurrentPrice");
        setUp();
        double current = 0.0;
        EMA instance = EMA.getInstance(data, numDays);
        instance.setCurrentPrice(data.getLastTradePrice());
        double result = data.getLastTradePrice();
        double expected = instance.getCurrentPrice();
        assertEquals(expected, result,0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPreviousEMAValue method, of class EMA.
     */
    @Test
    public void testGetPreviousEMAValue() {
        System.out.println("getPreviousEMAValue");
        setUp();
        EMA instance = EMA.getInstance(data, numDays);
        double expResult =instance.getPreviousEMAValue() ;
        //double result = instance;
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentPrice method, of class EMA.
     */
    @Test
    public void testGetCurrentPrice() {
        System.out.println("getCurrentPrice");
        setUp();
        EMA instance = EMA.getInstance(data, numDays);
        instance.setCurrentPrice(data.getLastTradePrice());
        double expResult = instance.getNumberOfDays();
        double result = this.numDays;
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
