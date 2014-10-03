
package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;

/**
 *
 * @brief This clas uses the Bollinger Brands pattern 
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class BollingerBands extends MarketIndicator{
    
    private Volatility sd;
    private double upperBand;
    private double lowerBand;
    private double standDev;
    private double middleBand;
    private final int NUM_DAYS = 20;
    private final int DEFAULT_FACTOR = 2;
    private  int factor;
    private MarketEntryAttemptBook data;
    private SMA sma;
    
    public BollingerBands()
    {
        super("Bollinger Bands");
        factor = DEFAULT_FACTOR;
    }
    
    public BollingerBands(MarketEntryAttemptBook _data)
    {
        super("Bollinger Bands");
        this.data = _data;
        this.sma = new SMA(DEFAULT_FACTOR);
        sd = new Volatility(NUM_DAYS, this.data);
        factor = DEFAULT_FACTOR;
    }
    
    public BollingerBands(int _factor,MarketEntryAttemptBook _data)
    {
        super("Bollinger Bands");
        this.data = _data;
        this.sma = new SMA(DEFAULT_FACTOR);
        sd = new Volatility(NUM_DAYS, this.data);
        factor = (_factor >0)?_factor:DEFAULT_FACTOR;
    }
    
    public double getSMA()
    {
        return middleBand;
    }
    
    /*public double getCurrentPrice()
    {
        return 0.0;
    }*/
    
    public void calculateSMA() 
    {
       middleBand = sma.calculateSMA();
    }
    
    public void calculateSD() throws NotEnoughDataException 
    {
       standDev = sd.calculateSD(); 
    }
    
    public double getSD()
    {
        return standDev;
    }
    
    public void setSMA(double _sma)
    {
        this.middleBand = _sma;
    }
    
    public double calculateUpperBand() throws NotEnoughDataException
    {
        double mean;
        if (this.getSMA() > 0)
        {
            mean = this.getSMA();
        }
        else
        {
            throw new NotEnoughDataException();
        }
        
        upperBand = mean + (factor * getSD());
        return upperBand;
    }
    
    public double calculateLowerBand() throws NotEnoughDataException
    {
        double mean;
        if (this.getSMA() > 0)
        {
            mean = this.getSMA();
        }
        else
        {
            throw new NotEnoughDataException();
        }
        
        lowerBand = mean - (factor * getSD());
        return lowerBand;
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

    /**
     * 
     * @todo ALTER FUNCTION TO RETURN THE CORRECT VALUE
     */
    @Override
    public Double calculateIndicator() throws NotEnoughDataException {
        return this.getBandWidth();
    }
}
