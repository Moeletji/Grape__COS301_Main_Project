
package strategiesUnitTests;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.ATR;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.indicators.PDI;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @brief PDI class unit test
 */
public class PDIUnitTest {
    
    /**
     * PDI object used throughout the unit tests.
     */
    PDI pdi;
    
    public PDIUnitTest() {
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
    public void calculatePDITest() throws NotEnoughDataException
    {
        double todaysHigh = 0.47;
        double todaysLow = 0.30;
        double previousClosing = 0.33;
        double currentPDM = 0.29;
        double previiousPDM = 0.31;
        double expectedResult;
        double observedResult;
        
        //***********************
        // Expected result calculation
        //***********************
        EMA ema = new EMA(14);
        ATR atr = new ATR(todaysHigh, todaysLow, previousClosing);
        
        ema.setCurrentPrice(currentPDM);
        ema.setPreviousEMAValue(previiousPDM);
        
        expectedResult = (100 * ema.calculateEMA() / atr.calculateATR());
        
        //***********************
        // Observed result calculation
        //***********************
        pdi = new PDI(todaysHigh, todaysLow, previousClosing);
        observedResult = pdi.calculatePDI(currentPDM, previiousPDM);
        
        assertEquals(expectedResult, observedResult, 0);
    }
}
