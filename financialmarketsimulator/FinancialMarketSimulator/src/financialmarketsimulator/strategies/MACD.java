package financialmarketsimulator.strategies;

/**
 *@brief MACD(Moving Average Convergence Divergence). This is a technical 
 * indicator which is used to measure momentum of a particular stock to indicate
 * buying and selling signals.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */


public class MACD {
    private double macd;
    //
    final int LONG_DAY = 26;
    final int SHORT_DAY = 12;
    
    public MACD()
    {
        
    }
    
    public double calculateMACD()
    {
        EMA longEMA = new EMA(LONG_DAY);
        EMA shortEMA = new EMA(SHORT_DAY);
        return (longEMA.calculateEMA() - shortEMA.calculateEMA());
    }
    
    public double getMACD()
    {
        return macd;
    }
}
