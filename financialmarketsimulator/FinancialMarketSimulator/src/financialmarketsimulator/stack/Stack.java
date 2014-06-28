package financialmarketsimulator.stack;

import financialmarketsimulator.exception.EmptyException;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */

public class Stack {

    AtomicReference<MarketEntryAttemptNode> top = new AtomicReference<>(null);
    static final int MIN_DELAY = 0;
    static final int MAX_DELAY = 0;
    Backoff backoff = new Backoff(MIN_DELAY, MAX_DELAY);
    private int length;

    public Stack()
    {
        length = 0;
    }
    
    protected boolean tryPush(MarketEntryAttemptNode node) {
        MarketEntryAttemptNode oldTop = top.get();
        node.next = oldTop;
        return (top.compareAndSet(oldTop, node));
    }

    public void push(MarketEntryAttemptNode node) throws InterruptedException {
        while (true) {
            if (tryPush(node)) {
                length++;
                return;
            } else {
                backoff.backoff();
            }
        }
    }

    protected MarketEntryAttemptNode tryPop() throws EmptyException {
        MarketEntryAttemptNode oldTop = top.get();
        if (oldTop == null) {
            length = 0;
            throw new EmptyException();
        }
        MarketEntryAttemptNode newTop = null;
        newTop= oldTop.next;
        if (top.compareAndSet(oldTop, newTop)) {
            return oldTop;
        } else {
            return null;
        }
    }

    public MarketEntryAttemptNode pop() throws EmptyException, InterruptedException {
        while (true) {
            MarketEntryAttemptNode returnNode = tryPop();
            if (returnNode != null) {
                length--;
                return returnNode;
            } else {
                backoff.backoff();
            }
        }
    }
    
    public MarketEntryAttemptNode peek() throws EmptyException
    {
        if(top.get() == null)
            throw new EmptyException();
        return top.get();
    }
    
    public int length()
    {
        return length;
    }
}
