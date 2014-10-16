/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketStrategy;
import financialmarketsimulator.market.MarketStrategy.SIGNAL;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.BID;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.DO_NOTHING;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.OFFER;
import static financialmarketsimulator.market.MarketStrategy.VOLATILITY.NORMAL;
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
public class MovingAverageFilterTest {
    
    public MovingAverageFilterTest() {
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
     * Test of trade method, of class MovingAverageFilter.
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
            MovingAverageEnvelope mae = MovingAverageEnvelope.getInstance(book);
            MovingAverageCrossover mac = MovingAverageCrossover.getInstance(book,14);
            MACDStrategy macd = MACDStrategy.getInstance(book);

            Vector<SIGNAL> outcome = new Vector<>();

            int buyCount = 0;
            int sellCount = 0;
            double buySignalStregnth;
            double sellSignalStrength;


            outcome.add(mae.trade().getSignal());
            outcome.add(mac.trade().getSignal());
            outcome.add(macd.trade().getSignal());

            for(MarketStrategy.SIGNAL sig : outcome)
            {
                if(sig == BID)
                {
                    buyCount++;
                }
                else if (sig == OFFER)
                {
                    sellCount++;
                }
            }

            buySignalStregnth = (double)buyCount/(double)outcome.size();
            sellSignalStrength = (double)sellCount/(double)outcome.size();

            if( buySignalStregnth > 0.5 )
            {
                //Moving average buy signal is string. Generate buy signal.
                expResult.setSignal(BID);
            }
            else if ( sellSignalStrength > 0.5 )
            {
                //Moving average buy signal is strong. Generate sell signal.
                expResult.setSignal(OFFER);
            }
            else
            {
                //Both buy and sell signals are weak. Generate do_nothing signal.
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
        catch(NotEnoughDataException e)
        {
            System.err.println("Moving Average Filter Test threw NotEnoughDataException.");
        }
    }
    
}
