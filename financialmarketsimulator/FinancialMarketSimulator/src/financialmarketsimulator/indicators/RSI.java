package financialmarketsimulator.indicators;

/**
 *
 * @brief Relative Strength Index
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
    
    /**
     * @param _currentUpClose The current up close value
     * @param _currentDownClose The current down close value
     * @param _currentClose The current close
     * @param _previousClose The previous close
     */
    public RSI(double _currentUpClose, double _currentDownClose, double _currentClose, double _previousClose) {
        currentUpClose = _currentUpClose;
        currentDownClose = _currentDownClose;
        currentClose = _currentClose;
        previousClose = _previousClose;
    }

    /**
     * @brief Calculates and returns the RSI value
     * @return Double value representing the RSI value
     */
    public double calculateRSI() {
        RSIValue = 100 - (100 / (1 + calculateRS()));
        return RSIValue;
    }

    /**
     * @brief Calculates and returns the Relative Strength over 14 days
     * @return Double value representing the relative strength value.
     * @todo Calculate Relative Strength.
     */
    public double calculateRS() {
        EMA emaUp = new EMA(14);
        EMA emaDown = new EMA(14);
        
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
}
