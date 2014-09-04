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
import financialmarketsimulator.indicators.NDM;
import financialmarketsimulator.indicators.PDI;
import financialmarketsimulator.indicators.PDM;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketStrategy;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.*;

/**
 * @brief This strategy works with the strength of a trend to generate signals.
 * Buy Condition - If the ADX and PDI are above 20 and NDI is below 20.
 * Sell Condition - If the ADX and NDI are above 20 and PDI is below 20.
 * @author Madimetja
 */
public class Simple_MACD_ADX extends MarketStrategy{
    
    private final MarketEntryAttemptBook book;
    private final ADX adx;
    private final PDI pdi;
    private final NDI ndi;
    private final PDM pdm;
    private final NDM ndm;
    private final int numDays;
    
    //The following variables are declared here as to be memory effificient when 
    //the trade method is consistently called.
    private double adxValue;
    private double pdiValue;
    private double ndiValue;
    private double prevPDI;
    private double prevNDI;
    private double prevNDM;
    private double prevPDM;
    private double currPDM;
    private double currNDM;
    
    public Simple_MACD_ADX(MarketEntryAttemptBook _book)
    {
        super("Simple MACD/ADX Strategy");
        this.book = _book;
        this.numDays = 14;
        this.adx = new ADX(this.book,numDays);
        this.pdi = new PDI(this.book, numDays);
        this.ndi = new NDI(this.book, numDays);
        this.pdm = new PDM(this.book, numDays);
        this.ndm = new NDM(this.book, numDays);
    }

    @Override
    public SignalDetails trade() throws NotEnoughDataException {
       
        currPDM = pdm.getCurrValue();
        currNDM = ndm.getCurrValue();
        prevNDI = ndi.getPrevValue();
        prevPDI = pdi.getPrevValue();
        prevNDM = ndm.getPrevValue();
        prevPDM = pdm.getPrevValue();
        adxValue = adx.calulateADX(prevPDI, prevNDI, currPDM, currNDM, prevPDM, prevNDM);
        pdiValue = pdi.calculatePDI(currPDM, prevPDM);
        ndiValue = ndi.calculateNDI(currNDM, prevNDM);
        
        if( adxValue > 20 && pdiValue > 20 && ndiValue < 20 )
        {
            //generate buy signal
            this.signalDetails.setSignal(BUY);
        }
        else if ( adxValue > 20 && ndiValue > 20 && pdiValue < 20 )
        {
            //Generate sell signal
            this.signalDetails.setSignal(SELL);
        }
        else
        {
            //Generate Do_Nothing signal
            this.signalDetails.setSignal(DO_NOTHING);
        }
        return this.signalDetails;
    }
}
