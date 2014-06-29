import financialmarketsimulator.Offer;
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

public class OfferUnitTest {
    
    public OfferUnitTest() {
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

    protected Offer offer;
    @Test
    /**
     * @todo Tests if the Offer object instantiates as expected
     * @brief Test to check if initialization of the Offer class occurs as it should
     */
    public void instantiation() {
        final double DELTA = 1e-20;
        double price = 0.0;
        int numShares = 0;
        String name = "";
        offer = new Offer(price,numShares, name);
        
        assertEquals(price, offer.getPrice(), DELTA);
        assertEquals(numShares, offer.getNumberOfShares());
        assertEquals(name, offer.getParticipantName());
        assertEquals(new Date().toString(), offer.getTimeStampString());
        
        String expectedOutput = "" + " offered " + numShares + "@" + price + " at " + new Date().toString();
        String actualOutput = offer.toString();
        assertEquals(expectedOutput, actualOutput);
    }
}
