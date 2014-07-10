
package financialmarketsimulator.indicators;

/**
 *
 * @brief
 */
public class BollingerBands {
    
    private SMA sma;
    private double upperBand;
    private double lowerBand;
    private double standDev;
    private final int NUM_DAYS = 20;
    private final int DEFAULT_FACTOR = 2;
    private int factor;
    
    public BollingerBands()
    {
        sma = new SMA(NUM_DAYS);
        factor = DEFAULT_FACTOR;
    }
    
    public BollingerBands(int _factor)
    {
        sma = new SMA(NUM_DAYS);
        int factor = (_factor >0)?_factor:DEFAULT_FACTOR;
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
        //fix
        //standDev = (sma.calculateSD()>0)?sma.calculateSD():standDev;
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
