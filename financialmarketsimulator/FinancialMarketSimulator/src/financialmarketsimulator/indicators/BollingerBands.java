
package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;

/**
 *
 * @brief
 */
public class BollingerBands {
    
    private Volatility sd;
    private double upperBand;
    private double lowerBand;
    private double standDev;
    private final int NUM_DAYS = 20;
    private final int DEFAULT_FACTOR = 2;
    private  int factor;
    private MarketEntryAttemptBook data;
    private SMA sma;
    
    public BollingerBands(MarketEntryAttemptBook _data)
    {
        this.data = _data;
        this.sma = new SMA(DEFAULT_FACTOR);
        sd = new Volatility(NUM_DAYS, this.data);
        factor = DEFAULT_FACTOR;
    }
    
    public BollingerBands(int _factor,MarketEntryAttemptBook _data)
    {
        this.data = _data;
        this.sma = new SMA(DEFAULT_FACTOR);
        sd = new Volatility(NUM_DAYS, this.data);
        factor = (_factor >0)?_factor:DEFAULT_FACTOR;
    }
    
    public double getSMA()
    {
        return sma.calculateSMA();
    }
    
    public double getCurrentPrice()
    {
        return 0.0;
    }
    
    public double getSD()
    {
        standDev = sd.calculateSD();
        return standDev;
    }
    public double calculateUpperBand()
    {
        return getSMA() + (factor * getSD());
    }
    
    public double calculateLowerBand()
    {
        return getSMA() - (factor * getSD());
    }
    
    public int getFactor()
    {
        return factor;
    }
    
    public void setStandardDeviation(double sd)
    {
        standDev = sd;
    }
    
    public double getLowerBand()
    {
        return lowerBand;
    }
    
    public double getUpperBand()
    {
        return upperBand;
    }
        
    public double getBandWidth()
    {
        return (getUpperBand() - getLowerBand());
    }
}
