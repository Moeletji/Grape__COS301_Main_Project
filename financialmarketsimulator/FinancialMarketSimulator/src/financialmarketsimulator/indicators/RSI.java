
package financialmarketsimulator.indicators;

import financialmarketsimulator.marketData.MatchedMarketEntryAttempt;
import java.util.Vector;

/**
 *
 * @brief Relative Strength Index
 */
public class RSI {
    private double firstAverageLosses =0;
    private double firstAverageGains =0;
    private double averageGains;
    private double averageLosses;
    private int period;
    private Vector<MatchedMarketEntryAttempt> data;
    private Vector<Double> previousAverageGains;
    private Vector<Double> previousAverageLosses;
    
    public RSI(Vector<MatchedMarketEntryAttempt> trades)
    {
        period = 14; 
        data = trades;
        previousAverageGains = new Vector<Double>();
        previousAverageLosses = new Vector<Double>();
        initializeAverageGainsAndLosses();
    }
    
    public RSI(int _period,Vector<MatchedMarketEntryAttempt> trades)
    {
        this.period = _period;
        data = trades;
        previousAverageGains = new Vector<Double>();
        previousAverageLosses = new Vector<Double>();
        initializeAverageGainsAndLosses();  
    }
    
    private void initializeAverageGainsAndLosses()
    {
        if (data.isEmpty() || data.size() < period+1)
            return;//exception thrown
        
        int start = data.size()-1-period;
        
        double totalLosses = 0;
        double totalGains = 0;
        
        for (int i = start; i<data.size()-1;i++)
        {
            if (data.get(i).getPrice() - data.get(i+1).getPrice() >= 0)
            {
                totalGains += (data.get(i).getPrice() - data.get(i+1).getPrice());
            }
            else
            {
                totalLosses += ((data.get(i).getPrice() - data.get(i+1).getPrice())*-1);
            }
        }
        
        firstAverageGains = totalGains/period;
        firstAverageLosses = totalLosses/period;
        
        previousAverageGains.add(firstAverageGains);
        previousAverageLosses.add(firstAverageLosses);
    }
    
    public double calculateAverageGains()
    {
        double prev = previousAverageGains.lastElement();
        double curr = (this.getCurrentGainOrLoss()>=0)?this.getCurrentGainOrLoss():0.0;
        averageGains = ((prev*13)+ curr)/period;
        previousAverageGains.add(averageGains);
        return averageGains;
    }
    
    public double calculateAverageLosses()
    {
        double prev = previousAverageLosses.lastElement();
        double curr = (this.getCurrentGainOrLoss()<0)?this.getCurrentGainOrLoss():0.0;
        averageLosses = ((prev*13)+ curr)/period;
        previousAverageLosses.add(averageLosses);
        return averageLosses;
    }
    
    public double getRS()
    {
        return getAverageGains()/getAverageLosses();
    }
    
    public double getRSI()
    {
        return (100-(100/1+getRS()));
    }
    
    public double getCurrentGainOrLoss()
    {
        return ((data.size()>period+1)?(data.lastElement().getPrice()-data.get(data.size()-2).getPrice()):0.0);
    }
            
    public double getAverageGains()
    {
        return averageGains;
    }
    
    public double getAverageLosses()
    {
        return averageLosses;
    }
}
