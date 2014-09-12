/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;
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
public class PDITest {
    MarketEntryAttemptBook book;
    public PDITest() {
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
     * Test of calculatePDI method, of class PDI.
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculatePDI() throws Exception {
        System.out.println("calculatePDI");
        double _currPDM = 0.0;
        double _prevPDM = 0.0;
        book = new MarketEntryAttemptBook();
        PDI instance = new PDI(book,14);
        double expResult;
        double currentNDM = 0.29;
        double previiousNDM = 0.31;
        book = new MarketEntryAttemptBook();
        //***********************
        // Expected result calculation
        //***********************
        EMA ema = new EMA(book,14);
        ATR atr = new ATR(book,14);
        
        ema.setCurrentPrice(currentNDM);
        ema.setPreviousEMAValue(previiousNDM);
        
        expResult = (100 * ema.calculateEMA() / atr.calculateATR());
        
        //***********************
        // Observed result calculation
        //***********************
        //double result = instance.calculatePDI();
        
        //assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getPDIValues method, of class PDI.
     */
    @Test
    public void testGetPDIValues() {
        System.out.println("getPDIValues");
        PDI instance = null;
        Vector<Double> expResult = null;
        //Vector<Double> result = instance.getPDIValues();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateIndicator method, of class PDI.
     */
    @Test
    public void testCalculateIndicator() throws Exception {
        System.out.println("calculateIndicator");
        PDI instance = null;
        Double expResult = null;
        //Double result = instance.calculateIndicator();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
