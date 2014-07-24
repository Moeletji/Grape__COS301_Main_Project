package financialmarketsimulator.market;

import financialmarketsimulator.exception.OrderHasNoValuesException;
import financialmarketsimulator.marketData.MatchedMarketEntryAttemptUpdate;
import java.util.ArrayList;

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
    //Snapshot of the market data
    private MatchedMarketEntryAttemptUpdate marketSnapShot;
    //List of all MarketParticipants
    private ArrayList<MarketParticipant> participants;
            
    /**
     * @brief MarketManager Constructor
     */
    public StockManager(String stockName, long timePeriod, int totalNumberOfShares) {
        this.orderList = new MarketEntryAttemptBook(stockName,timePeriod,totalNumberOfShares);
        this.stockName = stockName;
        this.participants = new ArrayList<MarketParticipant>();
    }
    
    /**
     * @brief MarketManager Constructor
     *
     * @param sName Name of the stock
     */
    public StockManager(String sName) {
        this.stockName = sName;
        this.orderList = new MarketEntryAttemptBook(stockName, 10, 1000);
        this.participants = new ArrayList<MarketParticipant>();
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
    public boolean acceptOrder(MarketEntryAttempt order) throws InterruptedException {
        if (order.getPrice() == 0 || order.getNumOfShares() == 0) {
            return false;
        }

        orderList.placeOrder(order);
        this.Notify();
        return true;
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
        this.Notify();
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
        this.Notify();
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
        this.Notify();
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
        this.Notify();
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
        this.Notify();
        orderList.alterOrder(orderId, numberShares, side);
    }

    public MatchedMarketEntryAttemptUpdate getMarketSnapShot() {
        return new MatchedMarketEntryAttemptUpdate(orderList.getMatchedOrders());
    }
    
    /**
     * @brief add MarketParticipant
     * @param participant MarketParticipant to be added
     */
    public void attach(MarketParticipant participant){
        participants.add(participant);
    }
    
    /**
     * @brief remove a MarketParticipant
     * @param participant MarketParticipant to be removed
     */
    public void detach(MarketParticipant participant){
        participants.remove(participant);
    }
    
    public void Notify(){
        for(MarketParticipant participant : participants){
            participant.update(this);
        }
    }
}
