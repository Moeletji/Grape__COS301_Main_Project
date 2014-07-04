package financialmarketsimulator.indicators;

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
    
    private double previousSMAValue = 0;
    
    public SMA(int numDays)
    {
        numOfDays = numDays;
    }
    
    public double calculateSMA()
    {
        double sum = 0.0;
        previousSMAValue = sum/numOfDays;
        return sum/numOfDays;
    }
    
    public double getPreviousSMAValue() {
        return previousSMAValue;
    }
}
