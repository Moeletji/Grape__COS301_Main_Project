package financialmarketsimulator.market;

import financialmarketsimulator.exception.OrderHasNoValuesException;
import financialmarketsimulator.marketData.MatchedMarketEntryAttemptUpdate;

/**
 * @brief The StockManager class is the super class from which each concrete
 * stock/security manager will inherit.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class StockManager {

    //Name of the stock
    private String stockName;
    //An order book of all the orders accepted
    private final MarketEntryAttemptBook orderList;
    private MatchedMarketEntryAttemptUpdate marketSnapShot;

    /**
     * MarketManager Constructor
     */
    public StockManager() {
        this.orderList = new MarketEntryAttemptBook();
        this.stockName = "";
    }

    /**
     * MarketManager Constructor
     *
     * @param sName Name of the stock
     */
    public StockManager(String sName) {
        this();
        this.stockName = sName;
    }

    public String getStockName() {
        return stockName;
    }

    /**
     * @brief Acknowledgement of the bid being accepted by the market manager
     * @param order MarketEntryAttempt object to be accepted
     * @return Returns a receipt object that acknowledges that a bid was
     * accepted
     * @throws InterruptedException
     */
    public void acceptOrder(MarketEntryAttempt order) throws InterruptedException {
        if (order.getPrice() == 0 || order.getNumOfShares() == 0) {
            return;
        }

        orderList.placeOrder(order);
    }

    /**
     * @brief get a list of all orders
     * @return a list of orders
     */
    public MarketEntryAttemptBook getOrderList() {
        return orderList;
    }

    /**
     * @brief Acknowledgement of the bid being removed by the market manager
     *
     * @param order MarketEntryAttempt to be removed
     * @return MarketEntryAttempt to be removed
     */
    public MarketEntryAttempt removeOrder(MarketEntryAttempt order) {
        return orderList.removeOrder(order);
    }

    /**
     * @brief Acknowledgement of the bid being removed by the market manager
     * @param orderId id of order
     * @param orderSide side of order
     * @return MarketEntryAttempt to be removed
     * @brief Acknowledgement of the bid being removed by the market manager
     */
    public MarketEntryAttempt removeOrder(String orderId, MarketEntryAttempt.SIDE orderSide) {
        return orderList.removeOrder(orderId, orderSide);
    }

    /**
     * @brief Edit the details of the order of a share
     *
     * @param orderId id of the order
     * @param price price of the order
     * @param numberShares number of shares by th e order
     * @param side side of the order
     * @throws OrderHasNoValuesException
     */
    public void editOrder(String orderId, double price, int numberShares, MarketEntryAttempt.SIDE side) throws OrderHasNoValuesException, CloneNotSupportedException {
        orderList.alterOrder(orderId, price, numberShares, side);
    }

    /**
     * @throws java.lang.CloneNotSupportedException
     * @brief Edit the details of the order of a share
     *
     * @param orderId id of the order
     * @param price price of the order
     * @param side side of the order
     * @throws OrderHasNoValuesException
     */
    public void editOrder(String orderId, double price, MarketEntryAttempt.SIDE side) throws OrderHasNoValuesException, CloneNotSupportedException {
        orderList.alterOrder(orderId, price, side);
    }

    /**
     * @throws java.lang.CloneNotSupportedException
     * @brief Edit the details of the order of a share
     *
     * @param orderId id of the order
     * @param numberShares number of shares by th e order
     * @param side side of the order
     * @throws OrderHasNoValuesException
     */
    public void editOrder(String orderId, int numberShares, MarketEntryAttempt.SIDE side) throws OrderHasNoValuesException, CloneNotSupportedException {
        orderList.alterOrder(orderId, numberShares, side);
    }

    public MatchedMarketEntryAttemptUpdate getMarketSnapShot() {
        return new MatchedMarketEntryAttemptUpdate(orderList.getMatchedOrders());
    }
}
