package financialmarketsimulator.receipts;

import financialmarketsimulator.Order;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @brief An acknowledgement for a trade that has occured
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MatchedOrder {

    private Order offer;
    private Order bid;
    private String id;
    private Date dateIssued;

    public MatchedOrder(Order _offer, Order _bid) {
        id = UUID.randomUUID().toString();
        if ((_offer.getSide() == Order.SIDE.OFFER) && (_bid.getSide() == Order.SIDE.BID)) {
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
        return (offer.getQuantity() <= bid.getQuantity())? offer.getQuantity():bid.getQuantity();
    }

    public String getDateIssued() {
        return dateIssued.toString();
    }
    
    public String getID()
    {
        return id;
    }
}