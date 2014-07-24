package financialmarketsimulator.market;

/**
 * @brief Stores all the variants used by the Market Participant
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */

public class Variants {
    
    private int maxShares;

    private int minShares;
    
    private int minInterval;
    
    private int maxInterval;
    
    private double stdDeviation;
    
    private double stdFactor;
    
    private int length;
    
    private double basePrice;
    
    private double priceVarianceBP;
    
    public Variants(int maxShares, int minShares, int minInterval, int maxInterval, double stdDeviation, double stdFactor, int length, double basePrice, double priceVarianceBP) {
        this();
        this.maxShares = maxShares;
        this.minShares = minShares;
        this.minInterval = minInterval;
        this.maxInterval = maxInterval;
        this.stdDeviation = stdDeviation;
        this.stdFactor = stdFactor;
        this.length = length;
        this.basePrice = basePrice;
        this.priceVarianceBP = priceVarianceBP;
    }
    
    public Variants() {
        this.maxShares = 0;
        this.minShares = 0;
        this.minInterval = 0;
        this.maxInterval = 0;
        this.stdDeviation = 0;
        this.stdFactor = 0;
        this.length = 0;
        this.basePrice = 0;
        this.priceVarianceBP = 0;
    }

    public int getMaxShares() {
        return maxShares;
    }

    public void setMaxShares(int maxShares) {
        this.maxShares = maxShares;
    }

    public int getMinShares() {
        return minShares;
    }

    public void setMinShares(int minShares) {
        this.minShares = minShares;
    }

    public int getMinInterval() {
        return minInterval;
    }

    public void setMinInterval(int minInterval) {
        this.minInterval = minInterval;
    }

    public int getMaxInterval() {
        return maxInterval;
    }

    public void setMaxInterval(int maxInterval) {
        this.maxInterval = maxInterval;
    }

    public double getStdDeviation() {
        return stdDeviation;
    }

    public void setStdDeviation(double stdDeviation) {
        this.stdDeviation = stdDeviation;
    }

    public double getStdFactor() {
        return stdFactor;
    }

    public void setStdFactor(double stdFactor) {
        this.stdFactor = stdFactor;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getPriceVarianceBP() {
        return priceVarianceBP;
    }

    public void setPriceVarianceBP(double priceVarianceBP) {
        this.priceVarianceBP = priceVarianceBP;
    }
    
}
