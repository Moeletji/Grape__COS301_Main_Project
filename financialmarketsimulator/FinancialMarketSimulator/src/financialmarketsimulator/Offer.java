package financialmarketsimulator;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class Offer extends MarketEntryAttempt{

    public Offer()
    {
        
    }
    
    public Offer(double _price, int _numShares, String _name) {
        this.price = _price;
        this.numberOfShares = _numShares;
        this.participantName = _name;
        this.timeStamp = UUID.randomUUID().toString();
    }
    
    public String toString()
    {
        return this.getParticipantName() + " offered " +
                this.getNumberOfShares() + "@" + this.getPrice() +
                " at " + this.getTimeStamp();
    }
}
