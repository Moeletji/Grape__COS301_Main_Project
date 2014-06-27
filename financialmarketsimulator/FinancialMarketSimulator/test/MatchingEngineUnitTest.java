/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import financialmarketsimulator.exception.EmptyException;
import financialmarketsimulator.Bid;
import financialmarketsimulator.MarketEntryAttempt;
import financialmarketsimulator.MatchingEngine;
import financialmarketsimulator.Offer;
import financialmarketsimulator.stack.*;
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
public class MatchingEngineUnitTest {

    public MatchingEngineUnitTest() {
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
    
    /**
     * The MatchingEngine object used for testing throughout the class
     */
    MatchingEngine matchingEngine;

    @Test
    /**
     * @todo Tests if the MatchingEngine object instantiates as expected
     */
    public void instantiation() {
        matchingEngine = new MatchingEngine();

    }

    @Test
    @SuppressWarnings("CallToPrintStackTrace")
    /**
     * @todo Tests the MatchingEngine trade() function. Two cases are tested:
     * Firstly, if the trade function will record a trade when an offer and a
     * bid are explicitly made to match, and secondly if the trade function will
     * record no trade when all bids and offers are made explicitly not to
     * match. The function creates mock bid and offer stacks and populates them
     * with mock bid and offer objects. Trade is then called on those stacks.
     */
    public void tradeTest() {
        matchingEngine = new MatchingEngine();

        //Mock bid and offer stacks
        Stack bidStack = new Stack();
        Stack offerStack = new Stack();

        //Create mock bid and offer objects
        MarketEntryAttempt bid = null;
        MarketEntryAttempt offer = null;
        MarketEntryAttemptNode bidNode = null;
        MarketEntryAttemptNode offerNode = null;

        //************//
        //***TEST 1***//
        //************//
        //The bid and offer attemts that will match
        MarketEntryAttempt expectedBid = new MarketEntryAttempt();
        MarketEntryAttempt expectedOffer = new MarketEntryAttempt();
        MarketEntryAttemptNode expectedBidNode = new MarketEntryAttemptNode(expectedBid);
        MarketEntryAttemptNode expectedOfferNode = new MarketEntryAttemptNode(expectedOffer);

        //Push the expected bid and offer to the stack first
        try {
            bidStack.push(expectedBidNode);
            offerStack.push(expectedOfferNode);

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        //Push mock bids and offers to the stack
        for (int i = 0; i < 9; i++) {
            bid = new Bid();
            offer = new Offer();
            bidNode = new MarketEntryAttemptNode(bid);
            offerNode = new MarketEntryAttemptNode(offer);

            try {
                bidStack.push(bidNode);
                offerStack.push(offerNode);

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        //Pop the first offer and bid. These should be a match as per the mock bids
        //and offers pushed. 
        try {
            bidNode = offerNode = null;
            bidNode = bidStack.pop();
            offerNode = offerStack.pop();
        } catch (EmptyException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        //Call matchingEngine.trade to perform the first test
        matchingEngine.trade();

        //Both assertEquals should return true
        assertEquals(expectedBidNode, bidNode);
        assertEquals(expectedOfferNode, offerNode);

        //************//
        //***TEST 2***//
        //************//
        //Push mock bids and offers to the stack
        for (int i = 0; i < 9; i++) {
            bid = new Bid();
            offer = new Offer();
            bidNode = new MarketEntryAttemptNode(bid);
            offerNode = new MarketEntryAttemptNode(offer);

            try {
                bidStack.push(bidNode);
                offerStack.push(offerNode);

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        //Call matchingEngine.trade to perform the second test
        matchingEngine.trade();

        //Both assertEquals should return false
        assertEquals(expectedBidNode, bidNode);
        assertEquals(expectedOfferNode, offerNode);

    }

    @Test
    /**
     * @todo Observes the bid and offer stacks when called and informs the
     * MarketManager to either update or not update the views accordingly.
     */
    public void updateTest() {

    }
}
