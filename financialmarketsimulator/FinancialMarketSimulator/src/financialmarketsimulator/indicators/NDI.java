package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;

/**
 * @brief Negative Directional Indicator
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */

public class NDI {

    /**
     * Variable housing the previous value of the Negative Directional Indicator
     */
    private double prevValue;
    /**
     * Variable housing the current value of the Negative Directional Indicator
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
     * @param _todaysHigh Todays highest NDI value
     * @param _todaysLow Todays lowest NDIvalue
     * @param _prevClosing Yesterdays stock closing value
     */
    public NDI(double _todaysHigh, double _todaysLow, double _prevClosing) {
        prevValue = currValue = 0;
        todaysHigh = _todaysHigh;
        todaysLow = _todaysLow;
        prevClosing = _prevClosing;
    }

    /**
     * 
     * @param _currNDM The current negative directional movement value
     * @param _prevNDM The previous negative directional movement value
     * @return Returns the current negative directional indicator
     */
    public double calculateNDI(double _currNDM, double _prevNDM) throws NotEnoughDataException {
        EMA ema = new EMA(14);
        ATR atr = new ATR(todaysHigh, todaysLow, prevClosing);
        
        ema.setCurrentPrice(_currNDM);
        ema.setPreviousEMAValue(_prevNDM);
        
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
