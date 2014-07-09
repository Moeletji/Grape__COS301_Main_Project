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
    private double relativeStrength;

    public RSI() {
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
        relativeStrength = 0;
        return relativeStrength;
    }
}
