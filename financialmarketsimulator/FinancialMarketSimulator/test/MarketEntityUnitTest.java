/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import financialmarketsimulator.MarketEntity;
import financialmarketsimulator.MarketStrategy;
import java.util.ArrayList;
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
public class MarketEntityUnitTest {
    
    /**
     * Market Entity test object used throughout the unit tests.
     */
    MarketEntity marketEntity;

    public MarketEntityUnitTest() {
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
    
    /**
     * This method tests if the marketEntity object is instantiated properly by
     * checking to see if the returned object attributes match those it was instantiated with.
     * The testing for all the get methods is done in this test.
     */
    @Test
    public void instantiation() {
        String expectedEntityName = "Test Name";
        String expectedEntityID = "e012345";
        String expectedEntityType = "investor";
        marketEntity = new MarketEntity(expectedEntityName, expectedEntityID, expectedEntityType);
        
        String observedEntityName = marketEntity.getMarketName();
        String observedEntityID = marketEntity.getID();
        String observedEntityType = marketEntity.getType();
        
        //Checks if the expecetd outputs match the observed outputs.
        assertEquals(expectedEntityName, observedEntityName);
        assertEquals(expectedEntityID, observedEntityID);
        assertEquals(expectedEntityType, observedEntityType);
    }
    
    /**
     * Tests if the setMarketName method sets the object's marketName as it should.
     */
    public void setMarketNameTest() {
        String expectedEntityName = "Test Name";
        String expectedEntityID = "";
        String expectedEntityType = "";
        marketEntity = new MarketEntity(expectedEntityName, expectedEntityID, expectedEntityType);
        
        String obeservedEntityName = marketEntity.getMarketName();
        assertEquals(expectedEntityName, obeservedEntityName);
    }

    /**
     * Tests if the setID method sets the object's ID as it should.
     */
    public void setIDTest() {
        String expectedEntityName = "";
        String expectedEntityID = "e012345";
        String expectedEntityType = "";
        marketEntity = new MarketEntity(expectedEntityName, expectedEntityID, expectedEntityType);
        
        String obeservedEntityID = marketEntity.getID();
        assertEquals(expectedEntityID, obeservedEntityID);
    }

    /**
     * Tests if the setType method sets the object's type as it should.
     */
    public void setTypeTest() {
        String expectedEntityName = "";
        String expectedEntityID = "";
        String expectedEntityType = "investor";
        marketEntity = new MarketEntity(expectedEntityName, expectedEntityID, expectedEntityType);
        
        String obeservedEntityType = marketEntity.getType();
        assertEquals(expectedEntityType, obeservedEntityType);
    }

    /**
     *
     */
    @Test
    public void addMarketStrategyTest() {

    }

    /**
     * 
     */
    @Test
    public void setCurrentStrategyTest() {

    }
}
