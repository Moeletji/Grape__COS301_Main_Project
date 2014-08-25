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

    private MarketEntryAttemptBook data;
    private MACD macd;

    public MACDStrategy(MarketEntryAttemptBook _data) throws NotEnoughDataException {
        this.data = _data;
        macd = new MACD(this.data);
    }

    public void generateMarketEntryAttempt() throws NotEnoughDataException {
        double currentValue = macd.calculateMACDValue();
        double prevMACDValue = macd.getPreviousMACDValue();
        //double signalLine = macd.calculateSignalValue();
        if (prevMACDValue > 0 && currentValue < 0) {
            //buy
            System.out.println(new Date().toString() + " BUY");
        }
        //else
        if (prevMACDValue < 0 && currentValue > 0) {
            //sell
            System.out.println(new Date().toString() + " SELL");
        }
    }
}
