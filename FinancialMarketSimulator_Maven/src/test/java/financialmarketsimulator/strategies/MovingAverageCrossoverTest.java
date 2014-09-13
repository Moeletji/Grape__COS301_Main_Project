/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.strategies;

import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.indicators.SMA;
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
public class MovingAverageCrossoverTest {
    
    public MovingAverageCrossoverTest() {
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
     * Test of trade method, of class MovingAverageCrossover.
     * @throws java.lang.Exception
     */
    @Test
    public void testTrade() throws Exception {
        System.out.println("trade");
        MarketEntryAttemptBook book = new MarketEntryAttemptBook();
        MovingAverageCrossover instance = new MovingAverageCrossover(book,14);
        
        //***************************
        //EXPECTED RESULT CALCULATION
        //***************************
        MovingAverageCrossover dummy = new MovingAverageCrossover(book,14);
        MarketStrategy.SignalMessage expResult = dummy.new SignalMessage();
        EMA emaObj = new EMA(book,14);
        SMA smaObj = new SMA(book,14);
        
        double emaCurr = emaObj.calculateEMA();
        double smaCurr = smaObj.calculateSMA();

        if ((emaCurr > smaCurr) && (emaObj.getPreviousEMAValue() < smaObj.getPreviousSMAValue())) {
            expResult.setSignal(BID);
            expResult.setVolaility(NORMAL);
        } else if ((emaCurr < smaCurr) && (emaObj.getPreviousEMAValue() > smaObj.getPreviousSMAValue())) {
            expResult.setSignal(OFFER);
            expResult.setVolaility(NORMAL);
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
