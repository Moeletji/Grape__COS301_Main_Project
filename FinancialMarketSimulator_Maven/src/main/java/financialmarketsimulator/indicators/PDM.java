package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;
import java.util.Vector;

/**
 * @brief Positive Directional Movement
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class PDM extends MarketIndicator{
    
    private final MarketEntryAttemptBook book; 
    private final int numDays;
    private Vector<Double> PDMValues;
    
    public PDM(MarketEntryAttemptBook _book, int _numDays)
    {
        super("Positive Directional Movement");
        book = _book;
        numDays = _numDays;
        PDMValues = new Vector<>();
    }
    
    /**
     * @todo MUST USE YESTERDAY's CLOSING HIGH AND LOW VALUES, INSTEAD OF THE FIRST ELEMENT!!!!
     * @return Double value representing the current NDM
     */
    public Double calculatePDM()
    {
        double upMove = this.book.getHighestTradePrice(numDays) - this.book.getFirstTradePrice();
        double downMove = this.book.getFirstTradePrice() - this.book.getLowestTradePrice(numDays);
        double result;
        
        if( upMove > downMove && upMove > 0 )
            result = upMove;
        else
            result = 0.0;
        
        this.PDMValues.add(result);
        return result;
    }
    
    public Vector<Double> getPDMValue()
    {
        this.calculatePDM();
        return this.PDMValues;
    }
    
    @Override
    public Double calculateIndicator() throws NotEnoughDataException {
        return this.calculatePDM();
    }
}
