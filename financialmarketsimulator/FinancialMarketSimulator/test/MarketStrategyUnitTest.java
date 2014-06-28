/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import financialmarketsimulator.MarketStrategy;
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
public class MarketStrategyUnitTest {

    /**
     * MarketStrategy test object used throughout the unit tests.
     */
    MarketStrategy marketStrategy;

    public MarketStrategyUnitTest() {
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

    @Test
    /**
     * @todo Tests if the getName method returns the expected strategy name
     */
    public void getNameTest() {
        String expectedName = "Test Strategy";
        marketStrategy = new MarketStrategy(expectedName);
        
        assertEquals(expectedName, marketStrategy.getName());
    }

    @Test
    /**
     * @todo
     */
    public void makeOfferTest() {
    }

    @Test
    /**
     * @todo
     */
    public void makeBidTest() {
    }

    @Test
    /**
     * @todo
     */
    public void retractBidTest() {

    }

    @Test
    /**
     * @todo
     */
    public void retractOfferTest() {

    }

    @Test
    /**
     * @todo
     */
    public void setStrategyTest() {

    }

    @Test
    /**
     * @todo
     */
    public void searchMarketEntryAttempt() {
    }
}
