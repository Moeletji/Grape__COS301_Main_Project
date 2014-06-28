package stackPackageUnitTests;

import financialmarketsimulator.Bid;
import financialmarketsimulator.Offer;
import financialmarketsimulator.exception.EmptyException;
import financialmarketsimulator.stack.MarketEntryAttemptNode;
import financialmarketsimulator.stack.Stack;
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

public class StackUnitTest {

    public StackUnitTest() {
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

    // TODO Tests whether the stack returns the correct size of nodes inside the stack.
    @Test
    public int lengthTest() throws InterruptedException {

        int expectedResult = 3;

        Stack stack = new Stack();

        MarketEntryAttemptNode node = new MarketEntryAttemptNode(new Bid());
        MarketEntryAttemptNode node1 = new MarketEntryAttemptNode(new Offer());
        MarketEntryAttemptNode node2 = new MarketEntryAttemptNode(new Offer());

        stack.push(node);
        stack.push(node1);
        stack.push(node2);

        int result = stack.length();

        assertEquals(expectedResult, result);

        return result;
    }

    @Test
    public void pushTest(MarketEntryAttemptNode node) throws InterruptedException, EmptyException {
        //if it pushed an object into the stack which is initially empty 
        //and then pops that node without throwing an exception then the 
        //test has completed successfully

        Stack stack = new Stack();

        stack.push(node);

        MarketEntryAttemptNode nodeTmp = stack.pop();
    }

    @Test
    public MarketEntryAttemptNode popTest() throws InterruptedException, EmptyException {
        //if it pushed an object into the stack which is initially empty 
        //and then pops that node without throwing an exception then the 
        //test has completed successfully

        Stack stack = new Stack();
        MarketEntryAttemptNode node = new MarketEntryAttemptNode();
        stack.push(node);

        MarketEntryAttemptNode tmp = stack.pop();

        return tmp;
    }
}
