/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financialmarketsimulator.stack;

import financialmarketsimulator.exception.EmptyException;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Madimetja
 */
public class Stack {

    AtomicReference<MarketEntryAttemptNode> top = new AtomicReference<>(null);
    static final int MIN_DELAY = 0;
    static final int MAX_DELAY = 0;
    Backoff backoff = new Backoff(MIN_DELAY, MAX_DELAY);

    protected boolean tryPush(MarketEntryAttemptNode node) {
        MarketEntryAttemptNode oldTop = top.get();
        node.next = oldTop.node;
        return (top.compareAndSet(oldTop, node));
    }

    public void push(MarketEntryAttemptNode node) throws InterruptedException {
        while (true) {
            if (tryPush(node)) {
                return;
            } else {
                backoff.backoff();
            }
        }
    }

    protected MarketEntryAttemptNode tryPop() throws EmptyException {
        MarketEntryAttemptNode oldTop = top.get();
        if (oldTop == null) {
            throw new EmptyException();
        }
        MarketEntryAttemptNode newTop = null;
        newTop.node = oldTop.next;
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
                return returnNode;
            } else {
                backoff.backoff();
            }
        }
    }
}
