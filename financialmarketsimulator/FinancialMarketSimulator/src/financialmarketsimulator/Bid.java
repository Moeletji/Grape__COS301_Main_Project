package financialmarketsimulator;

import java.util.UUID;

/**
 *
 * @authors Madimetja Shika, Moeletji Semenya, Daniel Makgonta
 */
public class Bid extends MarketEntryAttempt {

    public Bid() {

    }

    public Bid(double _price, int _numShares, String _name) {
        this.price = _price;
        this.numberOfShares = _numShares;
        this.participantName = _name;
        this.timeStamp = UUID.randomUUID().toString();
    }

    public String toString() {
        return this.getParticipantName() + " bid "
                + this.getNumberOfShares() + "@" + this.getPrice()
                + " at " + this.getTimeStamp();
    }
}
