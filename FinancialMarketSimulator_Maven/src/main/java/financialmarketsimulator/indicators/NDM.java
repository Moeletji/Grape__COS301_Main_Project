package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;

/**
 * @brief Negative Directional Movement
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class NDM extends MarketIndicator{

    /**
     * Variable housing the previous value of the Negative Directional Movement
     */
    private double prevValue;
    /**
     * Variable housing the current value of the Negative Directional Movement
     */
    private double currValue;
    private final MarketEntryAttemptBook book; 
    private final int numDays;
    
    public NDM(MarketEntryAttemptBook _book, int _numDays)
    {
        super("Negative Directional Movement");
        book = _book;
        numDays = _numDays;
        prevValue = book.getLastTradePrice();
    }
    
    /*public NDM() {
        prevValue = currValue = 0;
    }*/

    /**
     * @brief Sets the current value of the Negative Directional Movement
     * @param val The new value for the current Negative Directional Movement
     */
    public void setCurrValue(double val) {
        prevValue = currValue;
        currValue = val;
    }
    
    public void setPrevValue(int prev)
    {
        this.prevValue = prev;
    }

    /**
     * @brief Returns the previous value of the Negative Directional Movement
     * @return Previous Negative Directional Movement
     */
    public double getPrevValue() {
        return prevValue;
    }

    /**
     * @brief Return the current value of the Negative Directional Movement
     * @return Current Negative Directional Movement
     */
    public double getCurrValue() {
        return currValue;
    }

    @Override
    public Double calculateIndicator() throws NotEnoughDataException {
        return this.getCurrValue();
    }
}
