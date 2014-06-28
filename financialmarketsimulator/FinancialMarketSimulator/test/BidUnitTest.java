import financialmarketsimulator.Bid;
import java.util.Date;
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

public class BidUnitTest {
    
    public BidUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    protected Bid bid;
    
    @Test
    /**
     * @todo Tests if the Bid object instantiates as expected
     * @brief Test to check if initialization of the Bid class occurs as it should
     */
    public void instantiation() {
          final double DELTA = 1e-20;
        double price = 0.0;
        int numShares = 0;
        String name = "";
        bid = new Bid(price,numShares, name);
        
        assertEquals(price, bid.getPrice(), DELTA);
        assertEquals(numShares, bid.getNumberOfShares());
        assertEquals(name, bid.getParticipantName());
        assertEquals(new Date().toString(), bid.getTimeStamp());
        
        String expectedOutput = "" + " bid " + numShares + "@" + price + " at " + new Date().toString();
        String actualOutput = bid.toString();
        assertEquals(expectedOutput, actualOutput);
    }
}
