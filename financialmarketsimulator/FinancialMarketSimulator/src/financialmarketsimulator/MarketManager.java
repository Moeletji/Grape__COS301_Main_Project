package financialmarketsimulator;

import financialmarketsimulator.exception.BidNotFoundException;
import financialmarketsimulator.receipts.Receipt;
import financialmarketsimulator.stack.BidStack;
import financialmarketsimulator.stack.MarketEntryAttemptNode;
import financialmarketsimulator.stack.OfferStack;
import financialmarketsimulator.exception.EmptyException;
import financialmarketsimulator.exception.OfferNotFoundException;
import financialmarketsimulator.receipts.BidReceipt;
import financialmarketsimulator.receipts.OfferReceipt;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */

public abstract class MarketManager {

    private String stockName;
    private String stockType;
    private int totalNumberOfShares;
    private double stockMarketValue;
    private MatchingEngine matchingEngine;
    private BidStack bidStack;
    private OfferStack offerStack;

    /**
     * MarketManager Constructor
     */
    public MarketManager() {
        bidStack = new BidStack();
        offerStack = new OfferStack();
    }

    /**
     * MarketManager Constructor
     *
     * @param sName Name of the stock
     * @param sType Type of stock
     * @param numShares Number of shares the stock holds
     * @param val Market value of the stock
     */
    public MarketManager(String sName, String sType, int numShares, double val) {
        this();
        this.stockName = sName;
        this.stockType = sType;
        this.totalNumberOfShares = numShares;
        this.stockMarketValue = val;
        matchingEngine = new MatchingEngine();
    }

    /** 
     * @brief Acknowledgement of the bid being accepted by the market manager
     * @param bid Bid object to be accepted
     * @return Returns a receipt object that acknowledges that a bid was
     * accepted
     * @throws InterruptedException 
     */
    public Receipt acceptBid(Bid bid) throws InterruptedException {
        MarketEntryAttemptNode node = new MarketEntryAttemptNode(bid);
        bidStack.push(node);
        return new BidReceipt(bid);
    }

    /**
     * 
     * @brief Acknowledgement of the offer being accepted by the market manager
     *
     * @param offer Offer object to be accepted
     * @throws InterruptedException 
     * @return Returns a receipt object that acknowledges that an offer was accepted
     */
    public Receipt acceptOffer(Offer offer) throws InterruptedException {
        MarketEntryAttemptNode node = new MarketEntryAttemptNode(offer);
        offerStack.push(node);
        return new OfferReceipt(offer);
    }

    /**
     * @brief Acknowledgement of the bid being removed by the market manager
     * 
     * @param bid bid to be removed
     * @return
     * @throws EmptyException
     * @throws InterruptedException
     * @throws BidNotFoundException 
     */
    public Receipt removeBid(Bid bid) throws EmptyException, InterruptedException, BidNotFoundException {

        BidStack tmpStack = new BidStack();
        MarketEntryAttemptNode tmpNode = null;

        for (int j = 0; j < bidStack.length(); j++) {
            tmpStack.push(bidStack.pop());

            if (tmpStack.peek().node.getTimeStamp().equals(bid.getTimeStamp())) {
                //Remove the node that was found by popping it off the stack
                tmpNode = tmpStack.pop();
                for (int i = 0; i < tmpStack.length(); i++) {
                    bidStack.push(tmpStack.pop());
                }

                return new BidReceipt((Bid) tmpNode.node);
            }
        }

        for (int j = 0; j < tmpStack.length(); j++) {
            bidStack.push(tmpStack.pop());
        }
        throw new BidNotFoundException();
    }

    /**
     * @brief Acknowledgement of the offer being removed by the market manager
     * 
     * @param offer offer to be removed
     * @return
     * @throws InterruptedException
     * @throws EmptyException
     * @throws OfferNotFoundException 
     */
    public Receipt removeOffer(Offer offer) throws InterruptedException, EmptyException, OfferNotFoundException {

        OfferStack tmpStack = new OfferStack();
        MarketEntryAttemptNode tmpNode = null;

        for (int j = 0; j < offerStack.length(); j++) {
            tmpStack.push(offerStack.pop());

            if (tmpStack.peek().node.getTimeStamp().equals(offer.getTimeStamp())) {
                //Remove the node that was found by popping it off the stack
                tmpNode = tmpStack.pop();
                for (int i = 0; i < tmpStack.length(); i++) {
                    offerStack.push(tmpStack.pop());
                }

                return new OfferReceipt((Offer) tmpNode.node);
            }
        }

        for (int j = 0; j < tmpStack.length(); j++) {
            offerStack.push(tmpStack.pop());
        }
        throw new OfferNotFoundException();
    }

    /**
     * @brief Update the engine with the current states of the bid and offer
     * stack.
     */
    public void updateEngine() {
        matchingEngine.update(offerStack, bidStack);
    }

    /**
     * @todo I have no idea what to do here!
     * @brief Update entities
     */
    public void updateEntities() {
    }
}
