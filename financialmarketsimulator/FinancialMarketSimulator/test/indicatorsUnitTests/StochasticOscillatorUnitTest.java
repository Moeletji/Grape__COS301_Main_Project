// Error reading included file Templates/Classes/Templates/Licenses/license-Financial Market Simulator Licence.txt
package indicatorsUnitTests;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.StochasticOscillator;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Moeletji
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */


public class StochasticOscillatorUnitTest {
    
    StochasticOscillator so;
    ArrayList<Double> prevKValues;
    double errorBound = 0.00001;
    
    public StochasticOscillatorUnitTest()
    {
        so = new StochasticOscillator();
        prevKValues = new ArrayList<Double>();
    }
    
    @Test(expected=NullPointerException.class)
    public void testForNoData() throws NotEnoughDataException, NullPointerException
    {
        so.calculateK();
        so.calculateD();
    }
    
    @Test
    public void testCalculateK()
    {
        //declare and initialize values for testing
        double current = 127.29;
        double high = 128.43;
        double low = 124.56;
        
        //set the relevant values needed for testing
        so.setCurrentPrice(current);
        so.setHighestHigh(high);
        so.setLowestLow(low);
        
        //calculated answer
        double ans = so.calculateK();
        
        //expected answer
        double expectedAns = (current-low)/(high-low)*100;
        
        //add to ArrayList to be used for later tests
        prevKValues.add(ans);
        
        assertEquals(expectedAns, ans, errorBound);
        
        //Repeated test to be used in testing the calculation of %D 
        current = 127.18;
        so.setCurrentPrice(current);
        ans = so.calculateK();
        expectedAns = (current-low)/(high-low)*100;
        prevKValues.add(ans);
        assertEquals(expectedAns, ans, errorBound);
        
        //Repeated test to be used in testing the calculation of %D
        current = 128.01;
        so.setCurrentPrice(current);
        ans = so.calculateK();
        expectedAns = (current-low)/(high-low)*100;
        prevKValues.add(ans);
        assertEquals(expectedAns, ans, errorBound);
    }
    
    @Test
    public void testKValuesLength()
    {
        int expected = 3;
        int ans = so.getKValues().size();
        
        assertEquals(expected,ans);
    }
    
    @Test
    public void testCalculateD() throws NotEnoughDataException
    {
        double expectedAns = 0;
        for (Double prevKValue : prevKValues) {
            so.setKValues(prevKValue);
            expectedAns += prevKValue;
        }
        //the expected %D
        expectedAns = expectedAns/so.getNumDays();
        
        //calculated %D
        double ans  = so.calculateD();
        
        assertEquals(expectedAns, ans, errorBound);
    }
}
