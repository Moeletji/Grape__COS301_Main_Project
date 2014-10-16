package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;
import java.util.Vector;

/**
 * @brief Negative Directional Indicator
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */

public class NDI extends MarketIndicator{
    
    /**
     * 
     */
    private static NDI instance = null;
    private final int numDays;
    private final NDM ndm;
    private final ATR atr;
    private final MarketEntryAttemptBook book;
    private final Vector<Double> NDIValues;
    private double averageTR;
    private double averageNMD;
    private int count;
    private Double result;
    
    /**
     * 
     * @param _book MarketEntryAttemptBook object
     * @param _numDays number of days over which NDI must be calculated.
     */
    private NDI(MarketEntryAttemptBook _book, int _numDays)
    {
        super("Negative Directional Indicator");
        this.book = _book;
        this.numDays = _numDays;
        this.ndm = NDM.getInstance(_book, _numDays);
        this.atr = ATR.getInstance(_book, _numDays);
        this.NDIValues = new Vector<>();
    }
    
    public static NDI getInstance(MarketEntryAttemptBook _book, int _numDays) {
        if (instance == null) {
            instance = new NDI(_book, _numDays);
        }
        return instance;
    }
    
    public Double calculateNDI()
    {
        //Get numDays day NDM average
        Vector<Double> NDMValues = ndm.getNDMValues();
        
        if( NDMValues.size() < numDays )
            return 0.0;
        
       averageTR = atr.calculateATR();
       averageNMD = 0.0;
       count = 0;
       
       while( count < numDays )
       {
           averageNMD += NDMValues.get( (NDMValues.size()-1) - count);
           count++;
       }
       averageNMD = averageNMD/numDays;
       
       if( averageTR == 0.0 )
            result = 0.0;
       else
            result = 100 * averageNMD / averageTR;
       
       this.NDIValues.add(result);
       return result;
    }
    
    public Vector<Double> getNDIValues()
    {
        return this.NDIValues;
    }
    
    @Override
    public Double calculateIndicator() {
        return this.calculateNDI();
    }
}
