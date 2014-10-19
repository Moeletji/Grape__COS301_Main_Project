package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;

/**
 * @brief EMA(Exponential Moving Average) EMA places more weight on the most
 * recent closing price and less weight on the other prices(closing past prices)
 * and will be used to calculate other technical indicators.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public final class EMA extends MarketIndicator{

    /**
     * Singleton instance
     */
    private static EMA instance = null;
    /**
     * Number of days in EMA
     */
    private int numOfDays;
    /**
     * The previous EMA value that was calculated
     */
    private double previousEMAValue;
    /**
     * Todays closing price
     */
    private double currentPrice;
    /**
     * Data with traded prices
     */
    private MarketEntryAttemptBook data;
    /**
     * The current ema value
     */
    private double currentEmaValue;

    private EMA(int numDays)
    {
        super("Exponential Moving Average");
        this.numOfDays = numDays;
    }
    
    /**
     * @param _data
     * @brief Constructor for the EMA class
     * @param numDays
     */
    private EMA(MarketEntryAttemptBook _data, int numDays) {
        super("Exponential Moving Average");
        this.numOfDays = numDays;
        this.data = _data;
        previousEMAValue = SMA.getInstance(this.data,numOfDays).calculateSMA();
        this.setCurrentPrice(data.getLastTradePrice());
    }
    
    public static EMA getInstance(int _numDays) {
        if (instance == null) {
            instance = new EMA(_numDays);
        }
        return instance;
    }
    
    public static EMA getInstance(MarketEntryAttemptBook _book, int _numDays) {
        if (instance == null) {
            instance = new EMA(_book, _numDays);
        }
        return instance;
    }

    @SuppressWarnings("UnusedAssignment")
    public double calculateEMA() {
        this.setCurrentPrice(this.data.getLastTradePrice());
        if ((numOfDays <= 0) || (this.getCurrentPrice() <= 0)) {
           return 0.0;
        }
        previousEMAValue = currentEmaValue;
        double k = 2 / (numOfDays + 1);
        currentEmaValue = ((this.getCurrentPrice() * k) + (previousEMAValue * (1 - k)));
        return currentEmaValue;
    }
    
     public double calculateEMA(double prev, double current, int numDays) {

        if ((numDays <= 0) || (current == 0) || (prev == 0)) {
           return 0.0;
        }
        currentPrice = current;
        previousEMAValue = prev;
        numOfDays = numDays;
        
        double k = 2.0 / (numOfDays + 1);
        double currentEmaValue = ((currentPrice * k) + (previousEMAValue * (1 - k)));
        return currentEmaValue;
    }

    public int getNumberOfDays() {
        return numOfDays;
    }
    
    public void setPreviousEMAValue(double previous)
    {
        previousEMAValue = previous;
    }
    
    public void setCurrentPrice(double current)
    {
        currentPrice = current;
    }
    
    public double getPreviousEMAValue()
    {
        return previousEMAValue;
    }
    
    public double getCurrentPrice()
    {
        return this.data.getLastTradePrice();
    }
    
    public double getMultiplier()
    {
        return (2.0 / (numOfDays + 1)); 
    }
    @Override
    public Double calculateIndicator() {
        return this.calculateEMA();
    }
}
