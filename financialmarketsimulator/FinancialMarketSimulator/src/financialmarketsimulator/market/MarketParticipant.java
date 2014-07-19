package financialmarketsimulator.market;

import financialmarketsimulator.exception.NameAlreadyExistsException;
import financialmarketsimulator.exception.NameNotFoundException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;

/**
 * @brief The super class from which each market entity or market participant
 * will inherit from. Each entity will have a market name, an entity ID, an
 * entity type and a list of their strategies and the current strategy they are
 * using.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MarketParticipant extends Thread {

    /**
     * @brief name of the entity
     */
    private String marketName;
    /**
     * @brief entity ID
     */
    private String entityID;
    /**
     * @brief type of entity
     */
    private String type;
    /**
     * @brief market exchange
     */
    private MarketExchange exchange;
    /**
     * @brief current strategy used by market entity to trade
     */
    private MarketStrategy currentStrategy;
    /**
     * @brief list of all trade strategies used by the market entity to trade
     */
    private ArrayList<MarketStrategy> strategies;
    /**
     * @brief If the entity is trading with a specfic stock
     */
    private boolean started;
    /**
     * @brief pauses an entity from trading
     */
    private boolean paused;
    /**
     * @brief stops an entity from trading
     */
    private boolean stop;
    /**
     * @brief name of the stock the Market Entity is trading in
     */
    private final String stock;

    /**
     * @brief Constructing a MarketEnity object with parameters
     * @param marketName name of the entity
     * @param entityID id of the entity
     * @param type the type of the entity
     */
    public MarketParticipant(String marketName, String entityID, String type, MarketExchange exchange, String stock) {
        this.marketName = marketName;
        this.entityID = entityID;
        this.type = type;
        this.exchange = exchange;
        this.stock = stock;
        this.started = false;
        this.paused = false;
        this.stop = false;

        //Initialise trading strategies
        this.strategies = new ArrayList<>();
    }

    /**
     * @brief get the entity name
     * @return the name of the entity
     */
    public String getMarketName() {
        return this.marketName;
    }

    /**
     * @brief get the entity id
     * @return the id of the entity
     */
    public String getEntityID() {
        return entityID;
    }

    /**
     * @brief get the entity type
     * @return the type of the entity
     */
    public String getType() {
        return this.type;
    }

    /**
     * @brief Get the current strategy for the entity.
     * @return The current strategy being used by the entity.
     */
    public MarketStrategy getCurrentStrategy() {
        return this.currentStrategy;
    }

    /**
     * @brief get the full list of strategies being used by the entity.
     * @return ArrayList of all the strategies being used by the entity.
     */
    public ArrayList getStrategies() {
        return this.strategies;
    }

    /**
     * @brief set the entity name
     */
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    /**
     * @brief set the entity id
     */
    public void setID(String ID) {
        this.entityID = ID;
    }

    /**
     * @brief set the entity type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @brief add a strategy to be used to trade
     * @param strategy new strategy to be added
     * @throws NameAlreadyExistsException
     */
    public void addStrategy(MarketStrategy strategy) throws NameAlreadyExistsException {
        for (MarketStrategy strategyTmp : strategies) {
            if (strategyTmp.getName().equals(strategy.getName())) {
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
    public void setCurrentStrategy(String name) throws NameNotFoundException {
        for (MarketStrategy strategy : strategies) {
            if (strategy.getName().equals(name)) {
                currentStrategy = strategy;
                return;
            }
        }
        throw new NameNotFoundException();
    }

    public void run() {
        //Get the book for the stock entity is tradin in
        //You may add additional fields here if you require more 
        MarketEntryAttemptBook book = exchange.getBook(stock);
        
        while (true) {
            try {
                synchronized (this) {
                    while (paused) {
                        wait();
                    }
                }
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            
            if(stop)
                break;
            
            synchronized(book){
                
                
                //************************************************************************
                //This is where you trade ... 
                //*************************************************************************
                
                //REMOVE ALL CODE IN THIS BLOCK THIS IS JUST USED TO TEST IF IT WORKS
                //Uncomment line below and add trade functionality inside trade method
                //trade();
                int min = 0;
                int max = 100;
                
                int maxShares = 10000;
                int minShares = 500;
                
                boolean flag;
                
                flag = new Random().nextBoolean();
                
                MarketEntryAttempt.SIDE side;
                
                if(flag)
                    side = MarketEntryAttempt.SIDE.BID;
                else
                    side = MarketEntryAttempt.SIDE.OFFER;
                
                //MarketEntryAttempt newAttempt = new MarketEntryAttempt((double)(Math.random() * (max - min) + min), (int)(Math.random() * (maxShares - minShares) + minShares), marketName, side);
                MarketEntryAttempt newAttempt = new MarketEntryAttempt(45.56, 1500, marketName, side);
                book.placeOrder(newAttempt);
            }
        }
    }

    synchronized public void start() {
        if (!started) {
            started = true;
            super.start();
        }

        if (paused) {
            notify();
            paused = false;
            return;
        }
    }

    synchronized public void pause() {
        paused = true;
    }
    
    synchronized public void terminateTrading() {
        stop = true;
    }
    
    public void trade(){
        
    }

}
