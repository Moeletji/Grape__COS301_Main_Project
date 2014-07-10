package financialmarketsimulator.indicators;

//Error reading included file Templates/Classes/Templates/Licenses/license-Financial Market Simulator Licence.txt

import financialmarketsimulator.indicators.SMA;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.marketData.MatchedMarketEntryAttempt;
import java.util.Vector;

/**
 *
 * @author Moeletji
 */


public class Volatility {
    
    private int period;
    private SMA sma;
    private double mean;
    private MarketEntryAttemptBook data;
    private double sd;
    
    public Volatility(int _period,MarketEntryAttemptBook _data )
    {
        this.period = _period;
        this.data = _data;
        sma = new SMA(period, this.data);
    }
          
    public double getMean()
    {
        mean = sma.calculateSMA();
        return mean;
    }
    
    public double calculateSD()
    {
        if (period <=0 || data.isEmpty() || data.getMatchedOrders().size()<period)
            return 0.0;
        
        int range = data.getMatchedOrders().size()-period;
        int length = data.getMatchedOrders().size()-1;
        double variance = 0;
        Vector<MatchedMarketEntryAttempt> temp = data.getMatchedOrders();
        
        for (int i=range;i<length-1;i++)
        {
            variance += ((temp.get(i).getPrice() - getMean())*(temp.get(i).getPrice() - getMean()));
        }
        sd = Math.sqrt(variance/period);
        return sd;
    }
    
    public double getSD()
    {
        return sd;
    }
}
