/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.ADX;
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
public class Simple_MACD_ADXTest {
    
    public Simple_MACD_ADXTest() {
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
     * Test of trade method, of class Simple_MACD_ADX.
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
            ADX adx = ADX.getInstance(book,14);
            PDI pdi = PDI.getInstance(book,14);
            NDI ndi = NDI.getInstance(book,14);
            MACDStrategy macdStrategy = MACDStrategy.getInstance(book);

            double adxValue = adx.calculateADX();
            double pdiValue = pdi.calculatePDI();
            double ndiValue = ndi.calculateNDI();
            MarketStrategy.SignalMessage macdSignal = macdStrategy.trade();

            if( (adxValue > 20 && pdiValue > 20 && ndiValue < 20) && macdSignal.getSignal().equals(BID) )
            {
                expResult.setSignal(BID);
            }
            else if ( (adxValue > 20 && ndiValue > 20 && pdiValue < 20) && macdSignal.getSignal().equals(OFFER) )
            {
                expResult.setSignal(OFFER);
            }
            else
            {
                //Generate Do_Nothing signal
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
            System.err.println("Simple MACD/ADX strategy test threw NotEnoughDataException");
        }
    }
    
}
