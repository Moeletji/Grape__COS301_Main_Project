package financialmarketsimulator;

import financialmarketsimulator.exception.ItemNotFoundException;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MarketStrategy implements Trade {

    private String name;

    public MarketStrategy(String name) {
        this.name = name;
    }

    @Override
    public MarketEntryAttempt makeOffer() {
        return null;
    }

    @Override
    public MarketEntryAttempt makeBid() {
        return null;
    }

    @Override
    public void retractBid() {
    }

    @Override
    public void retractOffer() {
    }

    @Override
    public void setStrategy(String strategy) {
    }

    @Override
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
