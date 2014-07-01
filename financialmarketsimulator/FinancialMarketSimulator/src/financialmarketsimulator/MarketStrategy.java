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
    public Order makeOffer() {
        return null;
    }

    @Override
    public Order makeBid() {
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
    public Order searchMarketEntryAttempt(Order entry) throws ItemNotFoundException {
        Order foundNode = null;

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
