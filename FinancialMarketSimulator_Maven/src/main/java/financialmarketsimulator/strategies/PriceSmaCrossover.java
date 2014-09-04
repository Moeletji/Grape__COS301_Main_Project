package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.SMA;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketExchange;
import financialmarketsimulator.market.MarketStrategy;
import java.util.Vector;

/**
 *
 * @Brief Price-SMA Crossover Strategy
 */
public class PriceSmaCrossover extends Crossover {

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
    public PriceSmaCrossover(MarketExchange exchange, MarketEntryAttemptBook _data, int _numDays) {
        super(_data, _numDays, "Price", "SMA");
        smaObj = new SMA(this.data, _numDays);

        closingSmas = new Vector<>();
        closingStockPrice = new Vector<>();

        //TODO : Populate with objects housing closing Price and EMA values over the
        //specidies previous numDays days.
        closingSmas.add(smaObj.getPreviousSMAValue());
        closingStockPrice.add(_data.getHighestTradePrice(numDays));

    }

    @Override
    public SignalDetails trade() throws NotEnoughDataException {
        //Implement one trade instance here, infinite loop is in MarketParticipant
        smaCurr = smaObj.calculateSMA();
        smaPrev = smaObj.getPreviousSMAValue();
        priceCurr = data.getLastTradePrice(); //smaObj.calculateSMA();
        previousPrice = data.getPreviousTradePrice();

        if ((smaCurr > priceCurr) && (smaPrev < previousPrice)) {
            //Generate Buy Signal
            System.out.println("Price SMA Crossover : BUY SIGNAL.");
            this.signalDetails.setSignal(MarketStrategy.SIGNAL.BUY);
        } else if ((smaCurr < priceCurr) && (smaPrev > previousPrice)) {
            //Generate Sell Signal
            System.out.println("Price SMA Crossover : SELL SIGNAL.");
            this.signalDetails.setSignal(MarketStrategy.SIGNAL.SELL);
        } else {
            signalDetails.setSignal(MarketStrategy.SIGNAL.DO_NOTHING);
        }
        return this.signalDetails;
    }
}
