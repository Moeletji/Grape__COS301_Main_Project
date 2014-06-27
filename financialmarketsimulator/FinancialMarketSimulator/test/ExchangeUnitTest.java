/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import financialmarketsimulator.MarketEntryAttempt;
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
public class ExchangeUnitTest {
    
    public ExchangeUnitTest() {
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
    
    //Test Object
    MarketEntryAttempt exchange;

    /*
     * Function  : constuctor
     * Input     : 
     * Process   : 
     * Output    : 
     * Speed     : 
     */
    @Test
    public void instantiation() {
        final double DELTA = 1e-20;
        double price = 0.0;
        int numShares = 0;
        String name = "";
        exchange = new MarketEntryAttempt(price,numShares, name);
        
        assertEquals(price, exchange.getPrice(), DELTA);
        assertEquals(numShares, exchange.getNumberOfShares());
        assertEquals(name, exchange.getParticipantName());
        //test for timeStampNotDone
        // --timeStamp should only be off by mili or nano seconds yes?
    }
}
