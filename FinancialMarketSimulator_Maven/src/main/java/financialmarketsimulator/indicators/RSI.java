package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;
import java.util.Vector;

/**
 * @brief Relative Strength Index
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class RSI extends MarketIndicator{

    /**
     * Singleton instance
     */
    private static RSI instance = null;
    
    /**
     * Variable housing RSI value
     */
    private double RSIValue;
    /**
     * Variable housing RS value
     */
    private double relativeStrength;
    
    private Double averageGain;
    private Double averageLoss;
    private Vector<Double> gains;
    private Vector<Double> losses;
    private int count1;
    private int count2;
    
    //Variables housing current and previous closing values
    
    private final MarketEntryAttemptBook book; 
    private final int numDays;
    
    /**
     * @brief RSI Constructor
     * @param _book The Market Entry Attempt Book
     * @param _numDays The number of days over which the RSI should be calculated. Should ideally be 14 days.
     */
    private RSI(MarketEntryAttemptBook _book, int _numDays)
    {
        super("Reletive Strength Index");
        this.book = _book;
        this.numDays = _numDays;
    }
    
    public static RSI getInstance(MarketEntryAttemptBook _book, int _numDays) {
        if (instance == null) {
            instance = new RSI(_book, _numDays);
        }
        return instance;
    }
    
    /**
     * @brief Calculates and returns the RSI value
     * @return Double value representing the RSI value
     */
    public double calculateRSI() {
        RSIValue = 100 - (100 / (1 + calculateRS()));
        return RSIValue;
    }

    /**
     * @brief Calculates and returns the Relative Strength over 14 days
     * @return Double value representing the relative strength value.
     */
    public double calculateRS()  {
        
        gains = this.book.getGains();
        losses = this.book.getLosses();
        
        averageGain = 0.0;
        averageLoss = 0.0; 
        
        count1 = 0;
        count2 = 0;
        
        while(count1 < gains.size() && count1 <= numDays)
        {
            averageGain += gains.elementAt( (gains.size()-1) - count1 );
            count1++;
        }
        
        while(count2 < losses.size() && count2 <= numDays)
        {
            averageLoss += losses.elementAt( (losses.size()-1) - count2 );
            count2++;
        }
        
        averageGain = averageGain/(double)numDays;
        averageLoss = averageLoss/(double)numDays;
        
        if(averageLoss == 0)
        {
            relativeStrength = 0.0;
        }
        else
        {
            relativeStrength = averageGain/averageLoss;
        }
        
        return relativeStrength;
    }
    
    /**
     * @brief Returns the last calculated RSI value
     * @return RSI value
     */
    public double getRSI()
    {
        return this.RSIValue;
    }
    
    /**
     * @brief Returns the last calculated RS value
     * @return RS value
     */
    public double getRS()
    {
        return this.relativeStrength;
    }

    @Override
    public Double calculateIndicator() {
        return this.calculateRSI();
    }
}
