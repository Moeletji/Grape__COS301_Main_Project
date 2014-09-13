/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.strategies;

import financialmarketsimulator.indicators.NDI;
import financialmarketsimulator.indicators.PDI;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketStrategy;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.BID;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.DO_NOTHING;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.OFFER;
import static financialmarketsimulator.market.MarketStrategy.VOLATILITY.NORMAL;
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
public class DirectionalMovementIndexTest {
    
    public DirectionalMovementIndexTest() {
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
     * Test of trade method, of class DirectionalMovementIndex.
     * @throws java.lang.Exception
     */
    @Test
    public void testTrade() throws Exception {
        System.out.println("trade");
        
        MarketEntryAttemptBook book = new MarketEntryAttemptBook();
        DirectionalMovementIndex instance = new DirectionalMovementIndex(book,14);
        DirectionalMovementIndex dummy = new DirectionalMovementIndex(book,14);
        
        //***************************
        //EXPECTED RESULT CALCULATION
        //***************************
        MarketStrategy.SignalMessage expResult = dummy.new SignalMessage();
        PDI pdi = new PDI(book,14);
        NDI ndi = new NDI(book,14);
        
        double currentPDI = pdi.calculatePDI();
        double currentNDI = ndi.calculateNDI();
        
        if (currentPDI - currentNDI > 0) {
            expResult.setSignal(BID);
        } else if (currentPDI - currentNDI < 0) {
            expResult.setSignal(OFFER);
        } else {
            expResult.setSignal(DO_NOTHING);
        }

        expResult.setVolaility(NORMAL);
        
        //***************************
        //OBSERVED RESULT
        //***************************
        MarketStrategy.SignalMessage result = instance.trade();
        
        assertEquals(expResult.getSignal(), result.getSignal());
        assertEquals(expResult.getVolatility(), result.getVolatility());
    }
    
}
