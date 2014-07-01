package stackPackageUnitTests;

import financialmarketsimulator.Order;
import financialmarketsimulator.stack.MarketEntryAttemptNode;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */

public class MarketEntryAttemptNodeUnitTest {
    
    public MarketEntryAttemptNodeUnitTest() {
    }
    
    @Test
    public void toStringTest()
    {
        String expectedResult = "1000@100.01 by Daniel Smith";
        
        Order bid = new Order(100.01, 1000, "Daniel Smith", Order.SIDE.BID);
        MarketEntryAttemptNode node = new MarketEntryAttemptNode(bid);
        
        assertEquals(expectedResult, node.toString());
    }
    
}
