package financialmarketsimulator.market;

import financialmarketsimulator.exception.NameAlreadyExistsException;
import financialmarketsimulator.exception.NameNotFoundException;
import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttempt.SIDE;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.BID;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.DO_NOTHING;
import static financialmarketsimulator.market.MarketStrategy.SIGNAL.OFFER;
import static financialmarketsimulator.market.MarketStrategy.VOLATILITY.HIGH;
import static financialmarketsimulator.market.MarketStrategy.VOLATILITY.LOW;
import static financialmarketsimulator.market.MarketStrategy.VOLATILITY.MEDIUM;
import financialmarketsimulator.marketData.Message;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
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
     * @brief Amount a MarketParticipant has to trade
     */
    private final double BUDGET = 10000;
    /**
     * @brief Amount of shares a MarketParticipant has to trade
     */
    private final int SHARES = 5000;
    /**
     * @brief Current amount of money remaining
     */
    private double currentAmount;
    /**
     * @brief amount of shares owned
     */
    private int amountOfShares;
    /**
     * @brief amount of current physical money
     */
    private double currentMoney;
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
    private final double[] PRICES_RANGE = {16.66, 33.33, 50};
    /**
     * @brief different ranges for shares
     */
    private final int[] SHARES_RANGES = {100, 1000, 10000};

    double maxPrice;
    int maxShares;

    double attemptPrice;
    int attemptShares;

    String ID;
    MarketEntryAttempt.SIDE attemptSide;

    MarketEntryAttempt.SIDE side;
    /**
     * @brief price step used to determine how much to increase the price with.
     */
    private final double[] PRICE_STEP = {0.05, 0.10, 0.15, 0.2, 0.25, 0.50};

    private int downBidTimer, downCounter, randomDecrease;

    private Random rand;

    public MarketParticipant() {
        this.profit = 0;
        this.currentMoney = BUDGET;
        this.rand = new Random();
        this.downBidTimer = rand.nextInt((800 - 2) + 2) + 2;
        this.randomDecrease = rand.nextInt((3 - 1) + 1) + 1;
        downCounter = 0;
        this.amountOfShares = SHARES;
        this.currentAmount = currentMoney;
    }

    /**
     * @brief Constructing a MarketEnity object with parameters
     * @param participantName name of the entity
     * @param participantID id of the entity
     * @param type the type of the entity
     * @param stock name of the stock
     */
    public MarketParticipant(String participantName, String participantID, MarketExchange exchange, String stock, MarketStrategy strategy) throws NotEnoughDataException {
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

        //Get the OrderList book for the stock 
        if (exchange != null) {
            this.stockManager = exchange.getStocksManagers().get(this.stock);
        }
    }

    /**
     * @param exchange
     * @param stock
     * @brief Constructing a MarketEnity object with parameters
     * @param participantName name of the entity
     * @param participantID id of the entity
     */
    public MarketParticipant(String participantName, String participantID, MarketExchange exchange, String stock, Variants variants, MarketStrategy strategy, JList bidsList, JList offersList, JList matchedList) throws NotEnoughDataException {
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
        if (exchange != null) {
            this.stockManager = exchange.getStocksManagers().get(this.stock);
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

    /**
     * @brief Update the StockManager with the latest information
     * @param Updatedmanager StockManagwr to update.
     */
    public void update(StockManager Updatedmanager) {
        exchange.updateManager(stock, Updatedmanager);
    }

    @Override
    public void run() {

        System.out.println("Stock name: " + stock);
        while (!Thread.currentThread().isInterrupted()) {
            try {
                while (paused) {
                    wait();
                }
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }   

            synchronized (this) {
                try {
                    //Receive a signalMessage from strategy 
                    MarketStrategy.SignalMessage sigMessage = currentStrategy.trade();

                    if (sigMessage == null) {
                        System.out.println("No Signal Message");
                        continue;
                    }

                    if (sigMessage.getVolatility() == null) {
                        sigMessage.setVolaility(MarketStrategy.VOLATILITY.LOW);
                    }

                    if (sigMessage.getSignal() == null) {
                        if (new Random().nextBoolean()) {
                            sigMessage.setSignal(MarketStrategy.SIGNAL.BID);
                        } else {
                            sigMessage.setSignal(MarketStrategy.SIGNAL.OFFER);
                        }
                    }

                    //Decide on what to do based of Signal Message
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
                            if (new Random().nextBoolean()) {
                                side = MarketEntryAttempt.SIDE.BID;
                            } else {
                                side = MarketEntryAttempt.SIDE.OFFER;
                            }
                        }
                        default: {
                            continue;
                        }
                    }

                    switch (sigMessage.getVolatility()) {
                        case LOW: {
                            maxPrice = PRICES_RANGE[0];
                            maxShares = SHARES_RANGES[0];
                        }
                        break;
                        case MEDIUM: {
                            maxPrice = PRICES_RANGE[1];
                            maxShares = SHARES_RANGES[1];
                        }
                        break;
                        case HIGH: {
                            maxPrice = PRICES_RANGE[2];
                            maxShares = SHARES_RANGES[2];
                        }
                        break;
                        default: {
                            maxPrice = PRICES_RANGE[0];
                            maxShares = SHARES_RANGES[0];
                        }
                    }

                    //Select a minimum price range to trade
                    double minPrice = Math.round(maxPrice - 16.66);
                    //Select a minimum shares range to trade
                    int minShares = Math.round(maxShares / 10);

                    //Create MarketEntryAttempt based on data
                    attemptPrice = (double) (Math.random() * (maxPrice - minPrice) + minPrice);
                    attemptPrice = Math.abs(attemptPrice);
                    attemptShares = Math.abs((int) (Math.random() * (maxShares - minShares) + minShares));
                    ID = this.getParticipantID();
                    attemptSide = side;

                    //Trading with no price or shares is invalid
                    if (attemptPrice <= 0 || attemptShares <= 0) {
                        System.out.println("Invalid Price or Share");
                        continue;
                    }

                    MarketEntryAttempt newAttempt = new MarketEntryAttempt(attemptPrice, attemptShares, ID, attemptSide);

                    System.out.println("Strategy " + this.currentStrategy.getStrategyName() + ", Signal " + sigMessage.getSignal().toString());

                    //Construct message to be sent
                    Message message = new Message(newAttempt, 0, 0, Message.MessageType.ORDER);
                    //System.out.println(this.participantName);

                    //Only one thread may send a message at a time
                    stockManager.sendMessage(message);
                    //this.print();
                    bringDownTheMarket();

                } catch (NotEnoughDataException ex) {
                    ex.printStackTrace();
                }

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
        cancel();
        stop = true;
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

    @Override
    public String toString() {
        return participantName + ", " + participantID + ", " + stock + ", " + currentStrategy.getStrategyName();
    }

    private void cancel() {
        interrupt();
    }

    /**
     * @param tradePricePerShare
     * @brief Update the worth of a MarketParticipant
     * @param shares
     * @param pricePerShare
     * @param side
     */
    public void upadateWorth(int shares, double pricePerShare, double tradePricePerShare, MarketEntryAttempt.SIDE side) {
        if (side == MarketEntryAttempt.SIDE.BID) {
            currentAmount -= tradePricePerShare * shares;
            amountOfShares += shares;
            setCurrentAmount(pricePerShare);
        } else if (side == MarketEntryAttempt.SIDE.OFFER) {
            amountOfShares -= shares;
            currentAmount += tradePricePerShare * shares;
            setCurrentWorth(pricePerShare);
        }
    }

    private void setCurrentWorth(double pricePerShare) {
        currentAmount = 0.0;
        currentAmount += currentMoney;
        currentAmount += amountOfShares * pricePerShare;
    }

    private void bringDownTheMarket() {
        if (downCounter == downBidTimer) {
            this.exchange.getBook(this.stock).placeOrder(new MarketEntryAttempt(this.exchange.getBook(stock).getLastTradePrice() - this.randomDecrease, 1000, ID, SIDE.BID));
            this.exchange.getBook(this.stock).placeOrder(new MarketEntryAttempt(this.exchange.getBook(stock).getLastTradePrice() - this.randomDecrease, 1000, ID, SIDE.OFFER));

            this.randomDecrease = rand.nextInt((3 - 1) + 1) + 1;
            downCounter = 0;
            downBidTimer = rand.nextInt((800 - 2) + 2) + 1;
        } else if (downCounter > downBidTimer) {
            downCounter = 0;
            downBidTimer = rand.nextInt((800 - 2) + 2) + 1;
        }
        downCounter++;
    }
}
