package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;

/**
 * @brief SMA(Simple Moving Average). This technical indicator is the average of
 * the closing prices
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class SMA {

    /**
     * Number of days
     */
    private int numOfDays;
    
    /**
     * The last SMA value calculated
     */
    private double previousSMAValue = 0;
    
    /**
     * An MarketEntryAttemptBook object with all the MarketEntryAttempts(including 
     * MatchedMarketEntryAttempt objects) and functions to be used in 
     * calculation the SMA.
     */
    private MarketEntryAttemptBook book;
    
    /**
     * Current SMA value calculated
     */
    private double currentSmaValue;
    
    public SMA(int numDays)
    {
        numOfDays = numDays;
    }
    
    public SMA(int numDays, MarketEntryAttemptBook _book) {
        numOfDays = numDays;
        this.book = _book;
    }

    public double calculateSMA() {
        double sum = 0.0;
        currentSmaValue = sum / numOfDays;
        return sum / currentSmaValue;
    }
    
    public double calculateSMA(double total) {
        double sum = total;
        previousSMAValue = sum / numOfDays;
        return sum / numOfDays;
    }

    public double getPreviousSMAValue() {
        return previousSMAValue;
    }
    
    public double calculateSD()
    { 
        //Math.sqrt((x-currentSmaValue)*(x-currentSmaValue));
        return 0.0;
    }
            
}
