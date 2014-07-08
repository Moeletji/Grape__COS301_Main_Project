
package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;

/**
 *
 * @brief
 */
public class StochasticOscillators {
    private double currentPrice;
    private double low;
    private double high;
    private final double NUM_DAYS = 3;
    private SMA sma;
    private int lowerBound;
    private int upperBound;
    private double k;
    private double d;
    private int period;
    
    public StochasticOscillators()
    {
        lowerBound = 20;
        upperBound = 80;
        period = 3;
    }
    
    public StochasticOscillators(int _lowerBound, int _upperBound)
    {
        lowerBound = _lowerBound;
        upperBound = _upperBound;
    }
    
    public double calculateK(MarketEntryAttemptBook book)
    {
        double highestHigh = 0;
        double lowestLow = 0;
        return (currentPrice - lowestLow)/(highestHigh - lowestLow);
    }
    
    public double calculateD()
    {
        return 0.0;
    }
    
    public void setPeriod(int _period)
    {
        period = _period;
    }
}
