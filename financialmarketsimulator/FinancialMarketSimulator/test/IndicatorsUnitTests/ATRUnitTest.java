package IndicatorsUnitTests;

import financialmarketsimulator.indicators.ATR;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @brief ATR Unit Test Class
 */
public class ATRUnitTest {

    /**
     * ATR object used throughout the unit tests.
     */
    ATR atr;

    public ATRUnitTest() {
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
     * @brief Tests the calculate ATR function
     */
    @Test
    public void calculateATRTest() {
        double currentHigh = 0.51;
        double currentLow = 0.48;
        double previousClosing = 0.50;
        double previousATR = 0.45;
        int numDays = 14;
        double expectedResult;
        double observedResult;

        //*******************************
        // Excpected result calculation
        //*******************************
        double trueRange = currentHigh - currentLow;
        expectedResult = (previousATR * (numDays - 1)) + (trueRange / numDays);

        //*******************************
        // Observed results calculation
        //*******************************
        atr = new ATR(currentHigh, currentLow, previousClosing);
        atr.setPreviousATR(previousATR);
        observedResult = atr.calculateATR();

        assertEquals(expectedResult, observedResult, 0);
    }

}
