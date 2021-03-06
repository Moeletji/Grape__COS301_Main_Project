package indicatorUnitTests;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.indicators.MACD;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @brief Test class for the MACD class. All methods in the MACD class are
 * tested in this class.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MACDUnitTest {

    public MACDUnitTest() {
    }

    /**
     * MACD class test object use in each test. To be instantiated within each
     * test.
     */
    MACD macd;

    @Test(expected = NotEnoughDataException.class)
    public void testForNoData() throws NotEnoughDataException {
        macd = new MACD();
        macd.calculateMACDValue();
    }

    /**
     * @todo
     */
    @Test
    public void testMACD() throws NotEnoughDataException {
        int _long = 26;
        int _short = 12;

        EMA shortEma = new EMA(_short);
        EMA longEma = new EMA(_long);

        double curr1 = 50.00;
        double prev1 = 1.4;
        shortEma.setCurrentPrice(_short);
        shortEma.setPreviousEMAValue(curr1);

        double curr2 = 40.00;
        double prev2 = 2.4;
        longEma.setCurrentPrice(curr2);
        longEma.setPreviousEMAValue(prev2);

        double expectedMACD = longEma.calculateEMA() - shortEma.calculateEMA();

        macd = new MACD();
        double answer = macd.calculateMACDValue(shortEma, longEma);

        assertEquals(expectedMACD, answer, 0.0000001);
    }

    /**
     * @todo
     */
    @Test
    public void testEntryPoint() {
        macd = new MACD();

        double prevMacd = -0.5;
        double currMacd = 1.5;
        macd.setPreviousMACDValue(prevMacd);
        macd.setCurrentMACDValue(currMacd);

        boolean expected = true;
        boolean answer = (macd.getCurrentMACDValue() > 0 && macd.getPreviousMACDValue() < 0) ? true : false;

        assertEquals(expected, answer);

        prevMacd = 1.5;
        macd.setPreviousMACDValue(prevMacd);
        expected = false;
        answer = (macd.getCurrentMACDValue() > 0 && macd.getPreviousMACDValue() < 0) ? true : false;

        assertEquals(expected, answer);
    }

    /**
     * @todo
     */
    @Test
    public void testExitPoint() {
        macd = new MACD();

        double prevMacd = 1.2;
        double currMacd = -0.5;
        macd.setPreviousMACDValue(prevMacd);
        macd.setCurrentMACDValue(currMacd);

        boolean expected = true;
        boolean answer = (macd.getCurrentMACDValue() < 0 && macd.getPreviousMACDValue() > 0) ? true : false;

        assertEquals(expected, answer);

        prevMacd = 1.2;
        currMacd = 2.5;
        macd.setPreviousMACDValue(prevMacd);
        macd.setCurrentMACDValue(prevMacd);

        expected = false;
        answer = (macd.getCurrentMACDValue() < 0 && macd.getPreviousMACDValue() > 0) ? true : false;

        assertEquals(expected, answer);
    }
}
