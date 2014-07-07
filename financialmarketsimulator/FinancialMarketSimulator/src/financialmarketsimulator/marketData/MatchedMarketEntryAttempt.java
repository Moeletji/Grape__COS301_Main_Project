package financialmarketsimulator.marketData;

import financialmarketsimulator.market.MarketEntryAttempt;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @brief An acknowledgement for a trade that has occured.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MatchedMarketEntryAttempt {

    private final MarketEntryAttempt offer;
    private final MarketEntryAttempt bid;
    private final String id;
    private final Date dateIssued;
    private final SimpleDateFormat sdf;
    private final String date;

    public MatchedMarketEntryAttempt(MarketEntryAttempt _offer, MarketEntryAttempt _bid) {
        id = UUID.randomUUID().toString();
        if ((_offer.getSide() == MarketEntryAttempt.SIDE.OFFER) && (_bid.getSide() == MarketEntryAttempt.SIDE.BID)) {
            offer = _offer;
            bid = _bid;
        } else {
            offer = _bid;
            bid = _offer;
        }

        dateIssued = new Date();
        sdf = new SimpleDateFormat("YYYY.MM.DD HH:MM:SS");
        date = sdf.format(dateIssued);
    }

    public double getPrice() {
        return offer.getPrice();
    }

    public int getQuantity() {
        return (offer.getNumOfShares() <= bid.getNumOfShares()) ? offer.getNumOfShares() : bid.getNumOfShares();
    }

    public Date getDateIssued() {
        return dateIssued;
    }
    
    public String getDateIssuedToString()
    {
        return date;
    }

    public String getID() {
        return id;
    }
    
    public String getOffererName()
    {
        return offer.getParticipantName();
    }
    
    public String getBidderName()
    {
        return bid.getParticipantName();
    }
}
