/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author Madimetja
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
 
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
