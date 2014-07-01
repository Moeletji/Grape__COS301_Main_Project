package financialmarketsimulator;

import financialmarketsimulator.exception.BidNotFoundException;
import financialmarketsimulator.exception.EmptyException;
import financialmarketsimulator.exception.ItemNotFoundException;
import financialmarketsimulator.exception.OfferNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public abstract class MarketManager {

    //Name of the stock
    private String stockName;
    //Matching engine for the stock
    private MatchingEngine matchingEngine;
    //Stack of all bids
    private ArrayList<Order> bids;
    //Stack of all offers
    private ArrayList<Order> offers;
    //An order book of all the orders accepted
    private OrderList orderList; 

    /**
     * MarketManager Constructor
     */
    public MarketManager() {
        bids = new ArrayList();
        offers = new ArrayList();
    }

    /**
     * MarketManager Constructor
     *
     * @param sName Name of the stock
     * @param sType Type of stock
     * @param numShares Number of shares the stock holds
     * @param val Market value of the stock
     */
    public MarketManager(String sName) {
        this();
        this.stockName = sName;
        this.orderList = new OrderList();
        matchingEngine = new MatchingEngine();
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
    
    public OrderList getOrderList()
    {
        return orderList;
    }
    /**
     * @brief Acknowledgement of the bid being removed by the market manager
     *
     * @param order Order to be removed
     * @return
     * @throws EmptyException
     * @throws InterruptedException
     * @throws BidNotFoundException
     */
    public void removeOrder(Order order) throws EmptyException, InterruptedException, BidNotFoundException {
        //bids.remove(order);
    }
}
