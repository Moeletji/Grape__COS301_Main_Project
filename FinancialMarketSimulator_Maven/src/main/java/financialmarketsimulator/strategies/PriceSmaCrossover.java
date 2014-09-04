package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.SMA;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketExchange;
import financialmarketsimulator.market.MarketStrategy;
import static financialmarketsimulator.strategies.Crossover.HigherAverage.price;
import static financialmarketsimulator.strategies.Crossover.HigherAverage.sma;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Vector;

/**
 *
 * @Brief Price-SMA Crossover Strategy
 */
public class PriceSmaCrossover extends Crossover {

    private SMA smaObj;
    private final Vector<Double> closingSmas;
    private final Vector<Double> closingStockPrice;
    //indicator1 = Price
    //indicator2 = SMA

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
        double smaCurr = smaObj.calculateSMA();
        double priceCurr = data.getLastTradePrice(); //smaObj.calculateSMA();

        if ((smaCurr > priceCurr)) // && (emaObj.getPreviousEMAValue() < smaObj.getPreviousSMAValue()) )
        {
            //Generate Buy Signal
            System.out.println("Price SMA Crossover : BUY SIGNAL.");
            this.signalDetails.setSignal(MarketStrategy.SIGNAL.BUY);
            return this.signalDetails;
        } else if ((smaCurr < priceCurr)) //&& (emaObj.getPreviousEMAValue()> smaObj.getPreviousSMAValue()) )
        {
            //Generate Sell Signal
            System.out.println("Price SMA Crossover : SELL SIGNAL.");
            this.signalDetails.setSignal(MarketStrategy.SIGNAL.SELL);
            return this.signalDetails;
        }
        else
        {
            signalDetails.setSignal(MarketStrategy.SIGNAL.DO_NOTHING);
            return this.signalDetails;
        }
    }
}
