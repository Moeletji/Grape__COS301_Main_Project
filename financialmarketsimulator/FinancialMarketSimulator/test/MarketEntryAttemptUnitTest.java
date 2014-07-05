
import financialmarketsimulator.market.MarketEntryAttempt;
import static financialmarketsimulator.market.MarketEntryAttempt.SIDE.BID;
import static financialmarketsimulator.market.MarketEntryAttempt.SIDE.OFFER;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @brief Test class for the MarketEntryAttempt class. All methods for the
 * MarketEntryAttempt class are done in this class.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MarketEntryAttemptUnitTest {

    public MarketEntryAttemptUnitTest() {
    }

    /**
     * MarketEntryAttemp class test object. To be instantiated within each new test.
     */
    MarketEntryAttempt exchange;

    @Test
    /*
     * @brief Tests if the Market Entry object can be instantiated successfully.
     */
    public void instantiation() {
        final double DELTA = 1e-20;
        double price = 0.0;
        int numShares = 0;
        String name = "";
        exchange = new MarketEntryAttempt(price, numShares, name, BID);

        assertEquals(price, exchange.getPrice(), DELTA);
        assertEquals(numShares, exchange.getNumOfShares());
        assertEquals(name, exchange.getParticipantName());
        assertEquals(new Date().toString(), exchange.getTimeStampString());
        //test for timeStampNotDone
        // --timeStamp should only be off by mili or nano seconds yes?
    }
    
    @Test
    /**
     * @brief Tests if the market entry attempt side can be modified successfully.
     */
    public void setSideTest()
    {
        exchange = new MarketEntryAttempt(50, 100, "Participant 1", BID);
        exchange.setSide(OFFER);
        
        assertEquals(exchange.getSide(), OFFER);
    }
    
    @Test
    /**
     * @brief Tests if the market entry attempt price can be modified successfully.
     */
    public void setPriceTest()
    {
        exchange = new MarketEntryAttempt(50, 100, "Participant 1", BID);
        exchange.setPrice(60);
        
        assertEquals(exchange.getPrice(), 60);
    }
    
    @Test
    /**
     * @brief Test if the market entry attempt number of shares cab be modified successfully.
     */
    public void setNumSharesTest()
    {
        exchange = new MarketEntryAttempt(50, 100, "Participant 1", BID);
        exchange.setNumOfShares(200);
        
        assertEquals(exchange.getNumOfShares(), 200);
    }
    
    @Test
    /**
     * @brief Tests if the market entry attempts participant name can be modified successfully. 
     */
    public void setParticipantNameTest()
    {
        exchange = new MarketEntryAttempt(50, 100, "Participant 1", BID);
        exchange.setParticipantName("Partipant 2");
        
        assertEquals(exchange.getParticipantName(), "Partipant 2");
    }
    
    @Test
    /**
     * @brief
     */
    public void hasNoSharesLeftTest()
    {
        exchange = new MarketEntryAttempt(50, 100, "Participant 1", BID);
        exchange.setNumOfShares(0);
        
        assertEquals(exchange.hasNoSharesLeft(), 0);
    }
    
    @Test
    /**
     * @brief Tests if a market entry attempt object's attributes can be set to those of another
     * or new attributes.
     */
    public void replaceWithTest()
    {
        exchange = new MarketEntryAttempt(50, 100, "Participant 1", BID);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("E yyyy.MM.dd hh:mm:ss a zzz");
        String stamp = sdf.format(date);
        exchange.replaceWith(60, 500, "Participant 2", date, stamp);
        
        assertEquals(exchange.getPrice(),60,0);
        assertEquals(exchange.getNumOfShares(), 500);
        assertEquals(exchange.getParticipantName(), "Participant 2");
        assertEquals(exchange.getDate(), date);
        assertEquals(exchange.getTimeStampString(), stamp);
    }
    
}
