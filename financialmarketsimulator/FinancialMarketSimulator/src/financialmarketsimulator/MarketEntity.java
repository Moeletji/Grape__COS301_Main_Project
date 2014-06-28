package financialmarketsimulator;

import financialmarketsimulator.exception.NameAlreadyExistsException;
import financialmarketsimulator.exception.NameNotFoundException;
import java.util.ArrayList;

/**
 *
 * @authors Madimetja Shika, Moeletji Semenya, Daniel Makgonta
 */
public class MarketEntity {
    
    //Name of the entity
    private String marketName;
    //Entity id
    private String entityID;
    //Type of entity
    private String type;
    //Current stratgy used by market to trade
    private MarketStrategy currentStrategy;
    //List of all strategies enity uses to trade
    private ArrayList<MarketStrategy> strategies;
    
    /**
     * @brief Constructing a MarketEnity object with parameters
     * @param marketName name of the entity
     * @param entityID id of the entity
     * @param type the type of the entity
     */
    public MarketEntity(String marketName, String entityID, String type)
    {
        this.marketName = marketName;
        this.entityID = entityID;
        this.type = type;
        
        //Initialise trading strategies
        strategies = new ArrayList<>();
    }
    /**
     * @brief get the entity name 
     * @return the name of the entity
     */
    public String getMarketName()
    {
        return this.marketName;
    }
    
    /**
     * @brief get the entity id 
     * @return the id of the entity
     */
    public String getID()
    {
        return this.entityID;
    }
    
    /**
     * @brief get the entity type 
     * @return the type of the entity
     */
    public String getType()
    {
        return this.type;
    }
    
    /**
     * @brief Get the current strategy for the entity.
     * @return The current strategy being used by the entity.
     */
    public MarketStrategy getCurrentStrategy()
    {
        return this.currentStrategy;
    }
    
    /**
     * @brief get the full list of strategies being used by the entity.
     * @return ArrayList of all the strategies being used by the entity.
     */
    public ArrayList getStrategies()
    {
        return this.strategies;
    }
    
    /**
     * @brief set the entity name 
     */
    public void setMarketName(String marketName)
    {
        this.marketName = marketName;
    }
    
    /**
     * @brief set the entity id 
     */
    public void setID(String ID)
    {
        this.entityID = ID;
    }
    
    /**
     * @brief set the entity type 
     */
    public void setType(String type)
    {
        this.type = type;
    }
    
    /**
     * @brief add a strategy to be used to trade
     * @param strategy new strategy to be added
     * @throws NameAlreadyExistsException 
     */
    public void addStrategy(MarketStrategy strategy) throws NameAlreadyExistsException 
    {
        for(MarketStrategy strategyTmp : strategies)
        {
            if(strategyTmp.getName().equals(strategy.getName()))
            {
                throw new NameAlreadyExistsException();
            }
        }
        this.strategies.add(strategy);
    }
    
    /**
     * @brief set the current strategy used by the entity
     * @param name of the current strategy to be used
     * @throws NameNotFoundException 
     */
    public void setCurrentStrategy(String name) throws NameNotFoundException
    {
        for(MarketStrategy strategy : strategies)
        {
            if(strategy.getName().equals(name))
            {
                currentStrategy = strategy;
                return;
            }
        }
        throw new NameNotFoundException();
    }
}
