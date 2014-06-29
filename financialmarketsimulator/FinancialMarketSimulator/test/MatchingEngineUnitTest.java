import financialmarketsimulator.exception.EmptyException;
import financialmarketsimulator.Bid;
import financialmarketsimulator.MarketEntryAttempt;
import financialmarketsimulator.MatchingEngine;
import financialmarketsimulator.Offer;
import financialmarketsimulator.stack.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */

public class MatchingEngineUnitTest {

    /*public MatchingEngineUnitTest() {
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
    }*/
    
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
    public void tradeTest() throws EmptyException, InterruptedException {
        matchingEngine = new MatchingEngine();

        //Mock bid and offer stacks
        Stack bidStack = new Stack();
        Stack offerStack = new Stack();

        //Create mock bid and offer objects
        MarketEntryAttempt bid = null;
        MarketEntryAttempt offer = null;
        MarketEntryAttemptNode bidNode = null;
        MarketEntryAttemptNode offerNode = null;
        
        //Bid and offer instantiation variables
        int offerPrice;
        int offerNumShares;
        String offerName;
        int bidPrice;
        int bidNumShares;
        String bidName;

        //************//
        //***TEST 1***//
        //************//
        //The bid and offer attemts that will match
        
        //Instantiation values for the bid and offer
        offerPrice = 1000;
        offerNumShares = 150;
        offerName = "John Smith";
        bidPrice = 1000;
        bidNumShares = 150;
        bidName = "Mark Angelou";
        
        MarketEntryAttempt expectedBid = new MarketEntryAttempt(offerPrice, offerNumShares, offerName);
        MarketEntryAttempt expectedOffer = new MarketEntryAttempt(bidPrice, bidNumShares, bidName);
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
            //Set variables with different data from first and each other
            offerPrice = i*1000;
            offerNumShares = 500+i;
            offerName = "Offer Test " + i;
            bidPrice = i*2000;
            bidNumShares = 300+i;
            bidName = "Bid Test " + i;
            
            offer = new Bid(offerPrice, offerNumShares, offerName);
            bid = new Offer(bidPrice, bidNumShares, bidName);
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
            //Set variables with different data
            offerPrice = i*1000;
            offerNumShares = 150+i;
            offerName = "Offer Test " + i;
            bidPrice = i*1500;
            bidNumShares = 200+i;
            bidName = "Bid Test " + i;
            
            offer = new Bid(offerPrice, offerNumShares, offerName);
            bid = new Offer(bidPrice, bidNumShares, bidName);
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
    public void updateTest(OfferStack offerStack, BidStack bidStack) {
        OfferStack offerStack1 = offerStack;
        BidStack bidStack1 = bidStack;
        
        assertEquals(offerStack1, offerStack);
        assertEquals(bidStack1, bidStack);
    }
}
