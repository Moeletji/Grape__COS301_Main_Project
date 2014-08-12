//Error reading included file Templates/Classes/Templates/Licenses/license-Financial Market Simulator Licence.txt
package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.MACD;
import financialmarketsimulator.indicators.SMA;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */


public class MACDStrategy {
    private SMA sma;
    private MACD macd;
    
    private Vector<Double> prevMACDValues;
    
    private MarketEntryAttemptBook data;
    
    public MACDStrategy(MarketEntryAttemptBook _data) throws NotEnoughDataException
    {
        this.data = _data;
        sma = new SMA(this.data, 12);
        macd = new MACD(this.data);
        macd.setPreviousMACDValue(sma.calculateSMA());
        prevMACDValues.add(macd.getPreviousMACDValue());
    }
    
    
    public void generateMarketEntryAttempt() throws NotEnoughDataException
    {
        double currentValue = macd.calculateMACDValue();
        if (prevMACDValues.lastElement() > 0 && currentValue < 0)
        {
            //buy
            System.out.println(new Date().toString() + " BUY");
            prevMACDValues.add(currentValue);
        }
        //else
        if (prevMACDValues.lastElement() < 0 && currentValue > 0)
        {
           //sell
            System.out.println(new Date().toString() + " SELL");
            prevMACDValues.add(currentValue);
        }
        prevMACDValues.add(currentValue);
        //System.out.println("The current MACD value:" + currentValue);
    }
}
