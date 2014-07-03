package financialmarketsimulator.market;

import financialmarketsimulator.exception.OrderHasNoValuesException;
import financialmarketsimulator.marketData.MatchedMarketEntryAttempt;
import java.util.Vector;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */


public class MarketEntryAttemptBook {
/**
     * @brief list of offers
     */
    Vector<MarketEntryAttempt> offers;
    
    /**
     * @brief list of bids
     */
    Vector<MarketEntryAttempt> bids;
    
    /**
     * @brief list of all trades that occurred within the stock
     */
    Vector<MatchedMarketEntryAttempt> matchedOrders;
    
    /**
     * @brief Name of the stock stored as a string
     */
    private String stockName; 
    
    /**
     * 
     * @param _stock 
     */
    public MarketEntryAttemptBook(String _stock)
    {
        this();
        stockName = _stock;
    }
    
    public MarketEntryAttemptBook()
    {
        stockName = "";
        bids = new Vector<MarketEntryAttempt>();
        offers = new Vector<MarketEntryAttempt>();
        matchedOrders = new Vector<MatchedMarketEntryAttempt>();
    }
    
    /**
     * @brief Returns a list of all bids
     * @return list of bids
     */
    public Vector<MarketEntryAttempt> getBids()
    {
        return bids;
    }
    
    /**
     * @brief Returns a list of all offers
     * @return list of offers
     */
    public Vector<MarketEntryAttempt> getOffers()
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
     * @brief Function to look for trades and if there aren't any trades to be 
     * made the order is added to the relevant order(bid or order) list. 
     * @param newOrder 
     */
    public synchronized void placeOrder(MarketEntryAttempt newOrder)
    {
        if (newOrder.getPrice() == 0 || newOrder.getNumOfShares() == 0)
            return;//throw exception
        
        MarketEntryAttempt.SIDE orderSide = newOrder.getSide();
        //the list to check if any matchedOrders can be made
        Vector<MarketEntryAttempt> listToCheck =  (orderSide == MarketEntryAttempt.SIDE.BID) ? offers : bids;
       
        boolean hasMoreShares = true;
        
        //check if the other list still has orders and the order to be placed 
        //still has more shares
        while (listToCheck.size() >0 && hasMoreShares)
        {
            MarketEntryAttempt topOrder = listToCheck.get(0);
            //if theres no match
            if ((orderSide == MarketEntryAttempt.SIDE.BID && newOrder.getPrice() < topOrder.getPrice())||
                 (orderSide == MarketEntryAttempt.SIDE.OFFER && newOrder.getPrice() > topOrder.getPrice()))
            {
                return;
            }//if there is a match 
            else if ((orderSide == MarketEntryAttempt.SIDE.BID && newOrder.getPrice() >= topOrder.getPrice())||
                 (orderSide == MarketEntryAttempt.SIDE.OFFER && newOrder.getPrice() <= topOrder.getPrice()))
            {
                if (newOrder.getNumOfShares() == topOrder.getNumOfShares())
                {
                    hasMoreShares = false;
                    removeOrder(topOrder);
                    MatchedMarketEntryAttempt newTrade = new MatchedMarketEntryAttempt(newOrder,topOrder);
                    matchedOrders.add(newTrade);
                }
                else if (newOrder.getNumOfShares() > topOrder.getNumOfShares()) 
                {
                    MatchedMarketEntryAttempt newTrade = new MatchedMarketEntryAttempt(newOrder,topOrder);
                    matchedOrders.add(newTrade);
                    newOrder.setNumOfShares(newOrder.getNumOfShares() - topOrder.getNumOfShares());
                    removeOrder(topOrder);
                }
                else //if (newOrder.getQuantity() < topOrder.getQuantity())
                                                                {
                    MatchedMarketEntryAttempt newTrade = new MatchedMarketEntryAttempt(newOrder,topOrder);
                    matchedOrders.add(newTrade);
                    topOrder.setNumOfShares(topOrder.getNumOfShares() - newOrder.getNumOfShares());
                }
            }
        }
        
        //if there are still more shares then add the order to the list
        if (hasMoreShares)
        {
            addOrderToList(newOrder);
        }
    }
    
    /**
    * @param side
    * @throws financialmarketsimulator.exception.OrderHasNoValuesException
    * @brief Alter the price and/or shares of an Order
    * @param orderID Id of the order
    * @param price price of the order
    * @param shares number of the order
    */
    public synchronized void alterOrder(String orderID, double price, int shares, MarketEntryAttempt.SIDE side) throws OrderHasNoValuesException, CloneNotSupportedException{
        MarketEntryAttempt order = searchForOrder(orderID, side);
        
        if (price <= 0 || shares <= 0 || order == null)
            //throw new OrderHasNoValuesException();
            return;
        
        if (order.getNumOfShares() != shares)
        {
            order.setNumOfShares(shares);
        }
        
        if (price != order.getPrice())
        {
            order.setPrice(price);
            
            MarketEntryAttempt newOrder = (MarketEntryAttempt) order.clone();
            removeOrder(order.getOrderID(), order.getSide());
            addOrderToList(newOrder);
        }
    }
    
