package stackPackageUnitTests;

import financialmarketsimulator.Bid;
import financialmarketsimulator.MarketEntryAttempt;
import financialmarketsimulator.Offer;
import financialmarketsimulator.exception.EmptyException;
import financialmarketsimulator.stack.MarketEntryAttemptNode;
import financialmarketsimulator.stack.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    //Mock data used throughout the tests
    int mockPrice = 0;
    int mockNumShares = 0;
    String mockName = "";

    public StackUnitTest() {
    }

   

    // TODO Tests whether the stack returns the correct size of nodes inside the stack.
    @Test
    public void lengthTest() throws InterruptedException {

        int expectedResult = 3;

        Stack stack = new Stack();

        MarketEntryAttemptNode node = new MarketEntryAttemptNode(new Bid(mockPrice, mockNumShares, mockName));
        MarketEntryAttemptNode node1 = new MarketEntryAttemptNode(new Offer(mockPrice, mockNumShares, mockName));
        MarketEntryAttemptNode node2 = new MarketEntryAttemptNode(new Offer(mockPrice, mockNumShares, mockName));

        stack.push(node);
        stack.push(node1);
        stack.push(node2);

        int result = stack.length();

        assertEquals(expectedResult, result);
    }

    @Test
    public void pushAndPopTest() throws InterruptedException, EmptyException {
        //if it pushed an object into the stack which is initially empty 
        //and then pops that node without throwing an exception then the 
        //test has completed successfully
        MarketEntryAttemptNode expected = new MarketEntryAttemptNode(new MarketEntryAttempt(mockPrice, mockNumShares, mockName));
        Stack stack = new Stack();

        stack.push(expected);

        MarketEntryAttemptNode nodeTmp = stack.pop();
        assertEquals(expected, nodeTmp);
    }

    @Test
    public void popTest() throws InterruptedException, EmptyException {
        //if it pushed an object into the stack which is initially empty 
        //and then pops that node without throwing an exception then the 
        //test has completed successfully

        Stack stack = new Stack();
        MarketEntryAttemptNode node = new MarketEntryAttemptNode(new MarketEntryAttempt(mockPrice, mockNumShares, mockName));
        stack.push(node);

        MarketEntryAttemptNode tmp = stack.pop();
        assertEquals(node, tmp);
    }

    /**
     * @brief Test to check if the stack is empty
     * @throws EmptyException
     */
    @Test(expected = EmptyException.class)
    public void testEmptyStack() throws EmptyException, InterruptedException {
        Stack stack;

        stack = new Stack();
        assertTrue(stack.pop() == null ? true : false);
    }
}
