package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;
import java.util.Vector;

/**
 * @brief Relative Strength Index
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class RSI extends MarketIndicator{

    /**
     * Variable housing RSI value
     */
    private double RSIValue;
    /**
     * Variable housing RS value
     */
    private double relativeStrength;
    
    //Variables housing current and previous closing values
    
    private final double currentClose;
    private final double previousClose;
    private double currentUpClose;
    private double currentDownClose;
    private double previousUpClose;
    private double previousDownClose;
    private final MarketEntryAttemptBook book; 
    private final int numDays;
    
    /**
     * @brief RSI Constructor
     * @param _book The Market Entry Attempt Book
     * @param _numDays The number of days over which the RSI should be calculated. Should ideally be 14 days.
     */
    public RSI(MarketEntryAttemptBook _book, int _numDays)
    {
        super("Reletive Strength Index");
        this.book = _book;
        this.numDays = _numDays;
        this.previousClose = 0.0;
        this.currentClose = this.book.getLastTradePrice();
        this.currentUpClose = this.book.getHighestTradePrice(this.numDays);
        this.currentDownClose = this.book.getLowestTradePrice(this.numDays);
    }
    
    /**
     * @param _currentUpClose The current up close value
     * @param _currentDownClose The current down close value
     * @param _currentClose The current close
     * @param _previousClose The previous close
     */
    /*public RSI(double _currentUpClose, double _currentDownClose, double _currentClose, double _previousClose) {
        currentUpClose = _currentUpClose;
        currentDownClose = _currentDownClose;
        currentClose = _currentClose;
        previousClose = _previousClose;
    }*/

    /**
     * @throws financialmarketsimulator.exception.NotEnoughDataException
     * @brief Calculates and returns the RSI value
     * @return Double value representing the RSI value
     */
    public double calculateRSI() throws NotEnoughDataException {
        RSIValue = 100 - (100 / (1 + calculateRS()));
        return RSIValue;
    }

    /**
     * @throws financialmarketsimulator.exception.NotEnoughDataException
     * @brief Calculates and returns the Relative Strength over 14 days
     * @return Double value representing the relative strength value.
     */
    public double calculateRS() throws NotEnoughDataException {
        /*EMA emaUp = new EMA(book,14);
        EMA emaDown = new EMA(book,14);
        
        previousUpClose = currentUpClose;
        previousDownClose = currentDownClose;
        currentUpClose = (currentClose > previousClose) ? currentClose-previousClose : 0;
        currentDownClose = (currentClose < previousClose) ? previousClose-currentClose : 0;
        
        emaUp.setCurrentPrice(currentUpClose);
        emaUp.setPreviousEMAValue(previousUpClose);
        emaDown.setCurrentPrice(currentDownClose);
        emaDown.setPreviousEMAValue(previousDownClose);
        
        relativeStrength = emaUp.calculateEMA()/emaDown.calculateEMA();
        return relativeStrength;*/
        Vector<Double> gains = this.book.getGains();
        Vector<Double> losses = this.book.getLosses();
        
        double averageGain = 0.0;
        double averageLoss = 0.0; 
        
        int count1 = 0;
        int count2 = 0;
        
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
    public Double calculateIndicator() throws NotEnoughDataException {
        return this.calculateRSI();
    }
}
