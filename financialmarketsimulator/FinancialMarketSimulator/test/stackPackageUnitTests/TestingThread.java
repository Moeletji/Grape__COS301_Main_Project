/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackPackageUnitTests;

import financialmarketsimulator.MarketEntryAttempt;
import financialmarketsimulator.exception.EmptyException;
import financialmarketsimulator.stack.MarketEntryAttemptNode;
import financialmarketsimulator.stack.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Moeletji
 */
public class TestingThread extends Thread {

    private Stack stack;
    private int ItemCount;
    private Vector<MarketEntryAttemptNode> temp;
    
    public TestingThread(Stack _stack, int item_count) {
        stack = _stack;
        ItemCount = item_count;

        Vector<MarketEntryAttemptNode> temp = new Vector<MarketEntryAttemptNode>();
        for (int i = 0; i < ItemCount; i++) {
            temp.add(new MarketEntryAttemptNode(new MarketEntryAttempt(i, i, Integer.toString(i))));
        }
    }

    public void run() {
        if (ItemCount > 0) {
            for (int i = 0; i < ItemCount; i++) {
                try {
                    stack.push(temp.get(i));
                } catch (InterruptedException ex) {
                    Logger.getLogger(TestingThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                System.out.println("Thread " + ThreadID.get() + " : " + stack.pop().toString());
            } catch (EmptyException ex) {
                Logger.getLogger(TestingThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(TestingThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
