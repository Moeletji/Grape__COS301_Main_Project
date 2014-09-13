/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */
package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.indicators.RSI;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketStrategy;
import static financialmarketsimulator.market.MarketStrategy.VOLATILITY.*;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.*;
import static java.lang.Math.abs;

/**
 * @brief Buy Condition 1 - if the closing price is greater than its 200 day
 * exponential moving average and the Relative Strength Index is less than 30.
 *
 * Buy Condition 2 - if the closing price is less than its 200 day exponential
 * moving average and the Relative Strength Index less than 25 and absolute(open
 * - close) > .70 * (High - Low) and close less than open.
 *
 * Sell Condition 1 - Exit a long position when the Relative Strength Index
 * crosses above 40
 *
 * --This strategy was obtained from
 * http://www.tradestation.com/education/labs/analysis-concepts/relative-strength-index-strategy
 *
 * @author Madimetja
 */
public class SimpleRSI extends MarketStrategy {

    private final MarketEntryAttemptBook book;
    private final RSI rsi;
    private final EMA ema;
    private final int numDays;
    
    //The following variable are declared here as to be memory efficient when the
    //trade method is called consistantly.
    double openingPrice;
    double closingPrice;
    double currEma;
    double currRsi;
    double highestTradePrice;
    double lowestTradePrice;

    public SimpleRSI(MarketEntryAttemptBook _book) throws NotEnoughDataException {
        super("Simple RSI");
        this.book = _book;
        this.numDays = 50; //Should preferebly be over 200 days
        rsi = new RSI(this.book, numDays);
        ema = new EMA(this.book, numDays);
    }

    @Override
    public SignalMessage trade() throws NotEnoughDataException {
        openingPrice = book.getOpeningPrice();
        closingPrice = book.getLastTradePrice();
        currEma = ema.calculateEMA();
        currRsi = rsi.calculateRSI();
        highestTradePrice = book.getHighestTradePrice(numDays);
        lowestTradePrice = book.getLowestTradePrice(numDays);

        //Buy condition 1
        if (closingPrice > currEma && currRsi < 30) {
            this.signalDetails.setSignal(BID);
        } //Buy condition 2
        else if ((closingPrice < currEma && currRsi < 25)
                && (abs(openingPrice - closingPrice) > 0.70 * abs(highestTradePrice - lowestTradePrice)
                && closingPrice < openingPrice)) {
            this.signalDetails.setSignal(BID);
        } //Sell condition
        else if (currRsi > 40) {
            this.signalDetails.setSignal(OFFER);
            this.signalDetails.setVolaility(HIGH);
        } else {
            this.signalDetails.setSignal(DO_NOTHING);
        }

        this.signalDetails.setVolaility(NORMAL);
        return this.signalDetails;
    }
}
