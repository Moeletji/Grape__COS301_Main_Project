
package financialmarketsimulator.strategies;

import financialmarketsimulator.market.MarketExchange;
import financialmarketsimulator.market.MarketStrategy;

/**
 *
 * @brief Filter Strategy
 */
public class Filter extends MarketStrategy{
    
    public Filter(MarketExchange exchange)
    {
        super(exchange, "Filter");
    }
    
    @Override
    public void trade(){
        //Implement one trade instance here, infinite loop is in MarketParticipant
    }
}
