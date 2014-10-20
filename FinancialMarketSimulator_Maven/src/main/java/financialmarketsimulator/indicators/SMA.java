package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;

/**
 * @brief SMA(Simple Moving Average). This technical indicator is the average of
 * the closing prices
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class SMA extends MarketIndicator{

    /**
     * Singleton instance
     */
    private static SMA instance = null;
    
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
    
    private SMA(int numDays)
    {
        super("Simple Moving Average");
        numOfDays = numDays;
    }
    
    private SMA(MarketEntryAttemptBook _book ,int numDays) {
        super("Simple moving Average");
        numOfDays = numDays;
        this.book = _book;
    }
    
    public static SMA getInstance(int _numDays) {
        if (instance == null) {
            instance = new SMA(_numDays);
        }
        return instance;
    }
    
    public static SMA getInstance(MarketEntryAttemptBook _book, int _numDays) {
        if (instance == null) {
            instance = new SMA(_book, _numDays);
        }
        return instance;
    }

    @SuppressWarnings("empty-statement")
    public double calculateSMA() {
        if (book.getMatchedOrders().size() < numOfDays)
        {
            return 0.0;
        }
        previousSMAValue = currentSmaValue;
        double sum = 0.0;
        int range = book.getMatchedOrders().size() - numOfDays;
        for (int i= range; i<book.getMatchedOrders().size();i++ )
        {
            sum += book.getMatchedOrders().get(i).getPrice();
        }
        
        currentSmaValue = sum / numOfDays;
        return currentSmaValue;
    }
    
    public double calculateSMA(double total) {
        double sum = total;
        previousSMAValue = sum / numOfDays;
        return sum / numOfDays;
    }

    public double getPreviousSMAValue() {
        return previousSMAValue;
    }  
    
    public double getCurrentSMAValue()
    {
        return currentSmaValue;
    }

    @Override
    public Double calculateIndicator() {
        return this.calculateSMA();
    }
}
