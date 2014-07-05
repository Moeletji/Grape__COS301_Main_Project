
import financialmarketsimulator.market.MarketEntity;
import financialmarketsimulator.market.MarketStrategy;
import financialmarketsimulator.exception.NameAlreadyExistsException;
import financialmarketsimulator.exception.NameNotFoundException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @brief Test class for the MarketEntry class. ALl the methods of the
 * MarketEntry class are tested within this test class.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MarketEntityUnitTest {

    /**
     * Market Entity test object used throughout the unit tests.
     */
    MarketEntity marketEntity;

    public MarketEntityUnitTest() {
    }
    /**
     * @brief This method tests if the marketEntity object is instantiated
     * properly by checking to see if the returned object attributes match those
     * it was instantiated with. The testing for all the get methods is done in
     * this test.
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
     * @brief Tests if the setMarketName method sets the object's marketName as
     * it should.
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
     * @brief Tests if the setID method sets the object's ID as it should.
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
     * @brief Tests if the setType method sets the object's type as it should.
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
     * @brief Tests if a strategy can be added to the marketEntity by observing
     * if the strategy has been added to the entity's array of strategies. If
     * the strategy already exists in the strategy array list, an exception
     * should be thrown. This function also tests if the marketEntity strategy
     * getters work as expected.
     */
    @Test
    public void addMarketStrategyTest() {
        String expectedEntityName = "Test Name";
        String expectedEntityID = "e012345";
        String expectedEntityType = "investor";
        marketEntity = new MarketEntity(expectedEntityName, expectedEntityID, expectedEntityType);

        String expectedStrategyName = "testStrategy";
        Boolean expectedStrategyFound = false;
        MarketStrategy expectedStrategy = new MarketStrategy(expectedStrategyName);

        //Attempt to add the strategy. No exceptionn should be thrown.
        try {
            marketEntity.addStrategy(expectedStrategy);
        } catch (NameAlreadyExistsException ex) {
            ex.printStackTrace();
        }

        ArrayList<MarketStrategy> returnedStrategies = marketEntity.getStrategies();

        //Check if the added strategy exists in the strategy array list
        for (MarketStrategy tempStrategy : returnedStrategies) {
            if (tempStrategy.equals(expectedStrategy)) {
                expectedStrategyFound = true;
                break;
            }
        }

        //excpectedStrategyFound should return true
        assertEquals(expectedStrategyFound, true);
    }

    /**
     * @brief Tests if a strategy can be set as the current strategy being used
     * by the entity by observing if the marketEntity's currentStrategy field is
     * set accordingly. The test also checks if the strategy being set as the
     * current strategy exists in the strategy array list before it is set. Else
     * the strategy cannot be set as the current strategy if it does not exist.
     * This function also tests if the marketEntity strategy getters work as
     * expected.
     */
    @Test
    public void setCurrentStrategyTest() {
        String expectedEntityName = "Test Name";
        String expectedEntityID = "e012345";
        String expectedEntityType = "investor";
        marketEntity = new MarketEntity(expectedEntityName, expectedEntityID, expectedEntityType);

        //**********************//
        //*Add strategy to list*//
        //**********************//
        String expectedCurrentStrategy = "testStrategy";
        Boolean expectedStrategyFound = false;
        MarketStrategy strategy = new MarketStrategy(expectedCurrentStrategy);

        //Attempt to add the strategy. No exceptionn should be thrown.
        try {
            marketEntity.addStrategy(strategy);
        } catch (NameAlreadyExistsException ex) {
            ex.printStackTrace();
        }

        ArrayList<MarketStrategy> returnedStrategies = marketEntity.getStrategies();

        //Check if the added strategy exists in the strategy array list
        for (MarketStrategy tempStrategy : returnedStrategies) {
            if (tempStrategy.getName().equals(expectedCurrentStrategy)) {
                expectedStrategyFound = true;
                break;
            }
        }

        assertEquals(expectedStrategyFound, true);
        //At this point the strategy has been added and exists in the strategy array list

        //*******************************//
        //*Set added strategy as current*//
        //*******************************//
        //No exception should be thrown in this try block
        try {
            marketEntity.setCurrentStrategy(expectedCurrentStrategy);
        } catch (NameNotFoundException ex) {
            ex.printStackTrace();
        }

        //Check if the entity's returned strategy matches the excpeted strategy
        assertEquals(expectedCurrentStrategy, marketEntity.getCurrentStrategy().getName());
    }
}
