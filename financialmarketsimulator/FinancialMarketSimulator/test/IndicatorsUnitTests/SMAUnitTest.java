package IndicatorsUnitTests;

import financialmarketsimulator.indicators.SMA;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @brief Test class for the sma class. All methods in the SMA class are tested
 * within this class.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class SMAUnitTest {

    public SMAUnitTest() {
    }

    /**
     * SMA class test object used. To be instantiated within each unit test.
     */
    SMA sma;
    
    /**
     * @todo
     */
    @Test
    public void testSMA()
    {
        double total = 4000.0;
        int numDays = 14;
        
        double expected = total/numDays;
        
        sma = new SMA(numDays);
        double answer = sma.calculateSMA(total);
        
        assertEquals(expected, answer, 0.000001);
    }
}
