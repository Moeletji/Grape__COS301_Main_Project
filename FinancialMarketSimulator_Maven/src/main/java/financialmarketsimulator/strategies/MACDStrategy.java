//Error reading included file Templates/Classes/Templates/Licenses/license-Financial Market Simulator Licence.txt
package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.MACD;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketStrategy;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MACDStrategy extends MarketStrategy{

    private MarketEntryAttemptBook data;
    private MACD macd;

    public MACDStrategy(MarketEntryAttemptBook _data) throws NotEnoughDataException {
        super("MACD Strategy");
        this.data = _data;
        macd = new MACD(this.data);
    }
    
    @Override
    public SignalMessage trade() throws NotEnoughDataException {
        double currentValue = macd.calculateMACDValue();
        double prevMACDValue = macd.getPreviousMACDValue();
        //double signalLine = macd.calculateSignalValue();
        if (prevMACDValue > 0 && currentValue < 0) {
            //buy
            System.out.println(new Date().toString() + " BUY");
            this.signalDetails.setSignal(MarketStrategy.SIGNAL.BID);
            this.signalDetails.setVolaility(VOLATILITY.NORMAL);
            return this.signalDetails;
        }
        else if (prevMACDValue < 0 && currentValue > 0) {
            //sell
            System.out.println(new Date().toString() + " SELL");
            this.signalDetails.setSignal(MarketStrategy.SIGNAL.OFFER);
            this.signalDetails.setVolaility(VOLATILITY.NORMAL);
            return this.signalDetails;
        }
        else
        {
            this.signalDetails.setSignal(MarketStrategy.SIGNAL.DO_NOTHING);
            this.signalDetails.setVolaility(VOLATILITY.NORMAL);
            return this.signalDetails;
        }
    }
 
}
