import financialmarketsimulator.Order;
import financialmarketsimulator.MatchingEngine;
import financialmarketsimulator.exception.EmptyException;
import financialmarketsimulator.stack.*;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MatchingEngineUnitTest {

    public MatchingEngineUnitTest() {
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
    public void tradeTest() throws EmptyException, InterruptedException {
        matchingEngine = new MatchingEngine();

        //Mock bid and offer stacks
        ArrayList<Order> bids = new ArrayList<>();
        ArrayList<Order> offers = new ArrayList<>();

        //Create mock bid and offer objects
        Order bid = null;
        Order offer = null;
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
        //Push mock bids and offers to the stack
        for (int i = 0; i < 9; i++) {
            //Set variables with different data from first and each other
            offerPrice = i * 1000;
            offerNumShares = 500 + i;
            offerName = "Offer Test " + i;
            bidPrice = i * 2000;
            bidNumShares = 300 + i;
            bidName = "Bid Test " + i;

            offer = new Order(offerPrice, offerNumShares, offerName, Order.SIDE.OFFER);
            bid = new Order(bidPrice, bidNumShares, bidName, Order.SIDE.BID);
            bidNode = new MarketEntryAttemptNode(bid);
            offerNode = new MarketEntryAttemptNode(offer);

            bids.add(bidNode.node);
            offers.add(offerNode.node);
        }

        offerPrice = 1000;
        offerNumShares = 150;
        offerName = "John Smith";
        bidPrice = 1000;
        bidNumShares = 150;
        bidName = "Mark Angelou";

        Order expectedBid = new Order(offerPrice, offerNumShares, offerName, Order.SIDE.BID);
        Order expectedOffer = new Order(bidPrice, bidNumShares, bidName, Order.SIDE.OFFER);
        MarketEntryAttemptNode expectedBidNode = new MarketEntryAttemptNode(expectedBid);
        MarketEntryAttemptNode expectedOfferNode = new MarketEntryAttemptNode(expectedOffer);

        bids.add(expectedBid);
        offers.add(expectedOffer);

        //Pop the first offer and bid. These should be a match as per the mock bids
        //and offers pushed. 
        matchingEngine.populateStacks(offers, bids);

        BidStack bidStack = matchingEngine.getBidStack();
        OfferStack offerStack = matchingEngine.getOfferStack();

        try {
            bidNode = offerNode = null;
            bidNode = bidStack.pop();
            offerNode = offerStack.pop();
        } catch (EmptyException | InterruptedException ex) {
            ex.printStackTrace();
        }

        //Call matchingEngine.trade to perform the first test
        matchingEngine.trade();

        //Both assertEquals should return true
        assertEquals(expectedBidNode.node, bidNode.node);
        assertEquals(expectedOfferNode.node, offerNode.node);

        //************//
        //***TEST 2***//
        //************//
        bids.clear();
        offers.clear();

        //Push mock bids and offers to the stack
        for (int i = 0; i < 9; i++) {
            //Set variables with different data
            offerPrice = i * 1000;
            offerNumShares = 150 + i;
            offerName = "Offer Test " + i;
            bidPrice = i * 1500;
            bidNumShares = 200 + i;
            bidName = "Bid Test " + i;

            offer = new Order(offerPrice, offerNumShares, offerName, Order.SIDE.OFFER);
            bid = new Order(bidPrice, bidNumShares, bidName, Order.SIDE.BID);
            bidNode = new MarketEntryAttemptNode(bid);
            offerNode = new MarketEntryAttemptNode(offer);

            bids.add(bidNode.node);
            offers.add(offerNode.node);
        }

        matchingEngine.populateStacks(offers, bids);

        //Call matchingEngine.trade to perform the second test
        matchingEngine.trade();

        //Both assertEquals should return false
        assertFalse(expectedBidNode.node.equals(bidNode.node));
        assertFalse(expectedOfferNode.node.equals(offerNode.node));
    }

    /*@Test
     * @todo Observes the bid and offer stacks when called and informs the
     * @brief marketManager to either update or not update the views accordingly.

     public void updateTest() {
        
     OfferStack
        
     OfferStack offerStack1 = offerStack;
     BidStack bidStack1 = bidStack;
        
     assertEquals(offerStack1, offerStack);
     assertEquals(bidStack1, bidStack);
     }*/
    @Test
    /**
     * @brief
     */
    public void sortTest() throws CloneNotSupportedException, EmptyException, InterruptedException {
        matchingEngine = new MatchingEngine();

        ArrayList<Order> bids = new ArrayList<>();
        ArrayList<Order> offers = new ArrayList<>();

        Order bid1 = new Order(23.78, 1000, "Elliot", Order.SIDE.BID);
        Order bid2 = new Order(34.56, 1000, "Lois", Order.SIDE.BID);
        Order bid3 = new Order(34.55, 999, "Bobby", Order.SIDE.BID);
        Order bid4 = new Order(34.56, 500, "Theron", Order.SIDE.BID);

        Order offer1 = new Order(45.34, 456, "Lois", Order.SIDE.OFFER);
        Order offer2 = new Order(45.23, 643, "Bobby", Order.SIDE.OFFER);
        Order offer3 = new Order(67.34, 235, "Cindy", Order.SIDE.OFFER);
        Order offer4 = new Order(99.00, 456, "Andy", Order.SIDE.OFFER);

        bids.add(bid1);
        bids.add(bid2);
        bids.add(bid3);
        bids.add(bid4);

        offers.add(offer1);
        offers.add(offer2);
        offers.add(offer3);
        offers.add(offer4);

        matchingEngine.sort(bids, offers);

        ArrayList<Order> sortedBids = matchingEngine.getNewBidList();
        ArrayList<Order> sortedOffers = matchingEngine.getNewOfferList();

        ArrayList<Order> expectedSortedBids = new ArrayList<>();
        ArrayList<Order> expectedSortedOffers = new ArrayList<>();

        expectedSortedBids.add(bid4);
        expectedSortedBids.add(bid2);
        expectedSortedBids.add(bid3);
        expectedSortedBids.add(bid1);

        expectedSortedOffers.add(offer2);
        expectedSortedOffers.add(offer1);
        expectedSortedOffers.add(offer3);
        expectedSortedOffers.add(offer4);

        assertEquals(expectedSortedBids.get(0), sortedBids.get(0));
        assertEquals(expectedSortedBids.get(1), sortedBids.get(1));
        assertEquals(expectedSortedBids.get(2), sortedBids.get(2));
        assertEquals(expectedSortedBids.get(3), sortedBids.get(3));

        assertEquals(expectedSortedOffers.get(0), sortedOffers.get(0));
        assertEquals(expectedSortedOffers.get(1), sortedOffers.get(1));
        assertEquals(expectedSortedOffers.get(2), sortedOffers.get(2));
        assertEquals(expectedSortedOffers.get(3), sortedOffers.get(3));
    }

    /**
     * @throws java.lang.InterruptedException
     * @throws java.lang.CloneNotSupportedException
     * @throws financialmarketsimulator.exception.EmptyException
     * @brief test if the stacks have been populated
     */
    @Test
    public void populateStacksTest() throws InterruptedException, CloneNotSupportedException, EmptyException {
        matchingEngine = new MatchingEngine();

        ArrayList<Order> bids = new ArrayList<>();
        ArrayList<Order> offers = new ArrayList<>();

        Order bid1 = new Order(23.78, 1000, "Elliot", Order.SIDE.BID);
        Order bid2 = new Order(34.56, 1000, "Lois", Order.SIDE.BID);
        Order bid3 = new Order(34.55, 999, "Bobby", Order.SIDE.BID);
        Order bid4 = new Order(34.56, 500, "Theron", Order.SIDE.BID);

        Order offer1 = new Order(45.34, 456, "Lois", Order.SIDE.OFFER);
        Order offer2 = new Order(45.23, 643, "Bobby", Order.SIDE.OFFER);
        Order offer3 = new Order(67.34, 235, "Cindy", Order.SIDE.OFFER);
        Order offer4 = new Order(99.00, 456, "Andy", Order.SIDE.OFFER);

        bids.add(bid1);
        bids.add(bid2);
        bids.add(bid3);
        bids.add(bid4);

        offers.add(offer1);
        offers.add(offer2);
        offers.add(offer3);
        offers.add(offer4);

        matchingEngine.populateStacks(offers, bids);

        BidStack bidStack = matchingEngine.getBidStack();
        OfferStack offerStack = matchingEngine.getOfferStack();

        BidStack expectedBidStack = new BidStack();
        OfferStack expectedOfferStack = new OfferStack();

        expectedBidStack.push(new MarketEntryAttemptNode(bid1));
        expectedBidStack.push(new MarketEntryAttemptNode(bid2));
        expectedBidStack.push(new MarketEntryAttemptNode(bid3));
        expectedBidStack.push(new MarketEntryAttemptNode(bid4));

        expectedOfferStack.push(new MarketEntryAttemptNode(offer1));
        expectedOfferStack.push(new MarketEntryAttemptNode(offer2));
        expectedOfferStack.push(new MarketEntryAttemptNode(offer3));
        expectedOfferStack.push(new MarketEntryAttemptNode(offer4));

        assertEquals(expectedBidStack.peek().node, bidStack.peek().node);
        assertEquals(expectedOfferStack.peek().node, offerStack.peek().node);
    }
}
