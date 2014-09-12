package financialmarketsimulator.market;

import financialmarketsimulator.exception.OrderHasNoValuesException;
import financialmarketsimulator.marketData.MatchedMarketEntryAttempt;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

/**
 * @brief This encapsulates all the stock managers and all market participants
 * will interact with the market using this class's method. The class uses the
 * singleton design pattern because only one exchange is required.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MarketExchange {

    /**
     * @brief singleton instance
     */
    private static MarketExchange instance = null;
    /**
     * @brief name of the Stock Market Exchange
     */
    private String name;

    /**
     * @brief returns the name of the Stock Market Exchange
     * @return name of the stock market exchange
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Returns a Map of all StockManagers in the Exchange
     * @return Map of all StockManagers in Exchange
     */
    public Map<String, StockManager> getStocksManagers() {
        return stocksManagers;
    }

    /**
     * @brief indicates whether the Stock Market Exchange has any stock managers
     * @return true if Stock Market Exchange has no stock managers otherwise it
     * will return false
     */
    public boolean hasNoStockManagers() {
        return stocksManagers.isEmpty();
    }

    /**
     * @brief indicates whether the Stock Market Exchange has that particular
     * stock manager
     * @param stockName name of the stock to be searched
     * @return true if Stock Market Exchange has that particular stock manager
     * otherwise it will return false
     */
    public boolean stockFound(String stockName) {
        return stocksManagers.containsKey(stockName);
    }
    /**
     * @brief List of all StockManagers in Exchange
     */
    private Map<String, StockManager> stocksManagers = new Hashtable();

    /**
     * @brief Constructor
     * @param name name of the Stock Market Exchange
     */
    protected MarketExchange(String name) {
        this.name = name;
    }

    /**
     * @brief Get only instance of the object
     * @param name name of the Stock Market Exchange
     */
    public static MarketExchange getInstance(String name) {
        if (instance == null) {
            instance = new MarketExchange(name);
        }
        return instance;
    }

    /**
     * @brief add a new stock manager
     * @param stockManager stockmanager object to be added
     * @return true if the stockmanager has been added and false if it wasn't
     * successfully added
     */
    public boolean addStockManager(StockManager stockManager) {
        String searchKey = stockManager.getStockName();
        
        if (!stocksManagers.containsKey(searchKey)) {
            stocksManagers.put(searchKey, stockManager);
            return true;
        }
        
        return false;
    }

    /**
     * @brief remove a stock manager
     * @param stockManager stockmanager object to be removed
     * @return true if the stockmanager has been removed and false if it wasn't
     * successfully removed
     */
    public boolean removeStockManager(StockManager stockManager) {
        String searchKey = stockManager.getStockName();
        
        if (!stocksManagers.containsKey(searchKey)) {
            stocksManagers.remove(searchKey);
            return true;
        }
        
        return false;
    }

    /**
     * @brief Get the MarketEntryAttemptBook of a specific Stock
     * @param stockName name of the stock
     * @return MarketEntryAttemptBook for the stock
     */
    public MarketEntryAttemptBook getBook(String stockName) {
        return stocksManagers.get(stockName).getOrderList();
    }

    /**
     * @brief place an order on a specified stock
     * @param name name of the stock
     * @param attempt MarketEntryAttempt obect to be placed
     * @return true if place false if not placed
     */
    public boolean placeOrder(String name, MarketEntryAttempt attempt) throws InterruptedException {
        if (!stocksManagers.containsKey(name)) {
            stocksManagers.get(name).acceptOrder(attempt);
            return true;
        }
        
        return false;
    }

    /**
     * @brief edit an existing order
     * @param name name of the stock
     * @param attempt MarketEntryAttempt object to be edited
     * @param price new price
     * @param numberShares new number of shares
     * @return true if amended false if not amended
     * @throws InterruptedException
     * @throws OrderHasNoValuesException
     * @throws CloneNotSupportedException
     */
    public boolean amendOrder(String name, MarketEntryAttempt attempt, double price, int numberShares) throws InterruptedException, OrderHasNoValuesException, CloneNotSupportedException {
        if (!stocksManagers.containsKey(name)) {
            stocksManagers.get(name).editOrder(attempt.getOrderID(), price, numberShares, MarketEntryAttempt.SIDE.BID);
            return true;
        }
        
        return false;
    }

    /**
     * @brief cancel an order that has been placed
     * @param name of the order the stock
     * @param attempt MarketEntryAttempt to be canceled
     * @return true if canceled false if not canceled
     */
    public boolean cancelOrder(String name, MarketEntryAttempt attempt) {
        if (!stocksManagers.containsKey(name)) {
            stocksManagers.remove(name);
            return true;
        }
        return false;
    }

    /**
     * @brief update the manager with latest values
     * @param stockName name of the stock
     * @param updatedStockManager new StockManager
     */
    public void updateManager(String stockName, StockManager updatedStockManager) {
        stocksManagers.put(stockName, updatedStockManager);
    }
    
    public boolean stockAlreadyExists(String foundStockName) {
        
        for (int i = 0; i < stocksManagers.size(); i++) {
            
            StockManager manager = (StockManager) stocksManagers.get(foundStockName);
            
            if (manager.getStockName().equals(foundStockName)) {
                return true;
            }
        }
        
        return false;
    }
    
    public void clearStocks() {
        stocksManagers.clear();
    }

    /**
     * @brief get the profit for a specific MarketParticipant
     * @param marketParticipantID the MarketParticipant to get profit from
     * @return profit made or lost
     */
    public double getProfit(String marketParticipantID) {
        double totalProfit = 0.0;
        int countInstances = 0;
        double budget = 0.0;
        
        for (StockManager manager : stocksManagers.values()) {
            if (manager != null) {
                if (manager.participantIsTrading(marketParticipantID)) {
                    MarketParticipant participant = manager.getParticipant(marketParticipantID);
                    budget = participant.getBUDGET();
                    totalProfit += participant.getCurrentAmount();
                    countInstances++;
                }
            } else {
                System.out.println("Manager is null");
            }
        }
        
        double totalBudget = (budget * countInstances);
        double profit = (totalProfit / totalBudget);
        
        return profit;
    }
}