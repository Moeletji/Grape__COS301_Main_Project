package strategiesUnitTests;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.ADX;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.indicators.NDI;
import financialmarketsimulator.indicators.PDI;
import static java.lang.Math.abs;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @brief Test class for the ADX class. All methods within the ADX class are
 * tested in this class.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class ADXUnitTest {

    public ADXUnitTest() {
    }

    /**
     * ADX test object used in each test. To be instantiated within each unit
     * test.
     */
    ADX adx;
    
    /**
     * @brief Tests the calculateADX function.
     */
    @Test
    public void calculateADXTest() throws NotEnoughDataException
    {
        double expectedResult;
        double observedResult;
        double todaysHigh = 0.41;
        double todaysLow = 0.15;
        double prevClosing = 0.28;
        double prevPDI = 0.15;
        double prevNDI = 0.26;
        double currPDM = 0.20;
        double currNDM = 0.18;
        double prevPDM = 0.18;
        double prevNDM = 0.15;
        
        //**************************************
        //Calculations for expected results
        //**************************************
        EMA ema = new EMA(14);
        PDI pdi = new PDI(todaysHigh, todaysLow, prevClosing);
        NDI ndi = new NDI(todaysHigh, todaysLow, prevClosing);
        double currVal;
        double prevVal;
        
        pdi.setPreviousValue(prevPDI);
        ndi.setPreviousValue(prevNDI);
        
        //Set values.
        currVal = abs(pdi.calculatePDI(currPDM, prevPDM) - ndi.calculateNDI(currNDM, prevNDM))/abs(pdi.calculatePDI(currPDM, prevPDM) + ndi.calculateNDI(currNDM, prevNDM));
        prevVal = abs(pdi.getPrevValue() - ndi.getPrevValue())/abs(pdi.getPrevValue() + ndi.getPrevValue());
        
        ema.setCurrentPrice(currVal);
        ema.setPreviousEMAValue(prevVal);
        
        expectedResult = (100 * ema.calculateEMA());
        
        //**************************************
        //Calculations for actual observed results
        //**************************************
        adx = new ADX(todaysHigh, todaysLow, prevClosing);
        observedResult = adx.calulateADX(prevPDI, prevNDI, currPDM, currNDM, prevPDM, prevNDM);
        
        assertEquals(expectedResult, observedResult, 0);
    }
}
