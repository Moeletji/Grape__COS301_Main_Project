package financialmarketsimulator.indicators;

/**
 * @brief ADX (Average Directional Index).
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class ADX {

    /**
     * Current period directional movements
     */
    private double upMove;
    private double downMove;
    private double posDM;
    private double negDM;
    private final int numberOfDays = 14;

    public ADX() {
    }

    /**
     * @brief calculates the Average Directional Index
     * @return returns the average directional index
     */
    public double calculateADX() {
        double adxValue;
        double posDI = 0.0;
        double negDI = 0.0;
        EMA emaValue = new EMA(numberOfDays);

        if (upMove > downMove && upMove > 0) {
            posDM = upMove;
        } else {
            posDM = 0;
        }

        if (downMove > upMove && downMove > 0) {
            negDM = downMove;
        } else {
            negDM = 0;
        }

        //**************************
        //Must complete from here down
        //**************************
        //Calculation of the Average True Range
        adxValue = 0.0;

        return adxValue;
    }
}
