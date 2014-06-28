package financialmarketsimulator;

import financialmarketsimulator.exception.EmptyException;
import financialmarketsimulator.stack.BidStack;
import financialmarketsimulator.stack.MarketEntryAttemptNode;
import financialmarketsimulator.stack.OfferStack;

/**
 *
 * @authors Madimetja Shika, Moeletji Semenya, Daniel Makgonta
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

