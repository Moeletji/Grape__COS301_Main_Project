package financialmarketsimulator;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class Offer extends MarketEntryAttempt{
    
    public Offer(double _price, int _numShares, String _name) {
        super(_price, _numShares, _name);
    }
    
    public Offer(){
    }
    
    public String toString()
    {
        return this.getParticipantName() + " offered " +
                this.getNumberOfShares() + "@" + this.getPrice() +
                " at " + this.getTimeStampString();
    }
}
