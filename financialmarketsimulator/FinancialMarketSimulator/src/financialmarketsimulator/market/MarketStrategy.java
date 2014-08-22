package financialmarketsimulator.market;

import financialmarketsimulator.marketData.Message;
import java.util.Map;

/**
 * @brief The super market strategy class from which each concrete market
 * strategy used by each participant will inherit from.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public abstract class MarketStrategy {

    /**
     * @brief Name of the MarketExchange
     */
    protected MarketExchange exchange;

    /**
     * @brief Name of the MarketStrategy
     */
    private final String strategyName;

    /**
     * @brief MarketStrategy constructor
     * @param exchange Stock exchange
     * @param strategyName Name of the Market Strategy
     */
    public MarketStrategy(MarketExchange exchange, String strategyName) {
        this.exchange = exchange;
        this.strategyName = strategyName;
    }

    /**
     * @brief Retrieve the OrderList for a stock
     * @param stockName name of the stock to be searched
     * @return MarketEntryAttemptBook for a particular stock
     */
    public MarketEntryAttemptBook getOrderList(String stockName) {
        return exchange.getBook(stockName);
    }

    /**
     * @brief Retrieve the name of the name of the strategy
     * @return Strategy name
     */
    public String getStrategyName() {
        return strategyName;
    }

    /**
     * @brief Make a MarketEntryAttempt
     * @param attempt MarketEntryAttempt
     * @param stockName Name of the stock
     */
    public void makeMarketEntryAttempt(MarketEntryAttempt attempt, String stockName) {
        Map<String, StockManager> managers = exchange.getStocksManagers();
        StockManager manager = managers.get(stockName);
        Message message = new Message(attempt, 0, 0, Message.MessageType.ORDER);
        manager.sendMessage(message);
    }

    /**
     * @brief Cancel a MarketEntryAttempt
     * @param attempt MarketEntryAttempt
     * @param stockName Name of the stock
     */
    public void cancelMarketEntryAttempt(MarketEntryAttempt attempt, String stockName) {
        Map<String, StockManager> managers = exchange.getStocksManagers();
        StockManager manager = managers.get(stockName);
        Message message = new Message(attempt, 0, 0, Message.MessageType.CANCEL);
        manager.sendMessage(message);
    }
    
    /**
     * @brief Edit a MarketEntryAttempt
     * @param attempt MarketEntryAttempt
     * @param stockName Name of the stock
     * @param price
     * @param numShares
     */
    public void alterMarketEntryAttempt(MarketEntryAttempt attempt, double price, int numShares, String stockName) {
        Map<String, StockManager> managers = exchange.getStocksManagers();
        StockManager manager = managers.get(stockName);
        Message message = new Message(attempt, price, numShares, Message.MessageType.AMEND);
        manager.sendMessage(message);
    }
    
    /**
     * @Brief where different strategies trade
     */
    public abstract void trade();
}
