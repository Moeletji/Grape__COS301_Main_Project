package financialmarketsimulator.indicators;

/**
 * @brief MACD(Moving Average Convergence Divergence). This is a technical
 * indicator which is used to measure momentum of a particular stock to indicate
 * buying and selling signals.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MACD {

    private double currentMACDValue;
    private double previousMACDValue;
    final int LONG_DAY = 26;
    final int SHORT_DAY = 12;

    public MACD() {
        currentMACDValue = previousMACDValue;
    }

    public double calculateMACD() {
        EMA longEMA = new EMA(LONG_DAY);
        EMA shortEMA = new EMA(SHORT_DAY);
        currentMACDValue = longEMA.calculateEMA() - shortEMA.calculateEMA();
        return currentMACDValue;
    }

    public double getCurrentMACDValue() {
        return currentMACDValue;
    }

    public double getPreviousMACDValue() {
        return previousMACDValue;
    }
}
