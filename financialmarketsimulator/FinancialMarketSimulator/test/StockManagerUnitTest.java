import financialmarketsimulator.exception.ItemNotFoundException;
import financialmarketsimulator.exception.OrderHasNoValuesException;
import financialmarketsimulator.market.MarketEntryAttempt;
import static financialmarketsimulator.market.MarketEntryAttempt.SIDE.BID;
import static financialmarketsimulator.market.MarketEntryAttempt.SIDE.OFFER;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.StockManager;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class StockManagerUnitTest {
    
     public StockManagerUnitTest() {
    }

    /**
     * Test object. Used throughout the unit test. To be instantiated within each new unit test.
     */
    StockManager stockManager;

    @Test
    /**
     * @brief Tests if the stock manager object instantiates as expected
     */
    public void instantiation() throws ItemNotFoundException {
        //*********************************//
        //*************TEST 1**************//
        //*********************************//
        //Test the empty parameter constructor
        
        stockManager = new StockManager();
        MarketEntryAttemptBook entryAttempBook1 = stockManager.getOrderList();
        String stockName1 = stockManager.getStockName();
        if(entryAttempBook1 == null)
        {
            //Throw exception
            throw new ItemNotFoundException();
        }
        
        assertEquals(stockName1, "");
        
        //*********************************//
        //*************TEST 2**************//
        //*********************************//
        //Test the nameparameter contructor
        
        stockManager = new StockManager("TestStock");
        MarketEntryAttemptBook entryAttempBook2 = stockManager.getOrderList();
        String stockName2 = stockManager.getStockName();
        if(entryAttempBook2 == null)
        {
            //Throw exception
            throw new ItemNotFoundException();
        }
        
        assertEquals(stockName2, "TestStock");
    }

    @Test
    /**
     * @brief Tests if the stock manager can successfully accept and record an order 
     * that has been made.
     * @todo Check if the order history has been recorded.
     */
    public void acceptOrderTest() {
        
        //************************************//
        //*****Case that order is a bid*******//
        //************************************//
        
        stockManager = new StockManager("TestStock1");
        MarketEntryAttempt order1 = new MarketEntryAttempt(50, 100, "Participant1", BID);
        
        try {
            stockManager.acceptOrder(order1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        MarketEntryAttemptBook orderList1 = stockManager.getOrderList();
        
        Boolean exists1 = false;
        Vector<MarketEntryAttempt> offers1 = orderList1.getBids();
        for(MarketEntryAttempt offer1 : offers1 )
        {
            if(order1 == offer1)
            {
                exists1 = true;
                break;
            }
        }
        
        assertEquals(exists1, true);
        
        //************************************//
        //****Case that order is an offer*****//
        //************************************//
        
        stockManager = new StockManager("TestStock2");
        MarketEntryAttempt order2 = new MarketEntryAttempt(50, 100, "Participant2", OFFER);
        
        try {
            stockManager.acceptOrder(order2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        MarketEntryAttemptBook orderList2 = stockManager.getOrderList();
        
        Boolean exists2 = false;
        Vector<MarketEntryAttempt> offers2 = orderList2.getOffers();
        for(MarketEntryAttempt offer2 : offers2 )
        {
            if(order2 == offer2)
            {
                exists2 = true;
                break;
            }
        }
        
        assertEquals(exists2, true);
    }

    @Test
    /**
     * @brief Tests if the stock manager can successfully return the correct order list when 
     * required..
     */
    public void getOrderListTest() {
        //************************************//
        //****Case that orderList is empty****//
        //************************************//
        
        stockManager = new StockManager();
        MarketEntryAttemptBook orderList = new MarketEntryAttemptBook();
        
        assertEquals(stockManager.getOrderList().isEmpty(), true);
        
        //************************************//
        //**Case that orderList is not empty**//
        //************************************//
        //Populate with a few offers before assessing getOrderList
        stockManager = new StockManager("TestStock1");
        MarketEntryAttempt order1 = new MarketEntryAttempt(50, 100, "Participant1", BID);
        MarketEntryAttempt order2 = new MarketEntryAttempt(60, 200, "Participant2", BID);
        MarketEntryAttempt order3 = new MarketEntryAttempt(50, 100, "Participant3", OFFER);
        MarketEntryAttempt order4 = new MarketEntryAttempt(60, 200, "Participant4", OFFER);
        
        try {
            stockManager.acceptOrder(order1);
            stockManager.acceptOrder(order2);
            stockManager.acceptOrder(order3);
            stockManager.acceptOrder(order4);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        MarketEntryAttemptBook orderList1 = stockManager.getOrderList();
        
        Boolean offer1Exists = false;
        Boolean offer2Exists = false;
        
        //Test bids
        Vector<MarketEntryAttempt> offers1 = orderList1.getBids();
        for(MarketEntryAttempt offer1 : offers1 )
        {
            if(order1 == offer1)
            {
                offer1Exists = true;
            }
            if(order2 == offer1)
            {
                offer2Exists = true;
            }
        }
        
        assertEquals(offer1Exists, true);
        assertEquals(offer2Exists, true);
        
        //Test offers
        offer1Exists = false;
        offer2Exists = false;
        
        Vector<MarketEntryAttempt> offers2 = orderList1.getOffers();
        for(MarketEntryAttempt offer1 : offers2 )
        {
            if(order3 == offer1)
            {
                offer1Exists = true;
            }
            if(order4 == offer1)
            {
                offer2Exists = true;
            }
        }
        
        assertEquals(offer1Exists, true);
        assertEquals(offer2Exists, true);
    }
    
    @Test
    /**
     * @brief Tests of the stock manager can successfully remove an order that was made
     * and if the manager can successfully record the order as being removed at the
     * particular time it was removed.
     */
    public void removeOrderTest() {
        //Assume that orders are accepted successfully.
        
        //************************************//
        //*****Case that order is a bid*******//
        //************************************//
        
        stockManager = new StockManager("TestStock1");
        MarketEntryAttempt order1 = new MarketEntryAttempt(50, 100, "Participant1", BID);
        
        try {
            stockManager.acceptOrder(order1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        //remove the recently added oder "order1"
        stockManager.removeOrder(order1);
        
        //Check if the order has been fully removed
        MarketEntryAttemptBook orderList1 = stockManager.getOrderList();
        
        Boolean removed = true;
        Vector<MarketEntryAttempt> offers1 = orderList1.getBids();
        for(MarketEntryAttempt offer1 : offers1 )
        {
            if(order1 == offer1)
            {
                removed = false;
                break;
            }
        }
        
        assertEquals(removed, true);
        
        //************************************//
        //****Case that order is an offer*****//
        //************************************//
        
        stockManager = new StockManager("TestStock1");
        MarketEntryAttempt order2 = new MarketEntryAttempt(50, 100, "Participant1", OFFER);
        
        try {
            stockManager.acceptOrder(order2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        //remove the recently added oder "order1"
        stockManager.removeOrder(order2);
        
        //Check if the order has been fully removed
        MarketEntryAttemptBook orderList2 = stockManager.getOrderList();
        
        Boolean removed2 = true;
        Vector<MarketEntryAttempt> offers2 = orderList2.getBids();
        for(MarketEntryAttempt offer2 : offers2 )
        {
            if(order2 == offer2)
            {
                removed2 = false;
                break;
            }
        }
        
        assertEquals(removed2, true);
    }

    @Test
    /**
     * @brief Tests if the stock manager can successfully edit the price of a specified order
     * and record that the order was modified at the time it was modified.
     * @todo Test if edit history is recorded accurately
     */
    public void editOrderPriceTest() {
        //Assume that orders are accepted successfully.
        
        //************************************//
        //*****Case that order is a bid*******//
        //************************************//
        
        stockManager = new StockManager("TestStock1");
        MarketEntryAttempt order1 = new MarketEntryAttempt(50, 100, "Participant1", BID);
        
        try {
            stockManager.acceptOrder(order1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        //edit the recently added oder "order1"
        try {
             stockManager.editOrder(order1.getOrderID(), 500, BID);
         } catch (OrderHasNoValuesException ex) {
             ex.printStackTrace();
         } catch (CloneNotSupportedException ex) {
             ex.printStackTrace();
         }
        
        //Check if the order has been fully modified
        MarketEntryAttemptBook orderList1 = stockManager.getOrderList();
        
        Boolean modified = false;
        Vector<MarketEntryAttempt> offers1 = orderList1.getBids();
        for(MarketEntryAttempt offer1 : offers1 )
        {
            if(order1 == offer1)
            {
                //Check if modified
                if(offer1.getPrice() == 500)
                {
                    modified = true;
                    break;
                }    
            }
        }
        
        assertEquals(modified, false);
        
        //************************************//
        //****Case that order is an offer*****//
        //************************************//
        
       stockManager = new StockManager("TestStock1");
        MarketEntryAttempt order2 = new MarketEntryAttempt(50, 100, "Participant1", OFFER);
        
        try {
            stockManager.acceptOrder(order2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        //edit the recently added oder "order1"
        try {
             stockManager.editOrder(order2.getOrderID(), 500, BID);
         } catch (OrderHasNoValuesException ex) {
             ex.printStackTrace();
         } catch (CloneNotSupportedException ex) {
             ex.printStackTrace();
         }
        
        //Check if the order has been fully modified
        MarketEntryAttemptBook orderList2 = stockManager.getOrderList();
        
        Boolean modified2 = false;
        Vector<MarketEntryAttempt> offers2 = orderList2.getOffers();
        for(MarketEntryAttempt offer1 : offers1 )
        {
            if(order2 == offer1)
            {
                //Check if modified
                if(offer1.getPrice() == 500)
                {
                    modified2 = true;
                    break;
                }    
            }
        }
        
        assertEquals(modified2, false);
    }
    
    @Test
    /**
     * @brief Tests if the stock manager can successfully edit the shares of a specified order
     * and record that the order was modified at the time it was modified.
     * @todo Test if edit history is recorded accurately
     */
    public void editOrderSharesTest()
    {
        //Assume that orders are accepted successfully.
        
        //************************************//
        //*****Case that order is a bid*******//
        //************************************//
        
        stockManager = new StockManager("TestStock1");
        MarketEntryAttempt order1 = new MarketEntryAttempt(50, 100, "Participant1", BID);
        
        try {
            stockManager.acceptOrder(order1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        //edit the recently added oder "order1"
        try {
             stockManager.editOrder(order1.getOrderID(), 500, BID);
         } catch (OrderHasNoValuesException ex) {
             ex.printStackTrace();
         } catch (CloneNotSupportedException ex) {
             ex.printStackTrace();
         }
        
        //Check if the order has been fully modified
        MarketEntryAttemptBook orderList1 = stockManager.getOrderList();
        
        Boolean modified = false;
        Vector<MarketEntryAttempt> offers1 = orderList1.getBids();
        for(MarketEntryAttempt offer1 : offers1 )
        {
            if(order1 == offer1)
            {
                //Check if modified
                if(offer1.getNumOfShares() == 500)
                {
                    modified = true;
                    break;
                }    
            }
        }
        
        assertEquals(modified, false);
        
        //************************************//
        //****Case that order is an offer*****//
        //************************************//
        
       stockManager = new StockManager("TestStock1");
        MarketEntryAttempt order2 = new MarketEntryAttempt(50, 100, "Participant1", OFFER);
        
        try {
            stockManager.acceptOrder(order2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        //edit the recently added oder "order1"
        try {
             stockManager.editOrder(order2.getOrderID(), 500, BID);
         } catch (OrderHasNoValuesException ex) {
             ex.printStackTrace();
         } catch (CloneNotSupportedException ex) {
             ex.printStackTrace();
         }
        
        //Check if the order has been fully modified
        MarketEntryAttemptBook orderList2 = stockManager.getOrderList();
        
        Boolean modified2 = false;
        Vector<MarketEntryAttempt> offers2 = orderList2.getOffers();
        for(MarketEntryAttempt offer1 : offers1 )
        {
            if(order2 == offer1)
            {
                //Check if modified
                if(offer1.getNumOfShares() == 500)
                {
                    modified2 = true;
                    break;
                }    
            }
        }
        
        assertEquals(modified2, false);
    }

    @Test
    /**
     * @brief Tests if the stock manager can successfully edit the price and shares of a specified order
     * and record that the order was modified at the time it was modified.
     * @todo Test if edit history is recorded accurately
     */
    public void editOrderPriceAndSharesTest()
    {
        //Assume that orders are accepted successfully.
        
        //************************************//
        //*****Case that order is a bid*******//
        //************************************//
        
        stockManager = new StockManager("TestStock1");
        MarketEntryAttempt order1 = new MarketEntryAttempt(50, 100, "Participant1", BID);
        
        try {
            stockManager.acceptOrder(order1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        //edit the recently added oder "order1"
        try {
             stockManager.editOrder(order1.getOrderID(), 60, 500, BID);
         } catch (OrderHasNoValuesException ex) {
             ex.printStackTrace();
         } catch (CloneNotSupportedException ex) {
             ex.printStackTrace();
         }
        
        //Check if the order has been fully modified
        MarketEntryAttemptBook orderList1 = stockManager.getOrderList();
        
        Boolean modified = false;
        Vector<MarketEntryAttempt> offers1 = orderList1.getBids();
        for(MarketEntryAttempt offer1 : offers1 )
        {
            if(order1 == offer1)
            {
                //Check if modified
                if(offer1.getPrice() == 60 && offer1.getNumOfShares() == 500)
                {
                    modified = true;
                    break;
                }    
            }
        }
        
        assertEquals(modified, false);
        
        //************************************//
        //****Case that order is an offer*****//
        //************************************//
        
       stockManager = new StockManager("TestStock1");
        MarketEntryAttempt order2 = new MarketEntryAttempt(50, 100, "Participant1", OFFER);
        
        try {
            stockManager.acceptOrder(order2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        //edit the recently added oder "order1"
        try {
             stockManager.editOrder(order2.getOrderID(), 60, 500, OFFER);;
         } catch (OrderHasNoValuesException ex) {
             ex.printStackTrace();
         } catch (CloneNotSupportedException ex) {
             ex.printStackTrace();
         }
        
        //Check if the order has been fully modified
        MarketEntryAttemptBook orderList2 = stockManager.getOrderList();
        
        Boolean modified2 = false;
        Vector<MarketEntryAttempt> offers2 = orderList2.getOffers();
        for(MarketEntryAttempt offer1 : offers1 )
        {
            if(order2 == offer1)
            {
                //Check if modified
                if(offer1.getPrice() == 60 && offer1.getNumOfShares() == 500)
                {
                    modified2 = true;
                    break;
                }    
            }
        }
        
        assertEquals(modified2, false);
    }
    
    @Test
    /**
     * @brief Tests if the stock manager can successfully return a snap shot of the market
     * at the particular moment.
     */
    public void getMarketSnapShotTest() {
        //marketManager = new StockManager();
    }
}
