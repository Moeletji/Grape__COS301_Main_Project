/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.*;
import static financialmarketsimulator.market.MarketStrategy.VOLATILITY.*;
import java.util.Vector;

/**
 * @brief Considers all the moving average strategies and gives an average signal
 * from those strategies.
 * @author Madimetja
 */
public class MovingAverageFilter extends Filter{
    
    /**
     * Moving Average Strategy Objects
     */
    MovingAverageEnvelope mae;
    MovingAverageCrossover mac;
    MACDStrategy macd;
    
    Vector<SIGNAL> outcome;
    int buyCount;
    int sellCount;
    double buySignalStregnth;
    double sellSignalStrength;
    
    public MovingAverageFilter(MarketEntryAttemptBook _data, int numDays) throws NotEnoughDataException 
    {
        super(_data, "Moving Average");
        mae = new MovingAverageEnvelope(_data);
        mac = new MovingAverageCrossover(_data,14); //Over 14days
        macd = new MACDStrategy(_data);
        outcome = new Vector<>();
    }

    @Override
    public SignalMessage trade() throws NotEnoughDataException{
       
        outcome.clear();
        buyCount = 0;
        buySignalStregnth = 0.0;
        sellSignalStrength = 0.0;
        outcome.add(mae.trade().getSignal());
        outcome.add(mac.trade().getSignal());
        outcome.add(macd.trade().getSignal());
        
        for(SIGNAL sig : outcome)
        {
            if(sig == BID)
            {
                buyCount++;
            }
            else if (sig == OFFER)
            {
                sellCount++;
            }
        }
        
        buySignalStregnth = (double)buyCount/(double)outcome.size();
        sellSignalStrength = (double)sellCount/(double)outcome.size();
        
        if( buySignalStregnth > 0.5 )
        {
            //Moving average buy signal is string. Generate buy signal.
            this.signalDetails.setSignal(BID);
        }
        else if ( sellSignalStrength > 0.5 )
        {
            //Moving average buy signal is strong. Generate sell signal.
            this.signalDetails.setSignal(OFFER);
        }
        else
        {
            //Both buy and sell signals are weak. Generate do_nothing signal.
            this.signalDetails.setSignal(DO_NOTHING);
        }
        
        this.signalDetails.setVolaility(NORMAL);
        return this.signalDetails;
    }
}
