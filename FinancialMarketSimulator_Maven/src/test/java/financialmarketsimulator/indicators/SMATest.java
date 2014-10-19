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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Madimetja
 */
public class SMATest {
    
    private double []prices = {53.73,53.87,53.85,53.88,54.08,54.14,54.50,54.30,54.40,54.16};
    private MarketEntryAttemptBook data;
    private int numDays = 10;
    
    public SMATest() {
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
     * Test of calculateSMA method, of class SMA.
     */
    @Test
    public void testCalculateSMA_0args() throws Exception {
        System.out.println("calculateSMA");
        this.setUp();
        SMA instance = SMA.getInstance(data, numDays);
        double expResult = instance.calculateIndicator();
        double result = 0;
        if (data.getMatchedOrders().size() < numDays)
        {
            result = 0.0;
        }
        
        double sum = 0.0;
        int range = data.getMatchedOrders().size() - numDays;
        for (int i= 0; i<data.getMatchedOrders().size();i++ )
        {
            sum += data.getMatchedOrders().get(i).getPrice();
        }
        
        double currentSmaValue = sum / numDays;
        result = currentSmaValue;
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateSMA method, of class SMA.
     */
    @Test
    public void testCalculateSMA_double() {
        System.out.println("calculateSMA");
        setUp();
        double result = 0.0;
        if (data.getMatchedOrders().size() < numDays)
        {
            result = 0.0;
        }
        
        double sum = 0.0;
        int range = data.getMatchedOrders().size() - numDays;
        for (int i= 0; i<data.getMatchedOrders().size();i++ )
        {
            sum += data.getMatchedOrders().get(i).getPrice();
        }
        
        double currentSmaValue = sum / numDays;
        result = currentSmaValue;
        double total = 0.0;
        SMA instance = SMA.getInstance(numDays);
        double expResult = instance.calculateSMA(sum);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPreviousSMAValue method, of class SMA.
     */
    @Test
    public void testGetPreviousSMAValue() {
        System.out.println("getPreviousSMAValue");
        this.setUp();
        SMA instance = SMA.getInstance(numDays);
        double expResult = 0.0;
        double result = instance.getPreviousSMAValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentSMAValue method, of class SMA.
     */
    @Test
    public void testGetCurrentSMAValue() {
        this.setUp();
        System.out.println("getCurrentSMAValue");
        SMA instance = SMA.getInstance(data, numDays);
        double expResult = instance.calculateIndicator();
        double result = instance.getCurrentSMAValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
