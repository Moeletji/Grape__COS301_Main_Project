/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.SMA;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketStrategy;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.BID;
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
public class PriceSmaCrossoverTest {
    
    public PriceSmaCrossoverTest() {
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
     * Test of trade method, of class PriceSmaCrossover.
     * @throws java.lang.Exception
     */
    @Test
    public void testTrade() throws Exception {
        System.out.println("trade");
        MarketEntryAttemptBook book = new MarketEntryAttemptBook();
        MovingAverageCrossover instance = MovingAverageCrossover.getInstance(book,14);
        
        try{
        //***************************
        //EXPECTED RESULT CALCULATION
        //***************************
        MovingAverageCrossover dummy = MovingAverageCrossover.getInstance(book,14);
        MarketStrategy.SignalMessage expResult = dummy.new SignalMessage();
        SMA smaObj = SMA.getInstance(book,14);
        
        double smaCurr = smaObj.calculateSMA();
        double smaPrev = smaObj.getPreviousSMAValue();
        double priceCurr = book.getLastTradePrice(); //smaObj.calculateSMA();
        double previousPrice = book.getPreviousTradePrice();

        if ((smaCurr > priceCurr) && (smaPrev < previousPrice)) {
            expResult.setSignal(BID);
        } else if ((smaCurr < priceCurr) && (smaPrev > previousPrice)) {
            expResult.setSignal(MarketStrategy.SIGNAL.OFFER);
        } else {
            expResult.setSignal(MarketStrategy.SIGNAL.DO_NOTHING);
        }
        
        expResult.setVolaility(NORMAL);
        
        //***************************
        //OBSERVED RESULT
        //***************************
        MarketStrategy.SignalMessage result = instance.trade();
        
        assertEquals(expResult.getSignal(), result.getSignal());
        assertEquals(expResult.getVolatility(), result.getVolatility());
        }
        catch(NotEnoughDataException e)
        {
            System.err.println("Price SMA Crossover threw NotEnoughDataException");
        }
    }
    
}
