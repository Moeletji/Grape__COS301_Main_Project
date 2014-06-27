/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financialmarketsimulator;

import java.util.Date;

/**
 *
 * @authors Madimetja Shika, Moeletji Semenya, Daniel Makgonta
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
    /*!
    * Stores the date and time the offer or bid was made.
    */
    protected String timeStamp;
    
    //Constructor
    public MarketEntryAttempt()
    {
        
    }
    
    /**
     * @todo MarketEntryAttempt class constructor
     * 
     * @param pr The price of the entry attempt 
     * @param numShares The number of shares being bid or offered
     * @param name The name of the participant making the bid or the offer.
    */
    public MarketEntryAttempt(double pr, int numShares, String name) {
        this.price = pr;
        this.numberOfShares = numShares;
        this.participantName = name;
        date = new Date();
        this.timeStamp = date.toString();
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
     * @return String value representing time stamp of the entry attempt formatted as DD/MM/YYYY HH:MM:SS:MS TIME_ZONE
     */
    public String getTimeStanp() {
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
}
