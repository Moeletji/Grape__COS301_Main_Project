
import financialmarketsimulator.market.MarketStrategy;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @brief Test class for the MarketStrategy class. All methods in the
 * MarketStrategy class are tested in this class.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MarketStrategyUnitTest {

    /**
     * MarketStrategy test object used throughout the unit tests.
     */
    MarketStrategy marketStrategy;

    public MarketStrategyUnitTest() {
    }

    @Test
    /**
     * @brief Test if the object can be successfully instantiated.
     */
    public void instantiationTest()
    {
        marketStrategy = new MarketStrategy("TestStrategy1");
        assertEquals(marketStrategy.getName(), "TestStrategy1");
    }
    
    @Test
    /**
     * @brief Tests if the getName method returns the expected strategy name
     */
    public void getNameTest() {
        String expectedName = "Test Strategy";
        marketStrategy = new MarketStrategy(expectedName);

        assertEquals(expectedName, marketStrategy.getName());
    }

    @Test
    /**
     * @brief Tests if an offer can be made successfully.
     */
    public void makeOfferTest() {
    }

    @Test
    /**
     * @brief Tests if a bid can be made successfully.
     */
    public void makeBidTest() {
    }

    @Test
    /**
     * @brief Tests if a bid can be retracted/withdrawn successfully.
     */
    public void retractBidTest() {

    }

    @Test
    /**
     * @brief Tests if an offer can be retracted/withdrawn successfully.
     */
    public void retractOfferTest() {

    }

    @Test
    /**
     * @brief Tests if a strategy can be set successfully. 
     */
    public void setStrategyTest() {

    }

    @Test
    /**
     * @brief Tests if a market entry attempt, i.e. an offer or a bid, can be made 
     * successfully
     */
    public void searchMarketEntryAttempt() {
    }
}
