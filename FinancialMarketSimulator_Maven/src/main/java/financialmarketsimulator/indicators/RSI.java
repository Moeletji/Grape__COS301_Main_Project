package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;

/**
 * @brief Relative Strength Index
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class RSI {

    /**
     * Variable housing RSI value
     */
    private double RSIValue;
    /**
     * Variable housing RS value
     */
    private double relativeStrength;
    
    //Variables housing current and previous closing values
    
    private final double currentClose;
    private final double previousClose;
    private double currentUpClose;
    private double currentDownClose;
    private double previousUpClose;
    private double previousDownClose;
    private final MarketEntryAttemptBook book; 
    private final int numDays;
    
    public RSI(MarketEntryAttemptBook _book, int _numDays)
    {
        this.book = _book;
        this.numDays = _numDays;
        this.previousClose = 0.0;
        this.currentClose = this.book.getLastTradePrice();
        this.currentUpClose = this.book.getHighestTradePrice(this.numDays);
        this.currentDownClose = this.book.getLowestTradePrice(this.numDays);
    }
    
    /**
     * @param _currentUpClose The current up close value
     * @param _currentDownClose The current down close value
     * @param _currentClose The current close
     * @param _previousClose The previous close
     */
    /*public RSI(double _currentUpClose, double _currentDownClose, double _currentClose, double _previousClose) {
        currentUpClose = _currentUpClose;
        currentDownClose = _currentDownClose;
        currentClose = _currentClose;
        previousClose = _previousClose;
    }*/

    /**
     * @brief Calculates and returns the RSI value
     * @return Double value representing the RSI value
     */
    public double calculateRSI() throws NotEnoughDataException {
        RSIValue = 100 - (100 / (1 + calculateRS()));
        return RSIValue;
    }

    /**
     * @brief Calculates and returns the Relative Strength over 14 days
     * @return Double value representing the relative strength value.
     * @todo Calculate Relative Strength.
     */
    public double calculateRS() throws NotEnoughDataException {
        EMA emaUp = new EMA(book,14);
        EMA emaDown = new EMA(book,14);
        
        previousUpClose = currentUpClose;
        previousDownClose = currentDownClose;
        currentUpClose = (currentClose > previousClose) ? currentClose-previousClose : 0;
        currentDownClose = (currentClose < previousClose) ? previousClose-currentClose : 0;
        
        emaUp.setCurrentPrice(currentUpClose);
        emaUp.setPreviousEMAValue(previousUpClose);
        emaDown.setCurrentPrice(currentDownClose);
        emaDown.setPreviousEMAValue(previousDownClose);
        
        relativeStrength = emaUp.calculateEMA()/emaDown.calculateEMA();
        return relativeStrength;
    }
    
    public double getRSI()
    {
        return this.RSIValue;
    }
    
    public double getRS()
    {
        return this.relativeStrength;
    }
}
