package strategiesUnitTests;

import financialmarketsimulator.indicators.ADX;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.indicators.NDM;
import financialmarketsimulator.indicators.PDM;
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
    public void calculateADXTest()
    {
        double expectedResult;
        double observedResult;
        double currentPDMValue = 0.31;
        double previousPDMValue = 0.25;
        double currentNDMValue = 0.33;
        double previousNDMValue = 0.24;
        
        //**************************************
        //Calculations for expected results
        //**************************************
        EMA ema = new EMA(14); //Over 14 days period
        PDM pdm = new PDM();
        NDM ndm = new NDM();
        
        //Setting previous values through current setter
        pdm.setCurrValue(previousPDMValue);
        ndm.setCurrValue(previousNDMValue);
        
        //Setting actual current values
        pdm.setCurrValue(currentPDMValue);
        ndm.setCurrValue(currentNDMValue);
        
        ema.setCurrentPrice(abs(pdm.getCurrValue() - ndm.getCurrValue())/abs(pdm.getCurrValue() + ndm.getCurrValue()));
        ema.setPreviousEMAValue(abs(pdm.getPrevValue() - pdm.getPrevValue())/abs(pdm.getPrevValue() + ndm.getPrevValue()));
        
        expectedResult = (100 * ema.calculateEMA());
        
        //**************************************
        //Calculations for actual observed results
        //**************************************
        observedResult = adx.calulateADX(currentPDMValue, currentNDMValue, previousPDMValue, previousNDMValue);
        
        assertEquals(expectedResult, observedResult, 0);
    }
}
