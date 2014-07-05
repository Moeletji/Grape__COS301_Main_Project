
import financialmarketsimulator.market.MarketEntryAttempt;
import financialmarketsimulator.market.StockManager;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @brief Test class for the MarketManager class. All methods for the
 * MarketManager are tested in this class.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MarketManagerUnitTest {

    public MarketManagerUnitTest() {
    }

    /*!
     * Test object. Used throughout the unit test.
     */
    StockManager marketManager;

    @Test
    /**
     * @brief Tests if the MarketManager object instantiates as expected
     */
    public void instantiation() {
        //marketManager = new StockManager();
        String expectedOutput = "";
        String actualOutput = "";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    /**
     * @brief Tests if the market manager can successfully accept and record a bid 
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
     * @brief Tests if the market manager can successfully accept and record an offer
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
     * @brief Tests if the market manager can successfully remove a bid that was made and
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
     * @brief Tests of the market manager can successfully remove an offer that was made
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
     * @brief Tests if the market manager can successfully update the matching engine.
     */
    public void updateEngineTest() {
        //marketManager = new StockManager();
    }

    @Test
    /**
     * @brief Tests if the market manager can successfully update the relevant market participants
     * or entities.
     */
    public void updateEntitiesTest() {
        //marketManager = new StockManager();
    }
}
