//Error reading included file Templates/Classes/Templates/Licenses/license-Financial Market Simulator Licence.txt
package strategyUnitTests;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttempt;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.strategies.MovingAverageEnvelope;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Moeletji
 */


public class MovingAverageEnvelopeUnitTest {
    
    MarketEntryAttemptBook book;
    MovingAverageEnvelope mae ;
    
    public MovingAverageEnvelopeUnitTest() throws NotEnoughDataException
    {
        book = new MarketEntryAttemptBook("TMP");
        mae = new MovingAverageEnvelope(book);
    }
    
    @Test(expected=NullPointerException.class)
    public void testForNoData() throws NotEnoughDataException
    {
        MarketEntryAttempt temp = mae.generateMarketEntryAttempt();
        assertEquals(null, temp);
    }
    
    @Test
    public void testGenerateMarketEntryAttempt() throws NotEnoughDataException
    {
        MarketEntryAttempt order1 = new MarketEntryAttempt(40.01, 2000, "Daniel Smith", MarketEntryAttempt.SIDE.OFFER);
        book.placeOrder(order1);
        MarketEntryAttempt order2 = new MarketEntryAttempt(40.00, 2000, "Jonny Bravo", MarketEntryAttempt.SIDE.OFFER);
        book.placeOrder(order2);
        MarketEntryAttempt order3 = new MarketEntryAttempt(34.50, 1500, "Jimmy West", MarketEntryAttempt.SIDE.BID);
        book.placeOrder(order3);
        MarketEntryAttempt order4 = new MarketEntryAttempt(34.51, 500, "Tim West", MarketEntryAttempt.SIDE.BID);
        book.placeOrder(order4);
        MarketEntryAttempt order5 = new MarketEntryAttempt(40.01, 3000, "Tim West", MarketEntryAttempt.SIDE.OFFER);
        book.placeOrder(order5);
        MarketEntryAttempt order6 = new MarketEntryAttempt(34.56, 1000, "Jonny Bravo", MarketEntryAttempt.SIDE.BID);
        book.placeOrder(order6);
        MarketEntryAttempt order7 = new MarketEntryAttempt(35.00, 1000, "Daniel Smith", MarketEntryAttempt.SIDE.BID);
        book.placeOrder(order7);
        MarketEntryAttempt order8 = new MarketEntryAttempt(40.00, 500, "Tim West", MarketEntryAttempt.SIDE.BID);
        book.placeOrder(order8);
        MarketEntryAttempt order9 = new MarketEntryAttempt(40.00, 500, "Luis Mario", MarketEntryAttempt.SIDE.BID);
        book.placeOrder(order9);
        MarketEntryAttempt order10 = new MarketEntryAttempt(20.89, 2000, "Jonny", MarketEntryAttempt.SIDE.OFFER);
        book.placeOrder(order10);
        MarketEntryAttempt order11 = new MarketEntryAttempt(35.01, 2000, "Past Longstone", MarketEntryAttempt.SIDE.OFFER);
        book.placeOrder(order11);
        MarketEntryAttempt order12 = new MarketEntryAttempt(34.56, 2000, "Daniel Smith", MarketEntryAttempt.SIDE.BID);
        book.placeOrder(order12);
        
        MarketEntryAttempt order13 = new MarketEntryAttempt(34.50, 2000, "Daniel Smith", MarketEntryAttempt.SIDE.OFFER);
        book.placeOrder(order13);
        MarketEntryAttempt order14 = new MarketEntryAttempt(40.00, 2000, "Jonny Bravo", MarketEntryAttempt.SIDE.OFFER);
        book.placeOrder(order14);
        MarketEntryAttempt order15 = new MarketEntryAttempt(34.50, 1500, "Jimmy West", MarketEntryAttempt.SIDE.BID);
        book.placeOrder(order15);
        MarketEntryAttempt order16 = new MarketEntryAttempt(34.50, 500, "Tim West", MarketEntryAttempt.SIDE.BID);
        book.placeOrder(order16);
        MarketEntryAttempt order17 = new MarketEntryAttempt(40.00, 3000, "Tim West", MarketEntryAttempt.SIDE.OFFER);
        book.placeOrder(order17);
        MarketEntryAttempt order18 = new MarketEntryAttempt(34.56, 1000, "Jonny Bravo", MarketEntryAttempt.SIDE.BID);
        book.placeOrder(order18);
        MarketEntryAttempt order19 = new MarketEntryAttempt(35.00, 1000, "Daniel Smith", MarketEntryAttempt.SIDE.BID);
        book.placeOrder(order19);
        MarketEntryAttempt order20 = new MarketEntryAttempt(40.00, 500, "Tim West", MarketEntryAttempt.SIDE.BID);
        book.placeOrder(order20);
        MarketEntryAttempt order21 = new MarketEntryAttempt(40.00, 500, "Luis Mario", MarketEntryAttempt.SIDE.BID);
        book.placeOrder(order21);
        MarketEntryAttempt order22 = new MarketEntryAttempt(20.89, 2000, "Jonny", MarketEntryAttempt.SIDE.OFFER);
        book.placeOrder(order22);
        MarketEntryAttempt order23 = new MarketEntryAttempt(20.89, 2000, "Past Longstone", MarketEntryAttempt.SIDE.BID);
        book.placeOrder(order23);
        MarketEntryAttempt order24 = new MarketEntryAttempt(34.56, 2000, "Daniel Smith", MarketEntryAttempt.SIDE.BID);
        book.placeOrder(order24);
        
        int expNum = 10;
        
        assertEquals(expNum,book.getMatchedOrders().size());
        
        //MovingAverageEnvelope mae = new MovingAverageEnvelope(book); 
        double ave = mae.getSMA();
        
        mae.setClosingPrice(ave + (ave*0.05));
        MarketEntryAttempt temp = mae.generateMarketEntryAttempt();
        
        assertEquals(MarketEntryAttempt.SIDE.BID,temp.getSide());
        
    }
}
