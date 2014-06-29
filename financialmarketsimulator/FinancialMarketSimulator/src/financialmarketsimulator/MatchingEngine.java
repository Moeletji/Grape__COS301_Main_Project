package financialmarketsimulator;

import financialmarketsimulator.stack.BidStack;
import financialmarketsimulator.stack.MarketEntryAttemptNode;
import financialmarketsimulator.stack.OfferStack;
import java.util.ArrayList;

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
    public void update(ArrayList<Offer> offers, ArrayList<Bid> bids) throws InterruptedException {
        this.sort(offers, bids);
        this.populateStacks(offers, bids);
    }

    
    /* HELPER FUNCTIONS */
    /********************************************************************************************************/
    
    /**
     * @brief sort offers and bids for matching engine
     * 
     * @param offers list of all offers
     * @param bids list of all bids
     */
    private void sort(ArrayList offers, ArrayList bids) {
        
        //IMPLEMENT SORT ALGORITHM HERE!!!
        
        //implement sort function here in reverse order because remember stacks are FIFO
        //E.g. If offers should be arranged by highest price then sort by lowest price because when you
        //push it'll push those lowest ones first then the highest will be on top of the stack
    }
    
    /**
     * @brief populate stacks with modified bids and offers
     * 
     * @param offers list of all offers
     * @param bids list of all bids
     * @throws InterruptedException 
     */
    private void populateStacks(ArrayList<Offer> offers, ArrayList<Bid> bids) throws InterruptedException {
        for (Offer offer : offers) {
            MarketEntryAttemptNode node = new MarketEntryAttemptNode(offer);
            offerStack.push(node);
        }
        
        for (Bid bid : bids) {
            MarketEntryAttemptNode node = new MarketEntryAttemptNode(bid);
            bidStack.push(node);
        }
    }
}
