package financialmarketsimulator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MarketEntryAttempt {
 
    /*!
    * Stores the price of the entry attempt. This can be either a bid share price
    * or an offer share price.
    */
    protected double price;
    /*!
    * Stores the number of shares being offered of bid.
    */
    protected int numberOfShares;
    /*!
    * Stores the name of the participant making the bid or offer.
    */
    protected String participantName;
    /*!
    * Java Data variable to get current date. 
    */
    protected Date date;
    protected SimpleDateFormat sdf;
    /*!
    * Stores the date and time the offer or bid was made. day yyyy.mm.dd hh.mm.ss pm/am timezone
    */
    protected String timeStamp;
    /**
     * Stores a unique identifier for the entry attempt
     */
    protected UUID uniqueID;
    
    /**
     * @todo MarketEntryAttempt class constructor
     * 
     * @param pr The price of the entry attempt 
     * @param numShares The number of shares being bid or offered
     * @param name The name of the participant making the bid or the offer.
    */
    public MarketEntryAttempt(double price, int numShares, String name) {
        this.price = price;
        this.numberOfShares = numShares;
        this.participantName = name;
        this.uniqueID = UUID.randomUUID();
        this.date = new Date();
        this.sdf = new SimpleDateFormat("E yyyy.MM.dd hh:mm:ss a zzz");
        this.timeStamp = this.sdf.format(date);
    }

    /**
     * @return Double value for the price of the entry attempt, i.e. bid or offer
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * @return Integer value for the number of shares being offered or bid.
     */
    public int getNumberOfShares() {
        return this.numberOfShares;
    }

    /**
     * @return String value for the name of the market participant who made the entry attempt.
     */
    public String getParticipantName() {
        return this.participantName;
    }
    
    /**
     * 
     * @return Returns the date object housing the entry attempt date
     */
    public Date getDate()
    {
        return this.date;
    }

    /**
     * @return String value representing time stamp of the entry attempt formatted as day yyyy.mm.dd hh.mm.ss pm/am timezone
     */
    public String getTimeStampString() {
        return this.timeStamp;
    }
    
    /**
     * @todo Sets the price of the shares for entry attempt, i.e. sets the price of shares being offered or bid.
     * @param _price The price of the shares for the entry attempt.
     */
    public void setPrice(double _price)
    {
        this.price = _price;
    }
    
    /**
     * @todo Sets the number of shares for the entry attempt, i.e. the number of shares being offered or bid 
     * @param _numShares The number of shares for the entry attempt.
     */
    public void setNumberOfShares(int _numShares)
    {
        this.numberOfShares = _numShares;
    }
    
    /**
     * @todo Sets the name of the participant making the entry attempt.
     * @param _name The name of the participant making the entry attempt.
     */
    public void setParticipantName(String _name)
    {
        this.participantName = _name;
    }
    
    public boolean hasNoSharesLeft()
    {
        return (this.numberOfShares <= 0);
    }
    
    /**
     * @todo Must add history to this object modification
     * @brief Used in sort only. Replaces the current objects attributes with those in the
     * parameters
     */
    public void replaceWith(Double price, int numShares, String name, Date newDate, String stamp)
    {
        this.price = price;
        this.numberOfShares = numShares;
        this.participantName = name;
        this.uniqueID = UUID.randomUUID();
        this.date = newDate;
        this.timeStamp = stamp;
    }
}
