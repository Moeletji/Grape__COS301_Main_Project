package financialmarketsimulator;

import financialmarketsimulator.exception.ItemNotFoundException;

/**
 *
 * @authors Madimetja Shika, Moeletji Semenya, Daniel Makgonta
 */
public interface Trade {

    /**
     * @todo Make an offer
     *
     * @return will return a MarketEntryAttempt
     */
    public MarketEntryAttempt makeOffer();

    /**
     * @todo Make a bid
     *
     * @return will return a MarketEntryAttempt
     */
    public MarketEntryAttempt makeBid();

    /**
     * @todo Retract a bid that was previously accepted
     *
     */
    public void retractBid();

    /**
     * @todo Retract an offer that was previously accepted
     */
    public void retractOffer();

    /**
     * @todo Set the current strategy of the participant
     *
     * @param strategy strategy to be used by participant
     */
    public void setStrategy(String strategy);

    /**
     * @todo Search for a market entry attempt (bid or offer)
     * 
     * @param entry MarketEntryAttempt object to be searched
     * @return Returns the searched MarketEntryAttempt object
     * @throws Exception.ItemNotFoundException
     */
    public MarketEntryAttempt searchMarketEntryAttempt(MarketEntryAttempt entry) throws ItemNotFoundException;

}
