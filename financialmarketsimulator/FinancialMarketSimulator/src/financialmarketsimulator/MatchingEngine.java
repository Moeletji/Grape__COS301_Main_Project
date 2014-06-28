package financialmarketsimulator;

import financialmarketsimulator.exception.EmptyException;
import financialmarketsimulator.stack.BidStack;
import financialmarketsimulator.stack.MarketEntryAttemptNode;
import financialmarketsimulator.stack.OfferStack;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MatchingEngine {

    /**
     * @brief Stack containing all bids for a stock
     */
    private BidStack bidStack;
    /**
     * @brief Stack containing all offers for a stock
     */
    private OfferStack offerStack;

    public MatchingEngine() {
        bidStack = null;
        offerStack = null;
    }

    /**
     * @brief Matching a bids and an offers
     */
    public void trade() {
        
    }

    /**
     * @brief Update matching engine with modified bids and offers
     */
    public void update(OfferStack offerStack, BidStack bidStack) {
    }

    /* Private methods */
}

