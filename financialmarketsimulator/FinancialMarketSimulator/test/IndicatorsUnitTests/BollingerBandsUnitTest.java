// Error reading included file Templates/Classes/Templates/Licenses/license-Financial Market Simulator Licence.txt
package IndicatorsUnitTests;

import financialmarketsimulator.indicators.BollingerBands;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */


public class BollingerBandsUnitTest {
    
    BollingerBands bbands;
    public BollingerBandsUnitTest()
    {
        bbands = new BollingerBands();
    }
    
    @Test
    public void testBollingerBandsCalculation()
    {
        //values to be used in the test
        double mean = 88.71;
        double sd = 1.29;
        //bound on difference between the calculated answer and the expected answer
        double error = 0.00001;
        
        //setting the mean and standard deviation in the BollingerBands object
        bbands.setSMA(mean);
        bbands.setStandardDeviation(sd);
        
        //calculate the upper and lower bands
        double ansLowerBound = bbands.calculateLowerBand();
        double ansUpperBound = bbands.calculateUpperBand();
        
        //calculating the expected answers
        double expectedAnsLowerBound = mean - (bbands.getFactor()*sd);
        double expectedAnsUpperBound = mean + (bbands.getFactor()*sd);
        
        //Test if the values are equal
        assertEquals(expectedAnsLowerBound,ansLowerBound,error);
        assertEquals(expectedAnsUpperBound,ansUpperBound,error);
        
        //calculated the expected bandwith
        double expectedBandWidth = expectedAnsUpperBound - expectedAnsLowerBound;
        //calculate the bandwith using the BollingerBands object
        double ansBandWidth = bbands.getBandWidth();
        
        //Test if the values are equal
        assertEquals(expectedBandWidth,ansBandWidth, error);
    }
}
