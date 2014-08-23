/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;
import static java.lang.Math.abs;
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calulateADX method, of class ADX.
     */
    @Test
    public void testCalulateADX() throws Exception {
        System.out.println("calulateADX");
        double prevPDI = 0.15;
        double prevNDI = 0.26;
        double currPDM = 0.20;
        double currNDM = 0.18;
        double prevPDM = 0.18;
        double prevNDM = 0.15;
        book = new MarketEntryAttemptBook();
        ADX instance = new ADX(book, 14);
        double expResult;
        
        //**************************************
        //Calculations for expected results
        //**************************************
        EMA ema = new EMA(book,14);
        PDI pdi = new PDI(book,14);
        NDI ndi = new NDI(book,14);
        double currVal;
        double prevVal;
        
        pdi.setPreviousValue(prevPDI);
        ndi.setPreviousValue(prevNDI);
        
        //Set values.
        currVal = abs(pdi.calculatePDI(currPDM, prevPDM) - ndi.calculateNDI(currNDM, prevNDM))/abs(pdi.calculatePDI(currPDM, prevPDM) + ndi.calculateNDI(currNDM, prevNDM));
        prevVal = abs(pdi.getPrevValue() - ndi.getPrevValue())/abs(pdi.getPrevValue() + ndi.getPrevValue());
        
        ema.setCurrentPrice(currVal);
        ema.setPreviousEMAValue(prevVal);
        
        expResult = (100 * ema.calculateEMA());
        
        //**************************************
        //Calculations for actual observed results
        //**************************************
        double result = instance.calulateADX(prevPDI, prevNDI, currPDM, currNDM, prevPDM, prevNDM);
        
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getADX method, of class ADX.
     */
    @Test
    public void testGetADX() {
        System.out.println("getADX");
        book = new MarketEntryAttemptBook();
        ADX instance = new ADX(book, 14);
        double expResult = 0.0;
        double result = instance.getADX();
        assertEquals(expResult, result, 0.0);
    }
    
}
