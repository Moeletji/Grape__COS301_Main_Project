
package financialmarketsimulator.indicators;

import static java.lang.Math.*;

/**
 *
 * @brief Average True Range
 */
public class ATR {
    private double previousATR;
    private double currentATR;
    private final double numDays;
    private double currentTrueRange;
    private double currentHigh;
    private double currentLow;
    private double previousClosing;
    
    public ATR(double currHigh, double currLow, double prevClosing)
    {
        numDays = 14;
        currentHigh = currHigh;
        currentLow = currLow;
        previousClosing = prevClosing;
    }
    
    public double calculateATR()
    {
        double temp = currentATR;
        setTrueRange();
        currentATR = (previousATR * (numDays - 1)) + (currentTrueRange/numDays);
        previousATR = temp;
        return currentATR;
    }
    
    public double getTrueRange()
    {
        return currentTrueRange;
    }
    
    public void setTrueRange()
    {
        double val1 = currentHigh - currentLow;
        double val2 = abs(currentHigh - previousClosing);
        double val3 = abs(currentLow - previousClosing);
        currentTrueRange = max(max(val1,val2),val3);
    }
    
    public void setCurrentHight(double val)
    {
        currentHigh = val;
    }
    
    public void setCurrentLow(double val)
    {
        currentLow = val;
    }
    
    public void setPreviousClosing(double val)
    {
        previousClosing = val;
    }
    
    
}