package financialmarketsimulator;

import financialmarketsimulator.exception.EmptyException;
import financialmarketsimulator.receipts.MatchedOrder;
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
        bidStack = new BidStack();
        offerStack = new OfferStack();
    }

    /**
     * @brief returns the bid stack
     * @return current bid stack
     */
    public BidStack getBidStack() {
        return bidStack;
    }

    /**
     * @brief returns the offer stack
     * @return current offer stack
     */
    public OfferStack getOfferStack() {
        return offerStack;
    }

    /**
     * @brief Matching a bids and an offers
     */
    public void trade() throws EmptyException, InterruptedException {
        //trade while there is something to offer
        MarketEntryAttempt bid = bidStack.peek().node;
        MarketEntryAttempt offer = offerStack.peek().node;

        //First type of trade, if prices are the same
        if (bid.getPrice() == offer.getPrice()) {
            int bidShares = bid.getNumOfShares();
            int offerShares = offer.getNumOfShares();

            if (offerShares == bidShares) {
                bidStack.pop();
                offerStack.pop();
            } else if (offerShares > bidShares) {
                offerShares = (offerShares - bidShares);
                offerStack.peek().node.setNumOfShares(offerShares);
                bidStack.pop();
            } else if (bidShares > offerShares) {
                bidShares = (bidShares - offerShares);
                bidStack.peek().node.setNumOfShares(bidShares);
                offerStack.pop();
            }
            
           
        }

        //Calculate the spread because they have different prices
        double spread = (offer.getPrice() - bid.getPrice());

        //Implement the spread algorihms later
        
    }

    /**
     * @param offers list of offers to updated
     * @param bids list of bids to updated
     * @throws java.lang.InterruptedException
     * @brief Update matching engine with modified bids and offers
     */
    public void update(ArrayList<MarketEntryAttempt> offers, ArrayList<MarketEntryAttempt> bids) throws InterruptedException {
        this.sort(offers, bids);
        this.populateStacks(offers, bids);
    }

    public ArrayList<MarketEntryAttempt> getNewBidList() throws CloneNotSupportedException, EmptyException, InterruptedException {
        BidStack tmpBidStack = (BidStack) bidStack.clone();
        ArrayList<MarketEntryAttempt> tmpBidList = new ArrayList<>();

        for (int i = 0; i < tmpBidStack.length(); i++) {
            tmpBidList.add((MarketEntryAttempt) tmpBidStack.pop().node);
        }
        return tmpBidList;
    }

    public ArrayList<MarketEntryAttempt> getNewOfferList() throws CloneNotSupportedException, EmptyException, InterruptedException {
        OfferStack tmpOfferStack = (OfferStack) offerStack.clone();
        ArrayList<MarketEntryAttempt> tmpOfferList = new ArrayList<>();

        for (int i = 0; i < tmpOfferStack.length(); i++) {
            tmpOfferList.add((MarketEntryAttempt) tmpOfferStack.pop().node);
        }
        return tmpOfferList;
    }

    /* HELPER FUNCTIONS */
    /**
     * *****************************************************************************************************
     */
    /**
     * @brief sort offers and bids for matching engine
     *
     * @param offers list of all offers
     * @param bids list of all bids
     */
    public void sort(ArrayList offers, ArrayList bids) {
        //IMPLEMENT SORT ALGORITHM HERE!!!
        //implement sort function here in reverse order because remember stacks are FIFO
        //E.g. If offers should be arranged by highest price then sort by lowest price because when you
        //push it'll push those lowest ones first then the highest will be on top of the stack
        //Eventually we need to implement a more efficient method that can sort and populate the stacks in one go
    }

    /**
     * @brief populate stacks with modified bids and offers
     *
     * @param offers list of all offers
     * @param bids list of all bids
     * @throws InterruptedException
     */
    public void populateStacks(ArrayList<MarketEntryAttempt> offers, ArrayList<MarketEntryAttempt> bids) throws InterruptedException {
        for (MarketEntryAttempt offer : offers) {
            offerStack.push(new MarketEntryAttemptNode(offer));
        }

        for (MarketEntryAttempt bid : bids) {
            bidStack.push(new MarketEntryAttemptNode(bid));
        }
    }
}
