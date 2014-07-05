
import financialmarketsimulator.market.MarketStrategy;
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
public class MarketStrategyUnitTest {

    /**
     * MarketStrategy test object used throughout the unit tests.
     */
    MarketStrategy marketStrategy;

    public MarketStrategyUnitTest() {
    }

    @Test
    /**
     * @todo Tests if the getName method returns the expected strategy name
     */
    public void getNameTest() {
        String expectedName = "Test Strategy";
        marketStrategy = new MarketStrategy(expectedName);

        assertEquals(expectedName, marketStrategy.getName());
    }

    @Test
    /**
     * @todo
     */
    public void makeOfferTest() {
    }

    @Test
    /**
     * @todo
     */
    public void makeBidTest() {
    }

    @Test
    /**
     * @todo
     */
    public void retractBidTest() {

    }

    @Test
    /**
     * @todo
     */
    public void retractOfferTest() {

    }

    @Test
    /**
     * @todo
     */
    public void setStrategyTest() {

    }

    @Test
    /**
     * @todo
     */
    public void searchMarketEntryAttempt() {
    }
}
