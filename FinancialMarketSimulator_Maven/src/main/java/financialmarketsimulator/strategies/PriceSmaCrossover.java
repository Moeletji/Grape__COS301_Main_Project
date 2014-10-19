package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.SMA;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketStrategy;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.*;
import static financialmarketsimulator.market.MarketStrategy.VOLATILITY.*;
import java.util.Vector;

/**
 *
 * @Brief Price-SMA Crossover Strategy
 */
public class PriceSmaCrossover extends Crossover {

    /**
     * Singleton instance
     */
    private static PriceSmaCrossover instance = null;
    private final SMA smaObj;
    private final Vector<Double> closingSmas;
    private final Vector<Double> closingStockPrice;

    //The following variables are declared here as to be memory efficient when the
    //trade method is consistanctly called.
    double smaCurr;
    double smaPrev;
    double priceCurr;
    double previousPrice;

    @SuppressWarnings("Convert2Diamond")
    private PriceSmaCrossover(MarketEntryAttemptBook _data, int _numDays) {
        super(_data, _numDays, "Price", "SMA");
        smaObj = SMA.getInstance(this.data, _numDays);

        closingSmas = new Vector<>();
        closingStockPrice = new Vector<>();

        //TODO : Populate with objects housing closing Price and EMA values over the
        //specidies previous numDays days.
        closingSmas.add(smaObj.getPreviousSMAValue());
        closingStockPrice.add(_data.getHighestTradePrice(numDays));

    }
    
    public static PriceSmaCrossover getInstance(MarketEntryAttemptBook _book, int _numDays) {
        if (instance == null) {
            instance = new PriceSmaCrossover(_book, _numDays);
        }
        return instance;
    }

    @Override
    public synchronized SignalMessage trade() throws NotEnoughDataException {
        
        smaCurr = smaObj.calculateSMA();
        smaPrev = smaObj.getPreviousSMAValue();
        priceCurr = data.getLastTradePrice(); //smaObj.calculateSMA();
        previousPrice = data.getPreviousTradePrice();

        if ((smaCurr > priceCurr) && (smaPrev < previousPrice)) {
            //Generate Buy Signal
            this.signalDetails.setSignal(BID);
        } else if ((smaCurr < priceCurr) && (smaPrev > previousPrice)) {
            //Generate Sell Signal
            this.signalDetails.setSignal(MarketStrategy.SIGNAL.OFFER);
        } else {
            signalDetails.setSignal(MarketStrategy.SIGNAL.DO_NOTHING);
        }
        
        this.signalDetails.setVolaility(NORMAL);
        return this.signalDetails;
    }
}
