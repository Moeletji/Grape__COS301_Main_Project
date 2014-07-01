package financialmarketsimulator;

import financialmarketsimulator.exception.BidNotFoundException;
import financialmarketsimulator.exception.EmptyException;
import financialmarketsimulator.exception.ItemNotFoundException;
import financialmarketsimulator.exception.OfferNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public abstract class MarketManager {

    //Name of the stock
    private String stockName;
    //The type of stock used
    private String stockType;
    //Total number of shares that the stock holds
    private int totalNumberOfShares;
    //The market value of the stock
    private double stockMarketValue;
    //Matching engine for the stock
    private MatchingEngine matchingEngine;
    //Stack of all bids
    private ArrayList<MarketEntryAttempt> bids;
    //Stack of all offers
    private ArrayList<MarketEntryAttempt> offers;

    /**
     * MarketManager Constructor
     */
    public MarketManager() {
        bids = new ArrayList();
        offers = new ArrayList();
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
    public void ascceptBid(MarketEntryAttempt bid) throws InterruptedException {
        bids.add(bid);
    }

    /**
     *
     * @brief Acknowledgement of the offer being accepted by the market manager
     *
     * @param offer Offer object to be accepted
     * @throws InterruptedException
     * @return Returns a receipt object that acknowledges that an offer was
     * accepted
     */
    public void acceptOffer(MarketEntryAttempt offer) throws InterruptedException {
        offers.add(offer);
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
    public void removeBid(MarketEntryAttempt bid) throws EmptyException, InterruptedException, BidNotFoundException {
        bids.remove(bid);
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
    public void removeOffer(MarketEntryAttempt offer) throws InterruptedException, EmptyException, OfferNotFoundException {
        offers.remove(offer);
    }

    /**
     * @throws java.lang.InterruptedException
     * @brief Update the engine with the current states of the bid and offer
     * stack.
     */
    public void updateEngine() throws InterruptedException {
        
        //Remove any bids or offers that have no shares to trade
        for (MarketEntryAttempt offer : offers) {
            if (offer.hasNoSharesLeft()) {
                try {
                    if (!offers.remove(offer)) {
                        throw new ItemNotFoundException();
                    }
                } catch (ItemNotFoundException ei) {
                }
            }
        }

        for (MarketEntryAttempt bid : bids) {
            if (bid.hasNoSharesLeft()) {
                try {
                    if (!bids.remove(bid)) {
                        throw new ItemNotFoundException();
                    }
                } catch (ItemNotFoundException ei) {
                    ei.printStackTrace();
                }
            }
        }
        
        //update matching engine
        matchingEngine.update(offers, bids);
    }

    /**
     * @todo I have no idea what to do here!
     * @brief Update entities
     */
    public void updateEntities() {
    }
    
}
