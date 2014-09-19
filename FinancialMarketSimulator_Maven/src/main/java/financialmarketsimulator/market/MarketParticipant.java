package financialmarketsimulator.market;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import financialmarketsimulator.exception.NameAlreadyExistsException;
import financialmarketsimulator.exception.NameNotFoundException;
import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.marketData.MatchedMarketEntryAttempt;
import financialmarketsimulator.marketData.Message;
import financialmarketsimulator.marketData.XStreamTranslator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import static java.util.UUID.randomUUID;
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
@XStreamAlias("MarketParticipant")
public class MarketParticipant extends Thread {

    /**
     * @brief Amount a MarketParticipant has to trade
     */
    private final double BUDGET = 10000;
    /**
     * @brief Current amount of money remaining
     */
    private double currentAmount;
    /**
     * @brief amount of shares owned
     */
    private int amountOfShares;
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
     * @brief Profit of a MarketParticipant
     */
    private double profit;
    /**
     * @brief different price ranges
     */
    private final double[] prices = {5.33, 5.67, 6};
    /**
     * @brief different ranges for shares
     */
    private final int[] shares = {100, 1000, 10000};
    /**
     * Used for GUI
     */
    
    @XStreamOmitField
    private UUID filename = UUID.randomUUID();
    
    @XStreamOmitField
    XStreamTranslator xstream = new XStreamTranslator();
    
    @XStreamOmitField
    protected JList bidsList;
    
    @XStreamOmitField
    protected JList offersList;
    
    @XStreamOmitField
    protected JList matchedList;

    public MarketParticipant() {
        this.profit = 10000;
    }

    /**
     * @brief Constructing a MarketEnity object with parameters
     * @param participantName name of the entity
     * @param participantID id of the entity
     * @param type the type of the entity
     * @param stock name of the stock
     */
    public MarketParticipant(String participantName, String participantID, MarketExchange exchange, String stock, MarketStrategy strategy) throws NotEnoughDataException, IOException {
        this();
        this.participantName = participantName;
        this.participantID = participantID;
        this.exchange = exchange;
        this.stock = stock;
        this.started = false;
        this.paused = false;
        this.stop = false;
        this.currentStrategy = strategy;
        this.amountOfShares = 0;
        this.currentAmount = BUDGET;

        //Initialise trading strategies
        this.strategies = new ArrayList<>();

        bidsList = offersList = matchedList = null;

        //Get the OrderList book for the stock 
        this.stockManager = exchange.getStocksManagers().get(this.stock);
        //this.saveParticipant();
}

    /**
     * @param exchange
     * @param stock
     * @brief Constructing a MarketEnity object with parameters
     * @param participantName name of the entity
     * @param participantID id of the entity
     */
    public MarketParticipant(String participantName, String participantID, MarketExchange exchange, String stock, Variants variants, MarketStrategy strategy, JList bidsList, JList offersList, JList matchedList) throws NotEnoughDataException, IOException {
        this();
        this.participantName = participantName;
        this.participantID = participantID;
        this.exchange = exchange;
        this.stock = stock;
        this.variants = variants;
        this.started = false;
        this.paused = false;
        this.stop = false;
        this.amountOfShares = 0;
        this.currentAmount = BUDGET;

        this.bidsList = bidsList;
        this.offersList = offersList;
        this.matchedList = matchedList;
        this.currentStrategy = strategy;

        //Initialise trading strategies
        this.strategies = new ArrayList<>();

        //Get the OrderList book for the stock 
        this.stockManager = exchange.getStocksManagers().get(this.stock);
        //this.saveParticipant();
  
    }

    public void saveParticipant() 
    {
        try {
            if (xstream.storeObject(this,filename.toString()))
            {
                
            }
        } catch (IOException ex) {
            //Logger.getLogger(MarketParticipant.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception thrown in save Participant method");
        }
    }
    /**
     * @brief Returns the initial budget for a MarketParticipant
     * @return The Participant's Budget
     */
    public double getBUDGET() {
        return BUDGET;
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
    public void addStrategy(MarketStrategy strategy) throws NameAlreadyExistsException, IOException {
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
    public void setCurrentStrategy(String name) throws NameNotFoundException, IOException {
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
                    double minPrice = Math.round(maxPrice - 33);
                    //Select a minimum shares range to trade
                    int minShares = Math.round(maxShares / 10);

                    //Create MarketEntryAttempt based on data
                    double attemptPrice = (double) (Math.random() * (maxPrice - minPrice) + minPrice);
                    int attemptShares = (int) (Math.random() * (maxShares - minShares) + minShares);
                    String Id = this.getParticipantID();
                    MarketEntryAttempt.SIDE attemptSide = side;

                    //Trading with no price or shares is invalid
                    if (attemptPrice <= 0 || attemptShares <= 0) {
                        continue;
                    }

                    if (side == MarketEntryAttempt.SIDE.BID) {
                        //If you don't have enough money to buy shares, then don't buy
                        //An alternative can be thought of later
                        //Use PriceStep downwards until you find a suitable amount
                        if (currentAmount < (attemptPrice * attemptShares)) {
                            continue;
                        }
                        currentAmount += (attemptPrice * attemptShares);
                        amountOfShares += attemptShares;

                    } else if (side == MarketEntryAttempt.SIDE.OFFER) {
                        //If you don't have enough shares to sell then sell only what you have
                        if (amountOfShares < attemptShares) {
                            attemptShares = amountOfShares;
                        }

                        //Get the current price of the stock
                        double currentPrice = stockManager.getOrderList().getLowestOfferPrice();
                        currentAmount -= (currentPrice * attemptShares);
                        amountOfShares -= attemptShares;
                    }

                    MarketEntryAttempt newAttempt = new MarketEntryAttempt(attemptPrice, attemptShares, Id, attemptSide);

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

    /* Accessors and Mutators */
    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public int getAmountOfShares() {
        return amountOfShares;
    }

    public void setAmountOfShares(int amountOfShares) {
        this.amountOfShares = amountOfShares;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
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
    
    @Override
    public String toString(){
        return participantName + ", " + participantID + ", " + stock + ", " + currentStrategy.getStrategyName();
    }
}
