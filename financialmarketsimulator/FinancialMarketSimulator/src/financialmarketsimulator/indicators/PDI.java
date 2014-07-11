package financialmarketsimulator.indicators;

/**
 *
 * @brief Positive Directional Indicator
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

    /**
     *
     * @param _todaysHigh Todays highest PDI value
     * @param _todaysLow Todays lowest PDIvalue
     * @param _prevClosing Yesterdays stock closing value
     */
    public PDI(double _todaysHigh, double _todaysLow, double _prevClosing) {
        prevValue = currValue = 0;
        todaysHigh = _todaysHigh;
        todaysLow = _todaysLow;
        prevClosing = _prevClosing;
    }

    /**
     * 
     * @param _currPDM The current positive directional movement
     * @param _prevPDM The previous positive directional movement
     * @return Returns the current positive directional indicator
     */
    public double calculatePDI(double _currPDM, double _prevPDM) {
        EMA ema = new EMA(14);
        ATR atr = new ATR(todaysHigh, todaysLow, prevClosing);
        
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
}
