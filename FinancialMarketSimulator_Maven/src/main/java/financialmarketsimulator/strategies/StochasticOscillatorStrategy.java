/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */
package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.StochasticOscillator;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketStrategy;

/**
 *
 * @author Moeletji
 */
public class StochasticOscillatorStrategy extends MarketStrategy{
    
    private static StochasticOscillatorStrategy instance = null;
    private MarketEntryAttemptBook book;
    private double k;
    private double d;
    private StochasticOscillator so;
    private StochasticOscillatorStrategy(MarketEntryAttemptBook _book)
    {
        super("Stochastic Oscillator Strategy");
        this.book = _book;
        so = StochasticOscillator.getInstance(_book);
    }
    
    public static StochasticOscillatorStrategy getInstance(MarketEntryAttemptBook _book)
    {
        if (instance == null) {
            instance = new StochasticOscillatorStrategy(_book);
        }
        return instance;
    }
    
    @Override
    public synchronized SignalMessage trade() throws NotEnoughDataException {
        k = so.calculateIndicator();
        d = so.calculateD();
        
        if (k >= so.getUpperBound() && d >=so.getUpperBound())
        {
            this.signalDetails.setSignal(SIGNAL.OFFER);
            this.signalDetails.setVolaility(VOLATILITY.HIGH);
        }
        else if (k <= so.getLowerBound() && d <= so.getLowerBound())
        {
            this.signalDetails.setSignal(SIGNAL.BID);
            this.signalDetails.setVolaility(VOLATILITY.HIGH);
        }
        else if (k >= so.getUpperBound() && d < so.getUpperBound())
        {
            this.signalDetails.setSignal(SIGNAL.OFFER);
            this.signalDetails.setVolaility(VOLATILITY.MEDIUM);
        }
        else if (k <= so.getLowerBound() && d > so.getLowerBound())
        {
            this.signalDetails.setSignal(SIGNAL.BID);
            this.signalDetails.setVolaility(VOLATILITY.MEDIUM);
        }
        else if (k < so.getUpperBound() && d >= so.getUpperBound())
        {
            this.signalDetails.setSignal(SIGNAL.OFFER);
            this.signalDetails.setVolaility(VOLATILITY.LOW);
        }
        else if (k > so.getLowerBound() && d <= so.getLowerBound())
        {
            this.signalDetails.setSignal(SIGNAL.BID);
            this.signalDetails.setVolaility(VOLATILITY.LOW);
        }
        else
        {
            this.signalDetails.setSignal(SIGNAL.DO_NOTHING);
            this.signalDetails.setVolaility(VOLATILITY.NORMAL);
        }
        return this.signalDetails;
    }
}
