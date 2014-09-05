/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketStrategy;
import org.jfree.chart.JFreeChart;
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
public class CrossoverTest {
    
    public CrossoverTest() {
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
     * Test of trade method, of class Crossover.
     */
    @Test
    public void testTrade() throws Exception {
        System.out.println("trade");
        Crossover instance = null;
        MarketStrategy.SignalMessage expResult = null;
        MarketStrategy.SignalMessage result = instance.trade();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawCrossoverGraph method, of class Crossover.
     */
    @Test
    public void testDrawCrossoverGraph() {
        System.out.println("drawCrossoverGraph");
        Crossover instance = null;
        instance.drawCrossoverGraph();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumberOfDays method, of class Crossover.
     */
    @Test
    public void testSetNumberOfDays() {
        System.out.println("setNumberOfDays");
        int _numDays = 0;
        Crossover instance = null;
        instance.setNumberOfDays(_numDays);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCrossoverGraph method, of class Crossover.
     */
    @Test
    public void testGetCrossoverGraph() {
        System.out.println("getCrossoverGraph");
        Crossover instance = null;
        JFreeChart expResult = null;
        JFreeChart result = instance.getCrossoverGraph();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class CrossoverImpl extends Crossover {

        public CrossoverImpl() {
            super(null, 0, "", "");
        }

        public SignalMessage trade() throws NotEnoughDataException {
            return null;
        }
    }
    
}
