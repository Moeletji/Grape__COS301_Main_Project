package stackPackageUnitTests;

import financialmarketsimulator.Bid;
import financialmarketsimulator.MarketEntryAttempt;
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
 
    @Test
    public String toStringTest()
    {
        String expectedResult = "1000@100.01 by Daniel Smith";
        
        Bid bid = (Bid)new MarketEntryAttempt(100.01, 1000, "Daniel Smith");
        MarketEntryAttemptNode node = new MarketEntryAttemptNode(bid);
        
        assertEquals(expectedResult, node.toString());
        
        return node.toString();
    }
    
}
