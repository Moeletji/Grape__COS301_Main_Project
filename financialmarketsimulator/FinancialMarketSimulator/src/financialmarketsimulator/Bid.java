package financialmarketsimulator;

import java.util.UUID;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */

public class Bid extends MarketEntryAttempt {

    public Bid(double _price, int _numShares, String _name) {
        super(_price, _numShares, _name);
    }

    public String toString() {
        return this.getParticipantName() + " bid "
                + this.getNumberOfShares() + "@" + this.getPrice()
                + " at " + this.getTimeStampString();
    }
}
