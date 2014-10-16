package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.indicators.SMA;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.*;
import static financialmarketsimulator.market.MarketStrategy.VOLATILITY.*;
import java.util.Vector;

/**
 *
 * @brief Moving Average Crossover strategy
 */
public class MovingAverageCrossover extends Crossover {

    /**
     * Singleton instance
     */
    private static MovingAverageCrossover instance = null;
    private final SMA smaObj;
    private final EMA emaObj;
    private final Vector<Double> closingEmas;
    private final Vector<Double> closingSmas;

    //The following variables are declared here as to be memory efficient when the
    //trade method is consistanctly called.
    double emaCurr;
    double smaCurr;

    //indicator1 = EMA
    //indicator2 = SMA
    @SuppressWarnings("Convert2Diamond")
    private MovingAverageCrossover(MarketEntryAttemptBook _data, int _numDays) throws NotEnoughDataException {
        super(_data, _numDays, "EMA", "SMA");
        emaObj = EMA.getInstance(this.data, numDays);
        smaObj = SMA.getInstance(this.data, numDays);
        closingEmas = new Vector<>();
        closingSmas = new Vector<>();

        closingEmas.add(emaObj.getPreviousEMAValue());
        closingSmas.add(smaObj.getPreviousSMAValue());
    }
    
    public static MovingAverageCrossover getInstance(MarketEntryAttemptBook _book, int _numDays) {
        if (instance == null) {
            try {
                instance = new MovingAverageCrossover(_book, _numDays);
            } catch (NotEnoughDataException ex) {
                System.out.println("Moving average crossover - not enough data exception");
            }
        }
        return instance;
    }

    @Override
    public SignalMessage trade() throws NotEnoughDataException {

        emaCurr = emaObj.calculateEMA();
        smaCurr = smaObj.calculateSMA();

        if ((emaCurr > smaCurr) && (emaObj.getPreviousEMAValue() < smaObj.getPreviousSMAValue())) {
            //Generate Buy Signal
            this.signalDetails.setSignal(BID);
        } else if ((emaCurr < smaCurr) && (emaObj.getPreviousEMAValue() > smaObj.getPreviousSMAValue())) {
            //Generate Sell Signal
            this.signalDetails.setSignal(OFFER);
        } else {
            this.signalDetails.setSignal(DO_NOTHING);
        }

        this.signalDetails.setVolaility(NORMAL);
        return this.signalDetails;
    }
}
