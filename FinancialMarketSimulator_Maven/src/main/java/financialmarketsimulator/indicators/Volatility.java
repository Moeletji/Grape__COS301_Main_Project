package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.SMA;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.marketData.MatchedMarketEntryAttempt;
import java.util.Vector;

/**
 * @brief Class used to calculate the volatility of a Market
 * @author Grape <cos301.mainproject.grape@gmail.com>
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
        sma = new SMA(this.data, period);
    }
          
    public double getMean() throws NotEnoughDataException
    {
        mean = sma.calculateSMA();
        return mean;
    }
    
    public double calculateSD() throws NotEnoughDataException
    {
        if (period <=0 || data.getMatchedOrders().size()<period)
            throw new NotEnoughDataException();
        
        int range = data.getMatchedOrders().size()-period;
        int length = data.getMatchedOrders().size()-1;
        double variance = 0;
        Vector<MatchedMarketEntryAttempt> temp = data.getMatchedOrders();
        
        for (int i=range;i<length-1;i++)
        {
            variance += ((temp.get(i).getPrice() - getMean())*(temp.get(i).getPrice() - getMean()));
        }
        double num = variance/period;
        sd = Math.sqrt(num);
        return sd;
    }
    
    public double getSD()
    {
        return sd;
    }
}
