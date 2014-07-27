package financialmarketsimulator.market;

import financialmarketsimulator.exception.ItemNotFoundException;

/**
 * @brief The super market strategy class from which each concrete market
 * strategy used by each participant will inherit from.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MarketStrategy {

    private String name;
    private double basePrice;
    private int priceVariant;
    private double stdDeviation;
    private double stdFactor;
    private int minShares;
    private int maxShares;
    private int tradingLength;
    private int tradingMinInterval;
    private int tradingMaxInterval;
    /**
     * Used to determine if current period is buying period or not.
     */
    protected boolean buy;
    /**
     * Used to determine if current period is selling period or not.
     */
    protected boolean sell;

    
    public MarketStrategy() {
        this.name = "";
        this.stdDeviation = 0;
        this.stdFactor = 0;
        this.minShares = 0;
        this.maxShares = 0;
        this.tradingLength = 0;
        this.tradingMinInterval = 0;
        this.tradingMaxInterval = 0;
        this.basePrice = 0;
    }
    
    public MarketStrategy(String name) {
        this.name = name;
        this.stdDeviation = 0;
        this.stdFactor = 0;
        this.minShares = 0;
        this.maxShares = 0;
        this.tradingLength = 0;
        this.tradingMinInterval = 0;
        this.tradingMaxInterval = 0;
        this.basePrice = 0;
    }
    
    public MarketStrategy(  String name, 
                            double basePrice,
                            int priceVariant,
                            double stdDeviation,
                            double stdFactor,
                            int minShares,
                            int maxShares,
                            int tradingLength,
                            int tradingMinInterval,
                            int tradingMaxInterval) {
        this.name = name;
        this.stdDeviation = stdDeviation;
        this.stdFactor = stdFactor;
        this.minShares = minShares;
        this.maxShares = maxShares;
        this.tradingLength = tradingLength;
        this.tradingMinInterval = tradingMinInterval;
        this.tradingMaxInterval = tradingMaxInterval;
        this.basePrice = basePrice;
    }
    
    /**
     * @todo make an offer
     * @return
     */
    public MarketEntryAttempt makeOffer() {
        return null;
    }

    /**
     * @todo make a bid
     * @return
     */
    public MarketEntryAttempt makeBid() {
        return null;
    }

    public void retractBid() {
    }

    public void retractOffer() {
    }

    public void setStrategy(String strategy) {
    }

    public MarketEntryAttempt searchMarketEntryAttempt(MarketEntryAttempt entry) throws ItemNotFoundException {
        MarketEntryAttempt foundNode = null;

        //implement search function here ...
        if (foundNode == null) {
            throw new ItemNotFoundException();
        } else {
            return foundNode;
        }
    }

    public String getName() {
        return name;
    }
}
