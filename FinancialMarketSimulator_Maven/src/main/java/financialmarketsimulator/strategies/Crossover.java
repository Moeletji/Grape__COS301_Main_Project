package financialmarketsimulator.strategies;

import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketStrategy;

/**
 *
 * @brief Abstract Crossover Strategy class as there can be different kinds of
 * crossover strategies. Can be implemented by MovingAverage Crossover,
 * PriceVsEma Crossover and PriceVsSma Crossover.
 */
public abstract class Crossover extends MarketStrategy {
    
    /**
     * The number of days over which the strategy is observed
     */
    protected int numDays;

    protected final String ind1;
    protected final String ind2;

    protected MarketEntryAttemptBook data;
    
    public Crossover(MarketEntryAttemptBook _data, int _numDays, String _line1, String _line2) {
        super(_line1+ " & " +_line2+" - Crossover");
        this.numDays = _numDays;
        this.ind1 = _line1;
        this.ind2 = _line2;
        this.data = _data;
    }

    /**
     * @brief Sets the number of days over which the strategy is observed.
     * @param _numDays The number of days over which the strategy is observed
     */
    public void setNumberOfDays(int _numDays) {
        this.numDays = _numDays;
    }
}
