/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.indicators.RSI;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketStrategy;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.BID;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.DO_NOTHING;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.OFFER;
import static financialmarketsimulator.market.MarketStrategy.VOLATILITY.HIGH;
import static financialmarketsimulator.market.MarketStrategy.VOLATILITY.NORMAL;
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
public class SimpleRSITest {
    
    public SimpleRSITest() {
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
     * Test of trade method, of class SimpleRSI.
     * @throws java.lang.Exception
     */
    @Test
    public void testTrade() throws Exception {
        System.out.println("trade");
        MarketEntryAttemptBook book = new MarketEntryAttemptBook();
        MovingAverageCrossover instance = new MovingAverageCrossover(book,14);
        
        try{
            //***************************
            //EXPECTED RESULT CALCULATION
            //***************************
            MovingAverageCrossover dummy = new MovingAverageCrossover(book,14);
            MarketStrategy.SignalMessage expResult = dummy.new SignalMessage();
            EMA ema = new EMA(book,14);
            RSI rsi = new RSI(book,14);

            double openingPrice = book.getOpeningPrice();
            double closingPrice = book.getLastTradePrice();
            double currEma = ema.calculateEMA();
            double currRsi = rsi.calculateRSI();
            double highestTradePrice = book.getHighestTradePrice(14);
            double lowestTradePrice = book.getLowestTradePrice(14);

            //Buy condition 1
            if (closingPrice > currEma && currRsi < 30) {
                expResult.setSignal(BID);
                expResult.setVolaility(NORMAL);
            } //Buy condition 2
            else if ((closingPrice < currEma && currRsi < 25)
                    && (abs(openingPrice - closingPrice) > 0.70 * abs(highestTradePrice - lowestTradePrice)
                    && closingPrice < openingPrice)) {
                expResult.setSignal(BID);
                expResult.setVolaility(NORMAL);
            } //Sell condition
            else if (currRsi > 40) {
                expResult.setSignal(OFFER);
                expResult.setVolaility(HIGH);
            } else {
                expResult.setSignal(DO_NOTHING);
                expResult.setVolaility(NORMAL);
            }

            //***************************
            //OBSERVED RESULT
            //***************************
            MarketStrategy.SignalMessage result = instance.trade();

            assertEquals(expResult.getSignal(), result.getSignal());
            assertEquals(expResult.getVolatility(), result.getVolatility());
        }
        catch(NotEnoughDataException e)
        {
            System.err.println("Simple RSI Strategy Test threw NotEnoughDataException.");
        }
    }
    
}
