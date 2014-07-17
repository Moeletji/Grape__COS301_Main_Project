package financialmarketsimulator.marketData;

import financialmarketsimulator.market.StockManager;
import java.util.Date;

/**
 * @brief @author Grape <cos301.mainproject.grape@gmail.com>
 */
public final class QuoteUpdates {

    /**
     * @brief quote update time
     */
    private Date time;
    /**
     * @brief name of the market stock
     */
    private String stockName;
    /**
     * @brief highest stock bid price
     */
    private double bidPrice;
    /**
     * @brief total number of shares at market bid price
     */
    private int bidShares;
    /**
     * @brief lowest stock offer price
     */
    private double offerPrice;
    /**
     * @brief total number of shares at stock offer price
     */
    private int offerShares;
    /**
     * @brief number of orders at market bid price
     */
    private int bidOrders;
    /**
     * @brief number of orders at market offer price
     */
    private int offerOrders;

    public QuoteUpdates() {
        this.time = new Date();
    }

    /**
     * Constructor used when a quote of the entire stock
     *
     * @param manager manager of a stock
     */
    public QuoteUpdates(StockManager manager) {
        this();

        this.setStockName(manager.getStockName());

        this.setBidPrice(manager.getOrderList().getHighestBidPrice());
        this.setOfferPrice(manager.getOrderList().getLowestOfferPrice());

        this.setBidShares(manager.getOrderList().getTotalSharesForHighestBidPrice());
        this.setOfferShares(manager.getOrderList().getTotalSharesForLowestOfferPrice());

        this.setBidOrders(manager.getOrderList().getBidOrders());
        this.setOfferOrders(manager.getOrderList().getOfferOrders());
    }

    /**
     * Constructor used when a quote of the stock, with specified price orders
     *
     * @param manager manager of a stock
     * @param bidPrice
     * @param offerPrice
     */
    public QuoteUpdates(StockManager manager, double bidPrice, double offerPrice) {
        this();

        this.setStockName(manager.getStockName());

        this.setBidPrice(manager.getOrderList().getHighestBidPrice());
        this.setOfferPrice(manager.getOrderList().getLowestOfferPrice());

        this.setBidShares(manager.getOrderList().getTotalSharesForHighestBidPrice());
        this.setOfferShares(manager.getOrderList().getTotalSharesForLowestOfferPrice());

        this.setBidOrders(manager.getOrderList().getBidOrders(bidPrice));
        this.setOfferOrders(manager.getOrderList().getOfferOrders(offerPrice));
    }

    /**
     * Constructor used when a quote of the stock, with all specified values
     *
     * @param stockName
     * @param bidPrice
     * @param offerPrice
     * @param bidShares
     * @param offerShares
     * @param bidPriceOrders
     * @param offerPriceOrders
     */
    public QuoteUpdates(String stockName, double bidPrice, double offerPrice, int bidShares, int offerShares, int bidPriceOrders, int offerPriceOrders) {
        this();

        this.setStockName(stockName);

        this.setBidPrice(bidPrice);
        this.setOfferPrice(offerPrice);

        this.setBidShares(bidShares);
        this.setOfferShares(offerShares);

        this.setBidOrders(bidPriceOrders);
        this.setOfferOrders(offerPriceOrders);
    }

    /**
     * Constructor used when a quote of the stock without price orders
     *
     * @param stockName
     * @param bidPrice
     * @param offerPrice
     * @param bidShares
     * @param offerShares
     */
    public QuoteUpdates(String stockName, double bidPrice, double offerPrice, int bidShares, int offerShares) {
        this();

        this.setStockName(stockName);

        this.setBidPrice(bidPrice);
        this.setOfferPrice(offerPrice);

        this.setBidShares(bidShares);
        this.setOfferShares(offerShares);

        this.setBidOrders(0);
        this.setOfferOrders(0);
    }

    /* Getters and Setters*/
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public int getBidShares() {
        return bidShares;
    }

    public void setBidShares(int bidShares) {
        this.bidShares = bidShares;
    }

    public double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public int getOfferShares() {
        return offerShares;
    }

    public void setOfferShares(int offerShares) {
        this.offerShares = offerShares;
    }

    public int getBidOrders() {
        return bidOrders;
    }

    public void setBidOrders(int bidOrders) {
        this.bidOrders = bidOrders;
    }

    public int getOfferOrders() {
        return offerOrders;
    }

    public void setOfferOrders(int offerOrders) {
        this.offerOrders = offerOrders;
    }

    @Override
    public String toString() {
        return "Date: " + time + "\n"
                + "Stock name: " + stockName + "\n"
                + "Bid Price: " + bidPrice + "\n"
                + "Bid Shares: " + bidShares + "\n"
                + "Offer Price: " + offerPrice + "\n"
                + "Offer Shares " + offerShares + "\n"
                + "Bid Orders: " + bidOrders + "\n"
                + "Offer Orders: " + offerOrders + "\n";
    }
}
