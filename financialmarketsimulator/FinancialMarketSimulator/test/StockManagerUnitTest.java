import financialmarketsimulator.exception.ItemNotFoundException;
import financialmarketsimulator.market.MarketEntryAttempt;
import static financialmarketsimulator.market.MarketEntryAttempt.SIDE.BID;
import static financialmarketsimulator.market.MarketEntryAttempt.SIDE.OFFER;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.StockManager;
import java.util.Vector;
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
        //marketManager = new StockManager();
        MarketEntryAttempt expectedOutput = null;
        MarketEntryAttempt actualOutput = null;
        assertEquals(expectedOutput, actualOutput);
    }
    
    @Test
    /**
     * @brief Tests of the stock manager can successfully remove an order that was made
     * and if the manager can successfully record the order as being removed at the
     * particular time it was removed.
     */
    public void removeOrderTest() {
        //marketManager = new StockManager();
        Boolean expectedOutput = true;
        Boolean actualOutput = null;
        //assertEquals(expectedOutput, actualOutput);
    }

    @Test
    /**
     * @brief Tests if the stock manager can successfully edit the specified order
     * and record that the order was modified at the time it was modifie.
     */
    public void editOrderTest() {
        //marketManager = new StockManager();
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
