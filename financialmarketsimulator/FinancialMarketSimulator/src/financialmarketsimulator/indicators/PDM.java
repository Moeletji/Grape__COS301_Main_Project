package financialmarketsimulator.indicators;

/**
 * @brief Positive Directional Movement
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class PDM {

    /**
     * Variable housing the previous value of the Positive Directional Movement
     */
    private double prevValue;
    /**
     * Variable housing the current value of the Positive Directional Movement
     */
    private double currValue;

    public PDM() {
        prevValue = currValue = 0;
    }

    /**
     * @brief Sets the current value of the Positive Directional Movement. Also 
     * sets the previous value to the old current.
     * @param val The new value for the current Positive Directional Movement
     */
    public void setCurrValue(double val) {
        prevValue = currValue;
        currValue = val;
    }

    /**
     * @brief Returns the previous value of the Positive Directional Movement
     * @return Previous Positive Directional Movement
     */
    public double getPrevValue() {
        return prevValue;
    }

    /**
     * @brief Return the current value of the Positive Directional Movement
     * @return Current Positive Directional Movement
     */
    public double getCurrValue() {
        return currValue;
    }
}
