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
    
    private static PDI instance = null;
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
    private PDI(MarketEntryAttemptBook _book, int _numDays)
    {
        super("Positive Directional Movement");
        book = _book;
        numDays = _numDays;
        this.pdm = PDM.getInstance(_book, _numDays);
        this.atr = ATR.getInstance(_book, _numDays);
        this.PDIValues = new Vector<>();
    }
    
    public static PDI getInstance(MarketEntryAttemptBook _book, int _numDays) {
        if (instance == null) {
            instance = new PDI(_book, _numDays);
        }
        return instance;
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
