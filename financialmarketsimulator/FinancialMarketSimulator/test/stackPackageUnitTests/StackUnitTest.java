/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackPackageUnitTests;

import financialmarketsimulator.stack.BidStack;
import financialmarketsimulator.stack.OfferStack;
import financialmarketsimulator.stack.Stack;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Madimetja
 */
public class StackUnitTest extends Thread {

    private int PushCount;
    private int PopCount;
    private int ItemCount;

    public StackUnitTest(int push_count, int pop_count, int item_count) {
        PushCount = push_count;
        PopCount = pop_count;
        ItemCount = item_count;
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
    //
    // @Test
    // public void hello() {
}
