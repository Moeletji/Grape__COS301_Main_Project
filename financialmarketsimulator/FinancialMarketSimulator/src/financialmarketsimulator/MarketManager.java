package financialmarketsimulator;

import financialmarketsimulator.receipts.Receipt;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @authors Madimetja Shika, Moeletji Semenya, Daniel Makgonta
 */
public abstract class MarketManager {

    private String stockName;
    private String stockType;
    private int totalNumberOfShares;
    private double stockMarketValue;
    private MatchingEngine matchingEngine;
    private Stack<MarketEntryAttempt> bidsStack;
    private Stack<MarketEntryAttempt> offerStack;

    /**
     * MarketManager Constructor
     */
    public MarketManager() {

    }
    
    /**
     * MarketManager Constructor
     * 
     * @param sName Name of the stock
     * @param sType Type of stock
     * @param numShares Number of shares the stock holds
     * @param val Market value of the stock
     */
    public MarketManager(String sName, String sType, int numShares, double val)
    {
        this.stockName = sName;
        this.stockType = sType;
        this.totalNumberOfShares = numShares;
        this.stockMarketValue = val;
        matchingEngine = new MatchingEngine();
    }

    /**
     * @todo Acknowledgement of the bid being accepted by the market manager
     * @param bid Bid object to be accepted
     * @return Returns a receipt object that acknowledges that a bid was accepted
     */
    public Receipt acceptBid(Bid bid) {
        return new Receipt();
    }

    /**
     * @todo Acknowledgement of the offer being accepted by the market manager
     * 
     * @param offer Offer object to be accepted
     * @return Returns a receipt object that acknowledges that an offer was accepted
     */
    public Receipt acceptOffer(Offer offer) {
        return new Receipt();
    }

    /**
     * @todo Acknowledgement of the bid being removed by the market manager
     * 
     * @param bid Bid to be removed
     * @return Returns a receipt object that acknowledges that an offer was removed
     */
    public String removeBid(Bid bid) {
        return "";
    }

     /**
     * @todo Acknowledgement of the offer being removed by the market manager
     * 
     * @param bid Offer to be removed
     * @return Returns a receipt object that acknowledges that an offer was removed
     */
    public String removeOffer(Offer offer) {
        return "";
    }
    
    /**
     * @todo Update the engine with the current states of the bid and offer stack.
     */
    public void updateEngine() {

    }

    /**
     * @todo Update entities 
     */
    public void updateEntities() {

    }
}
