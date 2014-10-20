/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;
import static java.lang.Math.abs;
import java.util.Vector;
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
public class ADXTest {
    MarketEntryAttemptBook book;
    int numDays = 14;
    int executionCount;
    double previousADX;
    public ADXTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        book = new MarketEntryAttemptBook();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calculateADX method, of class ADX.
     */
    @Test
    public void testCalculateADX() throws Exception {
        System.out.println("calculateADX");
        this.setUp();
        ADX instance = ADX.getInstance(book, 14);
        DirectionalIndex di = DirectionalIndex.getInstance(book, 14);
        double expResult = instance.calculateIndicator();
        double result = 0.0;
        double previousADX = instance.getPreviousADX();
        Vector<Double> diValues = new Vector<Double>();
        diValues = di.getDiretionalIndexValues();
        double average = instance.calculateADX();
        if (diValues.size() < numDays) {
            result = 0.0;
        }

        if (this.executionCount == 0) {
            this.executionCount++;
            average = 0.0;

            for (double val : diValues) {
                average += val;
            }

            average = average / this.numDays;
            this.previousADX = average;
            result = average;
        } else {
            result = ((this.previousADX * (numDays - 1)) + diValues.lastElement()) / this.numDays;
        }

        assertEquals(expResult, result,0.0);
        //assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPreviousADX method, of class ADX.
     */
    @Test
    public void testGetPreviousADX() {
        System.out.println("getPreviousADX");
        this.setUp();
        ADX instance = ADX.getInstance(book, 14);
        double expResult = instance.getPreviousADX();
        double result = instance.calculateADX();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateIndicator method, of class ADX.
     */
    @Test
    public void testCalculateIndicator() throws Exception {
        System.out.println("calculateIndicator");
        this.setUp();
        ADX instance = ADX.getInstance(book, 14);
        DirectionalIndex di = DirectionalIndex.getInstance(book, 14);
        double expResult = instance.calculateIndicator();
        double result = 0;
        double previousADX = instance.getPreviousADX();
        Vector<Double> diValues = new Vector<Double>();
        diValues = di.getDiretionalIndexValues();
        double average = instance.calculateADX();
        if (diValues.size() < numDays) {
            result = 0.0;
        }

        if (this.executionCount == 0) {
            this.executionCount++;
            average = 0.0;

            for (double val : diValues) {
                average += val;
            }

            average = average / this.numDays;
            this.previousADX = average;
            result = average;
        } else {
            result = ((this.previousADX * (numDays - 1)) + diValues.lastElement()) / this.numDays;
        }

        assertEquals(expResult, result,0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
