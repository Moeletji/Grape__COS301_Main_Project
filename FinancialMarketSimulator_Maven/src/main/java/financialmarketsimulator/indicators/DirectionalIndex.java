/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;
import static java.lang.Math.abs;
import java.util.Vector;

/**
 *
 * @author Madimetja
 */
public class DirectionalIndex extends MarketIndicator{
    private final int numDays;
    private final PDI pdi;
    private final NDI ndi;
    private final Vector<Double> directionalIndexValues;
    private final MarketEntryAttemptBook book;
    private Double difference;
    private Double sum;
    private Double result;
    
    public DirectionalIndex(MarketEntryAttemptBook _book, int _numDays)
    {
        super("Directional Index");
        this.book = _book;
        this.numDays = _numDays;
        this.pdi = new PDI(this.book, this.numDays);
        this.ndi = new NDI(this.book, this.numDays);
        this.directionalIndexValues = new Vector<>();
    }
    
    public double calculateDirectionalIndex()
    {
        difference = abs(pdi.calculatePDI() - ndi.calculateNDI());
        sum = pdi.calculatePDI() + ndi.calculateIndicator();
        
        if( sum == 0.0 )
            result = 0.0; 
        else
            result = 100 *(difference/sum);
        
        this.directionalIndexValues.add(result);
        return result;
    }
    
    public Vector<Double> getDiretionalIndexValues()
    {
        this.calculateDirectionalIndex();
        return this.directionalIndexValues;
    }
    
    @Override
    public Double calculateIndicator(){
        return this.calculateDirectionalIndex();
    } 
}
