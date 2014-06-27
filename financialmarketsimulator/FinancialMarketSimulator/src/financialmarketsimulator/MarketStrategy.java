package financialmarketsimulator;

import financialmarketsimulator.exception.ItemNotFoundException;

/**
 *
 * @authors Madimetja Shika, Moeletji Semenya, Daniel Makgonta
 */
public class MarketStrategy implements Trade {

    public MarketStrategy() {
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

}
