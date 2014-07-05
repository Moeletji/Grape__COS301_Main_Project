package financialmarketsimulator.market;

import financialmarketsimulator.exception.ItemNotFoundException;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MarketStrategy {

    private String name;

    public MarketStrategy(String name) {
        this.name = name;
    }

    /**
     * @todo make an offer
     * @return
     */
    public MarketEntryAttempt makeOffer() {
        return null;
    }

    /**
     * @todo make a bid
     * @return
     */
    public MarketEntryAttempt makeBid() {
        return null;
    }

    public void retractBid() {
    }

    public void retractOffer() {
    }

    public void setStrategy(String strategy) {
    }

    public MarketEntryAttempt searchMarketEntryAttempt(MarketEntryAttempt entry) throws ItemNotFoundException {
        MarketEntryAttempt foundNode = null;

        //implement search function here ...
        if (foundNode == null) {
            throw new ItemNotFoundException();
        } else {
            return foundNode;
        }
    }

    public String getName() {
        return name;
    }
}
