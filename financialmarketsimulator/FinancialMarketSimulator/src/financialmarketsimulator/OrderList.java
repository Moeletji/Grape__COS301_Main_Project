package financialmarketsimulator;

import java.util.Vector;
import financialmarketsimulator.receipts.MatchedOrders;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */

public class OrderList {
    /**
     * @brief list of offers
     */
    Vector<Order> offers;
    
    /**
     * @brief list of bids
     */
    Vector<Order> bids;
    
    /**
     * @brief list of all trades that occurred within the stock
     */
    Vector<MatchedOrders> trade;
    
    /**
     * @brief Name of the stock stored as a string
     */
    private String stockName; 
    
    /**
     * 
     * @param _stock 
     */
    public OrderList(String _stock)
    {
        this();
        stockName = _stock;
    }
    
    public OrderList()
    {
        stockName = "";
        bids = new Vector<Order>();
        offers = new Vector<Order>();
    }
    
    /**
     * @brief Returns a list of all bids
     * @return list of bids
     */
    public Vector<Order> getBids()
    {
        return bids;
    }
    
    /**
     * @brief Returns a list of all offers
     * @return list of offers
     */
    public Vector<Order> getOffers()
    {
        return offers;
    }
    
    /**
     * @brief Gets the name of the stock
     * @return name of the stock
     */
    public String getStockName()
    {
        return this.stockName;
    }
    
    /**
    * @brief Alter the price and/or shares of an Order
    * @param orderID Id of the order
    * @param price price of the order
    * @param shares number of the order
    */
    public void alterOrder(String orderID, double price, int shares, Order.SIDE side){
        Order order = searchForOrder(orderID, side);
        if (price <= 0 || shares <= 0 || order == null)
            return;
    
        Vector<Order> temp =  (order.getSide() == Order.SIDE.BID) ? bids : offers;
        
        if (order.getQuantity() != shares)
        {
            order.setQuantity(shares);
        }
        else if (price != order.getPrice())
        {
            Order newOrder = new Order(price, order.getQuantity(), order.getParticipantName(),order.getSide());
            removeOrder(order.getOrderID(), order.getSide());
            addOrderToList(newOrder);
        }
    }
  
    /**
     * This method adds on order to a list of orders
     * @param order 
     */
    public void addOrderToList(Order order)
    {
        Order.SIDE side = order.getSide();
        Vector<Order> temp =  (order.getSide() == Order.SIDE.BID) ? bids : offers;

        //enter the bid at right index
        temp.add(searchForOrder(temp, order, side), order);
    }
    
    /**
     * 
     * @param list
     * @param element
     * @return An integer of the index where the order can be entered 
     */
    private int searchForOrder(Vector<Order> list,Order element, Order.SIDE side)
    {
        //start index
        int start = 0;
        //end index
        int end = list.size();
        
        while (start < end)
        {
            int middle = (start + end)/2;
            if (compareOrders(list.get(middle), element, side))
                start = middle+1;
            else
                end = middle;
        }
        
        return end;
    }
    
    private boolean compareOrders(Order order_1, Order order_2,Order.SIDE _side)
    {
        return (_side == Order.SIDE.BID) ? 
                !(order_1.getPrice()<order_2.getPrice()):
                    !(order_1.getPrice()>order_2.getPrice());
    }
    
    private Order searchForOrder(String orderId, Order.SIDE side)
    {
        Vector<Order> orders = (Order.SIDE == Order.SIDE.BID ? bids : offers);
          
        for(int i = 0; i < orders.size(); i++){
            Order order = orders.get(i);
            if(order.getOrderID().equals(orderId)){
                //update listeners
                //set ids at a later stage
                return orders.get(i);
            }
        }
        
        return null;
    }
    
    public synchronized Order removeOrder(String orderId, Order.SIDE side){
        Vector<Order> orders = (Order.SIDE == Order.SIDE.BID ? bids : offers);
          
        for(int i = 0; i < orders.size(); i++){
            Order order = orders.get(i);
            if(order.getOrderID().equals(orderId)){
                //update listeners
                //set ids at a later stage
                return orders.remove(i);
            }
        }
        
        return null;
    }
}

