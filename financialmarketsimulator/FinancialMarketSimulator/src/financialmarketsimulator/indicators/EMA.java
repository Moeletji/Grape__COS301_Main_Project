package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;

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
    private double previousEMAValue;
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
        //previousEMAValue = new SMA(numOfDays).calculateSMA();
    }

    @SuppressWarnings("UnusedAssignment")
    public double calculateEMA() throws NotEnoughDataException {

        if ((numOfDays <= 0) || (currentPrice <= 0) || (previousEMAValue <= 0)) {
            throw new NotEnoughDataException();
        }

        double k = 2 / (numOfDays + 1);
        return ((currentPrice * k) + (previousEMAValue * (1 - k)));
    }

    public int getNumberOfDays() {
        return numOfDays;
    }
    
    public void setPreviousEMAValue(double previous)
    {
        previousEMAValue = previous;
    }
    
    public void setCurrentPrice(double current)
    {
        currentPrice = current;
    }
    
    public double getPreviousEMAValue()
    {
        return previousEMAValue;
    }
    
    public double getCurrentPrice()
    {
        return currentPrice;
    }
}
