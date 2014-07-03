package financialmarketsimulator.receipts;

import financialmarketsimulator.MarketEntryAttempt;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @brief An acknowledgement for a trade that has occured
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MatchedOrder {

    private MarketEntryAttempt offer;
    private MarketEntryAttempt bid;
    private String id;
    private Date dateIssued;

    public MatchedOrder(MarketEntryAttempt _offer, MarketEntryAttempt _bid) {
        id = UUID.randomUUID().toString();
        if ((_offer.getSide() == MarketEntryAttempt.SIDE.OFFER) && (_bid.getSide() == MarketEntryAttempt.SIDE.BID)) {
            offer = _offer;
            bid = _bid;
        }
        else
        {
            offer = _bid;
            bid = _offer;
        }
       
        dateIssued = new Date();
    }

    public double getPrice() {
        return offer.getPrice();
    }

    public int getQuantity() {
        return (offer.getNumOfShares() <= bid.getNumOfShares())? offer.getNumOfShares():bid.getNumOfShares();
    }

    public String getDateIssued() {
        return dateIssued.toString();
    }
    
    public String getID()
    {
        return id;
    }
}
