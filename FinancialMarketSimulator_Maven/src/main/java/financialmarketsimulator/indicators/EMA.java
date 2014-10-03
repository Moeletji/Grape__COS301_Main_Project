package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;
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

    public EMA(int numDays)
    {
        super("Exponential Moving Average");
        this.numOfDays = numDays;
    }
    
    /**
     * @param _data
     * @brief Constructor for the EMA class
     * @param numDays
     */
    public EMA(MarketEntryAttemptBook _data, int numDays) {
        super("Exponential Moving Average");
        this.numOfDays = numDays;
        this.data = _data;
        previousEMAValue = new SMA(this.data,numOfDays).calculateSMA();
        this.setCurrentPrice(data.getLastTradePrice());
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
        
        double k = 2 / (numOfDays + 1);
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

    @Override
    public Double calculateIndicator() {
        return this.calculateEMA();
    }
}
