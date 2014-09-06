package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;
import java.util.Vector;

/**
 * @brief Negative Directional Movement
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class NDM extends MarketIndicator{
    
    private final MarketEntryAttemptBook book; 
    private final int numDays;
    private Vector<Double> NMDValues;
    
    public NDM(MarketEntryAttemptBook _book, int _numDays)
    {
        super("Negative Directional Movement");
        this.book = _book;
        this.numDays = _numDays;
        this.NMDValues = new Vector<>();
    }
    
    /**
     * @todo MUST USE YESTERDAY's CLOSING HIGH AND LOW VALUES, INSTEAD OF THE FIRST ELEMENT!!!!
     * @return Double value representing the current NDM
     */
    public Double calculateNDM()
    {
        double upMove = this.book.getHighestTradePrice(numDays) - this.book.getFirstTradePrice();
        double downMove = this.book.getFirstTradePrice() - this.book.getLowestTradePrice(numDays);
        double result;
        
        if( downMove > upMove && downMove > 0 )
            result = downMove;
        else
            result = 0.0;
        
        this.NMDValues.add(result);
        return result;
    }
    
    public Vector<Double> getNDMValues()
    {
        this.calculateNDM();
        return this.NMDValues;
    }

    @Override
    public Double calculateIndicator() throws NotEnoughDataException {
        return this.calculateNDM();
    }
}
