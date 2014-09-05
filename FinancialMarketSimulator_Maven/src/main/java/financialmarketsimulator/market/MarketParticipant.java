package financialmarketsimulator.market;

import financialmarketsimulator.exception.NameAlreadyExistsException;
import financialmarketsimulator.exception.NameNotFoundException;
import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.marketData.MatchedMarketEntryAttempt;
import financialmarketsimulator.marketData.Message;
import java.util.ArrayList;
import java.util.Vector;
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
    protected String participantName;
    /**
     * @brief participant ID
     */
    protected String participantID;
    /**
     * @brief market exchange
     */
    protected MarketExchange exchange;
    /**
     * @brief current strategy used by market entity to trade
     */
    protected MarketStrategy currentStrategy;
    /**
     * @brief list of all trade strategies used by the market entity to trade
     */
    protected ArrayList<MarketStrategy> strategies;
    /**
     * @brief If the entity is trading with a specfic stock
     */
    protected boolean started;
    /**
     * @brief pauses an entity from trading
     */
    protected boolean paused;
    /**
     * @brief stops an entity from trading
     */
    protected boolean stop;
    /**
     * @brief name of the stock the Market Entity is trading in
     */
    protected String stock;
    /**
     * @brief Order book used by participant
     */
    protected StockManager stockManager;
    /**
     * @brief Class encapsulating all the variants to be used by the class
     */
    protected Variants variants;
    /**
     * @brief different price ranges
     */
    private final double[] prices = {10.00, 100.00, 1000.00};
    /**
     * @brief different ranges for shares
     */
    private final int[] shares = {1000, 10000, 100000};
    /**
     * Used for GUI
     */
    protected JList bidsList;
    protected JList offersList;
    protected JList matchedList;

    /**
     * @brief Constructing a MarketEnity object with parameters
     * @param participantName name of the entity
     * @param participantID id of the entity
     * @param type the type of the entity
     * @param stock name of the stock
     */
    public MarketParticipant(String participantName, String participantID, MarketExchange exchange, String stock, MarketStrategy strategy) throws NotEnoughDataException {
        this.participantName = participantName;
        this.participantID = participantID;
        this.exchange = exchange;
        this.stock = stock;
        this.started = false;
        this.paused = false;
        this.stop = false;
        this.currentStrategy = strategy;

        //Initialise trading strategies
        this.strategies = new ArrayList<>();

        bidsList = offersList = matchedList = null;

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
    public MarketParticipant(String participantName, String participantID, MarketExchange exchange, String stock, Variants variants, MarketStrategy strategy, JList bidsList, JList offersList, JList matchedList) throws NotEnoughDataException {
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
        this.currentStrategy = strategy;

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
            if (strategyTmp.getStrategyName().equals(strategy.getStrategyName())) {
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
            if (strategy.getStrategyName().equals(name)) {
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
            //for(int i = 0; i < 1000; i++){
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
                    return;
                }
            }

            synchronized (this) {
                try {

                    double maxPrice;
                    int maxShares;

                    MarketEntryAttempt.SIDE side;

                    //Receive a signalMessage from strategy 
                    MarketStrategy.SignalMessage sigMessage = currentStrategy.trade();

                    if (sigMessage == null) {
                        System.out.println("No Signal Message");
                        continue;
                    }

                    //Decide on what to do based of Signal Message
                    switch (sigMessage.getVolatility()) {
                        case LOW: {
                            maxPrice = prices[0];
                            maxShares = shares[0];
                        }
                        break;
                        case MEDIUM: {
                            maxPrice = prices[1];
                            maxShares = shares[1];
                        }
                        break;
                        case HIGH: {
                            maxPrice = prices[2];
                            maxShares = shares[2];
                        }
                        break;
                        default: {
                            maxPrice = prices[0];
                            maxShares = shares[0];
                        }
                    }

                    switch (sigMessage.getSignal()) {
                        case BID: {
                            side = MarketEntryAttempt.SIDE.BID;
                        }
                        break;
                        case OFFER: {
                            side = MarketEntryAttempt.SIDE.OFFER;
                        }
                        break;
                        //Market is to volatile to create a MarketEntryAttempt
                        case DO_NOTHING: {
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }

                    //Select a minimum price range to trade
                    double minPrice = Math.round(maxPrice / 10);
                    //Select a minimum shares range to trade
                    int minShares = Math.round(maxShares / 10);

                    //Create MarketEntryAttempt based on data
                    MarketEntryAttempt newAttempt = new MarketEntryAttempt((double) (Math.random() * (maxPrice - minPrice) + minPrice), (int) (Math.random() * (maxShares - minShares) + minShares), this.getParticipantName(), side);

                    //Construct message to be sent
                    Message message = new Message(newAttempt, 0, 0, Message.MessageType.ORDER);

                    //Send a message to the stock manager
                    stockManager.sendMessage(message);

                } catch (NotEnoughDataException ex) {
                    ex.printStackTrace();
                }
                this.updateDisplay();
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

    public boolean hasStarted() {
        return started;
    }

    private void updateDisplay() {
        //Code used to update GUI
        DefaultListModel modelBids = new DefaultListModel();
        DefaultListModel modelOffers = new DefaultListModel();
        DefaultListModel modelMatched = new DefaultListModel();

        Vector offers = exchange.getBook(this.stock).getOffers();
        Vector bids = exchange.getBook(this.stock).getBids();
        Vector matched = exchange.getBook(this.stock).getMatchedOrders();

        for (int i = 0; i < bids.size(); i++) {
            MarketEntryAttempt attempt = (MarketEntryAttempt) bids.get(i);
            modelBids.addElement(attempt.toString());
        }

        for (int i = 0; i < offers.size(); i++) {
            MarketEntryAttempt attempt = (MarketEntryAttempt) offers.get(i);
            modelOffers.addElement(attempt.toString());
        }

        for (int i = 0; i < matched.size(); i++) {
            MatchedMarketEntryAttempt attempt = (MatchedMarketEntryAttempt) matched.get(i);
            modelMatched.addElement(attempt.toString());
        }

        if ((bidsList != null) && (offersList != null) && (matchedList != null)) {
            bidsList.setModel(modelBids);
            offersList.setModel(modelOffers);
            matchedList.setModel(modelMatched);
        }
    }
}
