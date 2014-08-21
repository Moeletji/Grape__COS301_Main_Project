package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;

/**
 *
 * 
 */

/**
 * @brief Positive Directional Indicator
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class PDI {

    /**
     * Variable housing the previous value of the Positive Directional Indicator
     */
    private double prevValue;
    /**
     * Variable housing the current value of the Positive Directional Indicator
     */
    private double currValue;
    /**
     * Variable housing the previous closing price
     */
    private double prevClosing;
    /**
     * variable housing todays highest value
     */
    private double todaysHigh;
    /**
     * Variable housing todays lowest value;
     */
    private double todaysLow;
    private final int numDays;
    
    private final MarketEntryAttemptBook book;
    
    /**
     * 
     * @param _book MarketEntryAttemptBook object
     * @param _numDays number of days over which NDI must be calculated.
     */
    public PDI(MarketEntryAttemptBook _book, int _numDays)
    {
        book = _book;
        numDays = _numDays;
        currValue = todaysHigh = book.getHighestTradePrice(numDays);
        todaysLow = book.getLowestTradePrice(numDays);
        prevClosing = book.getLastTradePrice(); //Might need to be changed
        
    }
     
    /**
     *
     * @param _todaysHigh Todays highest PDI value
     * @param _todaysLow Todays lowest PDIvalue
     * @param _prevClosing Yesterdays stock closing value
     */
    /*public PDI(double _todaysHigh, double _todaysLow, double _prevClosing) {
        prevValue = currValue = 0;
        todaysHigh = _todaysHigh;
        todaysLow = _todaysLow;
        prevClosing = _prevClosing;
    }*/

    /**
     * 
     * @param _currPDM The current positive directional movement
     * @param _prevPDM The previous positive directional movement
     * @return Returns the current positive directional indicator
     */
    public double calculatePDI(double _currPDM, double _prevPDM) throws NotEnoughDataException {
        EMA ema = new EMA(book,14);
        //ATR atr = new ATR(todaysHigh, todaysLow, prevClosing);
        ATR atr = new ATR(book,numDays);
        
        ema.setCurrentPrice(_currPDM);
        ema.setPreviousEMAValue(_prevPDM);
        
        //prevValue = currValue;
        currValue = (100 * ema.calculateEMA() / atr.calculateATR());
        return currValue;
    }
    
    public void setPreviousValue(double _prev)
    {
        prevValue = _prev;
    }

    public double getPrevValue() {
        return prevValue;
    }
    
    public void setTodaysHigh(int high)
    {
        this.todaysHigh = high;
    }
    
    public void setTodaysLow(int low)
    {
        this.todaysLow = low;
    }
    
    public void setPrevClosing(int preC)
    {
        this.prevClosing = preC;
    }
}
