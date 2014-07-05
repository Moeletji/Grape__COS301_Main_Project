package financialmarketsimulator.market;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MarketEntryAttempt implements Cloneable {

    //Static variables declaration
    public static enum SIDE {

        BID, OFFER
    }

    /**
     * @brief stores which side of the order it is
     */
    protected SIDE side;
    /*!
     * @brief Stores the price of the entry attempt. This can be either a bid share price
     * or an offer share price.
     */
    protected double price;
    /*!
     * @brief Stores the number of shares being offered of bid.
     */
    protected int numOfShares;
    /*!
     * @brief Stores the name of the participant making the bid or offer.
     */
    protected String participantName;
    /*!
     * @brief Java Data variable to get current date. 
     */
    protected Date date;
    /*!
     * Stores the date and time the offer or bid was made. day yyyy.mm.dd hh.mm.ss pm/am timezone
     */
    protected String timeStamp;
    /**
     * Stores a unique identifier for the entry attempt
     */
    protected UUID orderID;

    /**
     * @todo MarketEntryAttempt class constructor
     *
     * @param side side of order
     * @param price The price of the entry attempt
     * @param numShares The number of shares being bid or offered
     * @param name The name of the participant making the bid or the offer.
     */
    public MarketEntryAttempt(double price, int numShares, String name, SIDE side) {
        this();
        this.price = Math.round(price * 20) / 20.0;
        this.numOfShares = numShares;
        this.participantName = name;
        this.side = side;
    }

    public MarketEntryAttempt() {
        this.orderID = UUID.randomUUID();
        this.date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("E yyyy.MM.dd hh:mm:ss a zzz");
        this.timeStamp = sdf.format(date);
    }

    /**
     *
     * @return Object cloned
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     *
     * @return The side the order is on(BID or OFFER)
     */
    public SIDE getSide() {
        return side;
    }

    /**
     * @return Double value for the price of the entry attempt, i.e. bid or
     * offer
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * @return Integer value for the number of shares being offered or bid.
     */
    public int getNumOfShares() {
        return this.numOfShares;
    }

    /**
     * @return String value for the name of the market participant who made the
     * entry attempt.
     */
    public String getParticipantName() {
        return this.participantName;
    }

    /**
     *
     * @return Returns the date object housing the entry attempt date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * @return String value representing time stamp of the entry attempt
     * formatted as day yyyy.mm.dd hh.mm.ss pm/am timezone
     */
    public String getTimeStampString() {
        return this.timeStamp;
    }

    public String getOrderID() {
        return orderID.toString();
    }

    /**
     * @brief Sets the side the order is on(i.e. a bid or an offer)
     * @param side
     */
    public void setSide(SIDE _side) {
        this.side = _side;
    }

    /**
     * @todo Sets the price of the shares for entry attempt, i.e. sets the price
     * of shares being offered or bid.
     * @param _price The price of the shares for the entry attempt.
     */
    public void setPrice(double _price) {
        this.price = _price;
    }

    /**
     * @todo Sets the number of shares for the entry attempt, i.e. the number of
     * shares being offered or bid
     * @param _numShares The number of shares for the entry attempt.
     */
    public void setNumOfShares(int _numShares) {
        this.numOfShares = _numShares;
    }

    /**
     * @todo Sets the name of the participant making the entry attempt.
     * @param _name The name of the participant making the entry attempt.
     */
    public void setParticipantName(String _name) {
        this.participantName = _name;
    }

    public boolean hasNoSharesLeft() {
        return (this.numOfShares <= 0);
    }

    /**
     * @param price
     * @param numShares
     * @param name
     * @param newDate
     * @param stamp
     * @todo Must add history to this object modification
     * @brief Used in sort only. Replaces the current objects attributes with
     * those in the parameters
     */
    public void replaceWith(double price, int numShares, String name, Date newDate, String stamp) {
        this.price = price;
        this.numOfShares = numShares;
        this.participantName = name;
        this.orderID = UUID.randomUUID();
        this.date = newDate;
        this.timeStamp = stamp;
    }
}
