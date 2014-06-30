package financialmarketsimulator.strategies;

/**
 * @brief SMA(Simple Moving Average). This technical indicator is the average
 * of the closing prices
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */


public class SMA {
    
    /**
     * Number of days
     */
    private int numOfDays;
    
    public SMA(int numDays)
    {
        numOfDays = numDays;
    }
    
    public double calculateSMA()
    {
        double sum = 0.0;
        return sum/numOfDays;
    }
}
