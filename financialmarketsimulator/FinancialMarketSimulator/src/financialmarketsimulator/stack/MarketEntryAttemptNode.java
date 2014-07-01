package financialmarketsimulator.stack;

import financialmarketsimulator.Order;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MarketEntryAttemptNode {

    public MarketEntryAttemptNode next;
    public Order node;

    public MarketEntryAttemptNode(Order node) {
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
