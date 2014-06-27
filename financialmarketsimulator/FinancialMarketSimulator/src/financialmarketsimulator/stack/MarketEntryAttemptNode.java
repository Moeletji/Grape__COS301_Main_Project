/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financialmarketsimulator.stack;

import financialmarketsimulator.MarketEntryAttempt;

/**
 *
 * @author Madimetja
 */
public class MarketEntryAttemptNode {

    public MarketEntryAttempt next;
    public MarketEntryAttempt node;
    
    public MarketEntryAttemptNode(MarketEntryAttempt node1) {
        node = node1;
        next = null;
    }
    
    public String toString()
    {
        return node.getNumberOfShares() + "@" + node.getPrice() + " by " + node.getParticipantName();
    }
}
