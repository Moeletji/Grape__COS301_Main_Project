package financialmarketsimulator;

import financialmarketsimulator.exception.ItemNotFoundException;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public interface Trade {

    /**
     * @todo Make an offer
     *
     * @return will return a Order
     */
    public Order makeOffer();

    /**
     * @todo Make a bid
     *
     * @return will return a Order
     */
    public Order makeBid();

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
     * @param entry Order object to be searched
     * @return Returns the searched Order object
     * @throws Exception.ItemNotFoundException
     */
    public Order searchMarketEntryAttempt(Order entry) throws ItemNotFoundException;

}
