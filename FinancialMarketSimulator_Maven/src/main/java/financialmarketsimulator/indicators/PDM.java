package financialmarketsimulator.indicators;

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
    private final Vector<Double> PDMValues;
    private Double upMove;
    private Double downMove;
    private Double result;
    
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
        upMove = this.book.getHighestTradePrice(numDays) - this.book.getFirstTradePrice();
        downMove = this.book.getFirstTradePrice() - this.book.getLowestTradePrice(numDays);
        
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
    public Double calculateIndicator() {
        return this.calculatePDM();
    }
}
