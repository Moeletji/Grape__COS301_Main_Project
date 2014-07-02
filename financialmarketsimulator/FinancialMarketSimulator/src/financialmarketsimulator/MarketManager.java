package financialmarketsimulator;

import financialmarketsimulator.exception.BidNotFoundException;
import financialmarketsimulator.exception.EmptyException;
import financialmarketsimulator.exception.OrderHasNoValuesException;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MarketManager {

    //Name of the stock
    private String stockName;
    //An order book of all the orders accepted
    private final OrderList orderList; 

    /**
     * MarketManager Constructor
     */
    public MarketManager() {
        this.orderList = new OrderList();
        this.stockName = "";
    }

    /**
     * MarketManager Constructor
     *
     * @param sName Name of the stock
     */
    public MarketManager(String sName) {
        this();
        this.stockName = sName;
    }

    /**
     * @brief Acknowledgement of the bid being accepted by the market manager
     * @param order Order object to be accepted
     * @return Returns a receipt object that acknowledges that a bid was
     * accepted
     * @throws InterruptedException
     */
    public void acceptOrder(Order order) throws InterruptedException {
        if (order.getPrice() ==0  || order.getQuantity() ==0)
            return;
        
        orderList.addOrderToList(order);
    }
    
    /**
     * @brief get a list of all orders
     * @return a list of orders
     */
    public OrderList getOrderList()
    {
        return orderList;
    }
    
    /**
     * @brief Acknowledgement of the bid being removed by the market manager
     *
     * @param order Order to be removed
     * @return Order to be removed
     */
    public Order removeOrder(Order order) {
        return orderList.removeOrder(order);
    }
    
    /**
     * @brief Acknowledgement of the bid being removed by the market manager
     * @param orderId id of order
     * @param orderSide side of order
     * @return Order to be removed
     * @brief Acknowledgement of the bid being removed by the market manager
     */
    public Order removeOrder(String orderId, Order.SIDE orderSide){
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
    public void editOrder(String orderId, double price, int numberShares, Order.SIDE side) throws OrderHasNoValuesException, CloneNotSupportedException
    {
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
    public void editOrder(String orderId, double price, Order.SIDE side) throws OrderHasNoValuesException, CloneNotSupportedException
    {
        orderList.alterOrder(orderId, price, -999, side);
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
    public void editOrder(String orderId, int numberShares, Order.SIDE side) throws OrderHasNoValuesException, CloneNotSupportedException
    {
        orderList.alterOrder(orderId, -999, numberShares, side);
    }
}