    /**
    * @param side
    * @throws financialmarketsimulator.exception.OrderHasNoValuesException
     * @throws java.lang.CloneNotSupportedException
    * @brief Alter the price and/or shares of an Order
    * @param orderID Id of the order
    * @param shares number of the order
    */
    public synchronized void alterOrder(String orderID, int shares, MarketEntryAttempt.SIDE side) throws OrderHasNoValuesException, CloneNotSupportedException{
         MarketEntryAttempt order = searchForOrder(orderID, side);
        
        if (shares <= 0 || order == null)
            //throw new OrderHasNoValuesException();
            return;
        
        if (order.getNumOfShares() != shares)
        {
            order.setNumOfShares(shares);
        }
    }
    
    /**
    * @param side
    * @throws financialmarketsimulator.exception.OrderHasNoValuesException
    * @throws java.lang.CloneNotSupportedException
    * @brief Alter the price and/or shares of an Order
    * @param orderID Id of the order
    * @param price price of the order
    */
    public synchronized void alterOrder(String orderID, double price, MarketEntryAttempt.SIDE side) throws OrderHasNoValuesException, CloneNotSupportedException{
         MarketEntryAttempt order = searchForOrder(orderID, side);
        
        if (price <= 0 || order == null)
            //throw new OrderHasNoValuesException();
            return;
        
        if (price != order.getPrice())
        {
            order.setPrice(price);
            
            MarketEntryAttempt newOrder = (MarketEntryAttempt) order.clone();
            removeOrder(order.getOrderID(), order.getSide());
            addOrderToList(newOrder);
        }
    }
  
    /**
     * This method adds on order to a list of orders
     * @param order 
     */
    public synchronized void addOrderToList(MarketEntryAttempt order)
    {
        MarketEntryAttempt.SIDE side = order.getSide();
        Vector<MarketEntryAttempt> temp =  (order.getSide() == MarketEntryAttempt.SIDE.BID) ? bids : offers;

        //enter the bid at right index
        temp.add(searchForOrder(temp, order, side), order);
    }
    
    /**
     * 
     * @param list
     * @param element
     * @return An integer of the index where the order can be entered 
     */
    private int searchForOrder(Vector<MarketEntryAttempt> list,MarketEntryAttempt element, MarketEntryAttempt.SIDE side)
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
    
    private boolean compareOrders(MarketEntryAttempt order_1, MarketEntryAttempt order_2,MarketEntryAttempt.SIDE _side)
    {
        return (_side == MarketEntryAttempt.SIDE.BID) ? 
                !(order_1.getPrice()<order_2.getPrice()):
                    !(order_1.getPrice()>order_2.getPrice());
    }
    
    private MarketEntryAttempt searchForOrder(String orderId, MarketEntryAttempt.SIDE side)
    {
        Vector<MarketEntryAttempt> orders = (side == MarketEntryAttempt.SIDE.BID ? bids : offers);
          
        for(int i = 0; i < orders.size(); i++){
            MarketEntryAttempt order = orders.get(i);
            if(order.getOrderID().equals(orderId)){
                //update listeners
                //set ids at a later stage
                return orders.get(i);
            }
        }
        
        return null;
    }
    
    public synchronized MarketEntryAttempt removeOrder(String orderId, MarketEntryAttempt.SIDE _side){
        Vector<MarketEntryAttempt> orders = (_side == MarketEntryAttempt.SIDE.BID ? bids : offers);
          
        for(int i = 0; i < orders.size(); i++){
            MarketEntryAttempt order = orders.get(i);
            if(order.getOrderID().equals(orderId)){
                //update listeners
                //set ids at a later stage
                return orders.remove(i);
            }
        }
        
        return null;
    } 
    
    public synchronized MarketEntryAttempt removeOrder(MarketEntryAttempt orderToBeRemoved){
        Vector<MarketEntryAttempt> orders = (orderToBeRemoved.side == MarketEntryAttempt.SIDE.BID ? bids : offers);
          
        for(int i = 0; i < orders.size(); i++){
            MarketEntryAttempt order = orders.get(i);
            if(order.getOrderID().equals(orderToBeRemoved.getOrderID())){
                //update listeners
                //set ids at a later stage
                return orders.remove(i);
            }
        }
        
        return null;
    }
    
    public void clearAllBidsAndOffers(){
        bids.clear();
        offers.clear();
    }
    
    public synchronized Vector<MatchedMarketEntryAttempt> getMatchedOrders()
    {
        return matchedOrders;
    }
}
