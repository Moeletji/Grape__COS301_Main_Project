import financialmarketsimulator.Order;
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

public class MarketEntryAttemptUnitTest {
    
    public MarketEntryAttemptUnitTest() {
    }
    
    //Test Object
    Order exchange;

    /*
     * Function  : constuctor
     * Input     : 
     * Process   : 
     * Output    : 
     * Speed     : 
     */
    @Test
    public void instantiation() {
        final double DELTA = 1e-20;
        double price = 0.0;
        int numShares = 0;
        String name = "";
        exchange = new Order(price,numShares, name, Order.SIDE.BID);
        
        assertEquals(price, exchange.getPrice(), DELTA);
        assertEquals(numShares, exchange.getQuantity());
        assertEquals(name, exchange.getParticipantName());
        assertEquals(new Date().toString(), exchange.getTimeStampString());
        //test for timeStampNotDone
        // --timeStamp should only be off by mili or nano seconds yes?
    }
}
