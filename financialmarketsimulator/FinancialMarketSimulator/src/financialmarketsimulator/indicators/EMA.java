package financialmarketsimulator.indicators;

/**
 * @brief EMA(Exponential Moving Average) EMA places more weight on the most 
 * recent closing price and less weight on the other prices(closing past prices)
 * and will be used to calculate other technical indicators.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class EMA {

    /**
     * Number of days in EMA
     */
    private int numOfDays;
    /**
     * The previous EMA value that was calculated
     */
    private double previousEMA;
    /**
     * Todays closing price
     */
    private double currentPrice;
    
    /**
     * @brief Constructor for the EMA class
     * @param numDays 
     */
    public EMA(int numDays) {
        numOfDays = numDays;
        previousEMA = new SMA(numOfDays).calculateSMA();
    }

    @SuppressWarnings("UnusedAssignment")
    public double calculateEMA() {
        
        if ((numOfDays <= 0) || (currentPrice <= 0) || (previousEMA <= 0)) {
            return 0.0;
        }
        
        double k = 2 / (numOfDays + 1);
        return ((currentPrice * k) + (previousEMA * (1 - k)));
    }
    
    public int getNumberOfDays()
    {
        return numOfDays;
    }
}
