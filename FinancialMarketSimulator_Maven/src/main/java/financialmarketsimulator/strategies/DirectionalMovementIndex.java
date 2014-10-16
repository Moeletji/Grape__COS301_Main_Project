/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */
package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.NDI;
import financialmarketsimulator.indicators.PDI;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketStrategy;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.*;
import static financialmarketsimulator.market.MarketStrategy.VOLATILITY.*;

/**
 * @brief Directional Movement Index Strategy which confirms the strength of a
 * trend over a given time period. 
 * -Generates a buy signal when the positive Directional Indicator crosses above 
 * the negative Directional Indicator.
 * -Generates a sell signal when the positive Directional Indicator crosses below
 * the negative Directional Indicator.
 * @author Madimetja
 */
public class DirectionalMovementIndex extends MarketStrategy {

    /**
     * Singleton instance
     */
    private static DirectionalMovementIndex instance = null;
    private final MarketEntryAttemptBook book;
    private final int numDays;
    private final PDI pdi;
    private final NDI ndi;

    //These variables are declared here as to be memory efficient when the trade method
    //id consotantly called.
    private double currentPDI;
    private double currentNDI;

    private DirectionalMovementIndex(MarketEntryAttemptBook _book, int _numDays) {
        super("Directional Movement Index");
        this.book = _book;
        this.numDays = _numDays;
        this.pdi = PDI.getInstance(this.book, this.numDays);
        this.ndi = NDI.getInstance(this.book, this.numDays);
    }
    
    public static DirectionalMovementIndex getInstance(MarketEntryAttemptBook _book, int _numDays) {
        if (instance == null) {
            instance = new DirectionalMovementIndex(_book, _numDays);
        }
        return instance;
    }

    @Override
    public SignalMessage trade() throws NotEnoughDataException {

        this.currentPDI = this.pdi.calculatePDI();
        this.currentNDI = this.ndi.calculateNDI();

        if (this.currentPDI - this.currentNDI > 0) {
            this.signalDetails.setSignal(BID);
        } else if (this.currentPDI - this.currentNDI < 0) {
            this.signalDetails.setSignal(OFFER);
        } else {
            this.signalDetails.setSignal(DO_NOTHING);
        }

        this.signalDetails.setVolaility(NORMAL);
        return this.signalDetails;
    }
}
