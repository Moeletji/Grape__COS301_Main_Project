package strategiesUnitTests;

import financialmarketsimulator.indicators.EMA;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */


public class EMAUnitTest {
    
    public EMAUnitTest() {
    }
    
    EMA ema;
    
    /**
     * @brief Test to see that the calculate EMA returns the expected values 
     */
    @Test
    public void testCalculateEMA()
    {
        double expectedEMA = 0.0;
        int numberOfDays = 0;
        double closingPrice = 0.0;
        double previousEMA = 0.0;
        
        ema = new EMA(numberOfDays);
        
        double ans = ema.calculateEMA();
        assertEquals(expectedEMA, ans, 0.0000001);
        
        numberOfDays = 9;
        closingPrice = 50.0;
        previousEMA = 1.0;
        
        ema = new EMA(numberOfDays);
        
        double k = 2/(numberOfDays-1);
        expectedEMA = (k*closingPrice)+(previousEMA*(1-k));
        ans = ema.calculateEMA();
        
        assertEquals(expectedEMA, ans, 0.0000001);
    }
}
