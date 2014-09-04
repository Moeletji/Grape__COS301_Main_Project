package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.indicators.SMA;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketExchange;
import static financialmarketsimulator.strategies.Crossover.HigherAverage.ema;
import static financialmarketsimulator.strategies.Crossover.HigherAverage.sma;
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
    public MovingAverageCrossover(MarketExchange exchange, MarketEntryAttemptBook _data, int _numDays) throws NotEnoughDataException {
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
        //Implement one trade instance here, infinite loop is in MarketParticipant
        double emaCurr = emaObj.calculateEMA();
        double smaCurr = smaObj.calculateSMA();

        if ((emaCurr > smaCurr) && (emaObj.getPreviousEMAValue() < smaObj.getPreviousSMAValue())) {
            //Generate Buy Signal
            System.out.println("Moving Average Crossover : BUY SIGNAL.");
            this.signalDetails.setSignal(SIGNAL.BUY);
            return this.signalDetails;
        } else if ((emaCurr < smaCurr) && (emaObj.getPreviousEMAValue() > smaObj.getPreviousSMAValue())) {
            //Generate Sell Signal
            System.out.println("Moving Average Crossover : SELL SIGNAL.");
            this.signalDetails.setSignal(SIGNAL.SELL);
            return this.signalDetails;
        } else {
            this.signalDetails.setSignal(SIGNAL.DO_NOTHING);
            return this.signalDetails;
        }
    }
}
