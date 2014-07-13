
package strategiesUnitTests;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.indicators.RSI;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @brief RSI class unit tests.
 */
public class RSIUnitTest {
    
    RSI rsi;
    
    public RSIUnitTest() {
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
     * @brief Tests the calculateRSI function.
     */
    @Test
    public void calculateRSITest() throws NotEnoughDataException
    {
        double currentUpClose = 0.49;
        double currentDownClose = 0.44; 
        double currentClose = 0.48;
        double previousClose = 0.46;
        double previousUpClose;
        double previousDownClose;
        double expectedResult;
        double observedResult;
        
        //*****************************
        // Expected result calculation
        //*****************************
        EMA emaUp = new EMA(14);
        EMA emaDown = new EMA(14);
        
        previousUpClose = currentUpClose;
        previousDownClose = currentDownClose;
        currentUpClose = (currentClose > previousClose) ? currentClose-previousClose : 0;
        currentDownClose = (currentClose < previousClose) ? previousClose-currentClose : 0;
        
        emaUp.setCurrentPrice(currentUpClose);
        emaUp.setPreviousEMAValue(previousUpClose);
        emaDown.setCurrentPrice(currentDownClose);
        emaDown.setPreviousEMAValue(previousDownClose);
        
        double relativeStrength = emaUp.calculateEMA()/emaDown.calculateEMA();
        
        double RSvalue = 
        expectedResult = 100 - (100 / (1 + relativeStrength));
        
        //*****************************
        // Observed result calculation
        //*****************************
        rsi = new RSI(currentUpClose, currentDownClose, currentClose, previousClose);
        observedResult = rsi.calculateRSI();
        
        assertEquals(expectedResult, observedResult, 0);
    }
}
