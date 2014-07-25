package financialmarketsimulator.market;

import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;
import financialmarketsimulator.exception.NameAlreadyExistsException;
import financialmarketsimulator.exception.NameNotFoundException;
import financialmarketsimulator.marketData.MatchedMarketEntryAttempt;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * @brief The super class from which each market entity or market participant
 * will inherit from. Each entity will have a market name, an entity ID, an
 * entity type and a list of their strategies and the current strategy they are
 * using.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MarketParticipant extends Thread {

    /**
     * @brief name of the market participant
     */
    private String participantName;
    /**
     * @brief participant ID
     */
    private String participantID;
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
    private String stock;
    /**
     * @brief Order book used by participant
     */
    StockManager stockManager;
    /**
     * @brief Class encapsulating all the variants to be used by the class
     */
    private Variants variants;
    
    /**
     * Used for GUI
     */
    
    JList bidsList; 
    JList offersList; 
    JList matchedList;

    /**
     * @brief Constructing a MarketEnity object with parameters
     * @param participantName name of the entity
     * @param participantID id of the entity
     * @param type the type of the entity
     * @param stock name of the stock
     */
    public MarketParticipant(String participantName, String participantID, MarketExchange exchange, String stock) {
        this.participantName = participantName;
        this.participantID = participantID;
        this.exchange = exchange;
        this.stock = stock;
        this.started = false;
        this.paused = false;
        this.stop = false;

        //Initialise trading strategies
        this.strategies = new ArrayList<>();

        //Get the OrderList book for the stock 
        this.stockManager = exchange.getStocksManagers().get(this.stock);
    }

    /**
     * @param exchange
     * @param stock
     * @brief Constructing a MarketEnity object with parameters
     * @param participantName name of the entity
     * @param participantID id of the entity
     */
    public MarketParticipant(String participantName, String participantID, MarketExchange exchange, String stock, Variants variants, JList bidsList, JList offersList, JList matchedList) {
        this.participantName = participantName;
        this.participantID = participantID;
        this.exchange = exchange;
        this.stock = stock;
        this.variants = variants;
        this.started = false;
        this.paused = false;
        this.stop = false;
        
        this.bidsList = bidsList;
        this.offersList = offersList;
        this.matchedList = matchedList;

        //Initialise trading strategies
        this.strategies = new ArrayList<>();

        //Get the OrderList book for the stock 
        this.stockManager = exchange.getStocksManagers().get(this.stock);
    }

    /**
     * @brief get the entity name
     * @return the name of the entity
     */
    public String getParticipantName() {
        return this.participantName;
    }

    /**
     * @brief get the entity id
     * @return the id of the entity
     */
    public String getParticipantID() {
        return participantID;
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
    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    /**
     * @brief set the entity id
     */
    public void setID(String ID) {
        this.participantID = ID;
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

    @Override
    public void run() {

        System.out.println("Stock name: " + stock);

        while (true) {
            //for (int i = 0; i < 3; i++) {
            try {
                synchronized (this) {
                    while (paused) {
                        wait();
                    }
                }
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

            synchronized (this) {
                if (stop) {
                    break;
                }
            }


            //************************************************************************
            //This is where you trade ... 
            //*************************************************************************

            //REMOVE ALL CODE IN THIS BLOCK THIS IS JUST USED TO TEST IF IT WORKS

            int min = 0;
            int max = 5;

            int maxShares = 1000;
            int minShares = 500;


            int flag = new Random().nextInt(11);

            MarketEntryAttempt.SIDE side;

            if (flag > 5) {
                side = MarketEntryAttempt.SIDE.BID;
            } else {
                side = MarketEntryAttempt.SIDE.OFFER;
            }

            synchronized (this) {

                //Uncomment line below and add trade functionality inside trade method
                //trade();

                MarketEntryAttempt newAttempt = new MarketEntryAttempt((double) (Math.random() * (max - min) + min), (int) (Math.random() * (maxShares - minShares) + minShares), participantName, side);
                //MarketEntryAttempt newAttempt = new MarketEntryAttempt(45.56, 1500, participantName, side);

                try {
                    
                    stockManager.acceptOrder(newAttempt);
                   //DefaultListModel model = new DefaultListModel();
                    
                    //for(inti)
                    
                    //model.add();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MarketParticipant.class.getName()).log(Level.SEVERE, null, ex);
                }

                this.print();
            }
        }
    }

    @Override
    synchronized public void start() {
        System.out.println(this.participantID + " Thread has started");

        if (!started) {
            started = true;
            super.start();
        }

        if (paused) {
            notify();
            paused = false;
        }
    }

    synchronized public void pause() {
        paused = true;
    }

    synchronized public void terminateTrading() {
        stop = true;
    }

    public void trade() {
        
    }

    public void update(StockManager Updatedmanager) {
        exchange.updateManager(stock, Updatedmanager);
    }

    public void print() {
        Vector offers = exchange.getBook(this.stock).getOffers();
        Vector bids = exchange.getBook(this.stock).getBids();
        Vector matched = exchange.getBook(this.stock).getMatchedOrders();

        for (int i = 0; i < offers.size(); i++) {
            MarketEntryAttempt attempt = (MarketEntryAttempt) offers.get(i);
            System.out.println("Offers " + attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }

        System.out.println("\n\n*****************");

        for (int i = 0; i < bids.size(); i++) {
            MarketEntryAttempt attempt = (MarketEntryAttempt) bids.get(i);
            System.out.println("Bids " + attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }

        System.out.println("\n\n***********************");

        for (int i = 0; i < matched.size(); i++) {
            MatchedMarketEntryAttempt attempt = (MatchedMarketEntryAttempt) matched.get(i);
            System.out.println("Matched " + attempt.getQuantity() + "  " + attempt.getPrice() + "\n");
        }

        System.out.println("\n\n****************************\n****************************");
    }

    public boolean isTrading() {
        return this.started;
    }

    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
