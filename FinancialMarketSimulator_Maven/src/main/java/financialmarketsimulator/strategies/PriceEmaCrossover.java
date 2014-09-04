package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketExchange;
import financialmarketsimulator.market.MarketStrategy;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Vector;

/**
 *
 * @brief Price-EMA Crossover Strategy
 */
public class PriceEmaCrossover extends Crossover {

    private final EMA emaObj;
    private final Vector<Double> closingEmas;
    private final Vector<Double> closingStockPrice;

    //indicator1 = Price
    //indicator2 = EMA
    @SuppressWarnings("Convert2Diamond")
    public PriceEmaCrossover(MarketExchange exchange, MarketEntryAttemptBook _data, int _numDays) throws NotEnoughDataException {
        super(_data, _numDays, "Price", "EMA");
        emaObj = new EMA(this.data, _numDays);

        closingEmas = new Vector<>();
        closingStockPrice = new Vector<>();

        //TODO : Populate with objects housing closing Price and EMA values over the
        //specidies previous numDays days.
        closingEmas.add(emaObj.getPreviousEMAValue());
        closingStockPrice.add(_data.getHighestTradePrice(numDays));
        
    }

    @Override
    public SignalDetails trade() throws NotEnoughDataException {
        double emaCurr = emaObj.calculateEMA();
        double priceCurr = data.getLastTradePrice(); //smaObj.calculateSMA();

        if ((emaCurr > priceCurr)) // && (emaObj.getPreviousEMAValue() < smaObj.getPreviousSMAValue()) )
        {
            //Generate Buy Signal
            System.out.println("Price EMA Crossover : BUY SIGNAL.");
            this.signalDetails.setSignal(MarketStrategy.SIGNAL.BUY);
        } else if ((emaCurr < priceCurr)) //&& (emaObj.getPreviousEMAValue()> smaObj.getPreviousSMAValue()) )
        {
            //Generate Sell Signal
            System.out.println("Price EMA Crossover : SELL SIGNAL.");
            this.signalDetails.setSignal(MarketStrategy.SIGNAL.SELL);
        }
        else
        {
            this.signalDetails.setSignal(MarketStrategy.SIGNAL.DO_NOTHING);
        }
        return this.signalDetails;
    }
}
