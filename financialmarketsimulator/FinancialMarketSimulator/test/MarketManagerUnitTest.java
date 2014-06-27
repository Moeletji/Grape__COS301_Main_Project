/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import financialmarketsimulator.MarketEntryAttempt;
import financialmarketsimulator.MarketManager;
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
public class MarketManagerUnitTest {

    public MarketManagerUnitTest() {
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
    
    /*!
    * Test object. Used throughout the unit test.
    */
    MarketManager marketManager;

    @Test
    /**
     * Tests if the MarketManager object instantiates as expected
     */
    public void instantiation() {
        //marketManager = new MarketManager();
        String expectedOutput = "";
        String actualOutput = "";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    /**
     * @todo 
     */
    public void acceptBidTest() {
        //marketManager = new MarketManager();
        MarketEntryAttempt expectedOutput = null;
        MarketEntryAttempt actualOutput = null;
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    /**
     * @todo
     */
    public void acceptOfferTest() {
        //marketManager = new MarketManager();
        MarketEntryAttempt expectedOutput = null;
        MarketEntryAttempt actualOutput = null;
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    /**
     * @todo
     */
    public void removeBidTest() {
        //marketManager = new MarketManager();
        Boolean expectedOutput = true;
        Boolean actualOutput = null;
        //assertEquals(expectedOutput, actualOutput);
    }

    @Test
    /**
     * @todo
     */
    public void removeOfferTest() {
        //marketManager = new MarketManager();
        Boolean expectedOutput = true;
        Boolean actualOutput = null;
        //assertEquals(expectedOutput, actualOutput);
    }

    @Test
    /**
     * @todo
     */
    public void updateEngineTest() {
        //marketManager = new MarketManager();
    }

    @Test
    /**
     * @todo
     */
    public void updateEntitiesTest() {
        //marketManager = new MarketManager();
    }
}
