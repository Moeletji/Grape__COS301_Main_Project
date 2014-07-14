//Error reading included file Templates/Classes/Templates/Licenses/license-Financial Market Simulator Licence.txt
package indicatorsUnitTests;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.Volatility;
import financialmarketsimulator.market.MarketEntryAttempt;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Moeletji
 */


public class VolatilityUnitTest {
  
   public VolatilityUnitTest()
    {
        
    }
    
    MarketEntryAttemptBook data;
    
    @Test
    public void testCalculateSD() throws NotEnoughDataException
    {
        double []prices = {53.73,53.87,53.85,53.88,54.08,54.14,54.50,54.30,54.40,54.16};
        
        data = new MarketEntryAttemptBook();
        
        for(int i=0;i<prices.length;i++)
        {
            MarketEntryAttempt temp1 = new MarketEntryAttempt();
            temp1.setPrice(prices[i]);
            temp1.setSide(MarketEntryAttempt.SIDE.BID);
            temp1.setNumOfShares(i+1);
            data.placeOrder(temp1);
            
            MarketEntryAttempt temp2 = new MarketEntryAttempt();
            temp2.setPrice(prices[i]);
            temp2.setSide(MarketEntryAttempt.SIDE.OFFER);
            temp2.setNumOfShares(i+1);
            data.placeOrder(temp2);
        }
        
        int num_days = 10;
        
        Volatility sd = new Volatility(num_days, data);
        
        double expectedAns = 0.23;
        
        double ans = sd.calculateSD();
        
        assertEquals(expectedAns, ans, 0.01);
    }
}
