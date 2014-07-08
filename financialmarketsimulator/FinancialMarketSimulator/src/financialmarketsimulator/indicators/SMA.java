package financialmarketsimulator.indicators;

/**
 * @brief SMA(Simple Moving Average). This technical indicator is the average of
 * the closing prices
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class SMA {

    /**
     * Number of days
     */
    private int numOfDays;

    private double previousSMAValue = 0;
    
    private double currentSmaValue;

    public SMA(int numDays) {
        numOfDays = numDays;
    }

    public double calculateSMA() {
        double sum = 0.0;
        currentSmaValue = sum / numOfDays;
        return sum / currentSmaValue;
    }
    
    public double calculateSMA(double total) {
        double sum = total;
        previousSMAValue = sum / numOfDays;
        return sum / numOfDays;
    }

    public double getPreviousSMAValue() {
        return previousSMAValue;
    }
    
    public double calculateSD()
    { 
        //Math.sqrt((x-currentSmaValue)*(x-currentSmaValue));
        return 0.0;
    }
            
}
