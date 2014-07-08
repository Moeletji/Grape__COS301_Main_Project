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

    public double calculatePDI() {
        EMA ema = new EMA(14);
        ATR atr = new ATR(todaysHigh, todaysLow, prevClosing);
        ema.setCurrentPrice(currValue);
        ema.setPreviousEMAValue(prevValue);
        prevValue = currValue;
        currValue = (100 * ema.calculateEMA() / atr.calculateATR());
        return currValue;
    }

    public double getPrevValue() {
        return prevValue;
    }
}
