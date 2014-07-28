package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;

/**
 * @brief MACD(Moving Average Convergence Divergence). This is a technical
 * indicator which is used to measure momentum of a particular stock to indicate
 * buying and selling signals.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MACD {

    private double currentMACDValue;
    private double previousMACDValue;
    private final int LONG_DAY = 26;
    private final int SHORT_DAY = 12;
    private final int AVE_DAY = 9;
    
    private double signalValue;
    
    private MarketEntryAttemptBook data;

    public MACD() {
        //currentMACDValue = previousMACDValue;
        
    }

    public MACD(MarketEntryAttemptBook _data)
    {
        this.data = _data;
    }
    
    public double calculateMACDValue() throws NotEnoughDataException {
        EMA longEMA = new EMA(this.data, LONG_DAY);
        EMA shortEMA = new EMA(this.data, SHORT_DAY);
        currentMACDValue = longEMA.calculateEMA() - shortEMA.calculateEMA();
        return currentMACDValue;
    }
    
    public double calculateMACDValue(EMA _short, EMA _long) throws NotEnoughDataException
    {
        EMA longEMA = _long;
        EMA shortEMA = _short;
        currentMACDValue = longEMA.calculateEMA() - shortEMA.calculateEMA();
        return currentMACDValue;
    }
    
    public double calculateSignalValue()
    {
        return 0.0;
    }

    public double getCurrentMACDValue() {
        return currentMACDValue;
    }

    public double getPreviousMACDValue() {
        return previousMACDValue;
    }
    
    public void setCurrentMACDValue(double curr)
    {
        currentMACDValue = curr;
    }
    
    public void setPreviousMACDValue(double prev)
    {
        previousMACDValue = prev;
    }
    
    public void setSignalValue(double signal)
    {
        signalValue = signal;
    }
    
    public double getSignaValue()
    {
        return signalValue;
    }
            
}
