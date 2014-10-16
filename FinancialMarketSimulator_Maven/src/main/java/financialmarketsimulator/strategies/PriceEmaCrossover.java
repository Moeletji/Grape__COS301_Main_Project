package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketStrategy;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.*;
import static financialmarketsimulator.market.MarketStrategy.VOLATILITY.*;
import java.util.Vector;

/**
 *
 * @brief Price-EMA Crossover Strategy
 */
public class PriceEmaCrossover extends Crossover {

    /**
     * Singleton instance
     */
    private static PriceEmaCrossover instance = null;
    private final EMA emaObj;
    private final Vector<Double> closingEmas;
    private final Vector<Double> closingStockPrice;

    //The following variables are declared here as to be memory efficient when the
    //trade method is consistanctly called.
    double emaCurr;
    double emaPrev;
    double priceCurr;
    double previousPrice;

    //indicator1 = Price
    //indicator2 = EMA
    @SuppressWarnings("Convert2Diamond")
    private PriceEmaCrossover(MarketEntryAttemptBook _data, int _numDays) throws NotEnoughDataException {
        super(_data, _numDays, "Price", "EMA");
        emaObj = EMA.getInstance(this.data, _numDays);

        closingEmas = new Vector<>();
        closingStockPrice = new Vector<>();

        //TODO : Populate with objects housing closing Price and EMA values over the
        //specidies previous numDays days.
        closingEmas.add(emaObj.getPreviousEMAValue());
        closingStockPrice.add(_data.getHighestTradePrice(numDays));
    }
    
    public static PriceEmaCrossover getInstance(MarketEntryAttemptBook _book, int _numDays) {
        if (instance == null) {
            try {
                instance = new PriceEmaCrossover(_book, _numDays);
            } catch (NotEnoughDataException ex) {
                System.out.println("Price EMA Crossover - Not enough data exception");
            }
        }
        return instance;
    }

    @Override
    public SignalMessage trade() throws NotEnoughDataException {
        emaCurr = emaObj.calculateEMA();
        emaPrev = emaObj.getPreviousEMAValue();
        priceCurr = data.getLastTradePrice(); //smaObj.calculateSMA();
        previousPrice = data.getPreviousTradePrice();

        if ((emaCurr > priceCurr) && (emaPrev < previousPrice)) {
            //Generate Buy Signal
            this.signalDetails.setSignal(MarketStrategy.SIGNAL.BID);
        } else if ((emaCurr < priceCurr) && ((emaPrev > previousPrice))) {
            //Generate Sell Signal
            this.signalDetails.setSignal(MarketStrategy.SIGNAL.OFFER);
        } else {
            this.signalDetails.setSignal(DO_NOTHING);
        }
        
        this.signalDetails.setVolaility(NORMAL);
        return this.signalDetails;
    }
}
