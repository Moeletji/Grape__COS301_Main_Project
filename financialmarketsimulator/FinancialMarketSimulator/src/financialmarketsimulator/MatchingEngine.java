package financialmarketsimulator;

import financialmarketsimulator.exception.EmptyException;
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
    public void trade() throws EmptyException, InterruptedException {
        //trade while there is something to offer
        while (true) {
            Bid bid = (Bid) bidStack.peek().node;
            Offer offer = (Offer) offerStack.peek().node;

            //First type of trade, if prices are the same
            if (bid.getPrice() == offer.getPrice()) {
                int bidShares = bid.getNumberOfShares();
                int offerShares = offer.getNumberOfShares();

                if (offerShares == bidShares) {
                    bidStack.pop();
                    offerStack.pop();
                } else if (offerShares > bidShares) {
                    offerShares = (offerShares - bidShares);
                    offerStack.peek().node.setNumberOfShares(offerShares);
                    bidStack.pop();
                } else {
                    bidShares = (bidShares - offerShares);
                    bidStack.peek().node.setNumberOfShares(bidShares);
                    offerStack.pop();
                }
            }

            //Calculate the spread because they have different prices
            double spread = (offer.getPrice() - bid.getPrice());

            //Implement the spread algorihms later
        }
    }

    /**
     * @brief Update matching engine with modified bids and offers
     */
    public void update(ArrayList<Offer> offers, ArrayList<Bid> bids) throws InterruptedException {
        this.sort(offers, bids);
        this.populateStacks(offers, bids);
    }

    public ArrayList<Bid> getNewBidList() throws CloneNotSupportedException, EmptyException, InterruptedException {
        BidStack tmpBidStack = (BidStack) bidStack.clone();
        ArrayList<Bid> tmpBidList = new ArrayList<>();

        for (int i = 0; i < tmpBidStack.length(); i++) {
            tmpBidList.add((Bid) tmpBidStack.pop().node);
        }
        return tmpBidList;
    }

    public ArrayList<Offer> getNewOfferList() throws CloneNotSupportedException, EmptyException, InterruptedException {
        OfferStack tmpOfferStack = (OfferStack) offerStack.clone();
        ArrayList<Offer> tmpOfferList = new ArrayList<>();

        for (int i = 0; i < tmpOfferStack.length(); i++) {
            tmpOfferList.add((Offer) tmpOfferStack.pop().node);
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
