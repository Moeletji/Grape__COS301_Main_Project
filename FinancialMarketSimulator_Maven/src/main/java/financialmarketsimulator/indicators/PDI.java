package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;
import java.util.Vector;

/**
 *
 * 
 */

/**
 * @brief Positive Directional Indicator
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class PDI extends MarketIndicator{
    private final int numDays;
    private final PDM pdm;
    private final ATR atr;
    private final MarketEntryAttemptBook book;
    private final Vector<Double> PDIValues;
    private Vector<Double> PDMValues;
    private Double averageTR;
    private Double averageNMD;
    private int count;
    private Double result;
    
    /**
     * 
     * @param _book MarketEntryAttemptBook object
     * @param _numDays number of days over which NDI must be calculated.
     */
    public PDI(MarketEntryAttemptBook _book, int _numDays)
    {
        super("Positive Directional Movement");
        book = _book;
        numDays = _numDays;
        this.pdm = new PDM(this.book,14);
        this.atr = new ATR(this.book,14);
        this.PDIValues = new Vector<>();
    }
    
    public Double calculatePDI()
    {
        //Get numDays day NDM average
        PDMValues = pdm.getPDMValue();
        
        if( PDMValues.size() < numDays )
            return 0.0;
        
       averageTR = atr.calculateATR();
       averageNMD = 0.0;
       count = 0;
       
       while( count < numDays )
       {
           averageNMD += PDMValues.get( (PDMValues.size()-1) - count);
           count++;
       }
       averageNMD = averageNMD/numDays;
       
       if( averageTR == 0.0 )
            result = 0.0;
       else
            result = 100 * averageNMD / averageTR;
        
       PDIValues.add(result);
       return result;
    }
    
    public Vector<Double> getPDIValues()
    {
        return this.PDIValues;
    }
    
    @Override
    public Double calculateIndicator() {
        return this.calculatePDI();
    }
}
