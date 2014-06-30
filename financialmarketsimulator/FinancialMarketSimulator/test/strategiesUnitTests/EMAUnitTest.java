package strategiesUnitTests;

import financialmarketsimulator.strategies.EMA;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Moeletji
 */


public class EMAUnitTest {
    
    public EMAUnitTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    EMA ema;
    
    /**
     * @brief Test to see that the calculate EMA returns the expected values 
     */
    @Test
    public void testCalculateEMA()
    {
        ema = new EMA();
        
        double expectedEMA = 0.0;
        int numberOfDays = 0;
        double closingPrice = 0.0;
        double previousEMA = 0.0;
        
        double ans = ema.calculateEMA(closingPrice, numberOfDays, previousEMA);
        assertEquals(expectedEMA, ans, 0.0000001);
        
        numberOfDays = 9;
        closingPrice = 50.0;
        previousEMA = 20.0;
        
        double k = 2/(numberOfDays-1);
        expectedEMA = (k*closingPrice)+(previousEMA*(1-k));
        ans = ema.calculateEMA(closingPrice, numberOfDays, previousEMA);
        assertEquals(expectedEMA, ans, 0.0000001);
    }
}
