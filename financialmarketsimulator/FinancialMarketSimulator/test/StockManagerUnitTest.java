
import financialmarketsimulator.exception.ItemNotFoundException;
import financialmarketsimulator.market.MarketEntryAttempt;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.StockManager;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @brief Test class for the StockManager class. All methods for the
 * StockManager are tested in this class.
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
     * @brief Tests if the stock manager can successfully accept and record a bid 
     * that has been made.
     */
    public void acceptBidTest() {
        //marketManager = new StockManager();
        MarketEntryAttempt expectedOutput = null;
        MarketEntryAttempt actualOutput = null;
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    /**
     * @brief Tests if the stock manager can successfully accept and record an offer
     * that has been made.
     */
    public void acceptOfferTest() {
        //marketManager = new StockManager();
        MarketEntryAttempt expectedOutput = null;
        MarketEntryAttempt actualOutput = null;
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    /**
     * @brief Tests if the stock manager can successfully remove a bid that was made and
     * successfully record that the bid was removed at the time it was removed.
     */
    public void removeBidTest() {
        //marketManager = new StockManager();
        Boolean expectedOutput = true;
        Boolean actualOutput = null;
        //assertEquals(expectedOutput, actualOutput);
    }

    @Test
    /**
     * @brief Tests of the stock manager can successfully remove an offer that was made
     * and if the manager can successfully record the offer as being removed at the
     * particular time it was removed.
     */
    public void removeOfferTest() {
        //marketManager = new StockManager();
        Boolean expectedOutput = true;
        Boolean actualOutput = null;
        //assertEquals(expectedOutput, actualOutput);
    }

    @Test
    /**
     * @brief Tests if the stock manager can successfully update the matching engine.
     */
    public void updateEngineTest() {
        //marketManager = new StockManager();
    }

    @Test
    /**
     * @brief Tests if the stock manager can successfully update the relevant market participants
     * or entities.
     */
    public void updateEntitiesTest() {
        //marketManager = new StockManager();
    }
}
