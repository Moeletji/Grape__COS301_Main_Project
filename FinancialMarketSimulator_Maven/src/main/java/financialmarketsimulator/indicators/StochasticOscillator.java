
package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import java.util.ArrayList;

/**
 *
 * @brief Formula for %K and %D respectively:
 *      %K = (current close - lowest low)/(highest high - lowest low)*100
 *      %D = 3-day SMA of %K
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class StochasticOscillator {
    /**
     * The last traded price
     */
    private double currentPrice;
    
    /**
     * The trade of the lowest price over a given period of time 
     */
    private double lowestLow=0;
    
    /**
     * The trade of the highest price over a given period of time 
     */
    private double highestHigh=0;
    
    /**
     * The normal value used to calculate %D
     */
    private final int NUM_DAYS = 3;
    
    /**
     * The SMA is used in the calculation of %D 
     */
    private SMA sma;
    
    /**
     * The lower bound on %K and %D to determine is an instrument is 
     * oversold(undervalue)
     */
    private int lowerBound;
    
    /**
     * @brief The upper bound on %K and %D to determine is an instrument is 
     * overbought(overvalued)
     */
    private int upperBound;
    
    /**
     * @brief Value to store %K
     */
    private double k;
    
    /**
     * @brief Value to store %D
     */
    private double d;

    /**
     * @brief Stores the %K values calculated that will be used in calculations
     */
    private ArrayList<Double> kValues;
    
    /**
     * The number of days/trades to be considered when getting the 
     * highest high and lowest low
     */
    private int period = 14;
    
    /**
     * An object with all the MarketEntryAttempts(including 
     * MatchedMarketEntryAttempt objects) and functions to be used in 
     * calculation of %K and %D.
     */
    private MarketEntryAttemptBook book;
    
    public StochasticOscillator()
    {
        lowerBound = 20;
        upperBound = 80;
        kValues = new ArrayList<Double>();
    }
    
    /**
     * 
     * @param _book 
     */
    public StochasticOscillator(MarketEntryAttemptBook _book)
    {
        lowerBound = 20;
        upperBound = 80;
        kValues = new ArrayList<Double>();
        this.book = _book;
    }
    
    /**
     * 
     * @param _lowerBound
     * @param _upperBound
     * @param _book 
     */
    public StochasticOscillator(int _lowerBound, int _upperBound,MarketEntryAttemptBook _book)
    {
        lowerBound = _lowerBound;
        upperBound = _upperBound;
        kValues = new ArrayList<Double>();
        this.book = _book;
    }
    
    public double calculateK()
    {
        if (highestHigh == 0 || lowestLow == 0)
        {    
            highestHigh = book.getHighestTradePrice(period);
            lowestLow = book.getLowestTradePrice(period);
        }
        
        k = (currentPrice - lowestLow)/(highestHigh - lowestLow)*100;
        setKValues(k);
        return k;
    }
    
    public double calculateD() throws NotEnoughDataException
    {
        if (kValues.isEmpty() || kValues.size() < NUM_DAYS)
            throw new NotEnoughDataException();

        double total =0;
        int start = (kValues.size()==3)?0:kValues.size()-NUM_DAYS;
        
        for (int i = start; i<kValues.size();i++ )
        {
            total += kValues.get(i);
        }
 
        d = total/NUM_DAYS;
        return d;
    }
    
    public double getD()
    {
        return d;
    }
    
    public double getK()
    {
        return k;
    }
    
    public void setPeriod(int _period)
    {
        this.period = _period;
    }
    
    public void setKValues(double _kValue)
    {
        kValues.add(_kValue);
    }
    
    public void setLowestLow(double low)
    {
        this.lowestLow = low;
    }
    
    public void setHighestHigh(double high)
    {
        this.highestHigh = high;
    }
    
    public void setCurrentPrice(double current)
    {
        this.currentPrice = current;
    }
    public ArrayList<Double> getKValues()
    {
        return kValues;
    }
    public int getPeriod()
    {
        return period;
    }
    
    public int getNumDays()
    {
        return this.NUM_DAYS;
    }
}
