
package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;
import java.util.ArrayList;

/**
 *
 * @brief
 */
public class StochasticOscillators {
    private double currentPrice;
    private double low;
    private double high;
    private final int NUM_DAYS = 3;
    private SMA sma;
    private int lowerBound;
    private int upperBound;
    private double k;
    private double d;
    private int period;
    private ArrayList<Double> kValues;
    
    public StochasticOscillators()
    {
        lowerBound = 20;
        upperBound = 80;
        period = 14;
        kValues = new ArrayList<Double>();
    }
    
    public StochasticOscillators(int _lowerBound, int _upperBound)
    {
        lowerBound = _lowerBound;
        upperBound = _upperBound;
        kValues = new ArrayList<Double>();
    }
    
    public double calculateK(MarketEntryAttemptBook book)
    {
        double highestHigh = book.getHighestTradePrice(period);
        double lowestLow = book.getLowestTradePrice(period);
        kValues.add((currentPrice - lowestLow)/(highestHigh - lowestLow));
        return (currentPrice - lowestLow)/(highestHigh - lowestLow);
    }
    
    public double calculateD()
    {
        if (kValues.size() == 0 || kValues.size() < NUM_DAYS)
            return 0.0;//exception to be added
        SMA sma = new SMA(NUM_DAYS);
        double total =0;
        for (int i = kValues.size()-1-NUM_DAYS; i<kValues.size();i++ )
        {
            total += kValues.get(i);
        }
        
        return sma.calculateSMA(total);
    }
    
    public void setPeriod(int _period)
    {
        period = _period;
    }
}
