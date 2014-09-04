package financialmarketsimulator.strategies;

import financialmarketsimulator.market.MarketEntryAttempt;
import financialmarketsimulator.market.MarketExchange;
import financialmarketsimulator.market.MarketStrategy;
import financialmarketsimulator.market.StockManager;
import financialmarketsimulator.marketData.Message;
import static financialmarketsimulator.strategies.Phantom.LEVEL_SHARES.HIGH;
import static financialmarketsimulator.strategies.Phantom.LEVEL_SHARES.LOW;
import static financialmarketsimulator.strategies.Phantom.LEVEL_SHARES.MEDIUM;
import java.util.Map;
import java.util.Random;

/**
 * @brief @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class Phantom extends MarketStrategy {

    public static enum LEVEL_SHARES {

        LOW, HIGH, MEDIUM
    };

    public static enum LEVEL_PRICE {

        LOW, HIGH, MEDIUM
    };
    /**
     * @brief different price ranges
     */
    private final double[] prices = {10.00, 100.00, 1000.00};
    /**
     * @brief different ranges for shares
     */
    private final int[] shares = {1000, 10000, 100000};
    /**
     * @brief maximum amount of shares
     */
    private double maxPrice;
    /**
     * @brief maximum amount of shares
     */
    private long maxShares;

    public Phantom(MarketExchange exchange, Phantom.LEVEL_PRICE levelPrice, Phantom.LEVEL_SHARES levelShares) {

        super("Phantom");

        switch (levelPrice) {
            case LOW: {
                maxPrice = prices[0];
            }
            break;
            case MEDIUM: {
                maxPrice = prices[1];
            }
            break;
            case HIGH: {
                maxPrice = prices[2];
            }
            break;
            default: {
                maxPrice = prices[0];
            }
        }

        switch (levelShares) {
            case LOW: {
                maxShares = shares[0];
            }
            break;
            case MEDIUM: {
                maxShares = shares[1];
            }
            break;
            case HIGH: {
                maxShares = shares[2];
            }
            break;
            default: {
                maxShares = shares[0];
            }
        }
    }

    @Override
    public void trade() {

        double minPrice = Math.round(maxPrice / 10);
        int minShares = Math.round(maxShares / 10);

        //Choose a random SIZE to place an MarketEntryAttempt on
        int flag = new Random().nextInt(11);

        MarketEntryAttempt.SIDE side;

        if (flag > 5) {
            side = MarketEntryAttempt.SIDE.BID;
        } else {
            side = MarketEntryAttempt.SIDE.OFFER;
        }
        
        //Create MarketEntryAttempt
        MarketEntryAttempt newAttempt = new MarketEntryAttempt((double) (Math.random() * (maxPrice - minPrice) + minPrice), (int) (Math.random() * (maxShares - minShares) + minShares), this.getStrategyName(), side);

        //Construct message to be sent
        Message message = new Message(newAttempt, 0, 0, Message.MessageType.ORDER);

        //Send message to a random stock within the MarketExchange
        Map managers = exchange.getStocksManagers();
        int size = managers.size();
        int randomSize = new Random().nextInt(size);
        int i = 0;
        for (Object keySet : managers.keySet())
        {
            //If the stock for the random number found send a message
            if(i == randomSize)
            {
                String key = keySet.toString();
                StockManager manager = (StockManager) managers.get(key);
                if(manager != null)
                {
                    manager.sendMessage(message);
                    break;
                }
            }
            else
                i++;
        }
    }
}
