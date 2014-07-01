package financialmarketsimulator.stack;

import financialmarketsimulator.MarketEntryAttempt;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MarketEntryAttemptNode {

    public MarketEntryAttemptNode next;
    public MarketEntryAttempt node;

    public MarketEntryAttemptNode(MarketEntryAttempt node) {
        this.node = node;
        next = null;
    }

    public MarketEntryAttemptNode() {
        this.node = null;
        this.next = null;
    }

    public String toString() {
        return node.getQuantity() + "@" + node.getPrice() + " by " + node.getParticipantName();
    }
}
