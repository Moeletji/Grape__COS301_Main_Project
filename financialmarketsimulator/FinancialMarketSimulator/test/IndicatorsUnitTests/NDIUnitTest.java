
package IndicatorsUnitTests;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.ATR;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.indicators.NDI;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @brief NDI class unit tests
 */
public class NDIUnitTest {
    
    /**
     * NDI object used throughout the unit tests.
     */
    NDI ndi;
    
    public NDIUnitTest() {
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
     * 
     */
    @Test
    public void calculateNDITest() throws NotEnoughDataException
    {
        double todaysHigh = 0.47;
        double todaysLow = 0.30;
        double previousClosing = 0.33;
        double currentNDM = 0.29;
        double previiousNDM = 0.31;
        double expectedResult;
        double observedResult;
        
        //***********************
        // Expected result calculation
        //***********************
        EMA ema = new EMA(14);
        ATR atr = new ATR(todaysHigh, todaysLow, previousClosing);
        
        ema.setCurrentPrice(currentNDM);
        ema.setPreviousEMAValue(previiousNDM);
        
        expectedResult = (100 * ema.calculateEMA() / atr.calculateATR());
        
        //***********************
        // Observed result calculation
        //***********************
        ndi = new NDI(todaysHigh, todaysLow, previousClosing);
        observedResult = ndi.calculateNDI(currentNDM, previiousNDM);
        
        assertEquals(expectedResult, observedResult, 0);
    }
}
