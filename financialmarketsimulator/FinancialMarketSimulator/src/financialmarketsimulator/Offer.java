package financialmarketsimulator;

import java.util.Date;

/**
 *
 * @authors Madimetja Shika, Moeletji Semenya, Daniel Makgonta
 */
public class Offer extends MarketEntryAttempt{

    public Offer()
    {
        
    }
    
    public Offer(double _price, int _numShares, String _name) {
        this.price = _price;
        this.numberOfShares = _numShares;
        this.participantName = _name;
        date = new Date();
        this.timeStamp = date.toString();
    }
}
