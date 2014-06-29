package financialmarketsimulator.strategies;

/**
 * @brief EMA(Exponential Moving Average)
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class EMA {

    /**
     * Number of days in EMA
     */
    private int numOfDays;
    /**
     * Yesterdays EMA
     */
    private double EMAYesterday;
    /**
     * Todays closing price
     */
    private double todaysPrice;

    public EMA() {

    }

    @SuppressWarnings("UnusedAssignment")
    public double calculateEMA(double closingPrice, int numDays, double prevEMA) {
        if ((numDays <= 0) || (closingPrice <= 0) || (prevEMA <= 0)) {
            return 0.0;
        }

        numOfDays = numDays;
        todaysPrice = closingPrice;
        EMAYesterday = prevEMA;

        double k = 2 / (numOfDays + 1);
        return (todaysPrice * k * EMAYesterday * (1 - k));
    }
}
