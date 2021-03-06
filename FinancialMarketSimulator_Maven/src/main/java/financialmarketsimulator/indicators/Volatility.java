package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;
import financialmarketsimulator.marketData.MatchedMarketEntryAttempt;
import java.util.Vector;

/**
 * @brief Class used to calculate the volatility of a Market
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */

public class Volatility extends MarketIndicator{
    
    /**
     * Singleton instance
     */
    private static Volatility instance = null;
    private final int period;
    private final SMA sma;
    private double mean;
    private final MarketEntryAttemptBook data;
    private double sd;
    
    private Volatility(int _period,MarketEntryAttemptBook _data )
    {
        super("Volatility");
        this.period = _period;
        this.data = _data;
        sma = SMA.getInstance(this.data, period);
    }
    
    public static Volatility getInstance(int _numDays, MarketEntryAttemptBook _book) {
        if (instance == null) {
            instance = new Volatility(_numDays, _book);
        }
        return instance;
    }
          
    public double getMean()
    {
        mean = sma.calculateSMA();
        return mean;
    }
    
    public double calculateSD() 
    {
        if (period <=0 || data.getMatchedOrders().size()<period)
        {
            return 0.0;
        }
        
        int range = data.getMatchedOrders().size()-period;
        int length = data.getMatchedOrders().size();
        double variance = 0;
        Vector<MatchedMarketEntryAttempt> temp = data.getMatchedOrders();
        
        for (int i=0;i<length;i++)
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

    /**
     * 
     * @return 
     * @todo ALTER FUNCTION TO RETURN CORRECT DATA 
     */
    @Override
    public Double calculateIndicator() {
        return this.calculateSD();
    }
}
