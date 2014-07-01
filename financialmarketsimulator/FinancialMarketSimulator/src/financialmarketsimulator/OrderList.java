package financialmarketsimulator;

import java.util.Vector;

/**
 *
 * @author Moeletji
 */


public class OrderList {
    Vector<Order> offers;
    Vector<Order> bids;
    //Name of the stock stored as a string
    private String stock; 
    /**
     * 
     * @param _stock 
     */
    public OrderList(String _stock)
    {
        stock = _stock;
    }
    
    public OrderList()
    {
        stock = "";
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
                !(order_1.getPrice()>order_2.getPrice()):
                    !(order_1.getPrice()<order_2.getPrice());
    }
    
    public Vector<Order> getBids()
    {
        return bids;
    }
    
    public Vector<Order> getOffer()
    {
        return offers;
    }
}

