package financialmarketsimulator.indicators;

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

    private static MACD instance = null;
    private double currentMACDValue = 0;
    private double previousMACDValue = 0;
    private final int LONG_DAY = 26;
    private final int SHORT_DAY = 12;
    private final int AVE_DAY = 9;

    private double signalValue;

    public int getLONG_DAY() {
        return LONG_DAY;
    }

    public int getSHORT_DAY() {
        return SHORT_DAY;
    }

    public int getAVE_DAY() {
        return AVE_DAY;
    }

    public double getSignalValue() {
        return signalValue;
    }

    public SMA getSma() {
        return sma;
    }

    public EMA getEma() {
        return ema;
    }

    public Vector<Double> getPrevMACDValues() {
        return prevMACDValues;
    }

    public Vector<Double> getPrevSignalValues() {
        return prevSignalValues;
    }

    public MarketEntryAttemptBook getData() {
        return data;
    }

    private SMA sma;
    private EMA ema;

    private Vector<Double> prevMACDValues;
    private Vector<Double> prevSignalValues;

    private MarketEntryAttemptBook data;

    private MACD() {
        //currentMACDValue = previousMACDValue;
        super("Moving Average Convergence Divergence");
    }

    private MACD(MarketEntryAttemptBook _data)  {
        super("Moving Average Convergence Divergence");
        this.data = _data;
        prevMACDValues = new Vector<>();
        prevSignalValues = new Vector<>();
        sma = SMA.getInstance(this.data, 12);
        ema = EMA.getInstance(this.data, AVE_DAY);
        setPreviousMACDValue(sma.calculateSMA());
        prevMACDValues.add(getPreviousMACDValue());
    }
    
    public static MACD getInstance() {
        if (instance == null) {
            instance = new MACD();
        }
        return instance;
    }
    
    public static MACD getInstance(MarketEntryAttemptBook _book) {
        if (instance == null) {
            instance = new MACD(_book);
        }
        return instance;
    }

    public double calculateMACDValue() {
        EMA longEMA = EMA.getInstance(this.data, LONG_DAY);
        EMA shortEMA = EMA.getInstance(this.data, SHORT_DAY);
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
        return signalValue;
    }

    @Override
    public Double calculateIndicator() {
        return this.calculateMACDValue();
    }

}
