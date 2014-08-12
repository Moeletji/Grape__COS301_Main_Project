package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;

/**
 * @brief EMA(Exponential Moving Average) EMA places more weight on the most
 * recent closing price and less weight on the other prices(closing past prices)
 * and will be used to calculate other technical indicators.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class EMA {

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

    public EMA(int numDays)
    {
        this.numOfDays = numDays;

    }
    
    /**
     * @brief Constructor for the EMA class
     * @param numDays
     */
    public EMA(MarketEntryAttemptBook _data, int numDays) throws NotEnoughDataException {
        this.numOfDays = numDays;
        this.data = _data;
        previousEMAValue = new SMA(this.data,numOfDays).calculateSMA();
    }

    @SuppressWarnings("UnusedAssignment")
    public double calculateEMA() throws NotEnoughDataException {

        if ((numOfDays <= 0) || (this.getCurrentPrice() <= 0)) {
           return 0.0;// throw new NotEnoughDataException();
        }

        double k = 2 / (numOfDays + 1);
        double currentEmaValue = ((currentPrice * k) + (previousEMAValue * (1 - k)));
        //System.out.println("The current EMA: "+ currentEmaValue);
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
        return (data.getMatchedOrders().isEmpty())?0.0:data.getMatchedOrders().lastElement().getPrice();
    }
}
