/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttempt;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import java.util.ArrayList;
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
public class StochasticOscillatorTest {
    MarketEntryAttemptBook data;
    int period = 14;
    StochasticOscillator instance;
    ArrayList<Double> prevKValues;
    double errorBound = 0.00001;
    double currentPrice = 127.29;
    double high = 128.43;
    double low = 124.56;
    double[] prices = {127.01,125.36,127.62,126.16,
        126.59,	124.93,
        127.35,	126.09,
        128.17,	126.82,
        128.43,	126.48,
        127.37,	126.03,
        126.42,	124.83,
        126.90,	126.39,
        126.85,	125.72,
        125.65,	124.56,
        125.72,	124.57,
        127.16,	125.07,
        127.72,	126.86,
        127.69,	126.63
        };
    double k1,k2,k3;
    public StochasticOscillatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
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
     * Test of calculateK method, of class StochasticOscillator.
     */
    @Test
    public void testCalculateK() {
        System.out.println("calculateK");
        prevKValues = new ArrayList<Double>();
        setUp();
        instance = StochasticOscillator.getInstance(data);
        
        //expected answer
        double expected = instance.calculateK(currentPrice,low,high );
        
        //calculated answer
        double result = (instance.getCurrentPrice() - instance.getLowestLow()) / (instance.getHighestHigh() - instance.getLowestLow()) * 100;
        
        assertEquals(expected, result, errorBound);
        k1 = expected;
        prevKValues.add(expected);
        
        //Repeated test to be used in testing the calculation of %D 
        currentPrice = 127.18;
        instance.setCurrentPrice(currentPrice);
        result = instance.calculateK(currentPrice,low,high );
        expected = (currentPrice - low) / (high - low) * 100;
        assertEquals(expected, result, errorBound);
        prevKValues.add(expected);
        k3 = expected;
        
        //Repeated test to be used in testing the calculation of %D
        currentPrice = 128.01;
        instance.setCurrentPrice(currentPrice);
        result = instance.calculateK(currentPrice,low,high );
        expected = (currentPrice - low) / (high - low) * 100;
        assertEquals(expected, result, errorBound);
        prevKValues.add(expected);
        k3 = expected;
        
        int exp = prevKValues.size();     
        int ans = instance.getKValues().size();

        assertEquals(exp, ans,0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateD method, of class StochasticOscillator.
     */
    @Test
    public void testCalculateD() throws Exception {
        System.out.println("calculateD");
        setUp();
        StochasticOscillator instance = StochasticOscillator.getInstance();
        double result = 0.0;
        double expectedAns = 0;
        
        prevKValues = new ArrayList<Double>();
        prevKValues.add(k1);
        prevKValues.add(k2);
        prevKValues.add(k3);
        //calcuate Average of kValues
        for (Double prevKValue : prevKValues) {
            instance.setKValues(prevKValue);
            expectedAns += prevKValue;
        }
        //the expected %D
        result = expectedAns / instance.getNumDays();

        //calculated %D
        double expResult = instance.calculateD();

        assertEquals(expResult, expectedAns, errorBound);
        //double result = instance.calculateD();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of getD method, of class StochasticOscillator.
     */
    @Test
    public void testGetD() {
        System.out.println("getD");
        StochasticOscillator instance = StochasticOscillator.getInstance();
        double expected = instance.calculateD();
        double result = instance.getD();
        assertEquals(expected,result,0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getK method, of class StochasticOscillator.
     */
    @Test
    public void testGetK() {
        System.out.println("getK");
        setUp();
        StochasticOscillator instance = StochasticOscillator.getInstance();
        double expResult = instance.calculateK();
        double result = instance.getK();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setPeriod method, of class StochasticOscillator.
     */
    @Test
    public void testSetPeriod() {
        System.out.println("setPeriod");
        StochasticOscillator instance = StochasticOscillator.getInstance();
        instance.setPeriod(period);
        int expected = period;
        int result = instance.getPeriod();
        assertEquals(expected, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setKValues method, of class StochasticOscillator.
     */
    @Test
    public void testSetKValues() {
        System.out.println("setKValues");
        double expected_kValue = new Random().nextDouble();
        StochasticOscillator instance = StochasticOscillator.getInstance();
        instance.setKValues(expected_kValue);
        int index = instance.getKValues().size()-1;
        double result = instance.getKValues().get(index);
        assertEquals(expected_kValue, result, errorBound);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setLowestLow method, of class StochasticOscillator.
     */
    @Test
    public void testSetLowestLow() {
        System.out.println("setLowestLow");
        double low = 0.0;
        //StochasticOscillator instance = new StochasticOscillator();
        //instance.setLowestLow(low);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setHighestHigh method, of class StochasticOscillator.
     */
    @Test
    public void testSetHighestHigh() {
        System.out.println("setHighestHigh");
        double high = 0.0;
        //StochasticOscillator instance = new StochasticOscillator();
        //instance.setHighestHigh(high);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentPrice method, of class StochasticOscillator.
     */
    @Test
    public void testSetCurrentPrice() {
        System.out.println("setCurrentPrice");
        double current = 0.0;
        //StochasticOscillator instance = new StochasticOscillator();
        //instance.setCurrentPrice(current);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getKValues method, of class StochasticOscillator.
     */
    @Test
    public void testGetKValues() {
        System.out.println("getKValues");
        //StochasticOscillator instance = new StochasticOscillator();
        //ArrayList<Double> expResult = null;
        //ArrayList<Double> result = instance.getKValues();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPeriod method, of class StochasticOscillator.
     */
    @Test
    public void testGetPeriod() {
        System.out.println("getPeriod");
        //StochasticOscillator instance = new StochasticOscillator();
        int expResult = 14;
        //int result = instance.getPeriod();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNumDays method, of class StochasticOscillator.
     */
    @Test
    public void testGetNumDays() {
        System.out.println("getNumDays");
        //StochasticOscillator instance = new StochasticOscillator();
        int expResult = 3;
        //int result = instance.getNumDays();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
