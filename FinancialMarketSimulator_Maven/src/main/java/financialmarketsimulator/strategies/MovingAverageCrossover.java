package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.indicators.SMA;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketExchange;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Vector;

/**
 *
 * @brief Moving Average Crossover strategy
 */
public class MovingAverageCrossover extends Crossover {

    private final SMA smaObj;
    private final EMA emaObj;
    private final Vector<Double> closingEmas;
    private final Vector<Double> closingSmas;

    //indicator1 = EMA
    //indicator2 = SMA
    @SuppressWarnings("Convert2Diamond")
    public MovingAverageCrossover(MarketEntryAttemptBook _data, int _numDays) throws NotEnoughDataException {
        super(_data, _numDays, "EMA", "SMA");
        emaObj = new EMA(this.data, numDays);
        smaObj = new SMA(this.data, numDays);
        closingEmas = new Vector<>();
        closingSmas = new Vector<>();

        closingEmas.add(emaObj.getPreviousEMAValue());
        closingSmas.add(smaObj.getPreviousSMAValue());
        

    }

    @Override
    public SignalDetails trade() throws NotEnoughDataException {
        
        double emaCurr = emaObj.calculateEMA();
        double smaCurr = smaObj.calculateSMA();

        if ((emaCurr > smaCurr) && (emaObj.getPreviousEMAValue() < smaObj.getPreviousSMAValue())) {
            //Generate Buy Signal
            System.out.println("Moving Average Crossover : BUY SIGNAL.");
            this.signalDetails.setSignal(SIGNAL.BUY);
        } else if ((emaCurr < smaCurr) && (emaObj.getPreviousEMAValue() > smaObj.getPreviousSMAValue())) {
            //Generate Sell Signal
            System.out.println("Moving Average Crossover : SELL SIGNAL.");
            this.signalDetails.setSignal(SIGNAL.SELL);
        } else {
            this.signalDetails.setSignal(SIGNAL.DO_NOTHING);
        }
        
        return this.signalDetails;
    }
}
