package financialmarketsimulator.marketData;

import financialmarketsimulator.market.MarketEntryAttempt;
import java.util.Date;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @brief @author Grape <cos301.mainproject.grape@gmail.com>
 */
public final class DepthUpdates {

    public static enum UpdateType {

        NEW, UPDATE, ENTER, DELETE
    }
    private String stockName;
    private Date time;
    private String orderID;
    private int numberOfShares;
    private String updateType;

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:3306;FinancialMarketSimulatorHistoricalData", "root", "");
            Statement statement = con.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(DepthUpdates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DepthUpdates() {
        stockName = "";
        time = new Date();
        orderID = "";
        numberOfShares = 0;
        updateType = "";

    }

    public DepthUpdates(String stockName, String orderID, int numberOfShares, String upDate) {
        this();

        this.setStockName(stockName);
        this.setOrderID(orderID);
        this.setNumberOfShares(numberOfShares);
        this.setUpdateType(upDate);
    }

    public DepthUpdates(String stockName, String orderID, int numberOfShares, DepthUpdates.UpdateType upDate) {
        this();

        this.setStockName(stockName);
        this.setOrderID(orderID);
        this.setNumberOfShares(numberOfShares);
        switch (upDate) {
            case NEW:
                this.setUpdateType("NEW");
                break;
            case DELETE:
                this.setUpdateType("DELETE");
                break;
            case ENTER:
                this.setUpdateType("ENTER");
                break;
            case UPDATE:
                this.setUpdateType("UPDATE");
                break;
        }
    }

    public DepthUpdates(MarketEntryAttempt order, DepthUpdates.UpdateType upDate) {
        this();

        this.setStockName(order.getParticipantName());
        this.setOrderID(order.getOrderID());
        this.setNumberOfShares(order.getNumOfShares());
        switch (upDate) {
            case NEW:
                this.setUpdateType("NEW");
                break;
            case DELETE:
                this.setUpdateType("DELETE");
                break;
            case ENTER:
                this.setUpdateType("ENTER");
                break;
            case UPDATE:
                this.setUpdateType("UPDATE");
                break;
        }
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    @Override
    public String toString() {
        return "Stock Name: " + stockName + "\n"
                + "Date Time: " + time + "\n"
                + "Order ID: " + orderID + "\n"
                + "Number of Shares: " + numberOfShares + "\n"
                + "Update Type: " + updateType + "\n";
    }
}
