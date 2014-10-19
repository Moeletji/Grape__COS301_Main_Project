/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttempt;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.marketData.MatchedMarketEntryAttempt;
import java.util.Vector;
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
public class VolatilityTest {
    
    MarketEntryAttemptBook data=new MarketEntryAttemptBook();
    double []prices = {53.73,53.87,53.85,53.88,54.08,54.14,54.50,54.30,54.40,54.16};
    SMA sma;
    int num_days = 10;
    Volatility instance = Volatility.getInstance(num_days, data);
    int period =num_days;
    Volatility sd;
    
    public VolatilityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    
    public void setUp() {
        
        //data = new MarketEntryAttemptBook();
        
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
        
        sd = Volatility.getInstance(num_days, data);
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of getMean method, of class Volatility.
     */
    @Test
    public void testGetMean() throws Exception {
        System.out.println("getMean");
        Volatility instance = Volatility.getInstance(num_days, data);
        double expResult = 0.0;
        double result = instance.getMean();
        sma = SMA.getInstance(data, num_days);
        expResult = sma.calculateSMA();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateSD method, of class Volatility.
     */
    @Test
    public void testCalculateSD() throws Exception {
        this.setUp();
        System.out.println("calculateSD");
        Volatility instance = Volatility.getInstance(num_days, data);
        double expResult = 0.0;
        double result = instance.calculateSD();
        if (period <=0 || data.getMatchedOrders().size()<period)
        {
            expResult = 0.0;
        }
        
        int range = data.getMatchedOrders().size()-period;
        int length = data.getMatchedOrders().size();
        double variance = 0;
        Vector<MatchedMarketEntryAttempt> temp = data.getMatchedOrders();
        
        for (int i=0;i<length;i++)
        {
            variance += ((temp.get(i).getPrice() - instance.getMean())*(temp.get(i).getPrice() - instance.getMean()));
        }
        double num = variance/period;
        expResult = Math.sqrt(num);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getSD method, of class Volatility.
     */
    @Test
    public void testGetSD() {
        System.out.println("getSD");
        double expResult = instance.calculateIndicator();
        double result = instance.getSD();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
