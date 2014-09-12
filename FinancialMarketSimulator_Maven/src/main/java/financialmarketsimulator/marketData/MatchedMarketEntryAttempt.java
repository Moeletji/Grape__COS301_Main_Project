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

    /**
     * @brief Matched Offer MarketEntryAttempt
     */
    private final MarketEntryAttempt offer;
    /**
     * @brief Matched Bid MarketEntryAttempt
     */
    private final MarketEntryAttempt bid;
    /**
     * @brief Number of shares being traded
     */
    private final int numShares;
    /**
     * @brief Price at which trades occurred
     */
    private final double price;
    /**
     * @brief MatchedMarketEntryAttempt id
     */
    private final String id;
    /**
     * @brief TimeStamp of MatchedMarketEntryAttempt
     */
    private final Date dateIssued;
    /**
     * @brief Date Format MatchedMarketEntryAttempt
     */
    private final SimpleDateFormat sdf;
    /**
     * @brief Date of MatchedMarketEntryAttempt
     */
    private final String date;

    public MatchedMarketEntryAttempt(String _id, Date _dateIssued, int _numShares, double _price) {
        offer = null;
        bid = null;
        id = _id;
        dateIssued = _dateIssued;
        sdf = new SimpleDateFormat("YYYY.MM.DD HH:MM:SS");
        date = sdf.format(dateIssued);
        price = _price;
        numShares = _numShares;
    }

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
        numShares = (offer.getNumOfShares() <= bid.getNumOfShares()) ? offer.getNumOfShares() : bid.getNumOfShares();
        price = offer.getPrice();
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return numShares;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public String getDateIssuedToString() {
        return date;
    }

    public String getID() {
        return id;
    }

    public String getOffererID() {
        return offer.getParticipantID();
    }

    public String getBidderID() {
        return bid.getParticipantID();
    }

    @Override
    public String toString() {
        return numShares + " @ " + price + "     " + date;
    }

    public MarketEntryAttempt getOffer() {
        return offer;
    }

    public MarketEntryAttempt getBid() {
        return bid;
    }

}
