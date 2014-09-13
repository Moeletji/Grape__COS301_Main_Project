/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.ADX;
import financialmarketsimulator.indicators.NDI;
import financialmarketsimulator.indicators.PDI;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketStrategy;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.*;
import static financialmarketsimulator.market.MarketStrategy.VOLATILITY.*;

/**
 * @brief This strategy works with the strength of a trend to generate signals.
 * Buy Condition - If the ADX and PDI are above 20 and NDI is below 20.
 * Sell Condition - If the ADX and NDI are above 20 and PDI is below 20.
 * @author Madimetja
 */
public class Simple_MACD_ADX extends MarketStrategy{
    
    private final MarketEntryAttemptBook book;
    private final ADX adx;
    private final MACDStrategy macdSrategy;
    private final PDI pdi;
    private final NDI ndi;
    private final int numDays;
    private SignalMessage macdSignal;
    
    //The following variables are declared here as to be memory effificient when 
    //the trade method is consistently called.
    private double adxValue;
    private double pdiValue;
    private double ndiValue;
    
    public Simple_MACD_ADX(MarketEntryAttemptBook _book) throws NotEnoughDataException
    {
        super("Simple MACD/ADX Strategy");
        this.book = _book;
        this.numDays = 14;
        this.adx = new ADX(this.book,numDays);
        this.macdSrategy = new MACDStrategy(this.book);
        this.pdi = new PDI(this.book, numDays);
        this.ndi = new NDI(this.book, numDays);
    }

    @Override
    public SignalMessage trade() throws NotEnoughDataException {
       
        adxValue = adx.calculateADX();
        pdiValue = pdi.calculatePDI();
        ndiValue = ndi.calculateNDI();
        macdSignal  = macdSrategy.trade();
        
        if( adxValue > 20 && pdiValue > 20 && ndiValue < 20 && macdSignal.getSignal() == BID )
        {
            //generate buy signal
            this.signalDetails.setSignal(BID);
        }
        else if ( adxValue > 20 && ndiValue > 20 && pdiValue < 20 && macdSignal.getSignal() == OFFER)
        {
            //Generate sell signal
            this.signalDetails.setSignal(OFFER);
        }
        else
        {
            //Generate Do_Nothing signal
            this.signalDetails.setSignal(DO_NOTHING);
        }
        
        this.signalDetails.setVolaility(NORMAL);
        return this.signalDetails;
    }
}
