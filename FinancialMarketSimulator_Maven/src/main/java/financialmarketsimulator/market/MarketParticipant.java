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
import static financialmarketsimulator.market.MarketStrategy.VOLATILITY.NORMAL;
import financialmarketsimulator.marketData.Message;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class MarketParticipant {

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
     * @brief Percentage of shares to sell
     */
    private final double SHARES_PERCENTAGE = 0.1;
    /**
     * @brief Percentage of money to use when buying
     */
    private final double PRICE_PERCENTAGE = 0.15;
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
     * @brief If the entity is trading with a specific stock
     */
    protected volatile boolean started;
    /**
     * @brief pauses an entity from trading
     */
    protected volatile boolean paused;
    /**
     * @brief stops an entity from trading
     */
    protected volatile boolean stop;
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
    private final int[] PRICES_RANGE = {5, 10, 15};
    /**
     * @brief different ranges for shares
     */
    private final int[] SHARES_RANGES = {100, 1000, 10000};
    /**
     * @brief Side in which trade occurred
     */
    private MarketEntryAttempt.SIDE attemptSide;
    /**
     * @brief price step used to determine how much to increase the price with.
     */
    private final double[] PRICE_STEP = {0.05, 0.10, 0.15, 0.2, 0.25, 0.50};
    /**
     * @brief used to bring down the Market Trading Price
     */
    /**
     * @brief used to randomize the MarketParticipant
     */
    private Random rand;

    public MarketParticipant() {
        this.profit = 0;
        this.currentMoney = BUDGET;
        this.rand = new Random();
        this.amountOfShares = SHARES;
        this.currentAmount = currentMoney;
        this.stop = this.paused = this.started = false;
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
        this.currentStrategy = strategy;
        this.amountOfShares = 0;
        this.currentAmount = BUDGET;

        //Initialise trading strategies
        this.strategies = new ArrayList<>();

        //Get the OrderList book for the stock 
        if (exchange != null) {
            this.stockManager = exchange.getStocksManagers().get(this.stock);
        } else {
            System.out.println("No MarketExchange to trade from");
            System.out.println("Exiting system");
            System.exit(0);
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

    public void runSimulator() {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (!stop) {
                    try {
                        while (paused) {
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    MarketStrategy.SignalMessage sigMessage = null;
                    double price = 0;
                    int shares = 0;

                    try {
                        sigMessage = currentStrategy.trade();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (sigMessage == null) {
                        continue;
                    }

                    MarketStrategy.SIGNAL signal = sigMessage.getSignal();
                    MarketStrategy.VOLATILITY volatility = sigMessage.getVolatility();

                    if (signal == null) {
                        attemptSide = rand.nextBoolean() == true ? MarketEntryAttempt.SIDE.BID : MarketEntryAttempt.SIDE.OFFER;
                    } else {
                        switch (signal) {
                            case BID: {
                                attemptSide = MarketEntryAttempt.SIDE.BID;
                            }
                            break;
                            case OFFER: {
                                attemptSide = MarketEntryAttempt.SIDE.OFFER;
                            }
                            break;
                            case DO_NOTHING:
                            default: {
                                attemptSide = rand.nextBoolean() == true ? MarketEntryAttempt.SIDE.BID : MarketEntryAttempt.SIDE.OFFER;
                            }
                        }
                    }

                    if (volatility == null) {
                        StockManager manager = exchange.getManager(stock);
                        if (!(manager == null)) {
                            double lastTradedPrice = manager.getOrderList().getLastTradePrice();

                            if (lastTradedPrice <= 0.0) {
                                price = Math.abs(rand.nextInt(PRICES_RANGE[2]));
                                shares = SHARES_RANGES[0];
                            } else {
                                price = lastTradedPrice + PRICE_STEP[0];
                                shares = SHARES_RANGES[0];
                            }
                        } else {
                            System.out.println("StockManager not define for MarketParticipnt");
                            return;
                        }
                    } else {
                        Random random = new Random();
                        double factor = random.nextDouble();
                        if (attemptSide == MarketEntryAttempt.SIDE.BID) {
                            switch (volatility) {
                                case HIGH: {
                                    price = exchange.getManager(stock).getOrderList().getLastTradePrice() + (PRICE_STEP[5] * factor);
                                    shares = SHARES_RANGES[2];
                                }
                                break;
                                case MEDIUM: {
                                    price = exchange.getManager(stock).getOrderList().getLastTradePrice() + (PRICE_STEP[4] * factor);
                                    shares = SHARES_RANGES[1];
                                }
                                break;
                                case LOW: {
                                    price = exchange.getManager(stock).getOrderList().getLastTradePrice() + (PRICE_STEP[3] * factor);
                                    shares = SHARES_RANGES[0];
                                }
                                break;
                                case NORMAL:
                                default: {
                                    price = exchange.getManager(stock).getOrderList().getLastTradePrice() + (PRICE_STEP[3] * factor);
                                    shares = SHARES_RANGES[0];
                                }
                            }
                        } else {
                            switch (volatility) {
                                case HIGH: {
                                    price = exchange.getManager(stock).getOrderList().getLastTradePrice() - (PRICE_STEP[5] * factor);
                                    shares = SHARES_RANGES[2];
                                }
                                break;
                                case MEDIUM: {
                                    price = exchange.getManager(stock).getOrderList().getLastTradePrice() - (PRICE_STEP[4] * factor);
                                    shares = SHARES_RANGES[1];
                                }
                                break;
                                case LOW: {
                                    price = exchange.getManager(stock).getOrderList().getLastTradePrice() - (PRICE_STEP[3] * factor);
                                    shares = SHARES_RANGES[0];
                                }
                                break;
                                case NORMAL:
                                default: {
                                    price = exchange.getManager(stock).getOrderList().getLastTradePrice() - (PRICE_STEP[3] * factor);
                                    shares = SHARES_RANGES[0];
                                }
                            }
                        }
                    }

                    price = Math.abs(price);

                    if (price <= 0.0 || shares <= 0) {
                        continue;
                    }

                    MarketEntryAttempt newAttempt = new MarketEntryAttempt(price, shares, participantID, attemptSide);

                    //Construct message to be sent
                    Message message = new Message(newAttempt, 0, 0, Message.MessageType.ORDER);

                    //Only one thread may send a message at a time
                    stockManager.sendMessage(message);
                }
            }
        };
        t.start();
    }

    public void start() {
        System.out.println(this.participantID + " Thread has started");

        if (!started) {
            started = true;
        }

        if (paused) {
            notify();
            paused = false;
        }
        
        this.runSimulator();
    }

    synchronized public void pause() {
        paused = true;
    }

    synchronized public void terminateTrading() {
        stop = true;
    }

    /**
     * @brief see if a particular StockManager is trading
     * @return  if the participant has strated trading or not
     */
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

    public void writeTradesToFile() throws IOException {
        Path path = Paths.get("/tmp/foo/bar.txt");

        Files.createDirectories(path.getParent());

        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            System.err.println("already exists: " + e.getMessage());
        }
    }
}
