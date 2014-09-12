/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.indicators;

import java.util.ArrayList;
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
        //StochasticOscillator instance = new StochasticOscillator();
        double expResult = 0.0;
        //double result = instance.calculateK();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateD method, of class StochasticOscillator.
     */
    @Test
    public void testCalculateD() throws Exception {
        System.out.println("calculateD");
        StochasticOscillator instance = new StochasticOscillator();
        double expResult = 0.0;
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
        StochasticOscillator instance = new StochasticOscillator();
        double expResult = 0.0;
        //double result = instance.getD();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getK method, of class StochasticOscillator.
     */
    @Test
    public void testGetK() {
        System.out.println("getK");
        StochasticOscillator instance = new StochasticOscillator();
        double expResult = 0.0;
        //double result = instance.getK();
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setPeriod method, of class StochasticOscillator.
     */
    @Test
    public void testSetPeriod() {
        System.out.println("setPeriod");
        int _period = 0;
        //StochasticOscillator instance = new StochasticOscillator();
        //instance.setPeriod(_period);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setKValues method, of class StochasticOscillator.
     */
    @Test
    public void testSetKValues() {
        System.out.println("setKValues");
        double _kValue = 0.0;
        //StochasticOscillator instance = new StochasticOscillator();
        //instance.setKValues(_kValue);
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
