package financialmarketsimulator.stack;

import financialmarketsimulator.exception.EmptyException;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */

public class Stack implements Cloneable {

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
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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
    
    /**
     * @todo stack locking and unlocking before and after the sort respectively.
     * @todo must still test!
     * @brief This sort sorts the stack according to time and price. If two or
     * more entry attempts come in that at the same time but with different prices, the
     * higher price will be placed first and the lower price second. Else the
     * stack remains as is.
     */
    public void sortStack() {
        Boolean changeObserved = true;
        /**
         * Previous pointer starts on first node
         */
        MarketEntryAttemptNode previous = top.get();
        /**
         * Current pointer starts on second node as opposed to starting on
         * first.
         */
        MarketEntryAttemptNode current = top.get().next;

        //Continue the sort until a change is not observed
        //Stack must be locked here
        while (changeObserved) {
            changeObserved = false;
            //Must lock stack before this sorting occurs
            for (int i = 0; i < length(); i++) {
                //Observe if current and previous have equal timestamps. If so, 
                //continue with swap, else move on.
                if (!current.node.getDate().after(previous.node.getDate()) && !current.node.getDate().before(previous.node.getDate())) {
                    changeObserved = true;
                    //At this point the two dates are equal.
                    //If the prices are equal, leave as is, else order the entry attempts
                    //according to the price, with the highest price being on top.
                    if (current.node.getPrice() > previous.node.getPrice()) {
                        MarketEntryAttemptNode temp = previous;
                        previous.node.replaceWith(current.node.getPrice(), current.node.getNumberOfShares(), current.node.getParticipantName(), current.node.getDate(), current.node.getTimeStampString());
                        current.node.replaceWith(temp.node.getPrice(), temp.node.getNumberOfShares(), temp.node.getParticipantName(), temp.node.getDate(), temp.node.getTimeStampString());
                    }
                }
                //If the two dates differ, leave items as is and move on.
            }
        }
        //Stack must be unlocked here
        //At this point the the entry attempts are in perfect order.
    }
}
