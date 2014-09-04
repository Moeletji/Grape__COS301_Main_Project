
package financialmarketsimulator.strategies;

import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketStrategy;

/**
 *
 * @brief Filter Strategy
 */
public abstract class Filter extends MarketStrategy{
    
    /**
     * The type of filter strategy it is.
     */
    protected String filterType;
    
    private final MarketEntryAttemptBook book;
    
    public Filter(MarketEntryAttemptBook book)
    {
        super("Filter");
        
        this.book = book;
    }
    
    @Override
    public abstract SignalDetails trade();
}
