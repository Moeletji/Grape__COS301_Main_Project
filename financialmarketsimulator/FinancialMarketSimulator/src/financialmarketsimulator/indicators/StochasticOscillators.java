
package financialmarketsimulator.indicators;

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
    
    public StochasticOscillators()
    {
        lowerBound = 20;
        upperBound = 80;
    }
    
    public StochasticOscillators(int _lowerBound, int _upperBound)
    {
        lowerBound = _lowerBound;
        upperBound = _upperBound;
    }
    
    public double calculateK()
    {
        return 0.0;
    }
    
    public double calculayeD()
    {
        return 0.0;
    }
}
