import financialmarketsimulator.Order;
import financialmarketsimulator.MarketManager;
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

public class MarketManagerUnitTest {

    public MarketManagerUnitTest() {
    }
    
    /*!
    * Test object. Used throughout the unit test.
    */
    MarketManager marketManager;

    @Test
    /**
     * Tests if the MarketManager object instantiates as expected
     */
    public void instantiation() {
        //marketManager = new MarketManager();
        String expectedOutput = "";
        String actualOutput = "";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    /**
     * @todo 
     */
    public void acceptBidTest() {
        //marketManager = new MarketManager();
        Order expectedOutput = null;
        Order actualOutput = null;
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    /**
     * @todo
     */
    public void acceptOfferTest() {
        //marketManager = new MarketManager();
        Order expectedOutput = null;
        Order actualOutput = null;
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    /**
     * @todo
     */
    public void removeBidTest() {
        //marketManager = new MarketManager();
        Boolean expectedOutput = true;
        Boolean actualOutput = null;
        //assertEquals(expectedOutput, actualOutput);
    }

    @Test
    /**
     * @todo
     */
    public void removeOfferTest() {
        //marketManager = new MarketManager();
        Boolean expectedOutput = true;
        Boolean actualOutput = null;
        //assertEquals(expectedOutput, actualOutput);
    }

    @Test
    /**
     * @todo
     */
    public void updateEngineTest() {
        //marketManager = new MarketManager();
    }

    @Test
    /**
     * @todo
     */
    public void updateEntitiesTest() {
        //marketManager = new MarketManager();
    }
}
