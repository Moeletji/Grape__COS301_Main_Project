//Error reading included file Templates/Classes/Templates/Licenses/license-Financial Market Simulator Licence.txt
package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.MACD;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketStrategy;
import java.util.Date;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MACDStrategy extends MarketStrategy{

    /**
     * Singleton instance
     */
    private static MACDStrategy instance = null;
    private final MarketEntryAttemptBook data;
    private final MACD macd;

    private MACDStrategy(MarketEntryAttemptBook _data) throws NotEnoughDataException {
        super("MACD Strategy");
        this.data = _data;
        macd = MACD.getInstance(this.data);
    }
    
    public static MACDStrategy getInstance(MarketEntryAttemptBook _book) {
        if (instance == null) {
            try {
                instance = new MACDStrategy(_book);
            } catch (NotEnoughDataException ex) {
                System.out.println("MACD STrategy - not enough data exception");
            }
        }
        return instance;
    }
    
    @Override
    public synchronized SignalMessage trade() throws NotEnoughDataException {
        double currentValue = macd.calculateMACDValue();
        double prevMACDValue = macd.getPreviousMACDValue();
        //double signalLine = macd.calculateSignalValue();
        if (prevMACDValue > 0 && currentValue < 0) {
            //buy
            System.out.println(new Date().toString() + " BUY");
            this.signalDetails.setSignal(SIGNAL.BID);
            this.signalDetails.setVolaility(VOLATILITY.NORMAL);
            return this.signalDetails;
        }
        else if (prevMACDValue < 0 && currentValue > 0) {
            //sell
            System.out.println(new Date().toString() + " SELL");
            this.signalDetails.setSignal(SIGNAL.OFFER);
            this.signalDetails.setVolaility(VOLATILITY.NORMAL);
            return this.signalDetails;
        }
        else
        {
            this.signalDetails.setSignal(SIGNAL.DO_NOTHING);
            this.signalDetails.setVolaility(VOLATILITY.NORMAL);
            return this.signalDetails;
        }
    }
 
}
