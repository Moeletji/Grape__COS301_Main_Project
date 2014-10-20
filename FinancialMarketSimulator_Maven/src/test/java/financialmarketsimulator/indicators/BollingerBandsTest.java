/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;
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
public class BollingerBandsTest {
    MarketEntryAttemptBook data = new MarketEntryAttemptBook();
    int numDays = 20;
    double[] prices = {86.16,
89.09,
88.78,
90.32,
89.07,
91.15,
89.44,
89.18,
86.93,
87.68,
86.96,
89.43,
89.32,
88.72,
87.45,
87.26,
89.50,
87.90,
89.13,
90.70
};
    public BollingerBandsTest() {
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
     * Test of getSMA method, of class BollingerBands.
     */
    @Test
    public void testGetSMA() {
        System.out.println("getSMA");
        setUp();
        BollingerBands instance = BollingerBands.getInstance(data,numDays);
        double expResult = new Random().nextDouble();
        instance.setSMA(expResult);
        double result = instance.getSMA();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateSMA method, of class BollingerBands.
     */
    @Test
    public void testCalculateSMA() throws Exception {
        System.out.println("calculateSMA");
        setUp();
        BollingerBands instance = BollingerBands.getInstance(data,numDays);
        double expResult = instance.getSMA();
        double result;
        instance.setSMA(expResult);
        SMA sma = SMA.getInstance(data, numDays);
        result = instance.getSMA();
        assertEquals(expResult,result,0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateSD method, of class BollingerBands.
     */
    @Test
    public void testCalculateSD() throws Exception {
        System.out.println("calculateSD");
        setUp();
        BollingerBands instance = BollingerBands.getInstance(data,numDays);
        double expResult = instance.getSD();
        double result;
        instance.setSMA(expResult);
        Volatility sd = Volatility.getInstance(numDays, data);
        result = instance.getSD();
        assertEquals(expResult,result,0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getSD method, of class BollingerBands.
     */
    @Test
    public void testGetSD() {
        System.out.println("getSD");
        setUp();
        BollingerBands instance = BollingerBands.getInstance(data,numDays);
        double sd = new Random().nextDouble();
        instance.setStandardDeviation(sd);
        double expResult = sd;
        double result = instance.getSD();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setSMA method, of class BollingerBands.
     */
    @Test
    public void testSetSMA() {
        System.out.println("setSMA");
        setUp();
        BollingerBands instance = BollingerBands.getInstance(data,numDays);
        double sma = new Random().nextDouble();
        instance.setSMA(sma);
        double expResult = sma;
        double result = instance.getSMA();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateUpperBand method, of class BollingerBands.
     */
    @Test
    public void testCalculateUpperBand() throws Exception {
        System.out.println("calculateUpperBand");
        setUp();
        BollingerBands instance = BollingerBands.getInstance(data,numDays);
        double result = 0.0;
        double upperBand;
        double factor = instance.getFactor();
        double mean=0;
        if (instance.getSMA() > 0)
        {
            mean = instance.getSMA();
        }
        else
        {
            //throw new NotEnoughDataException();
        }
        
        upperBand = mean + (factor * instance.getSD());
        result = upperBand;
        double expResult = instance.calculateUpperBand();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateLowerBand method, of class BollingerBands.
     */
    @Test
    public void testCalculateLowerBand() throws Exception {
        System.out.println("calculateLowerBand");
        setUp();
        BollingerBands instance = BollingerBands.getInstance(data,numDays);
        double result = 0.0;
        double lowerBand;
        double factor = instance.getFactor();
        double mean=0;
        if (instance.getSMA() > 0)
        {
            mean = instance.getSMA();
        }
        else
        {
            //throw new NotEnoughDataException();
        }
        
        lowerBand = mean - (factor * instance.getSD());
        result = lowerBand;
        double expResult = instance.calculateLowerBand();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getFactor method, of class BollingerBands.
     */
    @Test
    public void testGetFactor() {
        System.out.println("getFactor");
        setUp();
        BollingerBands instance = BollingerBands.getInstance(data,numDays);
        int expResult = 2;
        int result = instance.getFactor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setStandardDeviation method, of class BollingerBands.
     */
    @Test
    public void testSetStandardDeviation() {
        System.out.println("setStandardDeviation");
        setUp();
        double sd = 0.0;
        BollingerBands instance = BollingerBands.getInstance(data,numDays);
        instance.setStandardDeviation(sd);
        double expResult = instance.getSD();
        assertEquals(expResult,sd,0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getLowerBand method, of class BollingerBands.
     */
    @Test
    public void testGetLowerBand() throws NotEnoughDataException {
        System.out.println("getLowerBand");
        setUp();
        BollingerBands instance = BollingerBands.getInstance(data,numDays);
        double expResult = instance.calculateLowerBand();
        double result = instance.getLowerBand();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getUpperBand method, of class BollingerBands.
     */
    @Test
    public void testGetUpperBand() throws NotEnoughDataException {
        System.out.println("getUpperBand");
        setUp();
        BollingerBands instance = BollingerBands.getInstance(data,numDays);
        double expResult = instance.calculateUpperBand();
        double result = instance.getUpperBand();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getBandWidth method, of class BollingerBands.
     */
    @Test
    public void testGetBandWidth() {
        System.out.println("getBandWidth");
        setUp();
        BollingerBands instance = BollingerBands.getInstance(data,numDays);
        double expResult = instance.getUpperBand()-instance.getLowerBand();
        double result = instance.getBandWidth();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
