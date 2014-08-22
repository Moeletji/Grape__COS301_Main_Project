package financialmarketsimulator.marketData;

import financialmarketsimulator.market.MarketEntryAttempt;
import java.util.Date;

/**
 * @brief Message sent to Matching Engine stored in MessageQueue
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class Message {
    
    public static enum MessageType {

        ORDER, CANCEL, AMEND
    };

    private String ID;
    private MarketEntryAttempt attempt;
    private Date time;
    private double price;
    private int shares;
    private Message.MessageType type;

    public Message(MarketEntryAttempt attempt, double price, int shares, Message.MessageType type) {
        this.attempt = attempt;
        this.time = new Date();
        this.price = price;
        this.shares = shares;
        this.type = type;
    }

    public Message() {
        this.attempt = null;
        this.time = null;
        this.price = 0.0;
        this.shares = 0;
        this.type = null;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    public MarketEntryAttempt getAttempt() {
        return attempt;
    }

    public void setAttempt(MarketEntryAttempt attempt) {
        this.attempt = attempt;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public Message.MessageType getType() {
        return type;
    }

    public void setType(Message.MessageType type) {
        this.type = type;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
