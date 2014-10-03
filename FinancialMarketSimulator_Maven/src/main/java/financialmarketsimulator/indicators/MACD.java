package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;
import java.util.Vector;

/**
 * @brief MACD(Moving Average Convergence Divergence). This is a technical
 * indicator which is used to measure momentum of a particular stock to indicate
 * buying and selling signals.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public final class MACD extends MarketIndicator{

    private double currentMACDValue = 0;
    private double previousMACDValue = 0;
    private final int LONG_DAY = 26;
    private final int SHORT_DAY = 12;
    private final int AVE_DAY = 9;

    private double signalValue;

    private SMA sma;
    private EMA ema;

    private Vector<Double> prevMACDValues;
    private Vector<Double> prevSignalValues;

    private MarketEntryAttemptBook data;

    public MACD() {
        //currentMACDValue = previousMACDValue;
        super("Moving Average Convergence Divergence");
    }

    public MACD(MarketEntryAttemptBook _data)  {
        super("Moving Average Convergence Divergence");
        this.data = _data;
        prevMACDValues = new Vector<Double>();
        prevSignalValues = new Vector<Double>();
        sma = new SMA(this.data, 12);
        ema = new EMA(this.data, AVE_DAY);
        setPreviousMACDValue(sma.calculateSMA());
        prevMACDValues.add(getPreviousMACDValue());
    }

    public double calculateMACDValue() {
        EMA longEMA = new EMA(this.data, LONG_DAY);
        EMA shortEMA = new EMA(this.data, SHORT_DAY);
        currentMACDValue = longEMA.calculateEMA() - shortEMA.calculateEMA();
        return currentMACDValue;
    }

    public double calculateMACDValue(EMA _short, EMA _long) {
        EMA longEMA = _long;
        EMA shortEMA = _short;
        currentMACDValue = longEMA.calculateEMA() - shortEMA.calculateEMA();
        return currentMACDValue;
    }

    public double calculateSignalValue() {
        if (prevMACDValues.size() == 1) {
            return 0.0;
        }
        this.signalValue = ema.calculateEMA(this.getPreviousMACDValue(), this.getCurrentMACDValue(), AVE_DAY);
        return signalValue;
    }

    public double getCurrentMACDValue() {
        return currentMACDValue;
    }

    public double getPreviousMACDValue() {
        previousMACDValue = prevMACDValues.lastElement();
        if (currentMACDValue != 0) {
            prevMACDValues.add(currentMACDValue);
        }
        return previousMACDValue;
    }

    public void setCurrentMACDValue(double curr) {
        currentMACDValue = curr;
    }

    public void setPreviousMACDValue(double prev) {
        previousMACDValue = prev;
        this.prevMACDValues.add(prev);
    }

    public void setSignalValue(double signal) {
        signalValue = signal;
        this.prevSignalValues.add(signal);
    }

    public double getSignaValue() {
        double previousSignalValue = prevSignalValues.lastElement();
        prevMACDValues.add(currentMACDValue);
        return previousSignalValue;
    }

    @Override
    public Double calculateIndicator() {
        return this.calculateMACDValue();
    }

}
