
package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;
import static java.lang.Math.*;

/**
 * @brief This class calculates the Average True Range
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class ATR extends MarketIndicator{
    private double previousATR;
    private double currentATR;
    private final int numDays;
    private double currentTrueRange;
    private double currentHigh;
    private double currentLow;
    private double previousClosing;
    private final MarketEntryAttemptBook book;
    private Double val1, val2, val3;
    
    public ATR(MarketEntryAttemptBook _book, int _numDays)
    {
        super("Average True Range");
        book = _book;
        if(_numDays <= 0) numDays = 14; else numDays = _numDays;
        currentHigh = book.getHighestTradePrice(numDays);
        currentLow = book.getLowestTradePrice(numDays);
        previousClosing = book.getLastTradePrice(); //Might need to be changed
    }
    
    public double calculateATR()
    {
        double temp = currentATR;
        setTrueRange();
        currentATR = ( (previousATR * (numDays - 1)) + currentTrueRange) / numDays;
        previousATR = temp;
        return currentATR;
    }
    
    public double getTrueRange()
    {
        return currentTrueRange;
    }
    
    public void setTrueRange()
    {
        setCurrentHight(book.getHighestTradePrice(numDays));
        setCurrentLow(book.getLowestTradePrice(numDays));
        setPreviousClosing(book.getLastTradePrice());
        
        val1 = currentHigh - currentLow;
        val2 = abs(currentHigh - previousClosing);
        val3 = abs(currentLow - previousClosing);
        currentTrueRange = max(max(val1,val2),val3);
    }
    
    public void setPreviousATR(double prev)
    {
        previousATR = prev;
    }
    
    public void setCurrentHight(double val)
    {
        currentHigh = val;
    }
    
    public void setCurrentLow(double val)
    {
        currentLow = val;
    }
    
    public void setPreviousClosing(double val)
    {
        previousClosing = val;
    }

    @Override
    public Double calculateIndicator() {
        return this.calculateATR();
    }
}
